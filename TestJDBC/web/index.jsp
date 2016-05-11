
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <h1>Hello World!</h1>
    <form method="POST" action="addPokemon" enctype="multipart/form-data">
        <input type="text" name="nombre">
        <input type="file" accept="image/*" name="img">
        <button>Procesar</button>
    </form>
    <c:forEach var="var" items="${listaPokemon}">
        <p>Pokemon</p>
        <ul>
            <li>${var.id}</li>
            <li>${var.nombre}</li>
            <li><img src="${var.img}" ></li>
        </ul>  
    </c:forEach>
</body>
</html>
