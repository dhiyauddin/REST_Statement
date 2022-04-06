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
					<li  class="active"><a href="Collection">Collection</a></li>
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
		<div>
			<a href="ProductForm">Create New Collection</a>
		</div>
		<div>
			<h3>Product List</h3>
		</div>
		<div>
			<form action="SearchProductBySupplier">
				<div>
					<label>Supplier</label> <select class="form-control"
						name="buy_summary_id">
						<option value="0">select supplier</option>
						<c:forEach items="${summaryBuyList}" var="element">
							<option value="${element.buy_summary_id}">${element.supplier}</option>
						</c:forEach>
					</select>
				</div>
				<input class="form-control" type="submit" value="Search" />
			</form>
		</div>
		<div class="table-responsive">
			<table class="table" border="1">
				<thead>
					<tr>
						<th>Item</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Description</th>
						<th>Package</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${statusSearch == 'No'}">
							<c:forEach items="${productList}" var="element">
								<tr>
									<td>${element.item}</td>
									<td>${element.quantity}</td>
									<td>${element.price}</td>
									<td>${element.description}</td>
									<td>${element.product_package}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${productListBySupplier}" var="element">
								<tr>
									<td>${element.item}</td>
									<td>${element.quantity}</td>
									<td>${element.price}</td>
									<td>${element.description}</td>
									<td>${element.product_package}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div>
			<c:choose>
				<c:when test="${statusSearch == 'No'}">
					<label>Download : </label>
					<a href="ProductsDownload">Products.PDF</a>
				</c:when>
				<c:otherwise>

				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
<%
	}
%>
