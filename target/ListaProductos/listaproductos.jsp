<%@page import="com.desafiolatam.ListaProductos.dto.CategoriaDto"%>
<%@page import="com.desafiolatam.ListaProductos.dto.ProductoDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script>
    	let productoDtoJson = '<%= request.getAttribute("productoDtoJson") %>';
	</script>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">

<!-- <script src="https://code.jquery.com/jquery-3.5.1.js"></script> -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table.min.css">
<script
	src="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table.min.js"></script>
<script
	src="https://unpkg.com/bootstrap-table@1.19.1/dist/bootstrap-table-locale-all.min.js"></script>
<link rel="stylesheet" href="Assets/css/init.css" />
<script src="Assets/js/listaproducto.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<title>Productos</title>
</head>
<body>
	<table class="table table-hover" id="tblListaProducto">
	</table>
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-dark" data-toggle="modal" data-target="#modalNuevoProducto">Agregar Producto</button>

	<!-- Modal Agregar Alumno-->
	<div class="modal fade" id="modalNuevoProducto" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Nuevo Producto</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="needs-validation" novalidate>
						<div class=form-row>
				            <div class="col-md-4 mb-3">
				                <div class="form-group">
				                    <label for="idTxtNombreProducto">Nombre</label>
				                    <div class="form-inline">
				                        <input type="text" class="form-control" id="idTxtNombreProducto" placeholder="nombre" required>
				                        <div class="valid-feedback">
                        					Correcto!
                    					</div>
					                    <div class="invalid-feedback">
					                        Debe ingresar un nombre válido
					                    </div>
				                    </div>
				                </div>
				            </div>
				            <div class="col-md-4 mb-3">
				                <div class="form-group">
				                    <label for="idTxtPrecioProducto">Precio</label>
				                    <div class="form-inline">
				                        <input type="number" class="form-control" id="idTxtPrecioProducto" placeholder="precio" required>
				                        <div class="valid-feedback">
                        					Correcto!
                    					</div>
					                    <div class="invalid-feedback">
					                        Debe ingresar un precio válido
					                    </div>
				                    </div>
				                </div>
				            </div>
				            <div class="col-md-4 mb-3">
				                <div class="form-group">
				                    <label for="idTxtDescripcionProducto">Descripción</label>
				                    <div class="form-inline">
				                        <input type="text" class="form-control" id="idTxtDescripcionProducto" placeholder="descripción" required>
				                        <div class="valid-feedback">
                        					Correcto!
                    					</div>
					                    <div class="invalid-feedback">
					                        Debe ingresar una descripción válida
					                    </div>
				                    </div>
				                </div>
				            </div>
				         </div>
				         <div class="form-row">
				            <div class="col-md-4 mb-3">
				                <div class="form-group">
				                    <label for="idSelCategoria">Categoria</label>
				                    <select class="form-control" id="idSelCategoria">
				                    	<option value="-1">-Seleccione Categoria-</option>
				                    	<c:forEach var="categoria" items="${categoriaDto.categorias}">
				                    		<option value="<c:out value='${categoria.idCategoria}' />"><c:out value="${categoria.nombreCategoria}" /> </option>
				                    	</c:forEach>
				                    </select>
				                    <div class="valid-feedback">
                        					Correcto!
                    					</div>
					                    <div class="invalid-feedback">
					                        Debe ingresar una categoria válida
					                    </div>
				                </div>
				            </div>
				         </div>
			    	</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary" id="idBtnGuardarProducto">Guardar</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal Editar Producto-->
	<div class="modal fade" id="modalEditarProducto" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Editar producto</h5>
					<h3 id="ponerIdProducto"></h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="needs-validation" novalidate>
						<div class=form-row>
				            <div class="col-md-4 mb-3">
				                <div class="form-group">
				                    <label for="idTxtEditarNombreProducto">Nombre</label>
				                    <div class="form-inline">
				                        <input type="text" class="form-control" id="idTxtEditarNombreProducto" placeholder="nombre" required>
				                        <input type="text" class="d-none form-control" id="idTxtEditarId" required>				        
				                        <div class="valid-feedback">
                        					Correcto!
                    					</div>
					                    <div class="invalid-feedback">
					                        Debe ingresar un nombre válido
					                    </div>
				                    </div>
				                </div>
				            </div>
				            <div class="col-md-4 mb-3">
				                <div class="form-group">
				                    <label for="idTxtEditarPrecioProducto">Precio</label>
				                    <div class="form-inline">
				                        <input type="number" class="form-control" id="idTxtEditarPrecioProducto" placeholder="precio" required>
				                        <div class="valid-feedback">
                        					Correcto!
                    					</div>
					                    <div class="invalid-feedback">
					                        Debe ingresar un precio válido
					                    </div>
				                    </div>
				                </div>
				            </div>
				            <div class="col-md-4 mb-3">
				                <div class="form-group">
				                    <label for="idTxtEditarDescripcionProducto">Descripción</label>
				                    <div class="form-inline">
				                        <input type="text" class="form-control" id="idTxtEditarDescripcionProducto" placeholder="descripción" required>
				                        <div class="valid-feedback">
                        					Correcto!
                    					</div>
					                    <div class="invalid-feedback">
					                        Debe ingresar una descripción válida
					                    </div>
				                    </div>
				                </div>
				            </div>
				         </div>
				         <div class="form-row">
				            <div class="col-md-4 mb-3">
				                <div class="form-group">
				                    <label for="idSelEditarCategoria">Categoria</label>
				                    <select class="form-control" id="idSelEditarCategoria">
				                    	<option value="-1">-Seleccione Categoria-</option>
				                    	<c:forEach var="categoria" items="${categoriaDto.categorias}">
				                    		<option value="<c:out value='${categoria.idCategoria}' />"><c:out value="${categoria.nombreCategoria}" /> </option>
				                    	</c:forEach>
				                    </select>
				                    <div class="valid-feedback">
                        					Correcto!
                    					</div>
					                    <div class="invalid-feedback">
					                        Debe ingresar una categoria válida
					                    </div>
				                </div>
				            </div>
				         </div>
			    	</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary" id=idBtnEditarProducto>Enviar</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Eliminar Producto-->
	<div class="modal fade" id="modalEliminarProducto" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body text-center">
					<h2>¿Está seguro de eliminar el producto?</h2>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary" id=idBtnEliminarProducto>Eliminar</button>
				</div>
			</div>
		</div>
	</div>
	
</body>
