<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
String loginUser = (String) request.getSession().getAttribute("loginUser");
if (loginUser == null) {
	try {
		response.sendRedirect("/");
	} catch (Exception e) {
		out.print("error");
	}
} else {
%>
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

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="Home">Home</a></li>
					<li class="active"><a href="Financial">Financial</a></li>
					<!-- <li><a href="Collection">Collection</a></li> -->
					<li style="align: right"><h4><%=loginUser%></h4></li>
					<li style="align: right"><a href="logoutProcess">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>Financial</h1>
		</div>
		<div>${message}</div>
		<div>
			<h3>Create New Financial</h3>

		</div>
		<div>
			<form action="FinancialSubmit">
				<div>
					<label>Detail</label> <input class="form-control" type="text"
						name="financial_detail" width="100" placeholder="Enter Detail"
						required></input>
				</div>
				<div>
					<label>MYR</label> <input class="form-control" type="number"
						name="financial_myr" width="10" min="0" value="0" step=".01"
						placeholder="Enter MYR" required></input>
				</div>
				<div>
					<label>Date</label> <input class="form-control" type="date"
						id="financial_transaction_date" name="financial_transaction_date"
						required></input>
				</div>
				<div>
					<div>
						<label>Debit</label>&nbsp<input type="radio"
							name="financial_transaction_type" value="Debit" width="10"
							placeholder="Choose transaction type" required></input> <label>Credit</label>&nbsp<input
							type="radio" name="financial_transaction_type" value="Credit"
							width="10" placeholder="Choose transaction type" required></input>
					</div>
				</div>
				<input class="form-control" type="submit" value="Submit" />
			</form>
		</div>

	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script>
		
	</script>

</body>

</html>
<%
	}
%>