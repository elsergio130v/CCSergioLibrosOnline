<%-- 
    Document   : tienda
    Created on : 28 oct. 2023, 12:15:29
    Author     : elser
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="es.albarregas.module.Utiles"%>
<%@page import="es.albarregas.bean.Libro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="/INC/metas.inc"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/style.css">
        <title>Tienda</title>
    </head>
    <body>
        <%@include file="/INC/cabecera.inc"%>
        <form class="formulario" action="<%=request.getContextPath()%>/TiendaController" method="post">
            <!-- buscamos la cookie, si la encontramos y contiene elementos, los escribimos
                
            -->

            <c:set var="lista" value="${sessionScope.carrito}" /> 
                <c:set var="cant" value="disabled = \"disabled\"" scope="page" />

            <c:if test="${not empty lista && lista.size() >= 1}">
                <c:set var="cant" value="" scope="page" />
                <c:forEach var="elemento" items="${lista}" varStatus="status">
                    <c:if test="${status.last}">
                        <p>se ha añadido : <c:out value="${elemento.cantidad}"/> libros de: <c:out value="${elemento.nombre}"/> a a cesta</p>
                    </c:if>
                </c:forEach>
            </c:if>

     




            <!-- Mostramos el contenido del formulario. -->
            <select name="selectLibros" id="selLib" size="6">
                <option >1984</option>
                <option >Dracula</option>
                <option >Frankenstein</option>
                <option >Ulises</option>
                <option >Niebla</option>
                <option >Rebeldes</option>
                <option >Criticon</option>
                <option >Matilda</option>
            </select>
            <br/>
            <label>cantidad: </label><input type="text" name="cantidad">
            <br/>
            <input type="submit" name="enviar" value="add">Añadir al Carrito</input>
            <input type="submit" name="enviar" value="ver" <c:out value="${cant}"/>>
            <input type="submit" name="enviar" value="fin" <c:out value="${cant}"/>>

        </form>
    </body>
</html>
