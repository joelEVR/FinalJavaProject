<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/css_JoelVelasquez.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>Login</title>
</head>
<body class="align">

    <div class="grid">

        <form name="form" action="<%=request.getContextPath()%>/LoginServlet"
            method="post" class="form login">

            <div class="form__field">
                <label for="login__email">
                    <i class="fas fa-user"></i> 
                    <span class="hidden">Email</span>
                </label>
                <input id="login__email" type="text" name="email"
                    class="form__input" placeholder="Email Address" required>
            </div>

            <div class="form__field">
                <label for="login__password">
                    <i class="fas fa-lock"></i> 
                    <span class="hidden">Password</span>
                </label>
                <input id="login__password" type="password" name="password"
                    class="form__input" placeholder="Password" required>
            </div>

            <p class="text--center">
                <span
                    style="color: #cc0000; text-transform: uppercase; font-weight: 500"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
            </p>

            <div class="form__field">
                <input type="submit" value="Login">
            </div>

        </form>

        <p class="text--center">
            Not a member? <a href="register.jsp">Register now</a>
        </p>

    </div>

</body>
</html>
