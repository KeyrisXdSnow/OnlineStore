<%--
  Created by IntelliJ IDEA.
  User: Keyris
  Date: 20.11.2020
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="resources/css/reg.css">
</head>
<style>
    @font-face {
        font-family: FedraSansPro;
        src: url("resources/fonts/FedraSansPro-Normal.ttf");
        font-weight: normal;
        font-style: normal;
    }

    .main_container {
        font-family: FedraSansPro, 'Comic Sans MS', cursive;
    }
</style>
<body>
<form action="${pageContext.request.contextPath}/reg" method="post">
    <div class="main_container">
        <div class="container">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <span style="color:red">${ErrorMessage}</span>
            <hr>

            <label><b>Login</b></label>
            <input type="text" placeholder="Enter Email" name="uname" required>

            <label><b>Email</b></label>
            <input type="text" placeholder="Enter Email" name="email" required>

            <label><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" required>

            <label><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="psw-repeat" required>

            <button type="submit" class="registerbtn">Register</button>
        </div>

        <div class="container signin">
            <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
        </div>
    </div>
</form>
</body>
</html>
