package com.edutecno.servlets;
import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@WebServlet("/CrearUsuario")
public class CrearUsuarioSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        String clave = request.getParameter("clave");
        String passwordrepeat = request.getParameter("passwordrepeat");
        String correo = request.getParameter("correo");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String animal = request.getParameter("animal");

        Date fechaNacimiento = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaNacimiento = sdf.parse(fechaNacimientoStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        LocalDate fechaNac = null;
        if (fechaNacimiento != null) {
            fechaNac = fechaNacimiento.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        Usuario usuario = new Usuario(nombre, username, clave, correo);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setAnimal(animal);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            if (usuarioDAO.existeUsuario(username)) {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script type=\"text/javascript\">");
                response.getWriter().println("alert('El nombre de usuario ya está registrado.');");
                response.getWriter().println("window.location.href = 'registro.jsp';");
                response.getWriter().println("</script>");
                return;
            }

            if (fechaNac != null && fechaNac.isAfter(LocalDate.now())) {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script type=\"text/javascript\">");
                response.getWriter().println("alert('La fecha de nacimiento no puede ser mayor al día actual.');");
                response.getWriter().println("window.location.href = 'registro.jsp';");
                response.getWriter().println("</script>");
                return;
            }

            if (!clave.equals(passwordrepeat)) {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script type=\"text/javascript\">");
                response.getWriter().println("alert('Las contraseñas no coinciden.');");
                response.getWriter().println("window.location.href = 'registro.jsp';");
                response.getWriter().println("</script>");
                return;
            }
            
            boolean resultado = usuarioDAO.crearUsuario(usuario);
            if (resultado) {
            	response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script type=\"text/javascript\">");
                response.getWriter().println("alert('Registro correcto.');");
                response.getWriter().println("window.location.href = 'index.jsp';");
                response.getWriter().println("</script>");
            } else {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script type=\"text/javascript\">");
                response.getWriter().println("alert('Hubo un error al crear el usuario.');");
                response.getWriter().println("window.location.href = 'registro.jsp';");
                response.getWriter().println("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script type=\"text/javascript\">");
            response.getWriter().println("alert('Error al interactuar con la base de datos.');");
            response.getWriter().println("window.location.href = 'registro.jsp';");
            response.getWriter().println("</script>");
        }
    }
}

