<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, core.entity.Message, java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>

<html>
<head>
<title>Chat Form</title>
</head>
<body>
<jsp:include page="/chatServlet" />
<%
    ArrayList<Message> list = (ArrayList<Message>) session.getAttribute("userChats");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
%>
<h2>Chat Form</h2>
<% if (list != null) { %>
<table border="1">
            <tbody>
                    <tr>
                        <td width="20%">From</td>
                        <td width="20%">When</td>
                        <td width="60%">Message</td>
                    </tr>

                <% for(int i = 0; i < list.size(); i++) { %>
                    <tr>
                        <td width="20%"><%=list.get(i).getSender() %></td>
                        <td width="20%"><%=list.get(i).getTime().format(formatter) %></td>
                        <td width="60%"><%=list.get(i).getText() %></td>
                    </tr>
                <%   }   %>

            </tbody>
        </table>
        <%   } else {
            out.println("You don't have any messages.");
         }   %>

<p><input type="button" onclick="location.href='/Mk-JD2-78-21-0.0.0-SHAPSHOT/profile';" value="Main page" /></p>

            <%
                session.removeAttribute("userChats");
            %>
</body>
</html>