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
					<li><a href="${homeUrl}">Home</a></li> 
					<li><a href="${logoutUrl}">Logout</a></li>
				  </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
	<!-- Page Content -->
    <div class="container">

<div class="row">	
<br>
<br>
Start Main Content
<%-- <form action="/output/" name="InputCacheCheck" method="post"> --%>
<!-- <div class="input-prepend input-append"> -->
<!-- 	<div class="btn-group"> -->
		
<!-- 		<button class="btn dropdown-toggle" name="os" data-toggle="dropdown">Operating System -->
<!-- 		<span class="caret"></span></button> -->
<!-- 		<ul class="dropdown-menu"> -->
<!-- 		<li><a href="#">Windows</a></li> -->
<!-- 		<li><a href="#">Ubuntu</a></li> -->
<!-- 		</ul> -->

<!--     </div> -->
<!-- </div> -->
</form>


<!-- <div class="btn-group"> -->
<!--     <button type="button" class="form-control btn btn-default dropdown-toggle" data-toggle="dropdown"> -->
<!--         Select Operating System <span class="caret"></span> -->
<!--     </button> -->
<!--     <ul class="dropdown-menu" role="menu"> -->
<!--         <li><a href="#">Windows</a></li> -->
<!--         <li><a href="#">Linux</a></li> -->
<!--         <li><a href="#">Ubuntu</a></li> -->
<!--     </ul> -->
<!-- </div> -->

<!-- <h3> Select Operating System</h3> -->
<!-- <div class="radio"> -->
<!--   <label><input type="radio" name="optradio">Windows</label> -->
<!-- </div> -->
<!-- <div class="radio"> -->
<!--   <label><input type="radio" name="optradio">Ubuntu</label> -->
<!-- </div> -->

<%-- <c:url var="crs" value="/crvm"/> --%>

<!-- <div class="container"> -->
<!--   <h2>Form control: inline radio buttons</h2> -->
<!--   <p>The form below contains three inline radio buttons:</p> -->
<%--   <form role="form"> --%>
<!--     <label class="radio-inline"> -->
<!--       <input type="radio" name="optradio">Option 1 -->
<!--     </label> -->
<!--     <label class="radio-inline"> -->
<!--       <input type="radio" name="optradio">Option 2 -->
<!--     </label> -->
<!--     <label class="radio-inline"> -->
<!--       <input type="radio" name="optradio">Option 3 -->
<!--     </label> -->
<%--   </form> --%>
<!-- </div> -->
<c:url var="vm" value="/vm"/>
  
    	<form:form role="form" action="${vm}" method="POST"  modelAttribute="vm">
	<div class="form-group">
	
	 <label class="radio-inline">
      <input path="os" value="win" type="radio" name="os" id="os">Windows
    </label>
    <label class="radio-inline">
      <input path="os" value="ubu"  type="radio" name="os" id="os">Ubuntu
    </label> 
    
<!-- 	<label for="Id">Id</label> -->
        <!-- <input path="vm"  id="vmname" type="text" name="vmname" value=""  class="form-control" 
        placeholder="Enter VM Name"> -->
        <br>
        <br>
       
    	<label for="vmname">VM Name:
        <input path="vmname"  id="vmname" type="text" name="vmname" value=""  class="form-control" 
        placeholder="Enter VM Name">        
        </label>
        <br>
        <label for="vmname">VM status:
        <input path="stat"  id="stat" type="text" name="stat" value=""  class="form-control" 
        placeholder="Enter Select">
        </label>
    </div>
    <button type="submit">Add VM </button>
    
</form:form>

<!-- End Main Content -->
</div>
</div>
	<!-- Scripts -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>