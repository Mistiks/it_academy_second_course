<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
<meta charset="utf-8">
<title>Информация о рейсах</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
    $( function() {
      $( "#datepicker1" ).datepicker();
    } );
    </script>
</body>
</head>
<body>
<div align="center">
    <h2>Выберите параметры поиска</h2>
        <form action="flights" method="post">
                Выберите аэропорт вылета:&nbsp;
                <select name="Departure">
                    <option value=""></option>
                    <c:forEach items="${listAirportNames}" var="airportDeparture">
                        <option value="${airportDeparture}"> ${airportDeparture} </option>
                    </c:forEach>
                </select>

                &nbsp&nbsp Выберите аэропорт прилета:&nbsp;
                <select name="Arrival">
                    <option value=""></option>
                    <c:forEach items="${listAirportNames}" var="airportArrival">
                        <option value="${airportArrival}"> ${airportArrival} </option>
                    </c:forEach>
                </select>

                &nbsp&nbsp Выберите дату вылета: <input type="text" id="datepicker1" name="timeDeparture"
                <p></p> <input type="submit" value="Найти" />
        </form>
          <c:if test = "${sessionScope.input_fail == true}">
                <p><span style='color: red;'>Некоторые поля пусты или неверная дата!</span></p>
          </c:if>
</div>
<c:remove var="input_fail"/>
</body>
</html>