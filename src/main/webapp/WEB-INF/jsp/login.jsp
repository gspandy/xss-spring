<%@include file="includes/header.jsp" %>
	<div class="col-md-offset-4 col-md-4">
	<div class="panel panel-custom">

	<div class="panel-heading">
		<h3 class="panel-title">Please sign in</h3>
	</div>
	
	<div class="panel-body">
	
		<c:if test="${param.error != null}">
		    <div class="alert alert-danger">
		        Invalid username and password.
		    </div>
	    </c:if>
	    
	    <c:if test="${param.logout != null}">
		    <div class="alert alert-danger">
		        You have been logged out.
		    </div>
	    </c:if>
	
		<form:form class="form-custom" role="form" method="post">
		
			<div class="form-group">
				<label for="username">Email address</label>
				<input id="username" name="username" type="email" class="form-control" placeholder="Enter email" />
			</div>
			
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" />
				<form:errors cssClass="error" path="password" />
			</div>
			
			<div class="form-group">
				<div class="checkbox">
					<label>
						<input name="_spring_security_remember_me" type="checkbox" >Remember Me
					</label>
				</div>
			</div>
			
			<button type="submit" class="btn btn-custom">Sign In</button>
			<a class="btn btn-custom" href="/forgot-password">Forgot Password</a>
			
		</form:form>
	</div>
	</div>
</div>

<%@include file="includes/footer.jsp" %>