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
					<li><a href="Financial">Financial</a></li>
					<li class="active"><a href="Collection">Collection</a></li>
					<li style="align: right"><h4><%=loginUser%></h4></li>
					<li style="align: right"><a href="logoutProcess">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>Product</h1>
		</div>
		<div>${message}</div>
		<div>
			<h3>Create New Collection</h3>

		</div>
		<div>
			<form action="ProductSubmit">
				<div>
					<label >Item</label> <input
						class="form-control" type="text" name="product_item" width="100"></input>
				</div>
				<div>
					<label >Quantity</label> <input
						class="form-control" type="number" name="product_quantity" width="10"></input>
				</div>
				<div>
					<label >Price</label> <input
						class="form-control" type="number" name="product_price" width="10"
						min="0" value="0" step=".01"></input>
				</div>
				<div>
					<label >Description</label> <input
						class="form-control" type="text" name="product_description"
						width="255"></input>
				</div>
				<div>
					<label >Package</label> <input
						class="form-control" type="text" name="product_package"
						width="25"></input>
				</div>
				<div>
					<label >Supplier</label> <select
						class="form-control" name="buy_summary_id">
						<option value="0">select supplier</option>
						<c:forEach items="${summaryBuyList}" var="element">
							<option value="${element.buy_summary_id}">${element.supplier}</option>
						</c:forEach>
					</select>
				</div>

				<input class="form-control" type="submit" value="Submit" />
			</form>
		</div>

	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
<%
	}
%>