package com.edutecno.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.edutecno.dao.HoroscopoDAO;
import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Horoscopo;
import com.edutecno.modelo.Usuario;
import java.io.IOException;
import java.util.List;

@WebServlet("/ConsultarAnimal")
public class ConsultarHoroscopoSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultarHoroscopoSvl() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String animal = usuario.getAnimal();

        try {
            if (animal == null || animal.isEmpty()) {
                HoroscopoDAO horoscopoDAO = new HoroscopoDAO();
                List<Horoscopo> horoscopos = horoscopoDAO.obtenerTodosHoroscopos();

                animal = determinarAnimal(usuario.getFechaNacimiento(), horoscopos);

                if (animal != null) {
                    usuario.setAnimal(animal);
                    sesion.setAttribute("usuario", usuario);
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usuarioDAO.actualizarAnimal(usuario.getId(), animal);
                }
            }

            sesion.setAttribute("horoscopoAnimal", animal);
            response.sendRedirect("tuAnimal.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            sesion.setAttribute("error", "Error al consultar el horóscopo.");
            response.sendRedirect("error.jsp");
        }
    }

    private String determinarAnimal(java.util.Date fechaNacimiento, List<Horoscopo> horoscopos) {
        if (fechaNacimiento == null) {
            return null;
        }

        for (Horoscopo horoscopo : horoscopos) {
            if (!fechaNacimiento.before(horoscopo.getFechaInicio()) &&
                !fechaNacimiento.after(horoscopo.getFechaFin())) {
                return horoscopo.getAnimal();
            }
        }
        return null;
    }
}
