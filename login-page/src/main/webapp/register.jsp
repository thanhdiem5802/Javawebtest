<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<style>
body {
    font-family: Arial, sans-serif;
    text-align: center;
    background-color: #f2f2f2;
    margin: 0;
    padding: 0;
}

.container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
    text-align: center;
    color: #333;
}

form {
    margin-top: 20px;
}

label {
    display: block;
    margin-bottom: 5px;
    color: #333;
}

input[type="text"],
input[type="password"],
input[type="email"],
select {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 3px;
}

.custom-file-input::-webkit-file-upload-button {
    visibility: hidden;
}

.custom-file-input::before {
    content: 'Choose file';
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
}

.custom-file-input:hover::before {
    border-color: #888;
}

button {
    padding: 10px 20px;
    font-size: 16px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 3px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}
</style>
</head>
<body>
<div class="container">
<% 
String msg = (String) request.getAttribute("msg");
if (msg != null) {
%>
    <div class="alert alert-danger">
        <%= msg %>
    </div>
<% } %>
<h1>Register</h1>
<form action="RegisterServerlet" method="post">
    <label for="username">Username:</label>
    <input type="text" id="user" name="user" required>
    <label for="password">Password:</label>
    <input type="password" id="pass" name="pass" required>
    <label for="cpassword">Confirm Password:</label>
    <input type="password" id="cpass" name="cpass" required>
   
    <label for="email">Email :</label>
    <input type="email" id="email" name="email" required>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required>

    <label for="country">Country:</label>
    <select id="country" name="country" required>
        <option value="USA">USA</option>
        <option value="VN">VN</option>
        <option value="UK">UK</option>
        <!-- Add more countries as needed -->
    </select>

    <button type="submit">Register</button>

    
</form>
</div>
</body>
</html>
