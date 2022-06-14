package com.desafiolatam.ListaProductos.dto;

import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.ListaProductos.dao.model.Categoria;
import com.google.gson.Gson;

public class CategoriaDto {

	private List<Categoria> categorias;
	private String mensaje;
	private String mensajeNuevaCategoria;
	
	public CategoriaDto() {
		super();
		this.categorias = new ArrayList<Categoria>();
	}
	
	public void setCategoriaFromJson(String json) {
	
		Categoria categoria = new Categoria();
		
		String dataSplit[] = json.split("&");
		
		categoria.setNombreCategoria(dataSplit[0].split("=")[1]);
		
		this.categorias.add(categoria);
	
	
	}

	public void setEditarCategoriaFromJson(String json) {
		Categoria categoria = new Categoria();
		String dataSplit[] = json.split("&");
		categoria.setIdCategoria(Integer.parseInt(dataSplit[0].split("=")[1]));
		categoria.setNombreCategoria(dataSplit[1].split("=")[1]);
		
		this.categorias.add(categoria);
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public String getMensajeNuevaCategoria() {
		return mensajeNuevaCategoria;
	}

	public void setMensajeNuevaCategoria(String mensajeNuevaCategoria) {
		this.mensajeNuevaCategoria = mensajeNuevaCategoria;
	}


	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
