package com.edutecno.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Usuario;
import java.io.IOException;

@WebServlet("/IniciarSesion")
public class IniciarSesionSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IniciarSesionSvl() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            if (!usuarioDAO.existeUsuario(username)) {
            	response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script type=\"text/javascript\">");
                response.getWriter().println("alert('El usuario no existe, debe registrarse.');");
                response.getWriter().println("window.location.href = 'index.jsp';");
                response.getWriter().println("</script>");
            }

            Usuario usuario = usuarioDAO.iniciarSesion(username, password);
            if (usuario != null) {
            	request.getSession().setAttribute("usuario", usuario);
            	response.sendRedirect("home.jsp");

            } else {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script type=\"text/javascript\">");
                response.getWriter().println("alert('Usuario o contraseña incorrectos.');");
                response.getWriter().println("window.location.href = 'index.jsp';");
                response.getWriter().println("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script type=\"text/javascript\">");
            response.getWriter().println("alert('Error al intentar inciar sesión.');");
            response.getWriter().println("window.location.href = 'index.jsp';");
            response.getWriter().println("</script>");
        }
    }
}