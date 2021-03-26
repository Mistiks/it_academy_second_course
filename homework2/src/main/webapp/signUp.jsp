<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
<title>Registration Form</title>
</head>
<body>
<h2>Register Form</h2>
<form action="registerServlet" method="get">
			<table style="with: 50%">
				<tr>
					<td>First Name</td>
					<td><input type="text" name="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastName" /></td>
				</tr>
				<tr>
                	<td>Patronymic</td>
                	<td><input type="text" name="patronymic" /></td>
                </tr>
				<tr>
					<td>Username</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Date of birth (dd/mm/yyyy)</td>
					<td><input type="text" name="dateOfBirth" /></td>
				</tr></table>
			<p><input type="submit" value="Submit" /></form></p>
			<p><input type="button" onclick="location.href='/Mk-JD2-78-21-0.0.0-SHAPSHOT/signIn';" value="Sign in" /></p>

			   <%
                    String showParameter = (String) session.getAttribute("fail_sign_up");
                    if(showParameter == "true")  {
                %>
                   <p><span style='color: red;'>Incorrect data. Please, try again!</span></p>
                <%
                        session.removeAttribute("fail_sign_up");
                    }
                %>
</body>
</html>