<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Private cloud management web gui">
    <meta name="author" content="Rohan Bhanderi">

    <title>Private Cloud</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker3.min.css" />

	<link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/signin.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
    body {
		padding-top: 40px;
		background: transparent;
		/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
	}	
	.panel {
		background-color: rgba(255, 255, 255, 0.9);
	}    
	
	.margin-base-vertical {
		margin: auto;
	}
    </style>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	 
	<!-- Page Content -->
    <div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 text-center panel panel-default">
			<form class="form-signin" action="<c:url value='/j_spring_security_check' />" method='POST'>
				<h1 class="form-signin-heading margin-base-vertical" >Please sign in</h1>
				<label for="username" class="sr-only">Username</label>
				<input type="text" id="username" name="username" class="form-control" placeholder="Username" value="${username}" required autofocus >
				<label for="password" class="sr-only">Password</label>
				<input type="password" id="password"  name="password" class="form-control" placeholder="Password" required>
				<div class="checkbox">
				  <label>
					<input type="checkbox" value="remember-me"> Remember me
				  </label>
				</div>
				<c:if test="${not empty error}">
					<div class="alert bg-danger alert-error alert-dismissible">  
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					  <strong>${error}</strong>  
					</div>  
				</c:if>
				<c:if test="${not empty msg}">
					<div class="alert alert-success bg-success alert-dismissible">  
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					  <strong>${msg}</strong>  
					</div>
				</c:if>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			  </form>
				<p><a href="<c:url value='/signup' />" >Register</a></p>
			</div>
		</div>
	</div>
	
	<!-- Scripts -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>