package com.edutecno.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Usuario;

@WebServlet("/EliminarCuenta")
public class EliminarUsuarioSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        request.getRequestDispatcher("eliminarCuenta.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        int userId = usuario.getId();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            if (usuarioDAO.eliminarUsuario(userId)) {
                session.invalidate();
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script type=\"text/javascript\">");
                response.getWriter().println("alert('Cuenta eliminada exitosamente.');");
                response.getWriter().println("window.location.href = 'index.jsp';");
                response.getWriter().println("</script>");
            } else {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script type=\"text/javascript\">");
                response.getWriter().println("alert('No se pudo eliminar la cuenta. Intente nuevamente.');");
                response.getWriter().println("window.location.href = 'home.jsp';");
                response.getWriter().println("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script type=\"text/javascript\">");
            response.getWriter().println("alert('Ocurrió un error al intentar eliminar la cuenta.');");
            response.getWriter().println("window.location.href = 'home.jsp';");
            response.getWriter().println("</script>");
        }
    }
}