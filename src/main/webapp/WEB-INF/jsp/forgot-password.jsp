<%@include file="includes/header.jsp"%>
<div class="col-md-offset-4 col-md-4">
<div class="panel panel-custom">
	<div class="panel-heading">
		<h3 class="panel-title">forgot password?</h3>
	</div>
	
	<div class="panel-body">
		<form:form modelAttribute="forgotPasswordForm" class="form-custom" role="form">
			<form:errors/>
			
			<div class="form-group">
				<form:label path="email">Email Address</form:label>
				<form:input path="email" type="email" class="form-control" placeholder="Email"/>
				<form:errors cssClass="errors" path="email"/>
				<p class="help-block">Please enter your email id</p>
			</div>
			<button type="submit" class="btn btn-custom">Reset Password</button>
		</form:form>
	</div>
</div>
</div>

<%@include file ="includes/footer.jsp"%>