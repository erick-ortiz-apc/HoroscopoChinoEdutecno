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
                    <h2>Conoce a tu animal del horóscopo chino</h2>
                    <h2>Tu animal es 
                        <strong>
                            <c:choose>
                                <c:when test="${not empty sessionScope.horoscopoAnimal}">
                                    <c:out value="${sessionScope.horoscopoAnimal}" />
                                </c:when>
                                <c:otherwise>
                                    No disponible.
                                </c:otherwise>
                            </c:choose>
                        </strong>
                    </h2>
                </div>
                <div class="col-12 mt-5 text-center">
                	<a class="btn btn-info text-white py-3 px-5" href="/HoroscopoChino/home.jsp">Volver</a>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>