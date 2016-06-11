<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Asset management system</title>

		<!-- Bootstrap Core CSS -->
		<link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

		<!-- MetisMenu CSS -->
		<link href="bower_components/metismenu/dist/metisMenu.css" rel="stylesheet">

		<!-- Custom CSS -->
		<link href="dist/css/sb-admin-2.css" rel="stylesheet">

		<!-- Custom Fonts -->
		<link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="css/stylesheet.css" rel="stylesheet" type="text/css">

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

	</head>

	<body>

		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="login-panel panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">กรุณาเข้าสู่ระบบ</h3>
						</div>
						<div class="panel-body">
							<form action="<%=request.getContextPath()%>/Login" method="post" id="loginForm">
								<fieldset>
									<div class="form-group">
										<input class="form-control" placeholder="ชื่อผู้ใช้" name="username" type="text" autofocus>
									</div>
									<div class="form-group">
										<input class="form-control" placeholder="รหัสผ่าน" name="password" type="password" value="">
									</div>
									<div class="checkbox">
										<label> <input name="remember" type="checkbox"
										value="Remember Me">จดจำฉัน
									</label>
									</div>
									<!-- Change this to a button or input when using this as a form -->
									<input type="submit" value="เข้าสู่ระบบ" class="btn btn-lg btn-success btn-block">
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- jQuery -->
		<script src="bower_components/jquery/dist/jquery.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

		<!-- Metis Menu Plugin JavaScript -->
		<script src="bower_components/metismenu/dist/metisMenu.js"></script>

		<!-- Custom Theme JavaScript -->
		<script src="dist/js/sb-admin-2.js"></script>
		<script src="js/jquery.validate.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
			$('#loginForm').validate({
				rules : {
					username : {
						required : true
					},
					password : {
						required : true
					}
				},
				messages : {
					username : {
						required : "กรุณาระบุชื่อผู้ใช้"
					},
					password : {
						required : "กรุณาระบุรหัสผ่าน"
					}
				}
			})
		})
		</script>

	</body>

	</html>