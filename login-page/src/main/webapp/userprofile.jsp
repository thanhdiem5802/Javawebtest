<%@page import="Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
String update = (String) request.getAttribute("update");
if (update != null) {
%>
<div class="alert alert-danger">
    <%= update %>
</div>
<% } %>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet">
<style>
.profile-container {
    display: flex;
    align-items: flex-start;
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
    width: 100%;
    border-collapse: collapse;
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
</style>
</head>
<body>
    <%
    User authenticatedUser = (User) session.getAttribute("authenticatedUser");
    if (authenticatedUser == null) {
        String message = "You must login first.";
        session.setAttribute("loginMessage", message);
        
        response.sendRedirect("login.jsp");
        return;
    }
    %>
    
    <% String uploadMessage = (String) request.getAttribute("uploadMessage"); 
        String imagePath = (String) request.getAttribute("imagePath");
    %>
    <!-- Check if uploadMessage is not null and display it -->
    <% if (uploadMessage != null) { %>
    <div class="alert alert-success">
        <%= uploadMessage %>
    </div>
    <% } %>
    <h1>User Profile</h1>

    <c:choose>
        <c:when test="${authenticatedUser != null}">
            <div class="profile-container">	
            
            
                <div class="avatar">
                    <img src="${authenticatedUser.urlimage}" alt="User Avatar">
                </div>
                
                <div class="user-info">
                    <p>Welcome, Your username is: ${authenticatedUser.user}!</p>
					 <a href="editProfile.jsp" class="btn btn-primary">Edit Profile</a>
                    <table>
                        <tr>
                            <th>User Information</th>
                            <th>Value</th>
                        </tr>
                        <tr>
                            <td>Username:</td>
                            <td>${authenticatedUser.user}</td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td>${authenticatedUser.email}</td>
                        </tr>
                        <tr>
                            <td>Address:</td>
                            <td>${authenticatedUser.address}</td>
                        </tr>
                        <tr>
                            <td>Country:</td>
                            <td>${authenticatedUser.country}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <form action="UploadPhotoServerlet" method="post"
                enctype="multipart/form-data">
                <!-- Add an input field for uploading a photo -->
                <div class="form-group">
                    <label for="photo">Upload Photo</label>
                    <div class="custom-file">
                       <input type="file" class="custom-file-input" id="photo" name="photo" onchange="checkImageUpload(this);">
                             <label class="custom-file-label"
                            for="photo">Choose file</label>
                    </div>
                </div>
                <!-- Add a button to submit the image -->
                <button type="submit" id="uploadButton" class="btn btn-primary" disabled>Upload Image</button>
            </form>
            <form action="logout" method="post">
                <button type="submit" class="btn btn-danger">Logout</button>
            </form>
            <script>
        function updateLabel(input) {
            var fileName = input.value.split('\\').pop();
            input.nextElementSibling.innerHTML = fileName;
        }
        
        </script>
        <script>
function checkImageUpload(input) {
    var fileInput = input;
    var submitButton = document.getElementById('uploadButton'); // Change 'uploadButton' to the actual ID of your submit button

    if (fileInput.files.length > 0) {
        // File(s) selected, enable the submit button
        submitButton.disabled = false;
    } else {
        // No file selected, disable the submit button
        submitButton.disabled = true;
    }
}
</script>

        </c:when>
        <c:otherwise>
            <p>User not authenticated.</p>
        </c:otherwise>
    </c:choose>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
