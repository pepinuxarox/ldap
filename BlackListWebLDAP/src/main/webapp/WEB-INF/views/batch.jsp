<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="shortcut icon" type="image/png" href="resources/favicon.png"/>
	<title>Search Name</title>
 	<link rel="stylesheet" type="text/css" href="resources/style.css"/> 
	<link rel="stylesheet" href="resources/css/font-awesome.min.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/font.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/app.css" type="text/css" />	
	<!-- ojo -->
	<link rel="stylesheet" href="resources/css/bootstrap.css" />
	<link rel="stylesheet" href="resources/js/datepicker/datepicker.css" type="text/css" />
	<link rel="stylesheet" href="resources/jquery.datetimepicker.css">
	<style>
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}
/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 2px;
    border: 1px solid #888;
    width: 80%;
    text-align:center;
}
/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 30px;
    font-weight: bold;
}
.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
</style>
<script>
	function carga() {
				
		setTimeout(
				"document.getElementById('cargando').style. display = 'block'",	0);
	
	}
	function change(){
		var d=document.getElementById("selectSource").value;
		if(d!=='CUSTOMERS'){
			document.getElementById('range').style. display = 'block';
			}
		else{
			document.getElementById('range').style. display = 'none';
		}
	}	
</script>
<style>
       tr:hover td {
        background-color:#b9b7b7;
       }

</style>
</head>
<body style="background-color:transparent">
<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
	
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<div class="imgLogo">
		<div style="float: left; width: 200px">
			<img src="resources/logo.png" alt="Logo Itau" />
		</div>
		<div style="float: right;">
			<img src="resources/itau2.png" alt="Logo Itau" style="height: 50px; margin-top: 10px" />
		</div>
	</div>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
	   <div style="margin-left:86%">
	   Welcome : ${pageContext.request.userPrincipal.name} |
         <a
				href="javascript:formSubmit()"> Logout</a>  
				</div>
	</c:if>
	<div class="topnav">
		<a href="search">Online Search</a> 
		<a href="batch" class="active">Batch Search</a> 
		<a href="resultQ" >Result Queue</a>
	</div>
<form action="batch" method="GET" role="form" class="form-horizontal"> 
	<div class="col-sm-12">
         <div style="padding-top:15px;">
		 <fieldset class="fieldsetCriteria" style="color:#717171"> 
			<legend class="legendFieldset">Criteria</legend> 
                      <div style="padding-left: 30px;">                       
                       <div style="float:left;margin-top: 20px;">
                       <div class="form-group"  style="font-family:Arial;">
                          <label class="col-lg-2 control-label" style="font-size: 15px;">Source:</label>
                          <div class="col-lg-10">
                          	  <select name="lsource" class="form-control m-b" id="selectSource" style="width: 165px;height: 28px;margin-left: 6px" onchange="change()"> 
 								<c:forEach items="${sources}" var="source">
								<option ${(searchValueSource==source.nombre)?"selected":""} 
									value="${source.nombre}">${source.nombre}</option> 
								</c:forEach> 
							</select>
						  </div>
                       </div>
                       <div class="form-group">
                          <label class="col-lg-2 control-label" style="font-size: 15px;">List:</label>
                          <div class="col-lg-10">
                            <select name="llist" class="form-control m-b" id="selectList" style="width: 165px;height: 28px;margin-left: 6px"> 
								<c:forEach items="${listas}" var="list"> 
									<option ${(searchValueList==list.nombre)?"selected":""} 
										value="${list.nombre}/${list.fecha}">${list.nombre}  ${list.fecha}</option>
								</c:forEach> 
							</select> 
                          </div>
                        </div>
                       </div>
                       <div style="float:left;display: none;" id="range" >
                       <header class="font-bold"  style="text-align: center;">Date Range</header>
                       <div class="form-group">                       
                          <label class="col-lg-2 control-label" style="font-size: 15px;">From:</label>
                          <div class="col-lg-10">
                            	<input id="datepicker" style="width: 132px;" autocomplete="off" name="from" class="input-sm input-s datepicker-input form-control" size="16" type="text"  data-date-format="mm/dd/yyyy"
                            	placeholder="mm/dd/yyyy"   >
						  </div>
                        </div>
                       <div class="form-group" >
                          <label class="col-lg-2 control-label" style="margin-top:10px; font-size: 15px;">To:</label>
                          <div class="col-lg-10" style="margin-top:10px;">
                            <input autocomplete="off" style=" width: 132px;"   name="to" class="input-sm input-s datepicker-input form-control" size="16" type="text"  
                            data-date-format="mm/dd/yyyy" placeholder="mm/dd/yyyy"  >
                          </div>
                        </div>
                       </div>
                      <div style="float:left;">
                      <header class="font-bold" style="text-align: center;">Execute Batch</header>
                       <div class="form-group">
                          <label class="col-lg-2 control-label" style="font-size: 15px;">Date:</label>
                          <div class="col-lg-10">
                             <input id="date" name="date" style="width: 132px;" autocomplete="off" class="input-sm input-s datepicker-input form-control" size="16" type="text"
                               data-date-format="mm/dd/yyyy"  placeholder="mm/dd/yyyy" > 
						 	</div>
                        </div>
                        <div class="form-group" >
                          <label class="col-lg-2 control-label" style="margin-top:10px;font-size: 15px;">Time:</label>
                          <div class="col-lg-10" style="margin-top:10px;">
                            <div style="float:left">
                            <select name="hour" class="form-control m-b" id="selectHours" style="width: 85px;height: 28px;margin-left: 6px"> 
								<c:forEach items="${hours}" var="hour"> 
									<option ${(searchValueHour==hour)?"selected":""} 
										value="${hour}">${hour}</option> 
								</c:forEach> 
							</select> 
							</div>
                          </div>
                        </div>
                       <div style="float:left;display: none;" id="range" >
                        <header class="font-bold"  style="text-align: center;">Date Range</header>
                       <div class="form-group">                       
                          <label class="col-lg-2 control-label" style="font-size: 15px;">From:</label>
                          <div class="col-lg-10">
                            	<input style=" width: 165px;" autocomplete="off" name="from" class="input-sm input-s datepicker-input form-control" size="16" type="text"  data-date-format="dd-mm-yyyy" placeholder="dd-mm-yyyy" >
						  </div>
                        </div>
                        <div class="form-group" >
                          <label class="col-lg-2 control-label" style="margin-top:10px; font-size: 15px;">To:</label>
                          <div class="col-lg-10" style="margin-top:10px;">
                            <input autocomplete="off" style=" width: 165px;"  name="to" class="input-sm input-s datepicker-input form-control" size="16" type="text"  data-date-format="dd-mm-yyyy" placeholder="dd-mm-yyyy" >
                          </div>
                        </div>
                       </div>                        
                       </div>                       
                       <div style="float:left;margin-left:10px;">
                       <div class="form-group"  style="font-family:Arial;">
                          <label class="col-lg-2 control-label" style="font-size: 15px;margin-top: 17px">Score:</label>
                          <div class="col-lg-10">
                            <select name="score" class="form-control m-b" id="selectScore"
							style="margin-top: 17px; width: 82px;height: 28px;margin-left: 11px;">
							<c:forEach items="${scores}" var="score">
								<option ${(searchValueScore==score.idNombre)?"selected":""}
									value="${score.idNombre}">${score.valor}</option>
							</c:forEach>
							</select>
						  </div>
                        </div>
                        <div class="form-group">
                          <div class="col-lg-10">
                          <input type="hidden" name="user" value="${pageContext.request.userPrincipal.name}" id="user" />
                             <button id="search" style="font-size: 15px; width: 120px; text-align: center; margin-left: 6px; border-radius: 6px;"
								type="submit" name="Search" class="btn btn-rounded btn-sm btn-facebook" value="Search" onclick="carga()">Schedule</button>
                        </div>                      
                        </div>
                       </div>
                      </div>
         </fieldset>	
		 </div>
     </div>
<section style="border-radius: 2px; max-width: 1335px;">
				<div class="table-responsive">
					<table class="table m-b-none" style="border-radius: 2px;color:#717171;margin-left:14px;">
						<thead style="background-color:white;cursor:default !important;">
							<tr>
								<th class="th-sortable" data-toggle="class">Created</th>
								<th class="th-sortable" data-toggle="class">User</th>
								<th class="th-sortable" data-toggle="class">Source</th>
								<th class="th-sortable" data-toggle="class">List</th>
								<th class="th-sortable" data-toggle="class">List Updated</th>
								<th class="th-sortable" data-toggle="class">From</th>
								<th class="th-sortable" data-toggle="class">To</th>
								<th class="th-sortable" data-toggle="class">Date</th>
								<th class="th-sortable" data-toggle="class">Time</th>
								<th class="th-sortable" data-toggle="class">Score</th>
								<th class="th-sortable" data-toggle="class">Action</th>
							</tr>
						</thead>
						<tbody style="cursor:pointer; cursor: hand !important">
							<c:forEach items="${lSchedules}" var="lSchedule">
								<tr>
									<td>${lSchedule.created}</td>
									<td>${lSchedule.user}</td>
									<td>${lSchedule.source}</td>
									<td>${lSchedule.list}</td>
									<td>${lSchedule.listUpdated}</td>
									<td>${lSchedule.from}</td>
									<td>${lSchedule.to}</td>
									<td>${lSchedule.date}</td>
									<td>${lSchedule.time}</td>
									<td>${lSchedule.score}%</td>
									<td>
								<button id="borrar_${lSchedule.nro}" type="submit" name="delete" onclick="carga()"
									value="${lSchedule.nro}" class="btn btn-rounded btn-sm btn-facebook" style="font-size: 12px; border-radius: 6px; margin-left: -5px">Delete</button>
							</td>									
								</tr>
							</c:forEach>
						
						</tbody>
					</table>
				</div>
			</section>
			</form> 
			<c:if test="${message!=''}">					
				<div id="myModal" style="margin-top: -250px; display: hidden;">
				  <!-- Modal content -->
				  <div class="modal-content" >
						    <span class="close">&times;</span>
						    <p>${message}</p>
						  </div>
						
						</div>
						<script>
							document.getElementById('myModal').style. display = 'block';
						</script>
			</c:if>
<div class="loader" id="cargando" style="display: none; z-index: 1; margin-left: 43%"></div>	
<script>
var modal = document.getElementById('myModal');
var span = document.getElementsByClassName("close")[0];
span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>	
	<!-- Bootstrap -->
  	<script src="resources/js/bootstrap.js"></script>
  	<!-- App -->
  	<script src="resources/js/app.js"></script>
  	<script src="resources/js/app.plugin.js"></script>
  	<script src="resources/js/slimscroll/jquery.slimscroll.min.js"></script>
  	<!-- datepicker -->
	<script src="resources/js/datepicker/bootstrap-datepicker.js"></script>
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>	
</body>
</html>