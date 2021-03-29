<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
<title>Message Form</title>
</head>
<body>
<h2>Message Form</h2>
<form action="messageServlet" method="post">
			<table style="with: 50%">
				<tr>
					<td>Recipient</td>
					<td><input type="text" name="recipient" /></td>
				</tr>
				<tr>
					<td>Text</td>
					<td><input type="text" name="text" /></td>
				</tr></table>
			<p><input type="submit" value="Send" /></form></p>

<p><input type="button" onclick="location.href='/Mk-JD2-78-21-0.0.0-SHAPSHOT/profile';" value="Main page" /></p>

            <c:if test = "${sessionScope.emptyField == true}">
                   <p><span style='color: red;'>Some fields are empty!</span></p>
            </c:if>

            <c:if test = "${sessionScope.messageSent == true}">
                   <p><span style='color: green;'>Message has been sent!</span></p>
            </c:if>

            <c:remove var="emptyField" scope="session" />
            <c:remove var="messageSent" scope="session" />

</body>
</html>