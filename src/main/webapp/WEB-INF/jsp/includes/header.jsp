<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="org.owasp.encoder.Encoder" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<%@ page import="org.owasp.encoder.Encode" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>The Insurance Bug</title>
		
		<!-- Bootstrap -->
		<link href="/public/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet"/>
		<!--  my CSS -->
		<link href="/public/css/styles.css" rel="stylesheet"/>
		
		
	</head>
	<body>
		<div>
			<div id="custom-bootstrap-menu" class="navbar navbar-default " role="navigation">
    <div class="container-fluid">
        <div class="navbar-header"><a class="navbar-brand" href="/">Insurance.bug</a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-menubuilder"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse navbar-menubuilder">
            <ul class="nav navbar-nav navbar-left">
                <li><a href="/">Home</a>
                </li>
                <li><a href="/comments">Messages</a>
                </li>
                <li><a href="/apply">Application</a>
                </li>
                <c:if test="${secure != null}">
	                <li><form id="toggle-button" method="get"><input class="btn ${secure}" name="toggle" value="${secure}" type="submit"/></form>
	                </li>
	            </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
				      <sec:authorize access="isAnonymous()"><!-- Means unauthenticated user -->
				        <li><a href="<c:url value="/signup"/>">Sign up</a></li>
				        <li><a href="<c:url value="/login"/>">Sign in <span class="glyphicon glyphicon-log-in"></span></a></li>
				      </sec:authorize>
			        
				      <sec:authorize access="isAuthenticated()">
				      	 <li class="dropdown">
	                  		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                      		<span class="glyphicon glyphicon-user"></span>
	                      		<sec:authentication property="principal.user.name" /> <b class="caret"></b>
	                  		</a>
	                  		<ul class="dropdown-menu">
		                     	<li><a href="/users/<sec:authentication property='principal.user.id' />"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
		                     	<li>
			                    	<c:url var="logoutUrl" value="/logout" />
				               		<form:form	id="logoutForm" action="${logoutUrl}" method="post">
							    	</form:form>
							    	<a href="#" onclick="document.getElementById('logoutForm').submit()"><span class="glyphicon glyphicon-log-out"></span> Sign out</a>
		                     	</li>
	                  		</ul>
	              		</li>
				      </sec:authorize>  
			      </ul>
        </div>
    </div>
</div>
			
		<sec:authorize access="hasRole('ROLE_UNVERIFIED')">
			<div class="alert alert-warning alert-dismissable">
		  		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		  		Your email id is unverified. <a href="/users/resend-verification-mail">Click here</a> to get the verification mail again.
			</div>
    	</sec:authorize>
    	
		<c:if test="${not empty flashMessage}">
			<div class="alert alert-${flashKind} alert-dismissable">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				${flashMessage}
			</div>
		</c:if>