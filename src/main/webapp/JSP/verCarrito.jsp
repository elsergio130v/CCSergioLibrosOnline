<%@page import="es.albarregas.bean.Libro"%>
<%@page import="es.albarregas.module.Utiles"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:set var = "context" value="<%=request.getContextPath()%>" scope="request"/>
        
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css">
        <title>vista Carrito</title>
    </head>
    <body>
        <form class="formulario" action="${context}/ControllerCarrito" method="post">
            <div class="ver">
                <c:set var="cant" value="" scope="page"/>
                <jsp:useBean id="libro" scope="page" class="es.albarregas.bean.Libro" />
                <c:forEach var="elemento" items="${sessionScope.carrito}">
                    <p><c:out value="${elemento.nombre}"/> - <c:out value="${elemento.cantidad}"/>
                        <button type="submit" name="enviar+" value="${elemento.nombre}" >+</button>
                        <c:if test="${elemento.cantidad<=1}"> 
                            <c:set var="cant" value="disabled = \"disabled\" "/>
                        </c:if>
                        <button type="submit" name="enviar-" value="${elemento.nombre}" <c:out value="${cant}"/> >-</button> 
                    </p>
                    <br/>
                </c:forEach>
            </div>
                <input
        </form>
    </body>
</html>
