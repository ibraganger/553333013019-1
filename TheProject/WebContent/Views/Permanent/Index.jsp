<%@page import="com.model.Permanent"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	List<Permanent> list = (List<Permanent>) request.getAttribute("list");
	Users gobalUser = (Users) session.getAttribute("gobalUser");
	int count = 1;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<title>วัสดุถาวร</title>
</head>
<body>
	<%@include file="/jspFile/header.jsp"%>
	<%@include file="/jspFile/js.jsp"%>
	<!-- Page Content -->
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">วัสดุถาวร</h1>
					</div>
					<!-- /.col-lg-12 -->
					<div class="col-sm-12">
						<form class="form-inline">
							<div class="form-group">
								<input type="text" class="form-control" name="search"
									id="search" value="<%=request.getAttribute("search")%>"
									placeholder="รหัสวัสดุ ,ชื่อวัสดุ ...">
							</div>
							<div class="form-group">
								<div class="input-group date" data-provide="datepicker">
									<input type="text" class="form-control" name="input_date"
										id="input_date"
										value="<%=request.getAttribute("input_date")%>"
										placeholder="วว/ดด/ปป">
									<div class="input-group-addon">
										<span class="fa fa-calendar"></span>
									</div>
								</div>
							</div>
							<button type="submit" class="btn btn-info">
								<i class="fa fa-search fa-fw"></i>ค้นหา
							</button>
							<a href="<%=request.getContextPath() + "/Permanent"%>"
								class="btn btn-warning"><i class="fa fa-refresh fa-fw"></i>รีเซ็ต</a>
							<%
								if (gobalUser.getRole().equals("admin")) {
							%>
							<a href="<%=request.getContextPath() + "/CreatePer"%>"
								class="btn btn-success"><i class="fa fa-plus fa-fw"></i>เพิ่มวัสดุ</a>
							<%
								}
							%>
						</form>
					</div>
					<div class="col-sm-12 text-right">
						<a href="<%=request.getContextPath()%>/PrintPermanent"><i
							class="fa fa-print fa-2x fa-fw"></i>พิมพ์</a>
					</div>
					<div class="col-sm-12">
						<table class="table" id="dataTables">
							<thead>
								<tr>
									<th class="text-center">#</th>
									<th class="text-left">หมายเลขวัสดุ</th>
									<th class="text-left">ชื่อวัสดุ</th>
									<th class="text-center">จำนวน</th>
									<th class="text-center">หน่วยนับ</th>
									<th class="text-center">วันที่นำเข้า</th>
									<%
										if (gobalUser.getRole().equals("admin")) {
									%>
									<th class="text-center">จัดการ</th>
									<%
										}
									%>
								</tr>
							</thead>
							<tbody>
								<%
									for (Permanent item : list) {
								%>
								<tr>
									<td class="text-center"><%=count++%></td>
									<td class="text-left"><%=item.getPer_code()%></td>
									<td class="text-left"><%=item.getPer_name()%></td>
									<td class="text-center"><%=item.getAmount()%></td>
									<td class="text-center"><%=item.getUnit()%></td>
									<td class="text-center"><%=item.getInput_date()%></td>
									<%
										if (gobalUser.getRole().equals("admin")) {
									%>
									<td class="text-center"><a
										href="<%=request.getContextPath() + "/EditPer?per_id=" + item.getPer_id()%>"><i
											class="fa fa-gear"></i></a> | <a class="btn-delete"
										data-id="<%=item.getPer_id()%>"
										data-code="<%=item.getPer_code()%>"
										data-name="<%=item.getPer_name()%>"
										style="color: red; cursor: pointer;"><i
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
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {

			$('#dataTables').DataTable({
				responsive : true,
				"searching" : false
			});

			$('.btn-delete').on('click', function() {
				var per_id = $(this).data('id');
				var text1 = 'รหัสวัสดุ : ';
				var text2 = 'ชื่อวัสดุ : ';
				$modal = $('.modal-delete');
				$modal.find('.code').text(text1 + $(this).data('code'));
				$modal.find('.name').text(text2 + $(this).data('name'));
				$modal.modal('show');
				$modal.find('.btn-comfirm-delete').on('click', function() {
					$.ajax({
						url : 'DeletePer',
						type : 'get',
						data : {
							per_id : per_id
						}
					}).done(function() {
						location.reload();
					});
				})
			});
		});
	</script>


	<div class="modal fade modal-delete" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">ลบวัสดุ</h4>
				</div>
				<div class="modal-body">
					<p>คุณต้องการดำเนินการลบวัสดุดังต่อไปนี้</p>
					<p class="code"></p>
					<p class="name"></p>
					<p>ออกจากฐานข้อมูลของระบบ ระบบจะไม่สามารถกู้คืนข้อมูลได้
						หากท่านต้องการดำเนินการลบ กรุณากด "ยืนยัน"</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
					<button type="button " class="btn btn-primary btn-comfirm-delete">ยืนยัน</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>
</html>
