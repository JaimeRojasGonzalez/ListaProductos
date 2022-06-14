package com.desafiolatam.ListaProductos.service.impl;

import com.desafiolatam.ListaProductos.dao.ProductoDao;
import com.desafiolatam.ListaProductos.dao.impl.ProductoDaoImpl;
import com.desafiolatam.ListaProductos.dao.model.Producto;
import com.desafiolatam.ListaProductos.dto.ProductoDto;
import com.desafiolatam.ListaProductos.service.ProductoService;

public class ProductoServiceImpl implements ProductoService{
	private ProductoDao productoDao = null;

	public ProductoServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.productoDao = new ProductoDaoImpl();
	}

	@Override
	public ProductoDto getProductos() {
		// TODO Auto-generated method stub
		ProductoDto productoDto = new ProductoDto();
		productoDto.setProductos(this.productoDao.getAll());
		return productoDto;
	}

	@Override
	public int addProductos(ProductoDto productoDto) {
		Producto producto = new Producto();
		producto = productoDto.getProductos().get(0);
		return this.productoDao.add(producto);
	}

	@Override
	public int deleteById(int idProducto) {
		return productoDao.deleteById(idProducto);
	}

	@Override
	public int update(ProductoDto productoDto) {
		Producto producto = new Producto();

		producto.setIdProducto(productoDto.getProductos().get(0).getIdProducto());
		producto.setNombreProducto(productoDto.getProductos().get(0).getNombreProducto());
		producto.setPrecioProducto(productoDto.getProductos().get(0).getPrecioProducto());
		producto.setDescripcionProducto(productoDto.getProductos().get(0).getDescripcionProducto());
		producto.setCategoria(productoDto.getProductos().get(0).getCategoria());

		return this.productoDao.update(producto);
	}
}
