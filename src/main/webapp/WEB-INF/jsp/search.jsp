<%@include file="includes/header.jsp" %>

     	<form:form modelAttribute="searchForm" class="form-custom form-inline" role="form">
			<form:errors />
			<div class="form-group">
				<form:label path="search">Search</form:label>
				<form:input path="search" id="searchText" type="search" class="form-control" placeholder="Search for employee" />
				<button type="submit" class="btn btn-custom">Submit</button>
				<form:errors cssClass="error" path="search"/>
			</div>
			
		</form:form>
		<!-- End Nav Bar -->
		
		<!-- Display the search table here -->
		<div class="page-header-custom">
		  <h1><small>Search For: </small> <e:forHtml value="${searchText}" /> </h1>
		  <!-- <h1><small>Search For: </small> ${searchForm.search}</h1>-->
		</div>
		<div class="col-md-10 col-md-offset-1">
     	<div class="panel panel-custom">
     		<table class="table table-striped">
		      <thead>
		        <tr>
		          <th>Id</th>
		          <th>First Name</th>
		          <th>Last Name</th>
		          <th>Username</th>
		        </tr>
		      </thead>
				<tbody>
					<c:forEach var="applicant" items="${applicants}">
						<tr>
							<td><e:forHtml value="${applicant.id}"/></td>
							<td><e:forHtml value="${applicant.firstName}"/></td>
							<td><e:forHtml value="${applicant.lastName}"/></td>
							<td><e:forHtml value="${applicant.email}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</div>
<%@include file="includes/footer.jsp" %>