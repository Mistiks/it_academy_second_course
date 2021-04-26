<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, model.dto.Airport, model.dto.Flight" %>

<html>
<head>
<title>Поиск рейсов</title>
</head>
<body>
<div align="center">
        <c:if test="${flightsList.size() > 0}">
            <table border="1" cellpadding="5">
                <caption><h2>Список рейсов</h2></caption>
                <tr>
                    <th>Код рейса</th>
                    <th>Отправление</th>
                    <th>Прибытие</th>
                    <th>Аэропорт отправления</th>
                    <th>Аэропорт прибытия</th>
                    <th>Статус</th>
                    <th>Модель самолета</th>
                </tr>
                <c:forEach var="flights" items="${flightsList}">
                    <tr>
                        <td><c:out value="${flights.flight}" /></td>
                        <td><c:out value="${flights.departure}" /></td>
                        <td><c:out value="${flights.arrival}" /></td>
                        <td><c:out value="${flights.departureAirport}" /></td>
                        <td><c:out value="${flights.arrivalAirport}" /></td>
                        <td><c:out value="${flights.status}" /></td>
                        <td><c:out value="${flights.aircraft}" /></td>
                    </tr>
                </c:forEach>
            </table>
            <p></p>
                <c:if test="${currentPage > 1}">
                    <td><a href="search?Departure=${Departure}&Arrival=${Arrival}&timeDeparture=${timeDeparture}&currentPage=${currentPage - 1}">Предыдущая страница</a></td>
                </c:if>

                <c:if test="${currentPage ne maxPage}">
                    <td><a href="search?Departure=${Departure}&Arrival=${Arrival}&timeDeparture=${timeDeparture}&currentPage=${currentPage + 1}">Следующая страница</a></td>
                </c:if>

                <p><td><a href="flights">Обратно к поиску</a></td></p>
        </c:if>

        <c:if test="${flightsList.size() == 0}">
            <p>Нет рейсов по заданным критериям</p>
            <td><a href="flights">Обратно к поиску</a></td>
        </c:if>
    </div>
</body>
</html>