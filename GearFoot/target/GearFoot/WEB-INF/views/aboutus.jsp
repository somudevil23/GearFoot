<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="images" value="/resources/images" />

<div class="container">
	<div class="row">
		<div class="col-sm-5">
			<img src="${images}/gf.png" class="img-fluid" width="450px"
				height="400px">

		</div>
		<div class="col-sm-7">
			<h2>About Us</h2>
			<p text="justify">As our company has grown and our software has evolved,
			 our focus has remained unchanged since the inception of GearFoot.
			  We provide effective online tools to help businesses small and large achieve success online.
			   This, combined with our desire for excellence in customer service, has allowed us to help our
			    customers grow their businesses from small start-ups to multi-million dollar enterprises time and time again.</p>
				 
		</div>
	</div>
</div>
