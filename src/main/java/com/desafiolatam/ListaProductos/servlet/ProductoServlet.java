package com.desafiolatam.ListaProductos.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.desafiolatam.ListaProductos.dto.ProductoDto;
import com.desafiolatam.ListaProductos.facade.CategoriaFacade;
import com.desafiolatam.ListaProductos.facade.ProductoFacade;
import com.desafiolatam.ListaProductos.facade.impl.CategoriaFacadeImpl;
import com.desafiolatam.ListaProductos.facade.impl.ProductoFacadeImpl;
import com.desafiolatam.ListaProductos.genericUtils.Utils;


@WebServlet("/listaProducto.srv")
public class ProductoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductoFacade productoFacade = null;
	private CategoriaFacade categoriaFacade = null;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		this.productoFacade = new ProductoFacadeImpl();
		this.categoriaFacade = new CategoriaFacadeImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		
		//String pattern = "dd-MM-yyyy";
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		req.setAttribute("productoDtoJson", this.productoFacade.getProductos().toString());
		req.setAttribute("categoriaDto", this.categoriaFacade.getCategorias());
		req.getServletContext().getRequestDispatcher("/listaproductos.jsp").forward(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String json = Utils.getJsonString(req.getInputStream());
		
		String dataSplit[] = json.split("&");
		
		int i = dataSplit.length -1;
		
		String accion = dataSplit[i].split("=")[1];
		ProductoDto productoDto = new ProductoDto();
		if(accion.equalsIgnoreCase("eliminarProducto")) {
			int resultado = this.productoFacade.deleteById(Integer.parseInt(dataSplit[0].split("=")[1]));
			if(resultado == 1) {
				productoDto = this.productoFacade.getProductos();
				productoDto.setMensaje("Eliminado Correctamente");
			} 
		} else if(accion.equalsIgnoreCase("crearProducto")) {
			productoDto.setProductoFromJson(json);	
			productoDto = this.productoFacade.addProducto(productoDto);
		} else if(accion.equalsIgnoreCase("actualizarProducto")) {
			
			productoDto.setEditarProductoFromJson(json);
			this.productoFacade.update(productoDto);
			productoDto = this.productoFacade.getProductos();
			productoDto.setMensaje("Actualizado Correctamente");
		} 
		
		PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(productoDto.toString()); 
        out.flush(); 
	
	}
	

}
