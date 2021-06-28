<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, javax.servlet.http.*" %>

<html>
<head>
<title>Profile page</title>
</head>
<body>
<h2>Profile Page</h2>
<p><span style='color: green;'>Welcome ${currentUser.username}. You have logged in.</span></p>
<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/message';" value="Send message" /></p>
<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/chats';" value="Show messages" /></p>
<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/signIn';" value="Logout" /></p>
</body>
</html>