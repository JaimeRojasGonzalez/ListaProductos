package com.desafiolatam.ListaProductos.dao;

import java.util.List;

import com.desafiolatam.ListaProductos.dao.model.Producto;

public interface ProductoDao {

	public int add(Producto producto);

	public List<Producto> getAll();

	public Producto getById(int idProducto);

	public int update(Producto producto);

	public int deleteById(int idProducto);
}
