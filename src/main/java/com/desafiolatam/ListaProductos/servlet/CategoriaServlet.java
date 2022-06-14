package com.desafiolatam.ListaProductos.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.desafiolatam.ListaProductos.dto.CategoriaDto;
import com.desafiolatam.ListaProductos.facade.CategoriaFacade;
import com.desafiolatam.ListaProductos.facade.impl.CategoriaFacadeImpl;
import com.desafiolatam.ListaProductos.genericUtils.Utils;


@WebServlet("/listaCategoria.srv")
public class CategoriaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CategoriaFacade categoriaFacade = null;

	@Override
	public void init() throws ServletException {
		super.init();
		this.categoriaFacade = new CategoriaFacadeImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("categoriaDtoJson", this.categoriaFacade.getCategorias().toString());
		req.getServletContext().getRequestDispatcher("/listacategorias.jsp").forward(req, resp);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String json = Utils.getJsonString(req.getInputStream());

		String dataSplit[] = json.split("&");

		int i = dataSplit.length -1;

		String accion = dataSplit[i].split("=")[1];
		CategoriaDto categoriaDto = new CategoriaDto();
		if (accion.equalsIgnoreCase("eliminarCategoria")) {
			int resultado = this.categoriaFacade.deleteById(Integer.parseInt(dataSplit[0].split("=")[1]));
			if (resultado == 1) {
				categoriaDto = this.categoriaFacade.getCategorias();
				categoriaDto.setMensaje("Eliminado Correctamente");
			}
		} else if (accion.equalsIgnoreCase("crearCategoria")) {
			 categoriaDto.setCategoriaFromJson(json);
			categoriaDto = this.categoriaFacade.addCategorias(categoriaDto);
		} else if (accion.equalsIgnoreCase("actualizarCategoria")) {

			categoriaDto.setEditarCategoriaFromJson(json);
			this.categoriaFacade.update(categoriaDto);
			categoriaDto = this.categoriaFacade.getCategorias();
			categoriaDto.setMensaje("Actualizado Correctamente");
		}

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(categoriaDto.toString());
		out.flush();

	}
}
