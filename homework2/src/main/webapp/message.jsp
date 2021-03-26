<!DOCTYPE html>

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

		    <%
                String emptyField = (String) session.getAttribute("emptyField");
                if(emptyField == "true")  {
            %>
                <p><span style='color: red;'>Some fields are empty!</span></p>
            <%   }   %>

		    <%
                String messageSent = (String) session.getAttribute("messageSent");
                if(messageSent == "true")  {
            %>
                <p><span style='color: green;'>Message has been sent!</span></p>
            <%   }   %>

            <%
                session.removeAttribute("emptyField");
                session.removeAttribute("messageSent");
            %>

</body>
</html>