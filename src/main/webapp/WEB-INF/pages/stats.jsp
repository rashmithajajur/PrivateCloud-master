<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="true"%>
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
<!-- 					<a class="navbar-brand" href="/home"> -->
						
<!-- 					</a> -->
				</div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <c:url value="/logout" var="logoutUrl" />
            <c:url value="/home" var="homeUrl" />
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" ng-controller="navController">
                <ul class="nav navbar-nav">
					<li><a href="${homeUrl} ">Home</a></li> 
					<li><a href="${logoutUrl}">Logout</a></li>
				  </ul>
            </div>
            </nav>
             </div>

	
            <!-- /.navbar-collapse -->
       
        <!-- /.container -->
    </nav>
	<!-- Page Content -->
<%--     	<form:form role="form" action="${shst}" method="POST"  modelAttribute="status"> --%>
<!-- 	<div class="form-group"> -->
	
<!-- 	 <button type="submit">Status</button> -->
<c:url var="shst" value="/stats"/>

<div class="container">
<div class="row">

<div class="brand col-sm-13">

<div class="table">
 
  <div class="container">
      <h3>Statistics of VM</h3>
      <div class="table-responsive">          
      <table class="table table-striped">
        <thead>
          <tr>
          <th>VM name</th>
        <th> CPU</th>
        <th>MEMORY</th>
        <th>IP ADDRESS</th>
        </tr>
        </thead>
        <tbody>
        <tr class= "sucess">
           <tr>   
     
			<c:forEach items="${sta}" var="element">
			<tr>
				<td>${element.vmName}</td>
				<td>${element.cpu}</td>
				<td>${element.mem}</td>
				<td>${element.ip}</td>
			</tr>
			</c:forEach>
			
        </tbody>
         
		</table>
		</div>
        			
	    </div>

  </div>
   </div>
   </div>
   </div>
 
	<!-- Scripts -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>