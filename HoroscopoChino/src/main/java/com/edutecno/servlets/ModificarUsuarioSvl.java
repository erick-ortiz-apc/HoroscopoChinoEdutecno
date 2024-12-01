package com.edutecno.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Usuario;

@WebServlet("/ModificarDatos")
public class ModificarUsuarioSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuario") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario.getFechaNacimiento() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fechaNacimientoStr = sdf.format(usuario.getFechaNacimiento());
			request.setAttribute("fechaNacimiento", fechaNacimientoStr);
		}

		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher("modificarDatos.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuario") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String nombre = request.getParameter("nombre");
		String username = request.getParameter("username");
		String clave = request.getParameter("clave");
		String correo = request.getParameter("correo");
		String fechaNacimientoStr = request.getParameter("fechaNacimiento");

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		String nombreActual = usuario.getNombre();
		String usernameActual = usuario.getUsername();
		String claveActual = usuario.getClave();
		String correoActual = usuario.getCorreo();
		Date fechaNacimientoActual = usuario.getFechaNacimiento();

		Date fechaNacimientoNueva = null;
		try {
			fechaNacimientoNueva = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimientoStr);
		} catch (ParseException e) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fechaNacimientoOriginal = sdf.format(usuario.getFechaNacimiento());
			request.setAttribute("fechaNacimiento", fechaNacimientoOriginal);
			request.setAttribute("error", "Fecha de nacimiento inválida");
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("modificarDatos.jsp").forward(request, response);
			return;
		}

		if (nombre.equals(nombreActual) && 
			username.equals(usernameActual) && 
			clave.equals(claveActual) && 
			correo.equals(correoActual) && 
			fechaNacimientoNueva.equals(fechaNacimientoActual)) {
			
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println("<script type=\"text/javascript\">");
			response.getWriter().println("alert('No se detectaron cambios.');");
			response.getWriter().println("window.location.href = 'home.jsp';");
			response.getWriter().println("</script>");
			return;
		}

		usuario.setNombre(nombre);
		usuario.setUsername(username);
		usuario.setClave(clave);
		usuario.setCorreo(correo);
		usuario.setFechaNacimiento(fechaNacimientoNueva);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			if (usuarioDAO.modificarUsuario(usuario)) {
				session.setAttribute("usuario", usuario);
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().println("<script type=\"text/javascript\">");
				response.getWriter().println("alert('Datos actualizados correctamente.');");
				response.getWriter().println("window.location.href = 'home.jsp';");
				response.getWriter().println("</script>");
			} else {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().println("<script type=\"text/javascript\">");
				response.getWriter().println("alert('No se pudo actualizar el usuario. Intente nuevamente.');");
				response.getWriter().println("window.location.href = 'home.jsp';");
				response.getWriter().println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println("<script type=\"text/javascript\">");
			response.getWriter().println("alert('Ocurrió un error al actualizar los datos.');");
			response.getWriter().println("window.location.href = 'home.jsp';");
			response.getWriter().println("</script>");
		}
	}
}