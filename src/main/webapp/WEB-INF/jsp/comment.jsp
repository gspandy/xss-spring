<%@include file="includes/header.jsp" %>
	<div class="page-header-custom">
		<h1>Message Center <small>Submit questions and feedback</small></h1>
	</div>
	<div id="comment-form" class="col-md-4">
	<div class="panel panel-custom">
	<div class="panel-heading">
		<h3 class="panel-title">Submit questions or feedback here!</h3>
	</div>
	<div class="panel-body">
		<form:form class="form-custom" modelAttribute="commentForm" action="?${secure}" role="form" method="post">
			<form:errors />
			<div class="form-group">
				<input id="comment" name="comment" class="form-control" placeholder="Add Comment"/>
			</div>
			
			<button type="submit" class="btn btn-custom">Add Comment</button>
			<form:errors cssClass="error" path="comment"/>
		</form:form>
	</div>
	</div>
	</div><!-- end add comment column -->
		<div class="col-md-8 col-md-offset-4">
			<c:choose>
				<c:when test="${secure == 'Secure'}">
					<c:forEach var="comment" items="${comments}">
						<div class="panel panel-custom">
							<div class="panel-heading"><e:forHtml value="Posted By: ${comment.name}"/></div>
							<div class="panel-body"><e:forHtml value="${comment.comment}"/></div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="comment" items="${comments}">
						<div class="panel panel-custom">
							<div class="panel-heading">Posted By: ${comment.name}</div>
							<div class="panel-body">${comment.comment}</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
				
					<br/>
				
				
			</div>
<%@include file="includes/footer.jsp" %>