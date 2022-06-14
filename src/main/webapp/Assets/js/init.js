$(document).ready(function() {

	// Load lista producto
	const loadListaProducto = () => {
		$("#idContenido").load('/ListaProductos/listaProducto.srv');
	}

	$("#idMenuListaProductos").click(function(event) {
		console.log("Click lista productos");
		loadListaProducto();
	});

	// Load lista Categoria
	const loadListaCategoria = () => {
		$("#idContenido").load('/ListaProductos/listaCategoria.srv');
	}

	$("#idMenuListaCategorias").click(function(event) {
		console.log("Click lista categoria");
		loadListaCategoria();
	});

});