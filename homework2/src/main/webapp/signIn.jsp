<!DOCTYPE html>

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
			<p><input type="button" onclick="location.href='/Mk-JD2-78-21-0.0.0-SHAPSHOT/signUp';" value="Register" /></p>
		    <%
                String showParameter = (String) session.getAttribute("fail_sign_in");
                if(showParameter == "true")  {
            %>
                <p><span style='color: red;'>Incorrect data. Please, try again!</span></p>
            <%   }   %>

            <%
                session.removeAttribute("fail_sign_in");
            %>
</body>
</html>