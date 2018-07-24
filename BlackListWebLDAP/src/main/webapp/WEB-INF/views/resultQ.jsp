<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Name</title>
<link rel="shortcut icon" type="image/png" href="resources/favicon.png"/>
<link rel="stylesheet" type="text/css" href="resources/style.css">

<link rel="stylesheet" href="resources/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="resources/css/animate.css" type="text/css" />
<link rel="stylesheet" href="resources/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="resources/css/font.css" type="text/css" />
<link rel="stylesheet" href="resources/css/app.css" type="text/css" />
<link rel="stylesheet" href="resources/js/datatables/datatables.css"
	type="text/css" />

<link rel="stylesheet" href="resources/js/select2/select2.css"
	type="text/css" />
<link rel="stylesheet" href="resources/js/select2/theme.css"
	type="text/css" />
<link rel="stylesheet" href="resources/js/slider/slider.css"
	type="text/css" />
  <link rel="stylesheet" href="resources/js/fuelux/fuelux.css" type="text/css"/>
	
<style>
       tr:hover td {
        background-color:#b9b7b7;
       }

</style>

	<script>
function carga() {
 setTimeout("document.getElementById('cargando').style. display = 'block'", 0);
}
function val() {
	var d=document.getElementById("Search");
	d.click();
	
	}

function loadDetail(status,queue){	
	window.location.href ="details?status="+status+"&queue="+queue+"&user="+user.value;
}

</script>
</head>
<body>
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
		<div style="float:left;width:200px"><img src="resources/logo.png" alt="Logo Itau"  /></div>
		<div style="float:right;"><img src="resources/itau2.png" alt="Logo Itau"  style="height:50px;margin-top:10px"/></div>
	</div>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
	   <div style="margin-left:86%">
	   Welcome : ${pageContext.request.userPrincipal.name} |
         <a
				href="javascript:formSubmit()"> Logout</a>  
				</div>
	</c:if>
	<div class="topnav">
		<a  href="search">Online Search</a> <a href="batch">Batch
			Search</a> <a href="resultQ" class="active">Result Queue</a>
	</div>
	<form action="resultQ" method="GET" class="form-inline" role="form">
		<div class="panel-body" >
		<fieldset class="fieldsetCriteria">
				<legend class="legendFieldset">Criteria</legend>
			<div class="form-group" style="width: 100%;">
				<div class="form-group" >
				<input type="hidden" name="user" value="${pageContext.request.userPrincipal.name}" id="user" />
				<label for="selectList" style="font-size: 17px;">Report</label>
				<select name="status"class="form-control m-b" id="selectList" style="width: 150px;margin-top: 13px;">
					<option value="Pending" ${(statusValue=="Pending")?"selected":""}>Pending</option>
					<option value="Complete" ${(statusValue=="Complete")?"selected":""}>Complete</option>
					<option value="Review" ${(statusValue=="Review")?"selected":""}>Review</option>
				</select>
				<label for="selectList" style="font-size: 17px;">Type</label>
				<select name="type"class="form-control m-b" id="selectType" style="width: 150px;margin-top: 13px;" >
					<option value="Batch" ${(typeValue=="Batch")?"selected":""}>Batch</option>
					<option value="Online" ${(typeValue=="Online")?"selected":""}>Online</option>
				</select>
				
				<button type="submit" name="Search" id="Search" 
					class="btn btn-rounded btn-sm btn-facebook" onclick="carga()"
					style="font-size: 15px; width: 120px; text-align: center; margin-left: 2em; border-radius: 6px;">Filter</button>
			</div>
			<c:if test="${cantRegistros!=0}">
					<div class="font-bold" style="float: right;margin-right: 20px;margin-top: 25px">Result:${cantRegistros}
				
				</div>
			</c:if>
			</div>
			</fieldset>
			<section  class="panel panel-default" style="border-radius: 2px;max-width: 1335px;">
				<div class="table-responsive">
					<table class="table m-b-none" style="border-radius: 2px;">
						<thead style="cursor:default !important;">
							<tr>
								<th class="th-sortable" data-toggle="class">Search ID</th>
								<th class="th-sortable" data-toggle="class">Date Processed</th>
								<th class="th-sortable" data-toggle="class">User</th>
								<th class="th-sortable" data-toggle="class">Name Search</th>
								<th class="th-sortable" data-toggle="class">List</th>
								<th class="th-sortable" data-toggle="class">List Date</th>
								<th class="th-sortable" data-toggle="class">Records</th>
								<th class="th-sortable" data-toggle="class">Score</th>
								<th class="th-sortable" data-toggle="class">Status</th>
								<th class="th-sortable" data-toggle="class">Nro Batch</th>
								<th class="th-sortable" data-toggle="class">Type Search</th>
							</tr>
						</thead>
						<tbody style="cursor:pointer; cursor: hand !important">
							<c:forEach items="${lqueue}" var="que">
								<tr onclick="loadDetail('${que.status}','${que.idSearch}')">								
									<td>${que.idSearch}</td>
									<td>${que.dateProcessed}</td>
									<td>${que.user}</td>
									<td><label input id="nsearch" name="nsearch"
										value="${que.nameSearch}">${que.nameSearch}</label></td>
									<td>${que.list}</td>
									<td>${que.listDate}</td>
									<td>${que.records}</td>
									<td>${que.score}%</td>
									<td>${que.status}</td>
									<td>${que.nroBatchSchedule}</td>
									<td>${que.typeSearch}</td>
								</tr>
							
							</c:forEach>
						</tbody>						                    
					</table>
				</div>
			</section>
			<c:if test="${cantRegistros>1}">
			<div style="margin-left:47%">
			<c:if test="${valorPage-1>0}">
				<button type="submit" name="pageMinus" id="Search" class="btn btn-rounded btn-sm btn-facebook" value="${valorPage-1}">
						Last
				</button>
			</c:if>
					${valorPage}
			<c:if test="${valorPage<valorMaximo}">
				<button type="submit" name="pagePlus" id="Search" class="btn btn-rounded btn-sm btn-facebook" value="${valorPage+1}">
						Next
				</button>
			</c:if>
			</div>
			</c:if>
			<div class="loader" id="cargando" style="display:none;z-index: 1; margin-left:43%"></div>
	</form>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
  <!-- Bootstrap -->
  <script src="resources/js/bootstrap.js"></script>
  <!-- App -->
  <script src="resources/js/app.js"></script>
  <script src="resources/js/app.plugin.js"></script>
  <script src="resources/js/slimscroll/jquery.slimscroll.min.js"></script>
  <!-- fuelux -->
<script src="resources/js/libs/underscore-min.js"></script>
<script src="resources/js/fuelux/fuelux.js"></script>
<script src="resources/js/fuelux/demo.datagrid.js"></script>
	
	
	
</body>
</html>