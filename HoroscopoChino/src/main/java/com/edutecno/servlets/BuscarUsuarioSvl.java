package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/BuscarUsuario")
public class BuscarUsuarioSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BuscarUsuarioSvl() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try {
			List<Usuario> usuarios = usuarioDAO.buscarUsuarios(filtro);

			request.setAttribute("usuarios", usuarios);
			request.setAttribute("filtro", filtro);
			request.getRequestDispatcher("buscarUsuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Ocurrió un error al buscar los usuarios.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
