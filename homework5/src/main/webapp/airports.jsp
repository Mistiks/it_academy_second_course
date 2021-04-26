<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, model.dto.Airport" %>

<html>
<head>
<title>
<c:if test="${language == 'ru'}"> Результат поиска </c:if>
<c:if test="${language == 'en'}"> Search result </c:if>
</title>
</head>
<body>
<c:if test="${language == 'ru'}">
    <div align="left">
    <td><a href="airports?language=en">EN</a></td>
    </div>

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
                        <td><c:out value="${airport.nameStoredLanguage}" /></td>
                        <td><c:out value="${airport.cityStoredLanguage}" /></td>
                        <td><c:out value="${airport.coordinates}" /></td>
                        <td><c:out value="${airport.timezone}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <c:if test="${language == 'en'}">
        <div align="left">
        <td><a href="airports?language=ru">RU</a></td>
        </div>

        <div align="center">
                <table border="1" cellpadding="5">
                    <caption><h2>List of airports</h2></caption>
                    <tr>
                        <th>Code</th>
                        <th>Name</th>
                        <th>City</th>
                        <th>Coordinates</th>
                        <th>Timezone</th>
                    </tr>
                    <c:forEach var="airport" items="${listAirport}">
                        <tr>
                            <td><c:out value="${airport.code}" /></td>
                            <td><c:out value="${airport.nameStoredLanguage}" /></td>
                            <td><c:out value="${airport.cityStoredLanguage}" /></td>
                            <td><c:out value="${airport.coordinates}" /></td>
                            <td><c:out value="${airport.timezone}" /></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    <c:remove var="listAirport"/>
    <c:remove var="language"/>
</body>
</html>