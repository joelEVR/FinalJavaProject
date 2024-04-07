<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/css_JoelVelasquez.css">
<title>Register</title>
<script>
	function validate() {
		var name = document.form.name.value;
		var email = document.form.email.value;
		var password = document.form.password.value;
		var conpassword = document.form.conpassword.value;
		var userType = document.form.userType.value;
		if (name == null || name === "") {
			alert("First Name can't be blank");
			return false;
		} else if (email == null || email === "") {
			alert("Email can't be blank");
			return false;
		} else if (password.length < 6) {
			alert("Password must be at least 6 characters long.");
			return false;
		} else if (password !== conpassword) {
			alert("Confirm Password should match with the Password");
			return false;
		} else if (userType !== "") {
			alert("User type can't be blank");
			return false;
	}
</script>
</head>

<body class="align">

	<div class="grid">

		<form name="form" action="RegisterServlet" method="post"
			class="form register" onsubmit="return validate()">

			<div class="form__field">
				<label for="name"> <img src="svg/name.svg" width="20px"
					height="20px" alt="name_logo"> <span class="hidden">Name</span>
				</label> <input id="name" type="text" name="name" class="form__input"
					placeholder="Your name" required>
			</div>

			<div class="form__field">
				<label for="email"> <img src="svg/mail.svg" width="20px"
					height="20px" alt="email_logo"> <span class="hidden">E-mail</span>
				</label> <input id="email" type="email" name="email" class="form__input"
					placeholder="E-mail" required>
			</div>

			<div class="form__field">
				<label for="password"> <img src="svg/password.svg"
					width="20px" height="20px" alt="password_logo"> <span
					class="hidden">Password</span>
				</label> <input id="password" type="password" name="password"
					class="form__input" placeholder="Password" required>
			</div>

			<div class="form__field">
				<label for="conpassword"> <img src="svg/password.svg"
					width="20px" height="20px" alt="password_logo"> <span
					class="hidden">Confirm password</span>
				</label> <input id="conpassword" type="password" name="conpassword"
					class="form__input" placeholder="Confirm password" required>
			</div>

			<div class="form__field">
				<label for="userType"> <img src="svg/username.svg"
					width="20px" height="20px" alt="usertype_logo"> <span
					class="hidden">User Type</span>
				</label> <select id="userType" name="userType" class="form__input" required>
					<option value="">Choose User Type</option>
					<option value="RETAILER">Retailer</option>
					<option value="CONSUMER">Consumer</option>
					<option value="CHARITY">Charity</option>
				</select>
			</div>


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