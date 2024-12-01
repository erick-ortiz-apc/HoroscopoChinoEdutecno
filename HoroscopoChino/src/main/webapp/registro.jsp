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
        		<div class="col-12">
		            <h2>Regístrate</h2>				
				</div>
        		<div class="col-12">
		            <form action="/HoroscopoChino/CrearUsuario" method="post">
		            	<div class="row">
		            		<div class="col-3 mb-2">
			                    <label for="nombre" class="form-label">Nombre:</label>
							</div>
		            		<div class="col-3 mb-2">
			                    <input type="text" id="nombre" name="nombre" class="form-control" required>
							</div>
		            		<div class="col-6 mb-2">
							</div>
							<div class="col-3 mb-2">
			                    <label for="correo" class="form-label">Correo electrónico:</label>
							</div>
		            		<div class="col-3 mb-2">
			                    <input type="email" id="correo" name="correo" class="form-control" required>
							</div>
		            		<div class="col-6 mb-2">
							</div>
							<div class="col-3 mb-2">
			                    <label for="username" class="form-label">Nombre de usuario:</label>
							</div>
		            		<div class="col-3 mb-2">
			                    <input type="text" id="username" name="username" class="form-control" required>
							</div>
		            		<div class="col-6 mb-2">
							</div>
							<div class="col-3 mb-2">
			                    <label for="fechaNacimiento" class="form-label">Fecha de nacimiento:</label>
							</div>
		            		<div class="col-3 mb-2">
			                    <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control" required>
							</div>
		            		<div class="col-6 mb-2">
							</div>
		            		<div class="col-3 mb-2">
			                    <label for="clave" class="form-label">Contraseña:</label>
							</div>
		            		<div class="col-3 mb-2">
			                    <input type="password" id="clave" name="clave" class="form-control" required>
							</div>
		            		<div class="col-6 mb-2">
							</div>
							<div class="col-3 mb-2">
			                    <label for="passwordrepeat" class="form-label">Repita contraseña:</label>
							</div>
		            		<div class="col-3 mb-2">
			                    <input type="password" id="passwordrepeat" name="passwordrepeat" class="form-control" required>
							</div>
		            		<div class="col-6 mb-2">
							</div>
							<div class="col-12 mt-4">
				                <button type="submit" class="btn btn-info text-white">Registrarse</button>
							</div>
		            	</div>
		            </form>
				</div>
        	</div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>