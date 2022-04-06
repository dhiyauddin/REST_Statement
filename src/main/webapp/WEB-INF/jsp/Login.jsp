<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this, 
        Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
    <spring:url value="/css/main.css" var="springCss" />
    <link href="${springCss}" rel="stylesheet" />
     -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<form action="loginProcess" method="post">
		<div class="container">
			<div>
				<h1>MDAA FINANCIAL</h1>
			</div>
		</div>

		<div class="container">
			<label for="uname"><b>Username</b></label> <input
				class="form-control" type="text" placeholder="Enter Username"
				name="uname" required> <label for="psw"><b>Password</b></label>
			<input class="form-control" type="password"
				placeholder="Enter Password" name="psw" required>
			<button class="form-control" type="submit">Login</button>
			<a href="RegisterForm"><i>Register Account</i></a>
		</div>
	</form>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>