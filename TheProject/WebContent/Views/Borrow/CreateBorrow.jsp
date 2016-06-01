<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>สร้างเอกสารยืมวัสดุ</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">สร้างเอกสารยืมวัสดุ</h1>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-4">
							<div class="form-group">
								<label>เลขที่เอกสาร</label> <input class="form-control"
									placeholder="เลขที่เอกสาร">
							</div>
							<div class="form-group">
								<label>ชื่อผู้ยืม</label> <input class="form-control"
									placeholder="ชื่อผู้ยืม">
							</div>
							<div class="form-group">
								<label>เพื่อใช้ในงาน</label>
								<textarea class="form-control" rows="" cols=""
									placeholder="เพื่อใช้ในงาน"></textarea>

							</div>
							<div class="form-group">
								<label>วันที่ต้องการรับวัสดุ</label>
								<div class="form-group">
									<div class='input-group date' id='datetimepicker1'>
										<input type='text' class="form-control"
											placeholder="วันที่ต้องการรับวัสดุ" /> <span
											class="input-group-addon"> <span
											class="fa fa-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>กำหนดส่งคืนวัสดุ</label>
								<div class="form-group">
									<div class='input-group date' id='datetimepicker2'>
										<input type='text' class="form-control"
											placeholder="กำหนดส่งคืนวัสดุ" /> <span
											class="input-group-addon"> <span
											class="fa fa-calendar"></span>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-8">
							<div class="col-sm-12">
								<label>เลือกวัสดุ</label>
							</div>
							<div class="col-sm-12 form-inline">
								<div class="form-group">
									<input class="form-control" placeholder="รหัสวัสดุที่ต้องการเลิอก.....">
								</div>
								<div class="form-group">
									<a class="btn btn-warning" style="cursor: pointer;"><i
										class="fa fa-plus fa-fw"></i>เพิ่ม</a>
								</div>
								<div class="form-group">
									<a class="btn btn-info" style="cursor: pointer;"><i
										class="fa fa-search fa-fw"></i>ตรวจสอบวัสดุ</a>
								</div>
							</div>
							<div class="col-sm-12">
								<table class="table table-striped">
									<thead>
										<tr>
											<th class="text-center">#</th>
											<th class="text-center">รหัสวัสดุ</th>
											<th class="text-center">ชื่อวัสดุ</th>
											<th class="text-center">จัดการ</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#datetimepicker1').datepicker();
			$('#datetimepicker2').datepicker();
		})
	</script>

</body>
</html>