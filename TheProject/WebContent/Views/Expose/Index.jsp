<%@page import="com.model.ExposeDB"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	Users gobalUser = (Users) session.getAttribute("gobalUser");
	List<ExposeDB> exposeList = (List<ExposeDB>) request.getAttribute("exposeList");
	String status = (String) request.getAttribute("status");
	int count = 1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>เบิก - จ่าย</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">เบิก - จ่าย</h1>
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
										placeholder="วันที่ทำการเบิกวัสดุ"> <span
										class="input-group-addon"> <span><i
											class="fa fa-calendar"></i></span>
									</span>
								</div>
							</div>
							<div class="form-group">

								<%
									if (status.equals("Waiting")) {
								%>
								<select class="form-control" name="status" id="status">
									<option value="">สถานะ....</option>
									<option value="Waiting" selected="selected">Waiting</option>
									<option value="Approved">Approved</option>
								</select>
								<%
									} else if (status.equals("Approved")) {
								%>
								<select class="form-control" name="status" id="status">
									<option value="">สถานะ....</option>
									<option value="Waiting">Waiting</option>
									<option value="Approved" selected="selected">Approved</option>
								</select>
								<%
									} else {
								%>
								<select class="form-control" name="status" id="status">
									<option value="">สถานะ....</option>
									<option value="Waiting">Waiting</option>
									<option value="Approved">Approved</option>
								</select>
								<%
									}
								%>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-info">
									<i class="fa fa-search fa-fw"></i>ค้นหา
								</button>
								<a href="<%=request.getContextPath() + "/Expose"%>"
									class="btn btn-warning"><i class="fa fa-refresh fa-fw"></i>รีเซ็ต</a>
								<a href="<%=request.getContextPath() + "/CreateExpose"%>"
									class="btn btn-success"><i class="fa fa-plus fa-fw"></i>สร้างเอกสาร</a>
							</div>
						</form>
					</div>
					<div class="col-sm-12">
						<p>
						<table class="table table-striped" id="myTable">
							<thead>
								<tr>
									<th class="text-center">#</th>
									<th class="text-center">เลขที่เอกสาร</th>
									<th class="text-center">วันที่ทำการเบิกวัสดุ</th>
									<th class="text-center">สถานะ</th>
									<th class="text-center">จัดการ</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (ExposeDB item : exposeList) {
								%>
								<tr>
									<td class="text-center"><%=count++%></td>
									<td class="text-left"><%=item.getDocument_no()%></td>
									<td class="text-center"><%=item.getDate()%></td>
									<td class="text-center"><%=item.getStatus()%></td>
									<%
										if (gobalUser.getRole().equals("admin")) {
									%>
									<td class="text-center"><a
										href="EditExpose?ex_id=<%=item.getEx_id()%>"
										style="cursor: pointer;"><i class="fa fa-gear"></i></a> | <a
										class="btn-delete" data-id="<%=item.getEx_id()%>"
										data-doc="<%=item.getDocument_no()%>"
										style="cursor: pointer; color: red;"><i
											class="fa fa-trash"></i></a></td>
									<%
										} else {
												if (item.getStatus().equals("Waiting")) {
									%>
									<td class="text-center"><a
										href="EditExpose?ex_id=<%=item.getEx_id()%>"
										style="cursor: pointer;"><i class="fa fa-gear"></i></a> | <a
										class="btn-delete" data-id="<%=item.getEx_id()%>"
										data-doc="<%=item.getDocument_no()%>"
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
					<p>คุณต้องการกำเนินการลบเอกสารดังต่อไปนี้</p>
					<p id="show-text"></p>
					<p>ระบบจะทำการลบข้อมูลเอกสารการเบิกออกจากฐานข้อมูลของระบบ
						หากต้องการดำเนินการต่อกรุณากด "Confirm"</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary btn-confirm">Confirm</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script type="text/javascript">
		$(document).ready(function() {
			$('#datePicker1').datepicker()

			$('#myTable').DataTable({
				responsive : true,
				'searching' : false
			});

			$('.btn-delete').on('click', function() {
				var id = $(this).data('id');
				var text = "เลขที่เอกสาร : " + $(this).data("doc");
				$modal = $('.modal-delete');
				$modal.find('#show-text').text(text);
				$modal.find('.btn-confirm').on('click', function() {
					$.ajax({
						url : 'DeleteExpose',
						type : 'get',
						data : {
							ex_id : id
						}
					}).done(function() {
						window.location.reload();
					})
				})
				$modal.modal('show');
			})
		})
	</script>

</body>
</html>