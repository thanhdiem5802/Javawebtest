<%@page import="Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

.profile-container {
	display: flex;
	align-items: flex-start;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
	margin: 20px;
}

.avatar {
	margin-right: 20px;
	border-radius: 50%;
	overflow: hidden;
	border: 3px solid #007bff;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	width: 150px;
	height: 150px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.avatar img {
	width: 100%;
	height: auto;
	border-radius: 50%;
}

.user-info {
	flex-grow: 1;
}

table {
	border-collapse: collapse;
	width: 100%;
}

table, th, td {
	border: 1px solid #ccc;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

.edit-button {
	background-color: #007bff;
	color: #fff;
	border: none;
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
	margin-top: 20px;
}

.custom-file-input::-webkit-file-upload-button {
	visibility: hidden;
}

.custom-file-input::before {
	content: 'image';
	display: inline-block;
	background: linear-gradient(top, #f9f9f9, #e3e3e3);
	border: 1px solid #ccc;
	border-radius: 3px;
	padding: 5px 15px;
	outline: none;
	white-space: nowrap;
	cursor: pointer;
	text-align: center;
	font-weight: bold;
	margin-top: 10px;
}

.custom-file-input:hover::before {
	border-color: #888;
}

/* Additional CSS for form elements */
form {
    margin-top: 20px;
}

label {
    font-weight: bold;
}

input[type="text"],
input[type="email"] {
    width: 100%;
    padding: 8px;
    margin-top: 4px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

</style>
</head>
<body>
	<%
    User user = (User) session.getAttribute("authenticatedUser");
    if (user == null) {
        String message = "You must login first.";
        session.setAttribute("loginMessage", message);
        response.sendRedirect("login.jsp");
        return;
    }
    %>
	<h1>Edit Profile</h1>

	<c:choose>
		<c:when test="${authenticatedUser != null}">
			<div class="profile-container">
				<div class="avatar">
					<img src="${authenticatedUser.urlimage}" alt="Profile Avatar">
				</div>
				<div class="user-info">
					<form action="UpdataeSeverlet" method="post">
						<p>
							Welcome, Your username is: <span>${authenticatedUser.user}</span>
						</p>
						<table>
							<tr>
								<th>User Information</th>
								<th>Value</th>
							</tr>
							<tr>
								<td>Email:</td>
								<td><input type="email" name="email"
									value="${authenticatedUser.email}"></td>
							</tr>
							<tr>
								<td>Address:</td>
								<td><input type="text" name="address"
									value="${authenticatedUser.address}"></td>
							</tr>
							<tr>
								<td>Country:</td>
								<td><input type="text" name="country"
									value="${authenticatedUser.country}"></td>
							</tr>
						</table>
						

						<input type="submit" class="edit-button" value="Save Changes">
					</form>
					
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<p>User not authenticated.</p>
		</c:otherwise>
	</c:choose>
</body>
</html>
