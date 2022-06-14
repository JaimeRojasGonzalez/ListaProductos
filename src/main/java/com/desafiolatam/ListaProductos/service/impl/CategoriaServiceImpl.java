package com.desafiolatam.ListaProductos.service.impl;

import com.desafiolatam.ListaProductos.dao.CategoriaDao;
import com.desafiolatam.ListaProductos.dao.impl.CategoriaDaoImpl;
import com.desafiolatam.ListaProductos.dao.model.Categoria;
import com.desafiolatam.ListaProductos.dto.CategoriaDto;
import com.desafiolatam.ListaProductos.service.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService {

private CategoriaDao categoriaDao = null;
	
	
	
	public CategoriaServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.categoriaDao = new CategoriaDaoImpl();
	}



	@Override
	public CategoriaDto getCategorias() {
		// TODO Auto-generated method stub
		CategoriaDto categoriaDto = new CategoriaDto();
		categoriaDto.setCategorias(this.categoriaDao.getAll());
		return categoriaDto;
	}



	@Override
	public int addCategorias(CategoriaDto categoriaDto) {
		Categoria categoria = new Categoria();
		categoria = categoriaDto.getCategorias().get(0);
		return this.categoriaDao.add(categoria);
	}



	@Override
	public int deleteById(int idCategoria) {
		return categoriaDao.deleteById(idCategoria);
	}



	@Override
	public int update(CategoriaDto categoriaDto) {
		Categoria categoria = new Categoria();
		
		categoria.setIdCategoria(categoriaDto.getCategorias().get(0).getIdCategoria());
		categoria.setNombreCategoria(categoriaDto.getCategorias().get(0).getNombreCategoria());
	
		
		return this.categoriaDao.update(categoria);
	}
}
