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

	<form action="registerProcess" method="post">
		<div class="container">
			<div><h1>Registration Account</h1><h4>Form</h4></div>
		</div>

		<div class="container">
			<label for="uname"><b>Username</b></label> <input class="form-control" type="text" placeholder="Enter Username" name="uname" required> 
			<label for="psw"><b>Password</b></label> <input class="form-control" type="password" placeholder="Enter Password" name="psw" required>
			<label for="c_contact_no"><b>Contact No</b></label> <input class="form-control" type="text" placeholder="Enter Contact No" name="c_contact_no" required>
			<button class="form-control" type="submit">Submit</button>			
		</div>
	</form>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>