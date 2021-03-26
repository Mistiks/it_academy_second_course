<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, jakarta.servlet.http.*" %>

<html>
<head>
<title>Profile page</title>
</head>
<body>
<h2>Profile Page</h2>
<p><span style='color: green;'>Welcome ${sessionScope.currentUser}. You have logged in.</span></p>
<p><input type="button" onclick="location.href='/Mk-JD2-78-21-0.0.0-SHAPSHOT/message';" value="Send message" /></p>
<p><input type="button" onclick="location.href='/Mk-JD2-78-21-0.0.0-SHAPSHOT/chats';" value="Show messages" /></p>
<p><input type="button" onclick="location.href='/Mk-JD2-78-21-0.0.0-SHAPSHOT/signIn';" value="Logout" /></p>
</body>
</html>