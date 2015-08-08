<%@include file="includes/header.jsp" %>
	
	<div class="page-header-custom">
		<h1>
		<script>
			if(window.location.hash){
				var state = window.location.hash.replace("#",'');
				if(state === "Washington" || state === "Oregon" || state === "California")
					document.write("Welcome to the " + encodeURI(state) + " application portal");
			}else{
				document.write("Welcome to the application portal!");
			}
		</script>
		<small> Where PII goes to die</small></h1>
	</div>
	
	<div class="col-md-6 col-md-offset-3">
	<div class="panel panel-custom">

		<div class="panel-heading">
			<h3 class="panel-title">Please Enter Registration Details</h3>
		</div>
	
	<div class="panel-body">
	
		<form:form class="form-custom" modelAttribute="applicantForm" role="form">
			<form:errors />
			<div class="col-md-6">
				<div class="form-group">
					<form:label path="firstName">First Name</form:label>
					<form:input path="firstName" type="firstName" class="form-control" placeholder="Ryan" value="Ryan"/>
					<form:errors cssClass="error" path="firstName"/>
					<p class="help-block">Enter your first name</p>
				</div>
				<div class="form-group">
					<form:label path="address">Address</form:label>
					<form:input path="address" class="form-control" placeholder="Address" value="123 Fake St" />
					<form:errors cssClass="error" path="address"/>
					<p class="help-block">Enter your address</p>
				</div>
				<div class="form-group">
					<form:label path="birthDate">Date of Birth</form:label>
					<form:input path="birthDate" class="form-control" placeholder="1/1/1990" value="01/01/1990"/>
					<form:errors cssClass="error" path="birthDate"/>
					<p class="help-block">Enter your date of birth</p>
				</div>
				<div class="form-group">
					<form:label path="gender">Gender</form:label>
					<form:input path="gender" class="form-control" placeholder="Gender" value="male"/>
					<form:errors cssClass="error" path="gender"/>
					<p class="help-block">Enter your gender</p>
				</div>
				<div class="form-group">
					<form:label path="ssn">Social Security Number</form:label>
					<form:input path="ssn" class="form-control" placeholder="___-__-____" value="1235666" />
					<form:errors cssClass="error" path="ssn"/>
					<p class="help-block">Enter your gender</p>
				</div>

				<button type="submit" class="btn btn-custom">Submit</button>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<form:label path="lastName">Last Name</form:label>
					<form:input path="lastName" class="form-control" placeholder="Perrizo" value="Hornwinkle" />
					<form:errors cssClass="error" path="lastName"/>
					<p class="help-block">Enter your last name</p>
				</div>
				<div class="form-group">
					<form:label path="state">State</form:label>
					<form:input path="state" class="form-control" placeholder="State" value = "WA" />
					<form:errors cssClass="error" path="state"/>
					<p class="help-block">Enter your state</p>
				</div>
				<div class="form-group">
					<form:label path="zip">Zip Code</form:label>
					<form:input path="zip" class="form-control" placeholder="Zip Code" value = "99203"/>
					<form:errors cssClass="error" path="zip"/>
					<p class="help-block">Enter your zip code</p>
				</div>
				<div class="form-group">
					<form:label path="city">City</form:label>
					<form:input path="city" class="form-control" placeholder="Spokane" value = "Spokane"/>
					<form:errors cssClass="error" path="city"/>
					<p class="help-block">Enter your city</p>
				</div>
				<div class="form-group">
					<form:label path="darkestSecret">Darkest Secrets</form:label>
					<form:input path="darkestSecret" class="form-control" placeholder="Evilllll" value="I ded Evul"/>
					<form:errors cssClass="error" path="darkestSecret"/>
					<p class="help-block">Enter the darkness!</p>
				</div>
				
			</div>
			
			
			
		</form:form>
	</div>
	</div>
</div>
<%@include file="includes/footer.jsp" %>