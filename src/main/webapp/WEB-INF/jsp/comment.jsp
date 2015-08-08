<%@include file="includes/header.jsp" %>
	<div class="page-header-custom">
		<h1>Message Center <small>Submit questions and feedback</small></h1>
	</div>
	<div id="comment-form" class="col-md-4">
	<div class="panel panel-custom">
	<div class="panel-heading">
		<h3 class="panel-title">Please sign in</h3>
	</div>
	<div class="panel-body">
		<form:form class="form-custom" role="form" method="post">
		
			<div class="form-group">
				<input id="comment" name="comment" type="comment" class="form-control" placeholder="Add Comment"/>
			</div>
			
			
			<button type="submit" class="btn btn-custom">Add Comment</button>
			
			
		</form:form>
	</div>
	</div>
	</div><!-- end add comment column -->
		<div class="col-md-8 col-md-offset-4">
				<c:forEach var="comment" items="${comments}">
				<div class="panel panel-custom">
					<div class="panel-heading">Posted By: ${comment.name}</div>
					<div class="panel-body">${comment.comment}</div>
				</div>
					<br/>
				
				</c:forEach>
			</div>
<%@include file="includes/footer.jsp" %>