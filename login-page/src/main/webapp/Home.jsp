<%@page import="Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
        }
        h2 {
            text-align: center;
            color: red;
        }

        /* Center the table */
        table {
            margin: 0 auto;
        }

        /* Style the buttons */
        button {
            padding: 10px 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<%
    User user = (User) session.getAttribute("authenticatedUser");
	
%>
<% 
String successmsg = (String) request.getAttribute("successmsg");
if (successmsg != null) {
%>
    <div class="alert alert-danger">
        <%= successmsg %>
    </div>
<% } %>
<h1>Welcome to the Home Page</h1>
<table>
    <tr>
        <c:choose>
            <c:when test="${authenticatedUser.user == null}">
           <h2 >You Must Login or Register First</h2>
                <td>
                    <form action="register.jsp" method="get">
                        <button type="submit">Register</button>
                    </form>
                </td>
                <td>
                    <form action="login.jsp" method="get">
                        <button type="submit">Login</button>
                    </form>
                </td>
            </c:when>
            <c:otherwise>
                <td>
                    <form action="logout" method="post">
                        <button type="submit">Logout</button>
                    </form>
                </td>
                <td>
                    <form action="userprofile.jsp" method="get">
                        <button type="submit">User Profile</button>
                    </form>
                </td>
            </c:otherwise>
        </c:choose>
    </tr>
</table>
</body>
</html>
