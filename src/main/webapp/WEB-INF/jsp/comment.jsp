<%@include file="includes/header.jsp" %>

		<form:form role="form" method="post">
		
			<div class="form-group">
				<label for="comment">Comment</label>
				<input id="comment" name="comment" type="comment" class="form-control" placeholder="Add Comment" />
				<p class="help-block">Enter a comment</p>
			</div>
			
			
			<button type="submit" class="btn btn-primary">Add Comment</button>
			
			
		</form:form>
		<div class="panel panel-info">
				<c:forEach var="comment" items="${comments}">
				<div class="panel-heading">Posted By: ${comment.name}</div>
				<div class="panel-body">${comment.comment}</div>
					<br/>
				</c:forEach>
			</div>
<%@include file="includes/footer.jsp" %>