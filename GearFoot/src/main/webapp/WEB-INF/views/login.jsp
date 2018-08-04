<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url var="images" value="/resources/images" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<div class= "container">
<div class="row col-md-6 col-md-offset-2 custyle">
<form class="form-horizontal" action='<c:url value='/perform_login'></c:url>' method="POST">
    <div class="form-group">
    
               <p style="text-align:center;"><img src="${images}/login.png" alt="Logo" width="100px"
				height="80px"></p>
      <!-- E-mail -->
      <label class="control-label" for="email">E-mail</label>
      <div class="controls">
        <input type="text" id="j_username" name="j_username" placeholder="" class="form-control input-xlarge">
       </div>
    </div>
 
    <div class="form-group">
      <!-- Password-->
      <label class="control-label" for="password">Password</label>
      <div class="controls">
        <input type="password" id="j_password" name="j_password" placeholder="" class="form-control input-xlarge">
       </div>
    </div>
    <div class="form-group">
      <label class="control-label"  for="submit"></label>
      <div class="controls">
        <input type="submit" id="submit" value="Sign In" name="submit" class="btn btn-success">
       </div>
    
          </div>
         
</form>
</div>
</div>