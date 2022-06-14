package com.desafiolatam.ListaProductos.facade.impl;

import com.desafiolatam.ListaProductos.dto.CategoriaDto;
import com.desafiolatam.ListaProductos.facade.CategoriaFacade;
import com.desafiolatam.ListaProductos.service.CategoriaService;
import com.desafiolatam.ListaProductos.service.impl.CategoriaServiceImpl;

public class CategoriaFacadeImpl implements CategoriaFacade {

	private CategoriaService categoriaService = null;

	public CategoriaFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.categoriaService = new CategoriaServiceImpl();
	}

	@Override
	public CategoriaDto getCategorias() {
		return this.categoriaService.getCategorias();
	}

	@Override
	public CategoriaDto addCategorias(CategoriaDto categoriaDto) {
		int resultado = this.categoriaService.addCategorias(categoriaDto);
		CategoriaDto categoriaDtoResultado = this.categoriaService.getCategorias();
		if (resultado == 1) {
			categoriaDtoResultado.setMensajeNuevaCategoria("La categoria se ha guardado correctamente");
		} else if (resultado == 0) {
			categoriaDtoResultado.setMensajeNuevaCategoria("la categoria NO se ha guardado correctamente");
		} else {
			categoriaDtoResultado.setMensajeNuevaCategoria("Error al guardar la categoria");
		}

		return categoriaDtoResultado;
	}

	@Override
	public int deleteById(int idCategoria) {
		return categoriaService.deleteById(idCategoria);
	}

	@Override
	public int update(CategoriaDto categoriaDto) {
		return categoriaService.update(categoriaDto);
	}

}
