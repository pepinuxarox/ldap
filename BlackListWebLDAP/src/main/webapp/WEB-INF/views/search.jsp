<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<html>
<head>
<meta charset="UTF-8">
<title>Search Name</title>
<link rel="shortcut icon" type="image/png" href="resources/favicon.png"/>
<link rel="stylesheet" type="text/css" href="resources/style.css">

<!-- ojo -->
<link rel="stylesheet" href="resources/css/bootstrap.css" />
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
<link rel="stylesheet" href="resources/js/fuelux/fuelux.css" type="text/css"/>
<link rel="stylesheet" href="resources/js/slider/slider.css"
	type="text/css" />

<script>
	function carga() {
		setTimeout(
				"document.getElementById('cargando').style. display = 'block'",	0);
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
		<div style="float: left; width: 200px">
			<img src="resources/logo.png" alt="Logo Itau" />
		</div>
		<div style="float: right;">
			<img src="resources/itau2.png" alt="Logo Itau"
				style="height: 50px; margin-top: 10px" />
		</div>
	</div>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
	   <div style="margin-left:86%">
	   Welcome : ${pageContext.request.userPrincipal.name}|
         <a
				href="javascript:formSubmit()"> Logout</a>  
				</div>
	</c:if>
	<div class="topnav">
		<a class="active" href="search">Online Search</a> <a href="batch">Batch
			Search</a> <a href="resultQ">Result Queue</a>
			
	</div>
	<div class="panel-body">
		<form action="search" method="GET" class="form-inline" role="form">
			<fieldset class="fieldsetCriteria">
				<legend class="legendFieldset">Criteria</legend>
				<div class="form-group" style="width: 100%;">
					<div class="form-group">
						<label for="inputName" style="font-size: 17px;">Name
							Search:</label> <input id="inputName" autocomplete="off"
							class="form-control" style="width: 200px;" type="text"
							name="name" value="${searchValueName}" placeholder="Enter Name" />

						<label for="selectList"
							style="padding-left: 2em; font-size: 17px;">List:</label>
						<select
							name="list" class="form-control m-b" id="selectList"
							style="width: 150px; margin-top: 13px;">
							<c:forEach items="${listas}" var="list">
								<option ${(searchValueList==list.nombre)?"selected":""}
									value="${list.nombre}">${list.nombre}  ${list.fecha}</option>
							</c:forEach>
						</select> <label for="selectScore"
							style="padding-left: 2em; font-size: 17px;">Score:</label> <select
							name="score" class="form-control m-b" id="selectScore"
							style="width: 85px; margin-top: 13px;">
							<c:forEach items="${scores}" var="score">
								<option ${(searchValueScore==score.idNombre)?"selected":""}
									value="${score.idNombre}">${score.valor}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="user" value="${pageContext.request.userPrincipal.name}" id="user" />
						<button id="search"
							style="font-size: 15px; width: 120px; text-align: center; margin-left: 2em; border-radius: 6px; background-color: #335397;color:#fff !important"
							type="submit" name="Search"
							class="btn btn-rounded btn-sm btn-facebook" value="Search"
							onclick="carga()">Search</button>
					</div>
					<c:if test="${cantRegistros!=0}">
					<div class="font-bold"
						style="float: right; margin-right: 20px; margin-top: 30px">Result:${cantRegistros}
					</div>
					</c:if>

				</div>
			</fieldset>
			
			<section class="panel panel-default" style="width: 100%;">
				<div class="table-responsive">
					<table class="table m-b-none"
						style="border-radius: 2px; max-width: 1335px;">
						<thead style="cursor:default !important;">
							<tr>
								<th class="th-sortable" data-toggle="class">Name</th>
								<th class="th-sortable" data-toggle="class">Last Update
									Date</th>
								<th class="th-sortable" data-toggle="class">Source</th>
								<th class="th-sortable" data-toggle="class">Original Source</th>
								<th class="th-sortable" data-toggle="class">DOB</th>
								<th class="th-sortable" data-toggle="class">Nationality</th>
								<th class="th-sortable" data-toggle="class">Addresses</th>
								<th class="th-sortable" data-toggle="class">Score</th>
							</tr>
						</thead>					
						<tbody style="cursor:default !important">
							<c:forEach items="${registros}" var="rsearch">
								<tr>
									<td>${rsearch.name}</td>
									<td>${rsearch.lastUpdateDate}</td>
									<td>${rsearch.source}</td>
									<td>${rsearch.originalSource}</td>
									<td>${rsearch.dob}</td>
									<td>${rsearch.nationality}</td>
									<td>${rsearch.address}</td>
									<td>${rsearch.score}</td>
								</tr>
							</c:forEach>
						</tbody>
						
					</table>
					
				</div>
			</section>
			<div class="loader" id="cargando" ></div>
			<c:if test="${cantRegistros>0}">
				<button id="save" type="submit" name="Save"
					style="float: right; font-size: 15px; width: 120px; text-align: center; margin-left: 2em; border-radius: 6px;"
					class="btn btn-rounded btn-sm btn-facebook" value="Save"
					onclick="carga()">Save</button>
			</c:if>
		</form>
	</div>
<div class="loader" id="cargando" style="display: none; z-index: 1; margin-left: 43%"></div>
	
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
