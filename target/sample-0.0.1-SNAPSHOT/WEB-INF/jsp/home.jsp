<%@include file="includes/header.jsp" %>
	<!--<spring:message code="hello" />-->
	<div class="page-header-custom">
		<h1>Welcome to Insurance.bug <small>We are totally legit</small></h1>
	</div>
	<div id="home-jumbotron" class="jumbotron">
		  <h1 class="col-md-offset-1 col-md-10">Apply Now!</h1>
		  <p class="col-md-offset-1 col-md-10">If you are an active or new employee for Insurance.bug, select your state to apply for our bonus program!</p>
		  <p class="col-md-offset-1">
			<div class="dropdown col-md-offset-1">
				  <button class="btn-lg btn-custom dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				    Select a State
		 					<span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
		            <li><a href="/apply#Washington">WA</a></li>
		            <li><a href="/apply#Oregon">OR</a></li>
		            <li><a href="/apply#California">CA</a></li>
				</ul>
			</div>
			</p>
	</div>
	<div class="col-md-6 col-md-offset-3">
	<div class="panel panel-custom">
		<div class="panel-body">
			<a class="btn btn-custom btn-lg btn-block" href="/comments" role="button">Messages</a>
			<a class="btn btn-custom btn-lg btn-block" href="/search" role="button">Employee Search</a>
		</div>
	</div>
	</div>
<%@include file="includes/footer.jsp" %>
		