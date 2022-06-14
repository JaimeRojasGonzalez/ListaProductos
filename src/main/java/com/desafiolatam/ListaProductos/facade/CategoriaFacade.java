package com.desafiolatam.ListaProductos.facade;

import com.desafiolatam.ListaProductos.dto.CategoriaDto;

public interface CategoriaFacade {
	
	public CategoriaDto getCategorias();
	public CategoriaDto addCategorias(CategoriaDto categoriaDto);
	public int update(CategoriaDto categoriaDto);
	public int deleteById(int idCategoria);

}
