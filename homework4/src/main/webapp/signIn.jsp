<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
<title>Login Form</title>
</head>
<body>
<h2>Login Form</h2>
<form action="loginServlet" method="get">
			<table style="with: 50%">
				<tr>
					<td>Username</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr></table>
			<p><input type="submit" value="Login" /></form></p>
			<p><input type="button" onclick="location.href='/Mk-JD2-78-21-4-0.0.0-SHAPSHOT/signUp';" value="Register" /></p>

            <c:if test = "${sessionScope.fail_sign_in == true}">
                   <p><span style='color: red;'>Incorrect data. Please, try again!</span></p>
            </c:if>

            <c:remove var="fail_sign_in" scope="session" />
            <c:remove var="currentUser" scope="session" />
</body>
</html>