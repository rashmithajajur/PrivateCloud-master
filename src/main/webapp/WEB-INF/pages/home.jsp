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
            <c:url value="/log" var="logUrl" />
             
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" ng-controller="navController">
                <ul class="nav navbar-nav">
					<li><a href="${homeUrl} ">Home</a></li> 
					<li><a href="${logoutUrl}">Logout</a></li>
					<li><a href="${logUrl}">Set Alarm</a></li>
					
				  </ul>
            </div>
            
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
	<!-- Page Content -->
    <div class="container">

<div class="row">	
<!-- Start Main Content -->
<div class="brand col-sm-6">
	<div class="welcome">
		<c:choose>
		    <c:when test="${pageContext.request.userPrincipal.name != null}">
		       <h1>Welcome ${pageContext.request.userPrincipal.name}</h1>
		    </c:when>
		    <c:otherwise>
		       <h1>Welcome,user</h1>
		    </c:otherwise>
		</c:choose>
		
		<div class="container">
  <h2></h2>
      <a  href="<c:url value='/createVM' />" class="btn btn-info" role="button">Create VM</a>
      <a  href="<c:url value='/stats' />" class="btn btn-info" role="button">VM Statistics</a>
 
</div>
		 			  
		<div class="table">
 
  <div class="container">
      <h3>Last of all Virtual Machines</h3>
      <div class="table-responsive">          
      <table class="table table-striped">
        <thead>
          <tr>
          <th>ID</th>
          <th>VM's</th>
        <th>STATUS</th>
        </tr>
        </thead>
        <tbody>
       		<c:set var="count" value="1" scope="page" />
            <c:forEach items="${vms}" var="element"> 
           	<tr class= "sucess">
			    <td>${count}</td>
			    <td>${element.vmname}</td>
			     <c:choose>
				      <c:when test="${element.isPowerOn==true}">
				      	<td><button type="button" class="btn btn-warning" id="${element.vmname}" onclick="powerOff(this)">Power Off</button></td>
				      </c:when>
				
				      <c:otherwise>
				      	<td><button type="button" class="btn btn-success" id="${element.vmname}" onclick="powerOn(this)">Power On</button>
				      	<button type="button" class="btn btn-danger" id="id_${element.vmname}" onclick="destroyVM(this)">Destroy VM</button>
				      	
				      	
				      	</td>
				      </c:otherwise>
				</c:choose>	
				
			  </tr>
			<c:set var="count" value="${count + 1}" scope="page"/>
			</c:forEach>
		
        </tbody>
         
		</table>
</div>
        	
	    </div>
  </div>

    </div>
</div>
<!-- End Main Content -->
</div>
</div>
	<!-- Scripts -->
	<script type="text/javascript">
		function getContextPath() {
		   return "${pageContext.request.contextPath}";
		}
	</script>
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.blockUI.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/vmpower.js"></script>
</body>
</html>