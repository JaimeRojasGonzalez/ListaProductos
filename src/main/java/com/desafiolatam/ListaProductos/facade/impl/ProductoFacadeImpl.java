package com.desafiolatam.ListaProductos.facade.impl;

import com.desafiolatam.ListaProductos.dto.ProductoDto;
import com.desafiolatam.ListaProductos.facade.ProductoFacade;
import com.desafiolatam.ListaProductos.service.ProductoService;
import com.desafiolatam.ListaProductos.service.impl.ProductoServiceImpl;


public class ProductoFacadeImpl implements ProductoFacade {
	private ProductoService productoService = null;

	public ProductoFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.productoService = new ProductoServiceImpl();
	}

	@Override
	public ProductoDto getProductos() {
		return this.productoService.getProductos();
	}

	@Override
	public ProductoDto addProducto(ProductoDto productoDto) {
		int resultado = this.productoService.addProductos(productoDto);
		ProductoDto productoDtoResultado = this.productoService.getProductos();
		if (resultado == 1) {
			productoDtoResultado.setMensajeNuevoProducto("El producto se ha guardado correctamente");
		} else if (resultado == 0) {
			productoDtoResultado.setMensajeNuevoProducto("El producto NO se ha guardado correctamente");
		} else {
			productoDtoResultado.setMensajeNuevoProducto("Error al guardar el producto");
		}

		return productoDtoResultado;
	}

	@Override
	public int deleteById(int idProducto) {
		return productoService.deleteById(idProducto);
	}

	@Override
	public int update(ProductoDto productoDto) {
		return productoService.update(productoDto);
	}
}
