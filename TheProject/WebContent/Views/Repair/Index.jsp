<%@page import="java.util.List"%>
<%@page import="com.model.RepairDB"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%
	List<RepairDB> list = (ArrayList<RepairDB>) request.getAttribute("list");
	Users gobalUser = (Users) session.getAttribute("gobalUser");
	int count = 1;
%>


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
								<select class="form-control" name="status">
									<option value="">สถานะ...</option>
									
									<option value="Repairing">Repairing</option>
									<option value="Returned">Returned</option>
								</select>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-info">
									<i class="fa fa-search fa-fw"></i>ค้นหา
								</button>
								<a href="<%=request.getContextPath()%>/Repair"
									class="btn btn-warning"><i class="fa fa-refresh fa-fw"></i>รีเซ็ต</a>
								<a href="<%=request.getContextPath()%>/CreateRepair" class="btn btn-success"><i
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
							 <tbody>
								<%
									for (RepairDB item : list){
								%>
								<tr>
									<td class="text-center"><%=count++ %></td>
									<td class="text-center"><%=item.getDocument_no() %></td>
									<td class="text-center"><%=item.getDate() %></td>
									<td class="text-center"><%=item.getReturn_date() %></td>
									<td class="text-center"><%=item.getStatus() %></td>
								<%
									if (gobalUser.getRole().equals("admin")) {
								%>
								<td class="text-center">
								<a href="EditRepair?repair_id=<%=item.getRepair_id()%>" style="cursor: pointer; color: blue;">
								<i class="fa fa-gear"></i></a> | <a class="btn-delete"
										data-id="<%=item.getRepair_id()%>>"
										data-doc_no="<%=item.getDocument_no()%>"
										style="cursor: pointer; color: red;"><i
										class="fa fa-trash"></i></a></td>
								<%
									} else {
								%>
								<td class="text-center"><a
										style="cursor: pointer; color: gray;"><i
											class="fa fa-gear"></i></a> | <a
										style="cursor: pointer; color: gray;"><i
											class="fa fa-trash"></i></a></td>
								<%
									}
								%>

								</tr>
								<%
									}
								%>
							</tbody>
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

			$('.btn-delete').on('click', function() {
				$modal = $('.modal-delete');
				var repair_id = $(this).data('id');
				var doc_no = $(this).data('doc_no');
				$modal.find('#text-doc_no').text('เลขที่เอกสาร : ' + doc_no);
				$modal.modal('show');
				$modal.find('.btn-confirm').on('click', function() {
					$.ajax({
						url : 'DeleteRepair',
						type : 'get',
						data : {
							repair_id : repair_id
						}
					}).done(function() {
						window.location.reload();
					})
				})
			})
		})
	</script>

	<div class="modal fade modal-delete" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">ลบเอกสาร</h4>
				</div>
				<div class="modal-body">
					<p>คุณต้องการดำเนินการลบเอกสารดังต่อไปนี้</p>
					<p id="text-doc_no"></p>
					<p>เอกสารจะถูกลบออกจากฐานข้อมูลของระบบ หากต้องการดำเนินการต่อ
						กรุณากด "ยืนยัน"</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning btn-confirm"
						data-dismiss="modal">ยืนยัน</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>
</html>