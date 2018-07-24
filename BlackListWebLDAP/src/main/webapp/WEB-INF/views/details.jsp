<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<html>
<head>
<meta charset="UTF-8">
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
<style>
	 tr:hover td {
        background-color:#b9b7b7;
       }
</style>

<script>
function carga() {
 setTimeout("document.getElementById('cargando').style. display = 'block'", 0);
}
function val(idRegistro) {
	var d=document.getElementById("enviar_"+idRegistro);
	d.click();
	
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
	   Welcome : ${pageContext.request.userPrincipal.name} 
         <a
				href="javascript:formSubmit()"> Logout</a>  
				</div>
	</c:if>
	<div class="topnav">
		<a  href="search">Online Search</a> <a href="batch">Batch
			Search</a> <a href="resultQ" class="active">Result Queue</a>
	</div>
	<div class="panel-body">
	<fieldset class="fieldsetCriteria">
				<legend class="legendFieldset">Resume ${queue}</legend>
		<div class="form-group" style="width: 100%;">
				<div class="form-group" style="width: 100%;" >
					<div style="float:left;"><label><b>Search ID:</b> ${queue}&nbsp;<b>Name:</b> ${name} <b>List:</b> ${lista} <b>List Date:</b> ${listDate} <b>User:</b> ${user}</div>
					<c:if test="${cantRegistros!=0}">
						<div class="font-bold" style="float: right;margin-right: 20px;">Result:${cantRegistros}</div>
					</c:if>
					</label>
					
				</div>
		</div>
	</fieldset>
	</div>	
	<form action="details" method="GET" role="form">
	<section  class="panel panel-default" style="border-radius: 2px;max-width: 1335px; margin-left: 5px;">
	<div class="table-responsive">
		<table class="table m-b-none" style="border-radius: 2px;">
						
						<thead style="cursor:default !important;"> 
					<tr >
						<th class="th-sortable" data-toggle="class" style="width:300px">Name</th>
						<th class="th-sortable" data-toggle="class" style="width:">Last Update</th>
						<th class="th-sortable" data-toggle="class" style="width:50px">Source</th>
						<th class="th-sortable" data-toggle="class" style="width:50px">Original Source</th>
						<th class="th-sortable" data-toggle="class" >DOB</th>
						<th class="th-sortable" data-toggle="class">Nationality</th>
						<th class="th-sortable" data-toggle="class">Addresses</th>
						<th class="th-sortable" data-toggle="class">Score</th>
						<th class="th-sortable" data-toggle="class">Status</th>
						<th class="th-sortable" data-toggle="class" style="width:250px">Text</th>
						<th class="th-sortable" data-toggle="class" style="width:50px">Action</th>
					</tr>
				</thead>
				<tbody style="font-size: 12px; width: 80%;cursor:pointer !important;">
					<c:forEach items="${registros}" var="rsearch">
						<tr>
							<td style="width:300px">${rsearch.name}</td>
							<td>${rsearch.lastUpdateDate}</td>
							<td style="width:50px">${rsearch.source}</td>
							<td style="width:50px">${rsearch.originalSource}</td>
							<td>${rsearch.dob}</td>
							<td>${rsearch.nationality}</td>
							<td>${rsearch.address}</td>
							<td>${rsearch.score}</td>
							<td><select name="status_${rsearch.id}" id="status" onchange="val(${rsearch.id})">
									<c:forEach items="${lstatus}" var="st">
										<option ${(st.nombre==rsearch.status)?"selected":""}
											value="${st.nombre}">${st.nombre}</option>
									</c:forEach>
							</select></td>
							<td><input id="text" type="text" name="text_${rsearch.id}"
								value="${rsearch.text}"  maxlength="100" style="width:250px"/></td>
							<td>
								<button id="enviar_${rsearch.id}" type="submit" name="update" onclick="carga()"
									value="${rsearch.id}" class="btn btn-rounded btn-sm btn-facebook" style="font-size: 12px; border-radius: 6px; margin-left: -5px">Update</button>
							</td>
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
	<div style="margin-left:37%;margin-top:20px;">
			<div style="float:left;" >
				<a href="resultQ"><button name="idSearch" type="button" value="Back to Queue"
						class="btn btn-rounded btn-sm btn-facebook" style="font-size: 15px; width: 120px; text-align: center; margin-left: 2em; border-radius: 6px;" onclick="carga()">Back to Queue</button>
				</a>
			</div>
			<div style="float:left;">
				<input type="hidden" name="status" value="${status}" />
				<input type="hidden" name="queue" value="${queue}" id="queue" />
				<input type="hidden" name="statusNow" value="${statusNow}" id="statusNow" />
				<c:if test="${news==0}">
					<button name="idSearch" type="submit" value="${queue}"
						 class="btn btn-rounded btn-sm btn-facebook" style="font-size: 15px; width: 140px; text-align: center; margin-left: 2em; border-radius: 6px;" onclick="carga()">Complete Review</button>
				</c:if>
				<c:if test="${news>0}">
					<button name="completeAll" type="submit" value="completeAll"
						 class="btn btn-rounded btn-sm btn-facebook" style="font-size: 15px; width: 147px; text-align: center; margin-left: 2em; border-radius: 6px;" onclick="carga()">Complete All News</button>
				</c:if>
				</div>
		</div>
	</form>
</body>
</html>
