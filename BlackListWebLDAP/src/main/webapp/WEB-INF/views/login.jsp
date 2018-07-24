<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html style="background-color:#ec7e23 ">
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
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
<script>
	function carga() {
		setTimeout(
				"document.getElementById('cargando').style. display = 'block'",	0);
	}
</script>
</head>
<body  onload='document.loginForm.username.focus();'>
	<div class="imgLogo">
		<div style="float: left; width: 200px">
			<img src="resources/logo_login.png" alt="Logo Itau" />
		</div>
		<div style="float: right;">
			<img src="resources/itau2.png" alt="Logo Itau"
				style="height: 50px; margin-top: 10px" />
		</div>
	</div>
	
	<form name='loginForm'
			action="<c:url value='/j_spring_security_check' />" method='POST' class="bs-example form-horizontal">
			
			 <div class="col-sm-6" style="margin-top:10%;margin-left:37%;width:300px;">                  
                  <section class="panel panel-default">
                  <header class="panel-heading font-bold" style="background-color: #335397;color:#fff">Login</header>
	                  <c:if test="${not empty error}">
							<div class="error">${error}</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="msg">${msg}</div>
						</c:if>
                    <div class="panel-body">
                        <div class="form-group">
                          <div class="col-lg-12" >
                            <input class="form-control" placeholder="User Name" type='text' name='username' autocomplete="off">
                          </div>
                        </div>
                        <div class="form-group" style="margin-top:5px;">
                          <div class="col-lg-12">
                            <input type="password" class="form-control" placeholder="Password" name='password' autocomplete="off">
                          </div>
                        </div>
                        <div class="form-group">
                          <div class="col-lg-offset-2 col-lg-6">
                            <button type="submit" style="font-size: 15px; width: 81px; text-align: center; margin-left: 2em;margin-top:20px; border-radius: 6px;"
						class="btn btn-rounded btn-sm btn-facebook" value="submit" name="submit">Sign in</button>
                          </div>
                        </div>
                    </div>
                  </section>
                </div>
			<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		</form>
<div class="loader" id="cargando" style="display: none; z-index: 1; margin-left: 43%"></div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
	
	<!-- Bootstrap -->
  	<script src="resources/js/bootstrap.js"></script>
  	<!-- App -->
  	<script src="resources/js/app.js"></script>
  	<script src="resources/js/app.plugin.js"></script>
  	<script src="resources/js/slimscroll/jquery.slimscroll.min.js"></script>
</body>

</html>
