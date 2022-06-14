package com.desafiolatam.ListaProductos.service;

import com.desafiolatam.ListaProductos.dto.ProductoDto;

public interface ProductoService {

	public ProductoDto getProductos();
	public int addProductos(ProductoDto productoDto);
	public int deleteById(int idProducto);
	public int update(ProductoDto idProducto);
}
