
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="resources/css/mycss.css">
<script src="resources/js/myjs.js">	</script>

<title>GearFoot - ${title}</title>
<style>
html {
	height:100%;
}

body {
    padding-top: 70px;
    height:100%;
}


.carousel-inner img {
      width: 100%;
      height: 100%;
  }

.wrapper {	
	min-height:100%;
	position: relative;
}


.content {
	
	padding-bottom: 100px; /*height of the footer*/	
}

.navbar-custom {
	background-color:#3D3C31;
    color:#ffffff;
  	border-radius:0;
  	min-height:50px
}
  
.navbar-custom .navbar-nav > li > a {
  	color:#fff;
  	padding-left:20px;
  	padding-right:20px;
}
.navbar-custom .navbar-nav > .active > a, .navbar-nav > .active > a:hover, .navbar-nav > .active > a:focus {
    color: #f39c12;
	background-color:transparent;
}
      
.navbar-custom .navbar-nav > li > a:hover, .nav > li > a:focus {
    text-decoration: none;
    background-color: #f39c12;
}
      
.navbar-custom .navbar-brand {
padding-top:0;
  	color:#eeeeee;
}
.navbar-custom .navbar-toggle {
  	background-color:#F0D61F;
}
.carousel-caption{
color:#D11141;
}
.footer {
   position: absolute;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: #000000;
   color: #ffffff;
   text-align: center;
}

.social:hover {
     -webkit-transform: scale(0.8);
     -moz-transform: scale(0.8);
     -o-transform: scale(0.8);
 }
 .social {
     -webkit-transform: scale(0.5);
     -moz-transform: scale(0.5);
     -o-transform: scale(0.5);
     -webkit-transition-duration: 0.5s;
     -moz-transition-duration: 0.5s;
     -o-transition-duration: 0.5s;
     color:#ffffff;
 }

 #social-fb:hover {
     color: #f39c12;
 }
 #social-tw:hover {
     color: #f39c12;
 }
 #social-gp:hover {
     color: #f39c12;
 }
 #social-em:hover {
     color: #f39c12;
 }
</style>
</head>

    <%@ include file = "navbar.jsp" %>
<body>
<div class="wrapper">
<div class="content">
<c:if test="${userClickHome == true }">
<%@ include file="home.jsp" %>
</c:if>
<c:if test="${userClickAbout == true }">
<%@ include file="aboutus.jsp" %>
</c:if>

<c:if test="${userClickContact == true }">
<%@ include file="contact.jsp" %>
</c:if>


<c:if test="${userClickCategory == true }">
<%@ include file="category.jsp" %>
</c:if>

<c:if test="${userClickProduct == true }">
<%@ include file="product.jsp" %>
</c:if>

<c:if test="${userClickLogin == true }">
<%@ include file="login.jsp" %>
</c:if>

<c:if test="${userClickProductInfo == true }">
<%@ include file="singleproduct.jsp" %>
</c:if>

<c:if test="${userClickRegistration == true }">
<%@ include file="registration.jsp" %>
</c:if>
<c:if test="${userClickSupplier == true }">
<%@ include file="supplier.jsp" %>
</c:if>
<c:if test="${userClickProductView == true }">
<%@ include file="productsview.jsp" %>
</c:if>
<c:if test="${userClickCart == true }">
<%@ include file="cart.jsp" %>
</c:if>
<c:if test="${userClickCheckOut == true }">
<%@ include file="address.jsp" %>
</c:if>
<c:if test="${userClickInvoice == true }">
<%@ include file="invoice.jsp" %>
</c:if>
<c:if test="${userClickAllOrder == true }">
<%@ include file="orderlist.jsp" %>
</c:if>
<c:if test="${userClickWish == true }">
<%@ include file="wishlist.jsp" %>
</c:if>
</div>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>