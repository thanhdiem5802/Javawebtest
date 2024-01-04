<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    >

</head>
<body>
    <div class="container">
        <h1 class="text-danger">Login to join my secret</h1>
        <form action="LoginServerlet" method="post">
            <div class="mb-3">
                <label class="form-label">Username</label> <input type="text"
                    class="form-control" name="user">
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label> <input type="text"
                    class="form-control" name="pass">
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
        <p style="margin-top: 20px;">Don't have an account? <a href="register.jsp" class="btn btn-success">Register</a></p>
        <%
            String message = (String) session.getAttribute("loginMessage");
            if (message != null && !message.isEmpty()) {
                session.removeAttribute("loginMessage"); // Remove the message from the session
        %>
        <p style="color: red;"><%= message %></p>
        <%
            }
        %>
        <script>
            // JavaScript to hide the message after 3 seconds
            setTimeout(function() {
                var loginMessage = document.getElementById("loginMessage");
                if (loginMessage) {
                    loginMessage.style.display = "none";
                }
            }, 3000); // 3 seconds (3000 milliseconds)
        </script>
    </div>
</body>
</html>
