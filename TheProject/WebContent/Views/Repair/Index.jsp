<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>ซ่อมวัสดุ</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">ซ่อมวัสดุ</h1>
					</div>
					<div class="col-sm-12">
						<form action="" class="form-inline">
							<div class="form-group">
								<input class="form-control" name="search" id="search"
									value="<%=request.getAttribute("search")%>"
									placeholder="เลขที่เอกสาร">
							</div>
							<div class="form-group">
								<div class="input-group date" id="datePicker1">
									<input class="form-control" name="input_date" id="input_date"
										value="<%=request.getAttribute("input_date")%>"
										placeholder="วันที่ทำการซ่อมวัสดุ"> <span
										class="input-group-addon"> <span><i
											class="fa fa-calendar"></i></span>
									</span>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group date" id="datePicker2">
									<input class="form-control" name="return_date" id="return_date"
										value="<%=request.getAttribute("return_date")%>"
										placeholder="วันที่รับคืนวัสดุ"> <span
										class="input-group-addon"> <span><i
											class="fa fa-calendar"></i></span>
									</span>
								</div>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-info">
									<i class="fa fa-search fa-fw"></i>ค้นหา
								</button>
								<a href="<%=request.getContextPath() + "/Repair"%>"
								class="btn btn-warning"><i class="fa fa-refresh fa-fw"></i>รีเซ็ต</a>
								<a href="CreateRepair" class="btn btn-success"><i
									class="fa fa-plus fa-fw"></i>สร้างเอกสาร</a>
							</div>
						</form>
					</div>
					<div class="col-sm-12">
						<p>
						<table class="table table-striped" id="myTable">
							<thead>
								<tr>
									<th class="text-center">#</th>
									<th class="text-left">เลขที่เอกสาร</th>
									<th class="text-center">วันที่ทำการซ่อมวัสดุ</th>
									<th class="text-center">วันที่รับคืนวัสดุ</th>
									<th class="text-center">สถานะ</th>
									<th class="text-center">จัดการ</th>
								</tr>
							</thead>
						</table>
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
			$('#datePicker1').datepicker()
			$('#datePicker2').datepicker()

			$('#myTable').DataTable({
				responsive : true,
				'searching' : false
			});
		})
	</script>

</body>
</html>