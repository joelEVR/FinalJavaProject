<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/css_login.css">
        <title>Login</title>
    </head>
    <body class="align">

        <div class="grid">

            <form name="form" action="<%=request.getContextPath()%>/LoginServlet" method="post"
                  class="form login">

                <div class="form__field">
                    <label for="login__username">
                        <img src="svg/username.svg" width="20px" height="20px" alt="profile_logo">
                        <span class="hidden">Username</span>
                    </label>
                    <input id="login__username" type="text" name="username" class="form__input" placeholder="Username"
                           required>
                </div>

                <div class="form__field">
                    <label for="login__password">
                        <img src="svg/password.svg" width="20px" height="20px" alt="password_logo">
                        <span class="hidden">Password</span>
                    </label>
                    <input id="login__password" type="password" name="password" class="form__input"
                           placeholder="Password" required>
                </div>

                <p class="text--center">
                    <span style="color:#4ceaae;text-transform: uppercase;font-weight: 500"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
                </p>

                <div class="form__field">
                    <input type="submit" value="Login">
                </div>

            </form>

            <p class="text--center">Not a member?
                <a href="register.jsp">Register now ?</a>
            </p>

        </div>

    </body>
</html>