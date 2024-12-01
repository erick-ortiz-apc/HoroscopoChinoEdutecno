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

@WebServlet("/ListarUsuarios")
public class ListarUsuariosSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListarUsuariosSvl() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try {
			List<Usuario> usuarios = usuarioDAO.buscarUsuarios("");
			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher("buscarUsuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println("<script type=\"text/javascript\">");
			response.getWriter().println("alert('Ocurrió un error al listar los usuarios.');");
			response.getWriter().println("window.location.href = 'home.jsp';");
			response.getWriter().println("</script>");
		}
	}
}
