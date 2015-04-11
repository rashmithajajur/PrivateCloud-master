<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Rohan Bhanderi">

    <title>Private Cloud</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker3.min.css" />
	<link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
    body {
        padding-top: 50px;
        /* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
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

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/home">
						
					</a>
				</div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" ng-controller="navController">
                <ul class="nav navbar-nav">
					<li><a href="">Home</a></li> 
					<li><a href="">Login</a></li>
				  </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
	<!-- Page Content -->
    <div class="container">

<div class="row">	
<!-- content -->
<div class="col-sm-2"></div>
<div class="col-sm-8">
    <h2>Register</h2>
    <c:url var="signUpUrl" value="/signup" />
	<form:form role="form" action="${signUpUrl}" method="POST"  modelAttribute="user">
	<div class="form-group">
    	<label for="username">Username:</label>
        <input path="username"  id="username" type="text" name="username" value=""  class="form-control" 
        placeholder="Enter username">
    </div>
    <div class="form-group">
    	<label for="firstname">First Name:</label>
        <input path="fname" id="fname" type="text" name="fname" value="" class="form-control" placeholder="Enter First Name"
        pattern="^[a-zA-Z ,.'-]+$">
    </div>
    <div class="form-group">
    	<label for="lname">Last Name:</label>
        <input path="lname" id="lname" type="text" name="lname" value="" class="form-control" placeholder="Enter Last Name"
        pattern="^[a-zA-Z ,.'-]+$">
    </div>
    <div class="form-group">
    	<label for="email">Email:</label>
        <input path="email" id="email" type="email" name="email" value=""  class="form-control" 
        placeholder="Enter email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
    </div>
    <div class="form-group">
    	<label for="password">Password:</label>
        <input path="password" id="password" type="password" name="password" class="form-control" 
                placeholder="Enter Password" title="At least one number, one lowercase and one uppercase letter. Minimum 4 characters">
    </div>
    <div class="form-group">
    	<label for="validate_password">Validate Password:</label>
        <input id="validate_password" type="password" name="vpassword" class="form-control" placeholder="Enter Password again">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form:form>    
 </div>
 <div class="col-sm-2"></div>
<!-- End content -->
</div>

	<!-- Scripts -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>