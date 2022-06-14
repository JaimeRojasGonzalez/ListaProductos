var $table = $('#tblListaCategoria')

$(document).ready(function() {
	const validaFormNuevaCategoria = () => {
		
		var txtNombreCategoria = false;


		if ($("#idTxtNombreCategoria").val().length == 0) {
			$("#idTxtNombreCategoria").addClass("is-invalid");
			$("#idTxtNombreCategoria").removeClass("is-valid");
			txtNombreCategoria = false;
		} else {
			$("#idTxtNombreCategoria").removeClass("is-invalid");
			$("#idTxtNombreCategoria").addClass("is-valid");
			txtNombreCategoria = true;
		}

		return txtNombreCategoria;
	}


	console.log("ready!", categoriaDtoJson);

	$table.bootstrapTable({
		data: JSON.parse(categoriaDtoJson).categorias,
		pagination: true,
		search: true,
		pageSize: 5,
		pageList: [5, 10],
		locale: "es-ES",
		columns: [{
			field: 'idCategoria',
			title: 'ID',
			width: '40px'
		}, {
			field: 'nombreCategoria',
			title: 'Nombre',
			width: '180px'
		}, {
			field: '',
			title: 'Acción',
			align: 'center',
			valign: 'middle',
			width: '150px',
			clickToSelect: false,
			formatter: function(value, row, index) {
				//Aqui defines el boton y en tu caso tendras que ponerle el onClick, 
				//recuerda que row tiene el objeto del renglon actual, 
				//en este ejemplo agrege funcionPorDefinir y le envio el row.id
				console.log(JSON.stringify(row));
				console.log($.param(row))
				return [
					"<a class='like' href='#' data-toggle='modal' data-target='#modalEditarCategoria' onclick='onClickEditar(\"" + JSON.stringify(row).split('"').join('\\"') + "\");' title='Like'>",
					"<i class='bi bi-pencil'></i>",
					"</a>  ",
					"<a class='remove' href='#'data-toggle='modal' data-target='#modalEliminarCategoria' onclick='onClickEliminar(\"" + row.idCategoria + "\");' title='Eliminar'>",
					'<i class="fa fa-trash"></i>',
					'</a>'
				].join('');
			}
		}
		]
	});

	$("#idBtnGuardarCategoria").click(function() {
		var dataCategoria = {
			"nombreCategoria": $("#idTxtNombreCategoria").val(),
		};
		console.log("DataCategoria: ", dataCategoria);

		if (validaFormNuevaCategoria()) {
			$.ajax({
				// En data puedes utilizar un objeto JSON, un array o un query string
				data: { dataCategoria, "accion": "crearCategoria" },
				//Cambiar a type: POST si necesario
				type: "PUT",
				// Formato de datos que se espera en la respuesta
				dataType: "json",
				// URL a la que se enviará la solicitud Ajax
				url: "/ListaProductos/listaCategoria.srv",
			})
				.done(function(data, textStatus, jqXHR) {
					swal({
						text: data.mensaje,
						icon: "success"
					});
					console.log("La solicitud se ha completado correctamente.", data, textStatus, jqXHR);
					console.log("Cursos a refrescar", data.categorias);
					$table.bootstrapTable('load', data.categorias);
					$table.bootstrapTable('refresh');

				})
				.fail(function(jqXHR, textStatus, errorThrown) {
					console.log("La solicitud a fallado: ", errorThrown, textStatus, jqXHR);
				});

		} else {
			console.log("Nada");
		}

	});

	$('#modalNuevaCategoria').on('show.bs.modal', function() {
		$("#idTxtAgregarNombreCategoria").val("");
		$("#idTxtAgregarNombreCategoria").removeClass("is-valid");
		$("#idTxtAgregarNombreCategoria").removeClass("is-invalid");

	});
});

let categoriaDto;

function onClickEditar(row) {
	//let rowObj = JSON.parse(row);
	categoriaDto = JSON.parse(row);

	//Limpiar campos del modal
	$("#idTxtEditarNombreCategoria").val("");
	$("#idTxtEditarNombreCategoria").attr("placeholder", categoriaDto.nombreCategoria);
	
	console.log("Editar Json string:... ", row);
	console.log("Editar Json object:... ", categoriaDto);
	//console.log(rowObj.orden_id);

}

$("#idBtnEditarCategoria").click(function() {

	var dataCategoria = {
		"idCategoria": categoriaDto.idCategoria,
		"nombreCategoria": $("#idTxtEditarNombreCategoria").val(),
	};

	console.log(dataCategoria.idCategoria);

	$.ajax({
		// En data puedes utilizar un objeto JSON, un array o un query string
		data: { dataCategoria, "accion": "actualizarCategoria" },
		//Cambiar a type: POST si necesario
		type: "PUT",
		// Formato de datos que se espera en la respuesta
		dataType: "json",
		// URL a la que se enviará la solicitud Ajax
		url: "/ListaProductos/listaCategoria.srv",
	})
		.done(function(data, textStatus, jqXHR) {
					swal({
						text: data.mensaje,
						icon: "success"
					});
					console.log("La solicitud se ha completado correctamente.", data, textStatus, jqXHR);
					console.log("Categorias a refrescar", data.categorias);
					$table.bootstrapTable('load', data.categorias);
					$table.bootstrapTable('refresh');

		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			console.log("La solicitud a fallado: ", errorThrown, textStatus, jqXHR);
		});

});

var dataCategoria;

function onClickEliminar(id) {
	console.log("Id a eliminar: " + id);

	dataCategoria = {
		"idCategoria": id
	};


}

$("#idBtnEliminarCategoria").click(function() {

	$.ajax({
		// En data puedes utilizar un objeto JSON, un array o un query string
		data: { dataCategoria, "accion": "eliminarCategoria" },
		//Cambiar a type: POST si necesario
		type: "PUT",
		// Formato de datos que se espera en la respuesta
		dataType: "json",
		// URL a la que se enviará la solicitud Ajax
		url: "/ListaProductos/listaCategoria.srv",
	})
		.done(function(data, textStatus, jqXHR) {
					swal({
						text: data.mensaje,
						icon: "success"
					});
					console.log("La solicitud se ha completado correctamente.", data, textStatus, jqXHR);
					console.log("Categorias a refrescar", data.categorias);
					$table.bootstrapTable('load', data.categorias);
					$table.bootstrapTable('refresh');

		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			console.log("La solicitud a fallado: ", errorThrown, textStatus, jqXHR);
		});

}); 