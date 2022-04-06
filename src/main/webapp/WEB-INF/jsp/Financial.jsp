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
		<div>
			<a href="FinancialForm">Create New Financial</a>
		</div>
		<div>
			<h3>Financial List</h3>
		</div>

		<div class="table-responsive">
			<table class="table" border="1">
				<thead>
					<tr>
						<th>Date</th>
						<th>Details</th>
						<th>MYR</th>
						<th>Debit/Credit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${financialList}" var="element">
						<tr>
							<td>${element.fn_trans_date}</td>
							<td>${element.fn_detail}</td>
							<td>${element.fn_myr}</td>
							<td>${element.fn_trans_type}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div>
			<label>Download : </label> <a href="FinancialDownload">Financial.PDF</a>
		</div>
	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
<%
	}
%>
