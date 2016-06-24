<%@page import="java.util.ArrayList"%>
<%@page import="com.model.BorrowDB"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	List<BorrowDB> list = (ArrayList<BorrowDB>) request.getAttribute("list");
	Users gobalUser = (Users) session.getAttribute("gobalUser");
	int count = 1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>ยืม - คืนวัสดุ</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">ยืม - คืนวัสดุ</h1>
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
										placeholder="วันที่ทำการยืมวัสดุ"> <span
										class="input-group-addon"> <span><i
											class="fa fa-calendar"></i></span>
									</span>
								</div>
							</div>
							<div class="form-group">
								<select class="form-control" name="status">
									<option value="">สถานะ...</option>
									<option value="Waiting">Waiting</option>
									<option value="Approved">Approved</option>
									<option value="Returned">Returned</option>
								</select>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-info">
									<i class="fa fa-search fa-fw"></i>ค้นหา
								</button>
								<a href="<%=request.getContextPath()%>/Borrow"
									class="btn btn-warning"><i class="fa fa-refresh fa-fw"></i>ระเซ็ต</a>
								<a href="<%=request.getContextPath()%>/CreateBorrow"
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
									<th class="text-left">เลขที่เอกสาร</th>
									<th class="text-center">วันที่ทำการยืมวัสดุ</th>
									<th class="text-center">วันที่ส่งคืนวัสดุ</th>
									<th class="text-center">สถานะ</th>
									<th class="text-center">จัดการ</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (BorrowDB item : list) {
								%>
								<tr>
									<td class="text-center"><%=count++%></td>
									<td class="text-left"><%=item.getDocument_no()%></td>
									<td class="text-center"><%=item.getDate()%></td>
									<td class="text-center"><%=item.getReturn_date()%></td>
									<td class="text-center"><%=item.getStatus()%></td>
									<%
										if (gobalUser.getRole().equals("admin")) {
									%>
									<td class="text-center"><a
										href="EditBorrow?bor_id=<%=item.getBor_id()%>"
										style="cursor: pointer; color: blue;"><i
											class="fa fa-gear"></i></a> | <a
										style="cursor: pointer; color: green;"><i
											class="fa fa-check-square-o"></i></a> | <a
										style="cursor: pointer; color: red;"><i
											class="fa fa-trash"></i></a></td>
									<%
										} else {
												if (item.getStatus().equals("Waiting")) {
									%>
									<td class="text-center"><a
										href="EditBorrow?bor_id=<%=item.getBor_id()%>"
										style="cursor: pointer; color: blue;"><i
											class="fa fa-gear"></i></a> | <a
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