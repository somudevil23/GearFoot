<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<form:form class="form-horizontal" action='addSupplier' method="POST"
		modelAttribute="supplier">
		
		<div class="form-group">
			<c:if test="${status}">
				<span class="alert alert-danger">Failed to Process the data</span>
			</c:if>
		</div>
		
		<c:if test="${edit}">
						<div class="form-group">
							<label class="col-md-4 control-label" for="supId">Supplier Id</label>
							<div class="col-md-5">
								<form:input type="text" class="fo
								rm-control input-md"
									path="supId" readonly="true" />
							</div>
						</div>
					</c:if>
		
		<div class="form-group">
			<label class="col-md-4 control-label" for="supName">Supplier
				Name</label>
			<div class="col-md-5">
				<form:input type="text" id="supName" name="supName" placeholder=""
					class="form-control input-md" path="supName" />
					<form:errors path="supName"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label" for="supCity">Supplier
				City</label>
			<div class="col-md-5">
				<form:input type="text" id="supCity" name="supCity" placeholder=""
					class="form-control input-md" path="supCity" />
					<form:errors path="supCity"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label" for="supPhNo">Supplier
				Phone Number</label>
			<div class="col-md-5">
				<form:input type="text" id="supPhNo" name="supPhNo" placeholder=""
					class="form-control input-md" path="supPhNo" />
					<form:errors path="supPhNo"/>
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
				<th>Supplier Id</th>
				<th>Supplier Name</th>
				<th>Supplier City</th>
				<th>Supplier Phone</th>
				<th class="text-center">Edit</th>
				<th class="text-center">Delete</th>
			</tr>
		</thead>
		<c:forEach items="${supplierlist}" var="sl">
			<tr>
				<td>${sl.supId}</td>
				<td>${sl.supName}</td>
				<td>${sl.supCity}</td>
				<td>${sl.supPhNo}</td>
				<td class="text-center"><a class='btn btn-info btn-xs' href="editsup?supName=${sl.supName}"><span
						class="glyphicon glyphicon-edit"></span> Edit</a></td>
				<td class="text-center"><a href="delsup?supName=${sl.supName}"
					class="btn btn-danger btn-xs"><span
						class="glyphicon glyphicon-remove"></span> Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</div>