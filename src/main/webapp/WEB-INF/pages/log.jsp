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

</head>

<body>
    <!-- Navigation -->
    
	
    <nav class="navbar navbar-inverse navbar-fixed-top">
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
          
            <c:url value="/home" var="homeUrl" />
            <c:url value="/logout" var="logoutUrl" />
            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" ng-controller="navController">
                <ul class="nav navbar-nav">
					<li><a href="${homeUrl} ">Home</a></li> 
					<li><a href="${logoutUrl} ">Logout</a></li> 
				  </ul>
            </div>
             </div>          <!-- /.navbar-collapse -->
       
        <!-- /.container -->
    </nav>
<div class="container">
<div class="brand col-sm-13">
<div class="table">
  <div class="container">
      <h3>Cloud Watch Alarm</h3>
      <p>	Cloud Watch Alarm.  </p>
	<p>	Here you can set an alarm for particular VM. So when the usage reaches particular Threshold level. you will be getting a notification. </p>
      
<!--       <div class="table-responsive">           -->
<div class="row">	
<!-- content -->
<div class="col-sm-2"></div>
<div class="col-sm-8">
 <c:url value="/logout" var="logoutUrl" />
            <c:url value="/home" var="homeUrl" />
<%--     <c:url var="logurl" value="/log" /> --%>
<%--     <c:url value="/getAlarmPage" var="alaUrl" /> --%>
<%--              <c:url value="/getVMAlarmThreshold" var="alUrl" /> --%>
<%--               <c:url value="/setVMAlarmThreshold" var="salUrl" /> --%>
<%--               <c:url value="/getVmAlarmStatus" var="salUrl" /> --%>
	<form:form role="form" action="${logurl}" method="POST"  modelAttribute="alarm" id="alarmset"  >

<div class="control-group" id="unamegroup">
    	<label for="vm_id">VM ID:</label>
    	<select name="vmId" id="vm_id">
    	<c:forEach items="${vms}" var="element"> 
		  	<option value="${element.id}">${element.vmname}</option>
		</c:forEach>
		</select>
       </div>
       
       <div class="control-group" id="unamegroup">
    	<label for="property_id">Parameter:</label>
    	<select name="propertyId" id="property_id">
    	<c:forEach items="${logs}" var="element"> 
		  	<option value="${element.paramsId}">${element.name}</option>
		</c:forEach>
		</select>
       </div>
<div class="control-group" id="unamegroup">
    	<label for="threshold_value">Threshold Value</label>
        <input path="threshold_value"  id="threshold_value" type="text" name="thresholdValue" value=""  class="form-control" 
        placeholder="Enter username" required="required">
<!--         <span id="unamemsg"></span> -->
    </div>
    <div class="control-group" id="unamegroup">
    	<label for="limit_exceed">limit_exceed</label>
        <input path="limit_exceed"  id="limit_exceed" type="text" name="limitExceed" value=""  class="form-control" 
        placeholder="Enter limit_exceed" required="required">
<!--         <span id="unamemsg"></span> -->
    </div>
 
 <br>
 <br>
     <button type="submit" class="btn btn-default">Set Alarm</button>
  </form:form>   
  
        			
<!--         		<a>http://localhost:5601/#/dashboard/Log-dashboard?_g=()&_a=(filters:!(),panels:!((col:1,id:log-cpu,row:1,size_x:3,size_y:2,type:visualization),(col:4,id:log-diskread,row:1,size_x:5,size_y:4,type:visualization),(col:7,id:log-diskwrite,row:5,size_x:3,size_y:2,type:visualization),(col:10,id:log-memory,row:1,size_x:3,size_y:2,type:visualization),(col:1,id:Memory,row:3,size_x:3,size_y:2,type:visualization),(col:4,id:Memory-Line,row:5,size_x:3,size_y:2,type:visualization),(col:7,id:Area-CPU,row:7,size_x:6,size_y:7,type:visualization),(col:10,id:log-net,row:14,size_x:3,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'*')),title:'Log%20dashboard') </a>	 -->
	    </div>
  </div>
   </div>
   </div>
   </div>
   
 </div>
<!--  </div> -->
  <br>
 <br>
 <iframe  width="1350" height="900" src="http://localhost:5601/#/dashboard/Log-dashboard?_g=()&_a=(filters:!(),panels:!((col:1,id:log-cpu,row:1,size_x:3,size_y:2,type:visualization),(col:4,id:log-diskread,row:3,size_x:4,size_y:2,type:visualization),(col:7,id:log-diskwrite,row:5,size_x:3,size_y:2,type:visualization),(col:4,id:log-memory,row:1,size_x:3,size_y:2,type:visualization),(col:1,id:Memory,row:3,size_x:3,size_y:2,type:visualization),(col:4,id:Memory-Line,row:5,size_x:3,size_y:2,type:visualization),(col:7,id:Area-CPU,row:1,size_x:5,size_y:2,type:visualization),(col:8,id:log-net,row:3,size_x:3,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'*')),title:'Log%20dashboard')">
<!-- <p>Your browser does not support iframes.</p> -->
</iframe>
	<!-- Scripts -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>