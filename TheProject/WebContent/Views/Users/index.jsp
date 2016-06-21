<%@page import="com.sun.xml.internal.ws.api.ha.StickyFeature"%>
<%@page import="com.dao.DepartmentDao"%>
<%@page import="com.dao.FacultyDao"%>
<%@page import="com.model.Department"%>
<%@page import="com.model.Faculty"%>
<%@page import="com.model.Role"%>
<%@page import="com.model.Users"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	Users gobalUser = (Users) session.getAttribute("gobalUser");
	ArrayList<Users> listUser = (ArrayList<Users>) request.getAttribute("listUser");
	ArrayList<Faculty> listFc = (ArrayList<Faculty>) request.getAttribute("listFc");
	ArrayList<Department> listDp = (ArrayList<Department>) request.getAttribute("listDp");
	ArrayList<Role> listRole = (ArrayList<Role>) request.getAttribute("listRole");
	int count = 1;
	DepartmentDao dpDao = new DepartmentDao();
	FacultyDao fcDao = new FacultyDao();

	String faculty_id = (String) request.getAttribute("faculty_id");
	if (faculty_id.equals("") | faculty_id == null) {
		faculty_id = "0";
	}

	String department_id = (String) request.getAttribute("department_id");
	if (department_id.equals("") | department_id == null) {
		department_id = "0";
	}

	String role = (String) request.getAttribute("role");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>ข้อมูลผู้ใช้</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">

					<div class="col-lg-12">
						<h1 class="page-header">ข้อมูลผู้ใช้</h1>
						<div class="row">
							<div class="col-sm-12">
								<form action="<%=request.getContextPath() + "/UserInfo"%>"
									method="get" class="form-inline">
									<div class="form-group">
										<input class="form-control"
											placeholder="รหัสประจำตัว, ชื่อผู้ใช้, ชื่อ-สกุล"
											name="search" id="search"
											value="<%=request.getAttribute("search")%>">
									</div>
									<div class="form-group">
										<select class="form-control" name="faculty_id" id="faculty_id">
											<option value="">เลือกคณะ...</option>
											<%
												for (Faculty fc : listFc) {
													if (fc.getFaculty_id() == Integer.parseInt(faculty_id)) {
											%>
											<option value="<%=fc.getFaculty_id()%>" selected="selected"><%=fc.getFaculty_name()%></option>
											<%
												} else {
											%>
											<option value="<%=fc.getFaculty_id()%>"><%=fc.getFaculty_name()%></option>
											<%
												}
												}
											%>
										</select>
									</div>
									<div class="form-group">
										<select class="form-control" name="department_id"
											id="department_id">
											<option value="">เลือกสาขาวิชา...</option>
											<%
												for (Department dp : listDp) {
													if (dp.getDepartment_id() == Integer.parseInt(department_id)) {
											%>
											<option value="<%=dp.getDepartment_id()%>"
												selected="selected"><%=dp.getDepartment_name()%></option>
											<%
												} else {
											%>
											<option value="<%=dp.getDepartment_id()%>"><%=dp.getDepartment_name()%></option>
											<%
												}
												}
											%>
										</select>
									</div>
									<div class="form-group">
										<select class="form-control" name="role" id="role">
											<option value="">สิทธิ์...</option>
											<%
												for (Role ro : listRole) {
													if (ro.getRole().equals(role)) {
											%>
											<option value="<%=ro.getRole()%>" selected="selected"><%=ro.getRole()%></option>
											<%
												} else {
											%>
											<option value="<%=ro.getRole()%>"><%=ro.getRole()%></option>
											<%
												}
												}
											%>
										</select>
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-info">
											<i class="fa fa-search fa-fw"></i>ค้นหา
										</button>
									</div>
									<div class="form-group">
										<a href="<%=request.getContextPath()%>/UserInfo"
											class="btn btn-warning"> <i class="fa fa-refresh fa-fw"></i>รีเซ็ต
										</a>
									</div>
									<div class="form-group">
										<a class="btn btn-success"
											href="<%=request.getContextPath()%>/CreateUser"><i
											class="fa fa-plus fa-fw"></i>เพิ่ม</a>
									</div>
								</form>
							</div>
						</div>
						<p>
						<div class="row">
							<div class="col-sm-12">
								<table class="table table-striped" id="myTable">
									<thead>
										<tr>
											<th class="text-center">#</th>
											<th>รหัสประจำตัว</th>
											<th>ชื่อผู้ใช้</th>
											<th>ชื่อ - สกุล</th>
											<th class="text-center">คณะ</th>
											<th class="text-center">สาขาวิชา</th>
											<th class="text-center">สิทธิ์</th>
											<th class="text-center">สถานะ</th>
											<th class="text-center">จัดการ</th>
										</tr>
									</thead>
									<tbody>
										<%
											for (Users item : listUser) {
										%>
										<tr>
											<td class="text-center"><%=count++%></td>
											<td><%=item.getUser_code()%></td>
											<td><%=item.getUsername()%></td>
											<td><%=item.getFirst_name() + " " + item.getLast_name()%></td>
											<td class="text-center"><%=fcDao.find(item.getFaculty_id()).getFaculty_name()%></td>
											<td class="text-center"><%=dpDao.find(item.getDepartment_id()).getDepartment_name()%></td>
											<td class="text-center"><%=item.getRole()%></td>
											<td class="text-center"><%=item.getStatus()%></td>
											<td class="text-center"><a
												href="EditUser?username=<%=item.getUsername()%>"><i
													class="fa fa-gear"></i></a> | <a
												style="color: red; cursor: pointer;"><i
													class="fa fa-trash btn-delete"
													data-id="<%=item.getUser_id()%>"
													data-username="<%=item.getUsername()%>"
													data-code="<%=item.getUser_code()%>"></i></a></td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
							<!-- /.col-lg-8 -->
						</div>
						<!-- /.row -->
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->


		<div class="modal fade modal-delete" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">คุณต้องการลบผู้ใช้งาน</h4>
					</div>
					<div class="modal-body">
						<p id="set-user_code"></p>
						<p id="set-username"></p>
						<p>ออกจากฐานข้อมูลของระบบ</p>
						<p>เมื่อทำการลบข้อมูลผู้ใช้ออกแล้ว
							ระบบจะไม่สามารถกู้คืนข้อมูลของผู้ใช้งานได้</p>
						<p>คุณต้องการลบผู้ใช้งานนี้หรือไม่ ?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">ยกเลิก</button>
						<button type="button" class="btn btn-warning" id="btn-yes">ลบทันที</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#myTable').DataTable({
				responsive : true,
				"searching" : false
			});

			$('.btn-delete').on('click', function() {
				$modal = $('.modal-delete');
				$modal.find('#set-user_code').text($(this).data('code'));
				$modal.find('#set-username').text($(this).data('username'));
				$modal.modal('show');
				var id = $(this).data('id');
				$('#btn-yes').on('click', function() {
					$.ajax({
						url : 'DeleteUser',
						type : 'get',
						data : {
							id : id
						}
					}).done(function() {
						location.reload();
					});
				});
			});
		});
	</script>
</body>
</html>