<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, model.Airport" %>

<html>
<head>
<title>Результат поиска</title>
</head>
<body>
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Список аэропортов</h2></caption>
            <tr>
                <th>Код</th>
                <th>Название</th>
                <th>Город</th>
                <th>Координаты</th>
                <th>Часовой пояс</th>
            </tr>
            <c:forEach var="airport" items="${listAirport}">
                <tr>
                    <td><c:out value="${airport.code}" /></td>
                    <td><c:out value="${airport.name}" /></td>
                    <td><c:out value="${airport.city}" /></td>
                    <td><c:out value="${airport.coordinates}" /></td>
                    <td><c:out value="${airport.timezone}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
<c:remove var="listAirport"/>
</body>
</html>