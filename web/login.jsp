<%--
  Created by IntelliJ IDEA.
  User: Keyris
  Date: 20.11.2020
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <link rel="stylesheet" href="resources/css/login.css">
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
<form action="${pageContext.request.contextPath}/login" method="post">

    <div class="main_container">
        <div class="container">
            <h1>Log in</h1>

            <span style="color:red">${ErrorMessage}</span>
            <span style="color:#3fd445">${OKMessage}</span>
            <hr>
            <div class="imgcontainer">
                <img src="resources/img/iconfinder_batman_hero_avatar_comics_4043232%20(2).png" alt="Avatar" class="avatar">
            </div>

            <div class="container">

                <label><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="uname" required><label>

                <b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="psw" required>

                <button type="submit">Login</button>

                <label><input type="checkbox" checked="checked" name="remember"> Remember me</label>
            </div>

            <div class="container regIn">
                <p>Don't have an account?<a href="registration.jsp"> Register now</a>.</p>
            </div>

        </div>
    </div>
</form>
<a href="index.jsp"> User </a>
</body>
</html>
