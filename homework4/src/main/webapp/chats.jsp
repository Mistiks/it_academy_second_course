<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, model.Message, java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>

<html>
<head>
<title>Chat Form</title>
</head>
<body>
<h2>Chat Form</h2>
<c:choose>
<c:when test="${userChats.size() > 0}">
        <table border="1">
            <tbody>
                    <tr>
                        <td width="20%">From</td>
                        <td width="20%">When</td>
                        <td width="60%">Message</td>
                    </tr>

                <c:forEach items="${userChats}" var="item" varStatus="status">
                    <tr>
                        <td width="20%">${item.sender}</td>
                        <td width="20%">${item.time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))}</td>
                        <td width="60%">${item.text}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>You don't have any messages.</p>
    </c:otherwise>
</c:choose>

<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/profile';" value="Main page" /></p>

<c:remove var="userChats" scope="session" />
</body>
</html>