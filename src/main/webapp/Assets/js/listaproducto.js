var $table = $('#tblListaProducto')

$(document).ready(function() {
	const validaFormNuevoProducto = () => {
		var txtNombreProducto = false;
		var txtPrecioProducto = false;
		var txtDescripcionProducto = false;
		var selCategoria = false;

		if ($("#idTxtNombreProducto").val().length == 0) {
			$("#idTxtNombreProducto").addClass("is-invalid");
			$("#idTxtNombreProducto").removeClass("is-valid");
			txtNombreProducto = false;
		} else {
			$("#idTxtNombreProducto").removeClass("is-invalid");
			$("#idTxtNombreProducto").addClass("is-valid");
			txtNombreProducto = true;
		}

		if ($("#idTxtPrecioProducto").val().length == 0) {
			$("#idTxtPrecioProducto").addClass("is-invalid");
			$("#idTxtPrecioProducto").removeClass("is-valid");
			txtPrecioProducto = false;
		} else {
			$("#idTxtPrecioProducto").removeClass("is-invalid");
			$("#idTxtPrecioProducto").addClass("is-valid");
			txtPrecioProducto = true;
		}

		if ($("#idTxtDescripcionProducto").val().length == 0) {
			$("#idTxtDescripcionProducto").addClass("is-invalid");
			$("#idTxtDescripcionProducto").removeClass("is-valid");
			txtDescripcionProducto = false;
		} else {
			$("#idTxtDescripcionProducto").removeClass("is-invalid");
			$("#idTxtDescripcionProducto").addClass("is-valid");
			txtDescripcionProducto = true;
		}

		if ($("#idSelCategoria").val() === '-1') {
			$("#idSelCategoria").addClass("is-invalid");
			$("#idSelCategoria").removeClass("is-valid");
			selCategoria = false;
		} else {
			$("#idSelCategoria").removeClass("is-invalid");
			$("#idSelCategoria").addClass("is-valid");
			selCategoria = true;
		}

		return txtNombreProducto && txtPrecioProducto && txtDescripcionProducto && selCategoria;
	}


	console.log("ready!", productoDtoJson);
	//console.log("ready!", JSON.parse(alumnoDtoJson).alumnos[0].nombre);

	$table.bootstrapTable({
		data: JSON.parse(productoDtoJson).productos,
		pagination: true,
		search: true,
		pageSize: 5,
		pageList: [5, 10],
		locale: "es-ES",
		columns: [{
			field: 'idProducto',
			title: 'ID',
			width: '40px'
		}, {
			field: 'nombreProducto',
			title: 'Nombre',
			width: '180px'
		}, {
			field: 'precioProducto',
			title: 'Precio',
			width: '180px'
		}, {
			field: 'descripcionProducto',
			title: 'Descripción',
			width: '180px',
			
		}, {
			field: 'categoria.nombreCategoria',
			title: 'Categoria',
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
					"<a class='like' href='#' data-toggle='modal' data-target='#modalEditarProducto' onclick='onClickEditar(\"" + JSON.stringify(row).split('"').join('\\"') + "\");' title='Like'>",
					"<i class='bi bi-pencil'></i>",
					"</a>  ",
					"<a class='remove' href='#'data-toggle='modal' data-target='#modalEliminarProducto' onclick='onClickEliminar(\"" + row.idProducto + "\");' title='Eliminar'>",
					'<i class="fa fa-trash"></i>',
					'</a>'
				].join('');
			}
		}
		]
	});

	$("#idBtnGuardarProducto").click(function() {
		var dataProducto = {
			"nombreProducto": $("#idTxtNombreProducto").val(),
			"predioProducto": $("#idTxtPrecioProducto").val(),
			"descripcionProducto": $("#idTxtDescripcionProducto").val(),
			"idCategoria": $("#idSelCategoria").val()
		};
		console.log("DataProducto: ", dataProducto);

		if (validaFormNuevoProducto()) {
			$.ajax({
				// En data puedes utilizar un objeto JSON, un array o un query string
				data: { dataProducto, "accion": "crearProducto" },
				//Cambiar a type: POST si necesario
				type: "PUT",
				// Formato de datos que se espera en la respuesta
				dataType: "json",
				// URL a la que se enviará la solicitud Ajax
				url: "/ListaProductos/listaProducto.srv",
			})
				.done(function(data, textStatus, jqXHR) {
					swal({
						text: data.mensaje,
						icon: "success"
					});
					console.log("La solicitud se ha completado correctamente.", data, textStatus, jqXHR);
					console.log("Productos a refrescar", data.productos);
					$table.bootstrapTable('load', data.productos);
					$table.bootstrapTable('refresh');

				})
				.fail(function(jqXHR, textStatus, errorThrown) {
					console.log("La solicitud a fallado: ", errorThrown, textStatus, jqXHR);
				});
		}

	});
		$('#modalNuevoProducto').on('show.bs.modal', function() {
		$("#idTxtNombreProducto").val("");
		$("#idTxtNombreProducto").removeClass("is-valid");
		$("#idTxtNombreProducto").removeClass("is-invalid");
		
		$("#idTxtPrecioProducto").val("");
		$("#idTxtPrecioProducto").removeClass("is-valid");
		$("#idTxtPrecioProducto").removeClass("is-invalid");
		
		$("#idTxtDescripcionProducto").val("");
		$("#idTxtDescripcionProducto").removeClass("is-valid");
		$("#idTxtDescripcionProducto").removeClass("is-invalid");
		
		$("#idSelCategoria").val("");
		$("#idSelCategoria").removeClass("is-valid");
		$("#idSelCategoria").removeClass("is-invalid");
	
	});
});

let productoDto;

function onClickEditar(row) {
	//let rowObj = JSON.parse(row);
	productoDto = JSON.parse(row);

	//Limpiar campos del modal
	$("#idTxtEditarNombreProducto").val("");
	$("#idTxtEditarNombreproducto").attr("placeholder", productoDto.nombreProducto);
	$("#idTxtEditarPrecioProducto").val("")
	$("#idTxtEditarPrecioProducto").attr("placeholder", productoDto.precioProducto);
	$("#idTxtEditarDescripcionProducto").val("")
	$("#idTxtEditarDescripcionProducto").attr("placeholder", productoDto.descripcionProducto);

	
	console.log("Editar Json string:... ", row);
	console.log("Editar Json object:... ", productoDto);
	//console.log(rowObj.orden_id);

}

$("#idBtnEditarProducto").click(function() {

	var dataProducto = {
		"idProducto": productoDto.idProducto,
		"nombreProducto": $("#idTxtEditarNombreProducto").val(),
		"precioProducto": $("#idTxtEditarPrecioProducto").val(),
		"descripcionProducto": $("#idTxtEditarDescripcionProducto").val(),
		"idCategoria": $("#idSelEditarCategoria").val(),
	};

	console.log(dataProducto.idProducto);

	$.ajax({
		// En data puedes utilizar un objeto JSON, un array o un query string
		data: { dataProducto, "accion": "actualizarProducto" },
		//Cambiar a type: POST si necesario
		type: "PUT",
		// Formato de datos que se espera en la respuesta
		dataType: "json",
		// URL a la que se enviará la solicitud Ajax
		url: "/ListaProductos/listaProducto.srv",
	})
		.done(function(data, textStatus, jqXHR) {
			swal({
						text: data.mensaje,
						icon: "success"
					});			console.log("La solicitud se ha completado correctamente.", data, textStatus, jqXHR);
			console.log("Productos a refrescar", data.productos);
			$table.bootstrapTable('load', data.productos);
			$table.bootstrapTable('refresh');

		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			console.log("La solicitud a fallado: ", errorThrown, textStatus, jqXHR);
		});

});

var dataProducto;

function onClickEliminar(id) {
	console.log("Id a eliminar: " + id);

	dataProducto = {
		"idProducto": id
	};


}

$("#idBtnEliminarProducto").click(function() {

	$.ajax({  
		// En data puedes utilizar un objeto JSON, un array o un query string
		data: { dataProducto, "accion": "eliminarProducto" },
		//Cambiar a type: POST si necesario
		type: "PUT",
		// Formato de datos que se espera en la respuesta
		dataType: "json",
		// URL a la que se enviará la solicitud Ajax
		url: "/ListaProductos/listaProducto.srv",
	})
		.done(function(data, textStatus, jqXHR) {
					swal({
						text: data.mensaje,
						icon: "success"
					});			console.log("La solicitud se ha completado correctamente.", data, textStatus, jqXHR);
			console.log("Productos a eliminar", data.productos);
			$table.bootstrapTable('load', data.productos);
			$table.bootstrapTable('refresh');

		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			console.log("La solicitud a fallado: ", errorThrown, textStatus, jqXHR);
		});

});