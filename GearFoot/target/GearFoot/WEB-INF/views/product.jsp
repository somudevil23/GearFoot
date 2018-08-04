
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/pimages" />
	<c:url value='/setproduct' var="url"></c:url>

<div class="container">

	<form:form class="form-horizontal" action='${url}' method="POST"
		modelAttribute="product" enctype="multipart/form-data">
		
		
		<c:if test="${edit}">
						<div class="form-group">
							<label class="col-md-4 control-label" for="catid">Product Id</label>
							<div class="col-md-5">
								<form:input type="text" class="fo
								rm-control input-md"
									path="prodId" readonly="true" />
							</div>
						</div>
					</c:if>
		
		<div class="form-group">
			<c:if test="${status}">
				<span class="alert alert-danger">Failed to Process the data</span>
			</c:if>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="proname">Product
				Name</label>
			<div class="col-md-5">
				<form:input type="text" id="proname" name="proname" placeholder=""
					class="form-control input-md" path="prodName" />
					<form:errors path="prodName" cssStyle="color:red"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="catidesc">Product
				Description</label>
			<div class="col-md-5">
				<form:input type="text" id="prodesc" name="prodesc" placeholder=""
					class="form-control input-md" path="prodDescription" />
					
					<form:errors path="prodDescription" cssStyle="color:red"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="catidesc">Category</label>
			<div class="col-md-5">
				<form:select path="categoryId" class="form-control input-md">
					<c:forEach var="c" items="${catlist}">
						<form:option value="${c.categoryId }"
							class="form-control input-md">${c.categoryName}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>


		<div class="form-group">
			<label class="col-md-4 control-label" for="catidesc">Product
				Quantity</label>
			<div class="col-md-5">
				<form:input type="text" id="desc" name="catdesc" placeholder=""
					class="form-control input-md" path="productQuantity" />
					
					<form:errors path="productQuantity" cssStyle="color:red"></form:errors>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="catidesc">Product
				Price</label>
			<div class="col-md-5">
				<form:input type="text" id="catdesc" name="catdesc" placeholder=""
					class="form-control input-md" path="productPrice" />
					
					<form:errors path="productPrice" cssStyle="color:red"></form:errors><br>
					
					<form:input class="form-control input-md" type="file"
							name="fileToUpload" id="fileToUpload" path="pimage"></form:input>
			</div>
		</div>
   			
		
		<div class="form-group">
			<label class="col-md-4 control-label" for="submit"></label>
			<div class="col-md-4">
				<input type="submit" id="submit" name="submit"
					class="btn btn-success">
			</div>
		</div>

	</form:form>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Image</th>
				<th>product Name</th>
				<th>Product Description</th>
				<th>Product Category</th>
				<th>Product Quantity</th>
				<th>Product Price</th>
				
			</tr>
		</thead>
		<c:forEach items="${productlist}" var="pl">
			<tr>
			
				<td><img src="${CR}/${pl.prodId}.jpg" width="100" height="100"/></td>
				<td>${pl.prodName}</td>
				<td>${pl.prodDescription}</td>
				<td>${pl.categoryId}</td>
				<td>${pl.productQuantity}</td>
				<td>${pl.productPrice}</td>
				<td>${p1.sellerId}</td>
				<td class="text-center"><a class='btn btn-info btn-xs' href="editprod/${pl.prodName}"><span
						class="glyphicon glyphicon-edit"></span> Edit</a></td>
				<td class="text-center"><a href="delprod/${pl.prodName}"
					class="btn btn-danger btn-xs"><span
						class="glyphicon glyphicon-remove"></span> Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</div>