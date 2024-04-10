<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/css_JoelVelasquez.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>Register</title>

</head>

<body class="align">

    <div class="grid">

        <form name="form" action="RegisterServlet" method="post"
            class="form register" onsubmit="return validate()">

            <div class="form__field">
                <label for="name"> <i class="fas fa-user"></i> <span class="hidden">Name</span>
                </label> <input id="name" type="text" name="name" class="form__input"
                    placeholder="Your name" required>
            </div>

            <div class="form__field">
                <label for="email"> <i class="fas fa-envelope"></i> <span class="hidden">E-mail</span>
                </label> <input id="email" type="email" name="email" class="form__input"
                    placeholder="E-mail" required>
            </div>

            <div class="form__field">
                <label for="password"> <i class="fas fa-lock"></i> <span
                    class="hidden">Password</span>
                </label> <input id="password" type="password" name="password"
                    class="form__input" placeholder="Password" required>
            </div>

            <div class="form__field">
                <label for="confirmPassword"> <i class="fas fa-lock"></i> <span
                    class="hidden">Confirm password</span>
                </label> <input id="confirmPassword" type="password" name="confirmPassword"
                    class="form__input" placeholder="Confirm password" required>
            </div>

            <div class="form__field">
                <label for="userType"> <i class="fas fa-users-cog"></i> <span
                    class="hidden">User Type</span>
                </label> <select id="userType" name="userType" class="form__input" required>
                    <option value="">Choose User Type</option>
                    <option value="RETAILER">Retailer</option>
                    <option value="CONSUMER">Consumer</option>
                    <option value="CHARITY">Charity</option>
                </select>
            </div>

            <h3>Do you want to receive notifications?</h3>

            <div class="form__field">
                <input id="notification" type="checkbox" name="notification"
                    class="form__input" value="true">
            </div>
            
            <p class="text--center">
                <span
                    style="color: #cc0000; text-transform: uppercase; font-weight: 500"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
            </p>


            <div class="form__field">
                <input type="submit" value="Register" />
            </div>

            <div class="form__field">
                <input type="reset" value="Reset" />
            </div>



        </form>

        <p class="text--center">
            Already have an account? <a href="login.jsp">Login here ?</a>
        </p>

    </div>

</body>
</html>
