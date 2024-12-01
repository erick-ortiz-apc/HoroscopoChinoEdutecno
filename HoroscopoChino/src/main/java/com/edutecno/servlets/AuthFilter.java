package com.edutecno.servlets;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/home.jsp", "/tuAnimal.jsp", "/BuscarUsuario", "/ListarUsuarios", "/ModificarDatos",
		"/EliminarCuenta" })
public class AuthFilter extends HttpFilter implements Filter {

	public AuthFilter() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession sesion = httpRequest.getSession(false);
		if (sesion == null || sesion.getAttribute("usuario") == null) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
