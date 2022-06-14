package com.desafiolatam.ListaProductos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.desafiolatam.ListaProductos.dao.ProductoDao;
import com.desafiolatam.ListaProductos.dao.model.Producto;
import com.desafiolatam.ListaProductos.dao.model.Categoria;
import com.desafiolatam.ListaProductos.dao.utils.ConnectionUtils;


public class ProductoDaoImpl implements ProductoDao {

	@Override
	public int add(Producto producto) {
		// TODO Auto-generated method stub
		Connection cn = null;
		int resultado = 0;
		try {
			cn = ConnectionUtils.getConnection();
			PreparedStatement st = cn.prepareStatement("INSERT INTO producto(id_producto, nombre_producto, precio_producto, descripcion_producto, id_categoria) VALUES (?, ?, ?, ?, ?)");
			
			//Al cambiar el componente del modal a date, la fecha ya l envía en el formato corecto - YYYY-MM-DD
			//String fechaDb = Utils.getFechaDB(alumno.getFechaNac());
			
			int lastId = getLastId();
			
			st.setInt(1, (lastId + 1));
			st.setString(2, producto.getNombreProducto());
			st.setInt(3, producto.getPrecioProducto());
			st.setString(4, producto.getDescripcionProducto());
			st.setInt(5, producto.getCategoria().getIdCategoria());
	
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
	public List<Producto> getAll() {
		Connection cn = null;
		List<Producto> productos = null;
		try {
			cn = ConnectionUtils.getConnection();
			Statement st = cn.createStatement();
			ResultSet rset = st.executeQuery("SELECT a.id_producto, a.nombre_producto, a.precio_producto, a.descripcion_producto, c.id_categoria, c.nombre_categoria\r\n"
					+ "	FROM producto a, categoria c where a.id_categoria = c.id_categoria ORDER BY a.id_producto");
			productos = new ArrayList<Producto>();
			while(rset.next()) {
				Producto producto = new Producto();
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rset.getInt("id_categoria"));
				categoria.setNombreCategoria(rset.getString("nombre_categoria"));
				
				producto.setCategoria(categoria);
				producto.setIdProducto(rset.getInt("id_producto"));
				producto.setNombreProducto(rset.getString("nombre_producto"));
				producto.setPrecioProducto(rset.getInt("precio_producto"));
				producto.setDescripcionProducto(rset.getString("descripcion_producto"));
				productos.add(producto);
			}
			
			rset.close();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ConnectionUtils.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		return productos;
	}

	@Override
	public Producto getById(int idProducto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Producto producto) {
		// TODO Auto-generated method stub
		//return 0;
		String sql = "UPDATE producto SET nombre_producto = ?, precio_producto = ?, descripcion_producto = ?, id_categoria = ? WHERE id_producto = ?";
		
		Connection cn = null;
		int resultado = 0;
		try {
			cn = ConnectionUtils.getConnection();
			PreparedStatement st = cn.prepareStatement(sql);
			
			st.setString(1, producto.getNombreProducto());
			st.setInt(2, producto.getPrecioProducto());
			st.setString(3, producto.getDescripcionProducto());
			st.setInt(4, producto.getCategoria().getIdCategoria());
			st.setInt(5, producto.getIdProducto());
				
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
	public int deleteById(int idProducto) {
		
		String sql = "DELETE FROM producto WHERE id_producto = ?";
		
		Connection cn = null;
		int resultado = 0;
		try {
			cn = ConnectionUtils.getConnection();
			PreparedStatement st = cn.prepareStatement(sql);
	
			st.setInt(1, idProducto);
			
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
			PreparedStatement pt = cn.prepareStatement("SELECT MAX(ID_Producto) AS max FROM PRODUCTO");
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
	
}
