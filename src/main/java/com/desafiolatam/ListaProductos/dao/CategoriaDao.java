package com.desafiolatam.ListaProductos.dao;

import java.util.List;

import com.desafiolatam.ListaProductos.dao.model.Categoria;

public interface CategoriaDao {

	public int add(Categoria categoria);

	public List<Categoria> getAll();

	public Categoria getById(int idCategoria);

	public int update(Categoria categoria);

	public int deleteById(int idCategoria);

}
