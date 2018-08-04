
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
	
<div class="container">
<div class="row">
<div class="col-xs-1"></div>
<div class="col-xs-10">
<c:if test="${empty(cartlist)}">
							<h2 class="jumbotron">Cart Is Empty</h2>
							<a href="${contextRoot}/productview" class="btn button"><i class="fa fa-angle-left"></i> Continue Shopping</a>
							</c:if>
					<c:if test="${!empty(cartlist)}">
					
	<table id="cart" class="table table-hover table-condensed ">
    				<thead>
						<tr>
							<th style="width:50%">Product</th>
							<th style="width:10%">Price</th>
							<th style="width:8%">Quantity</th>
							<th style="width:22%" class="text-center">Subtotal</th>
							<th style="width:10%"></th>
						</tr>
					</thead>
					<tbody>
					<c:set var="totalPrice" value="0"></c:set>
					<c:forEach var="cl" items="${cartlist}">
						<tr>
							<td data-th="Product">
								<div class="row">
								
									<div class="col-sm-2 hidden-xs"><img src="${contextRoot}/resources/pimages/${cl.pid}.jpg" class="img-responsive"></div>
									<div class="col-sm-10">
										<h4 class="nomargin">${cl.pname}</h4>
									</div>
								</div>
							</td>
							<td data-th="Price">&#8377 ${cl.pprice}</td>
							<td data-th="Quantity">
								${cl.qty}
							</td>
							<td data-th="Subtotal" class="text-center">&#8377 ${cl.total}</td>
							<c:set var="totalPrice" value="${totalPrice + cl.total}"></c:set>
							<td class="actions" data-th="">
								<a href="${contextRoot}/info/${cl.pname}" class="btn button btn-sm"><i class="glyphicon glyphicon-edit"></i></a>
								<a href="${contextRoot}/deletecart/${cl.id}" class="btn button btn-sm"><i class="fa fa-trash-o"></i></a>								
							</td>
						</tr>
						</c:forEach>
						
					</tbody>
					
					<tfoot>
						
				
						<tr>
							<td><a href="${contextRoot}/productview" class="btn button"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
							
							
							<td colspan="2" class="hidden-xs"></td>
							
							<td class="hidden-xs text-center"><strong>Total &#8377 ${totalPrice }</strong></td>
							<td><a href="${contextRoot}/address" class="btn button btn-block">Checkout <i class="fa fa-angle-right"></i></a></td>
						</tr>
					</tfoot>
				</table>
				</c:if>
				</div>
<div class="col-xs-1"></div>
				</div>
</div>