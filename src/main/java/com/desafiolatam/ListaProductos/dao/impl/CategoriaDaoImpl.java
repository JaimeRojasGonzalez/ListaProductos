package com.desafiolatam.ListaProductos.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.desafiolatam.ListaProductos.dao.CategoriaDao;
import com.desafiolatam.ListaProductos.dao.model.Categoria;
import com.desafiolatam.ListaProductos.dao.utils.ConnectionUtils;

public class CategoriaDaoImpl implements CategoriaDao {

	@Override
	public int add(Categoria categoria) {
		Connection cn = null;
		int resultado = 0;
		try {
			cn = ConnectionUtils.getConnection();
			PreparedStatement st = cn.prepareStatement("INSERT INTO categoria(id_categoria, nombre_categoria) VALUES (?, ?)");
			
			int lastId = getLastId();
			
			st.setInt(1, (lastId + 1));
			st.setString(2, categoria.getNombreCategoria());
			
	
			resultado = st.executeUpdate();
			
			st.close();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionUtils.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultado;
	}

	@Override
	public List<Categoria> getAll() {
		
		Connection cn = null;
		List<Categoria> categorias = null;
		
		try {
			cn = ConnectionUtils.getConnection();
			Statement st = cn.createStatement();
			ResultSet rset = st.executeQuery("SELECT c.id_categoria, c.nombre_categoria FROM categoria c ORDER BY c.id_categoria");
			categorias = new ArrayList<Categoria>();
			
			while(rset.next()) {

				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rset.getInt("id_categoria"));
				categoria.setNombreCategoria(rset.getString("nombre_categoria"));
				categorias.add(categoria);
				
			}
			
			rset.close();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionUtils.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return categorias;
	}

	@Override
	public int update(Categoria categoria) {
		// TODO Auto-generated method stub
		//return 0;
		String sql = "UPDATE categoria SET nombre_categoria = ? WHERE id_categoria = ?";
		
		Connection cn = null;
		int resultado = 0;
		try {
			cn = ConnectionUtils.getConnection();
			PreparedStatement st = cn.prepareStatement(sql);
			
			st.setString(1, categoria.getNombreCategoria());
			st.setInt(2, categoria.getIdCategoria());
				
			resultado = st.executeUpdate();
			
			st.close();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionUtils.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultado;
		
	}

	@Override
	public int deleteById(int idCategoria) {
		
		String sql = "DELETE FROM categoria WHERE id_categoria = ?";
		
		Connection cn = null;
		int resultado = 0;
		try {
			cn = ConnectionUtils.getConnection();
			PreparedStatement st = cn.prepareStatement(sql);
	
			st.setInt(1, idCategoria);
			
			resultado = st.executeUpdate();
			
			st.close();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionUtils.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultado;
		
	}


	public int getLastId() {
		
		Connection cn = null;
		int lastId = 0;
		try {
			cn = ConnectionUtils.getConnection();
			PreparedStatement pt = cn.prepareStatement("SELECT MAX(ID_CATEGORIA) AS max FROM CATEGORIA");
			ResultSet rset = pt.executeQuery();
			
			if(rset.next()) {
				lastId = rset.getInt("max");
			}
			 
			rset.close();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionUtils.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return lastId;
	}

	@Override
	public Categoria getById(int idCategoria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

