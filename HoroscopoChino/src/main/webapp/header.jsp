<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import = "jakarta.servlet.http.HttpSession" %>
<header>
    <nav class="navbar navbar-expand-lg bg-info">
        <div class="container-fluid">
            <a class="navbar-brand text-white" href="index.jsp">Horóscopo Chino</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
               	<div class="row w-100">
               		<div class="col-12 d-flex justify-content-between">
	                    <span class="nav-item">
	                        <a class="nav-link text-white active" aria-current="page" href="/HoroscopoChino/home.jsp">Tu Horóscopo Chino</a>
	                    </span>
	                    <c:if test="${not empty sessionScope.usuario}">
	                        <span class="nav-item">
	                            <a class="nav-link text-white" href="logout.jsp">Logout</a>
	                        </span>
	                    </c:if>
               		</div>
               	</div>
            </div>
        </div>
    </nav>
</header>
