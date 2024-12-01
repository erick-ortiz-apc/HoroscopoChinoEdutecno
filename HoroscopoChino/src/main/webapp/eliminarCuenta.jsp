<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import = "jakarta.servlet.http.HttpSession" %>
<% HttpSession sesion = request.getSession(false); %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/style.css">
    <title>Horóscopo Chino</title>
</head>
<body>
    <div class="main">
        <div class="container-fluid">
            <div class="row m-5">
                <div class="col-12 text-center">
                    <h2>¿Estás seguro de que deseas eliminar tu cuenta?</h2>
                    <p class="text-danger">Esta acción no se puede deshacer.</p>
			        <form action="EliminarCuenta" method="post">
				        <div class="col-12 mt-5 d-flex justify-content-center gap-5">
		                	<button type="submit" class="btn btn-danger py-3 px-5">Eliminar cuenta</button>
			            	<a href="/HoroscopoChino/home.jsp" class="btn btn-info text-white py-3 px-5">Cancelar</a>
		                </div>
			        </form>
                </div>
                
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>