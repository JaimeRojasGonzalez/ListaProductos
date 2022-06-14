package com.desafiolatam.ListaProductos.dto;

import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.ListaProductos.dao.model.Categoria;
import com.desafiolatam.ListaProductos.dao.model.Producto;
import com.google.gson.Gson;

public class ProductoDto {

	private List<Producto> productos;
	private String mensajeNuevoProducto;
	private String mensaje;
	
	public ProductoDto() {
		super();
		// TODO Auto-generated constructor stub
		this.productos = new ArrayList<Producto>();
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public void setProductoFromJson(String json) {
		Producto producto = new Producto();
		Categoria categoria = new Categoria();
		
		String dataSplit[] = json.split("&");

		producto.setNombreProducto(dataSplit[0].split("=")[1]);
		producto.setPrecioProducto(Integer.parseInt(dataSplit[1].split("=")[1]));
		producto.setDescripcionProducto(dataSplit[2].split("=")[1]);
		categoria.setIdCategoria((Integer.parseInt(dataSplit[3].split("=")[1])));
		producto.setCategoria(categoria);
		
		this.productos.add(producto);
	}
	
	public void setEditarProductoFromJson(String json) {
		Producto producto = new Producto();
		Categoria categoria = new Categoria();
		
		String dataSplit[] = json.split("&");
		
		producto.setIdProducto(Integer.parseInt(dataSplit[0].split("=")[1]));
		producto.setNombreProducto(dataSplit[1].split("=")[1]);
		producto.setPrecioProducto(Integer.parseInt(dataSplit[2].split("=")[1]));
		producto.setDescripcionProducto(dataSplit[3].split("=")[1]);
		categoria.setIdCategoria(Integer.parseInt(dataSplit[4].split("=")[1]));
		producto.setCategoria(categoria);
		
		this.productos.add(producto);
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public String getMensajeNuevoProducto() {
		return mensajeNuevoProducto;
	}

	public void setMensajeNuevoProducto(String mensajeNuevoProducto) {
		this.mensajeNuevoProducto = mensajeNuevoProducto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
