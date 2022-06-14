package com.desafiolatam.ListaProductos.facade;

import com.desafiolatam.ListaProductos.dto.ProductoDto;

public interface ProductoFacade {

	public ProductoDto getProductos();
	public ProductoDto addProducto(ProductoDto productoDto);
	public int update(ProductoDto productoDto);
	public int deleteById(int idProducto);
}
