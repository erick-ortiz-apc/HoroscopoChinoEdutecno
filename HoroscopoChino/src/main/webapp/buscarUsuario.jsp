<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%
HttpSession sesion = request.getSession(false);
%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" href="assets/css/style.css">
<title>Horóscopo Chino</title>
</head>
<body>
	<div class="main">
		<div class="container-fluid">
			<div class="row m-5">
				<div class="col-12">
					<h2>Buscar Usuario</h2>
				</div>
				<div class="col-12">
					<form action="BuscarUsuario" method="get" id="searchForm">
						<div class="row">
							<div class="col-3 mb-2 d-flex align-items-end gap-3">
								<label for="filtro" class="form-label">Buscar:</label>
								<input type="text" class="form-control" id="filtro"
									name="filtro" placeholder="Ingrese un Nombre o Username"
									required>
							</div>
							<div class="col-9 mb-2"></div>
							<div class="col-12 mt-4 d-flex justify-content-between">
								<button type="submit" class="btn btn-info text-white py-3 px-5">Buscar</button>
								<button type="button" class="btn btn-info text-white py-3 px-5"
									id="limpiarBtn">Limpiar</button>
								<a class="btn btn-info text-white py-3 px-5"
									href="/HoroscopoChino/ListarUsuarios">Listar Todos</a> <a
									class="btn btn-info text-white py-3 px-5"
									href="/HoroscopoChino/home.jsp">Volver</a>
							</div>
							<div class="col-12 mt-4">
								<c:if test="${not empty usuarios}">
									<h2>Usuarios Registrados</h2>
									<table class="table table-bordered" id="usuariosTable">
										<thead>
											<tr>
												<th>ID</th>
												<th>Nombre</th>
												<th>Username</th>
												<th>Email</th>
												<th>Fecha de Nacimiento</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="usuario" items="${usuarios}">
												<tr>
													<td>${usuario.id}</td>
													<td>${usuario.nombre}</td>
													<td>${usuario.username}</td>
													<td>${usuario.correo}</td>
													<td>${usuario.fechaNacimiento}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>

								<c:if test="${empty usuarios && not empty filtro}">
									<div class="alert alert-warning" role="alert">
										No se encontraron usuarios para el criterio: <strong>${filtro}</strong>
									</div>
								</c:if>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script>
		document.getElementById('limpiarBtn').addEventListener('click',
				function() {
					document.getElementById('filtro').value = '';
					var table = document.getElementById('usuariosTable');
					var rows = table.getElementsByTagName('tbody')[0];
					rows.innerHTML = '';
				});
	</script>
</body>
</html>
