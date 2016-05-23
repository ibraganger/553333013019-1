<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>สร้างเอกสารการเบิก</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">สร้างเอกสารการเบิก</h1>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-4">
							<div class="form-group">
								<label>เลขที่เอกสาร</label> <input class="form-control" name=""
									id="" placeholder="เลขที่เอกสาร">
							</div>
							<div class="form-group">
								<label>ชื่อผู้ยืม</label> <input class="form-control" name=""
									id="" placeholder="ชื่อผู้ยืม">
							</div>
							<div class="form-group">
								<label>เพื่อใช้ในงาน</label> <input class="form-control" name=""
									id="" placeholder="เพื่อใช้ในงาน">
							</div>
							<div class="form-group">
								<label>ใช้งานในวันที่</label>
								<div class="input-group date" id="datePicker1">
									<input class="form-control" name="" id=""
										placeholder="วันที่ทำการเบิกวัสดุ"> <span
										class="input-group-addon"> <span><i
											class="fa fa-calendar"></i></span>
									</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<button type="submit" class="btn btn-success btn-block">สร้าง</button>
								</div>
								<div class="col-sm-6">
									<a href="#" class="btn btn-danger btn-block">ยกเลิก</a>
								</div>
							</div>
						</div>
						<div class="col-sm-8">
							<div class="col-sm-12">
								<div class="col-sm-12">
									<label>เพิ่มวัสดุ</label>
								</div>
								<div class="col-sm-12 form-inline">
									<a class="btn btn-warning" style="cursor: pointer;"><i
										class="fa fa-plus fa-fw"></i>เพิ่ม</a>
								</div>
								<div class="col-sm-12">
									<table id="myTable" class="table table-striped">
										<thead>
											<tr>
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
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$('#datePicker1').datepicker()
		</script>
</body>
</html>