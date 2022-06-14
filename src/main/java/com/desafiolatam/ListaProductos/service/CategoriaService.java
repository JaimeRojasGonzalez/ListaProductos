package com.desafiolatam.ListaProductos.service;

import com.desafiolatam.ListaProductos.dto.CategoriaDto;

public interface CategoriaService {

	public CategoriaDto getCategorias();
	public int addCategorias(CategoriaDto categoriaDto);
	public int deleteById(int idCategoria);
	public int update(CategoriaDto idCategoria);
	
	
}
