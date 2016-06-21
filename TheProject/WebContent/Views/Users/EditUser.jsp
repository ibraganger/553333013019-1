<%@page import="com.model.TitleName"%>
<%@page import="com.model.Department"%>
<%@page import="com.model.Faculty"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dao.DepartmentDao"%>
<%@page import="com.dao.FacultyDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	ArrayList<Faculty> listFc = (ArrayList<Faculty>) request.getAttribute("listFc");
	ArrayList<Department> listDp = (ArrayList<Department>) request.getAttribute("listDp");
	ArrayList<TitleName> titleList = (ArrayList<TitleName>) request.getAttribute("titleList");
	Users userEdit = (Users) request.getAttribute("userEdit");
	Users gobalUser = (Users) request.getSession().getAttribute("gobalUser");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>แก้ไขข้อมุลผู้ใช้</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="row" style="margin-top: 50px;">
				<div class="col-lg-12">
					<h1 class="page-header">แก้ไขข้อมุลผู้ใช้</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<form method="post" id="formCreate" action="EditUser">

						<div class="col-sm-6 form-horizontal">
							<div class="form-group">
								<label class="col-sm-3 control-label">รหัสผู้ใช้</label>
								<div class="col-sm-9">
									<input name="user_code" id="user_code" class="form-control"
										value="<%=userEdit.getUser_code()%>" placeholder="รหัสผู้ใช้">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"></label>
								<div class="col-sm-9">
									<%
										for (TitleName tt : titleList) {
											if (tt.getTitleName().trim().equals(userEdit.getTitle().trim())) {
									%>
									<label class="radio-inline"> <input type="radio"
										name=title id="title_id1" value="<%=tt.getTitleName()%>"
										checked> <%=tt.getTitleName()%>
									</label>
									<%
										} else {
									%>
									<label class="radio-inline"> <input type="radio"
										name=title id="title_id1" value="<%=tt.getTitleName()%>">
										<%=tt.getTitleName()%>
									</label>
									<%
										}
										}
									%>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">ชื่อ</label>
								<div class="col-sm-9">
									<input name="first_name" id="first_name" type="text"
										class="form-control" value="<%=userEdit.getFirst_name()%>"
										placeholder="ชื่อ">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">นามสกุล</label>
								<div class="col-sm-9">
									<input name="last_name" id="last_name" type="text"
										class="form-control" value="<%=userEdit.getLast_name()%>"
										placeholder="นามสกุล">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">ที่อยู่</label>
								<div class="col-sm-9">
									<textarea name="address" id="address" class="form-control"
										value="<%=userEdit.getAddress()%>" placeholder="ที่อยู่"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">โทรศัพท์</label>
								<div class="col-sm-9">
									<input name="phone" id="phone" type="text" class="form-control"
										value="<%=userEdit.getPhone()%>" placeholder="โทรศัพท์">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">คณะ</label>
								<div class="col-sm-9">
									<select name="faculty_id" id="faculty_id" class="form-control">
										<option value="">เลือกคณะ...</option>
										<%
											for (Faculty fc : listFc) {
												if (fc.getFaculty_id() == userEdit.getFaculty_id()) {
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
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">สาขาวิชา</label>
								<div class="col-sm-9">
									<select name="department_id" id="department_id"
										class="form-control">
										<option value="">เลือกสาขาวิชา...</option>
										<%
											for (Department dp : listDp) {
												if (dp.getDepartment_id() == userEdit.getDepartment_id()) {
										%>
										<option value="<%=dp.getDepartment_id()%>" selected="selected"><%=dp.getDepartment_name()%></option>
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
							</div>
						</div>
						<div class="col-sm-6 form-horizontal">
							<input type="hidden" name="checkEmail" id="checkEmail"
								value="<%=userEdit.getEmail()%>"> <input type="hidden"
								name="checkUser_code" id="checkUser_code"
								value="<%=userEdit.getUser_code()%>"> <input
								type="hidden" name="checkUsername" id="checkUsername"
								value="<%=userEdit.getUsername()%>"> <input
								type="hidden" name="user_id" value="<%=userEdit.getUser_id()%>">
							<div class="form-group">
								<label class="col-sm-3 control-label">ชื่อผู้ใช้</label>
								<div class="col-sm-9">
									<input name="username" id="username" class="form-control"
										placeholder="ชื่อผู้ใช้" autocomplete="off"
										value="<%=userEdit.getUsername()%>" readonly><label
										id="username-error" class="error" for="username"></label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">รหัสผ่าน</label>
								<div class="col-sm-9">
									<input name="password" id="password" type="password"
										class="form-control" value="<%=userEdit.getPassword()%>"
										placeholder="รหัสผ่าน">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">รหัสผ่านซ้ำ</label>
								<div class="col-sm-9">
									<input name="re_password" id="re_password"
										value="<%=userEdit.getPassword()%>" type="password"
										class="form-control" placeholder="รหัสผ่านซ้ำ">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-3 control-label">อีเมลล์</label>
								<div class="col-sm-9">
									<input name="email" type="email" class="form-control"
										id="email" value="<%=userEdit.getEmail()%>"
										placeholder="อีเมลล์">
								</div>
							</div>
							<%
								if (gobalUser.getRole().equals("admin")) {
							%>
							<div class="form-group">
								<label class="col-sm-3 control-label">บทบาท</label>
								<div class="col-sm-9">
									<div class="radio">
										<%
											if (userEdit.getRole().equals("user")) {
										%>
										<label> <input type="radio" name="role" id="role1"
											value="user" checked> ผู้ใช้
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="role" id="role2"
											value="admin"> ผู้ดูแล
										</label>
									</div>
									<%
										} else {
									%>
									<label> <input type="radio" name="role" id="role1"
										value="user"> ผู้ใช้
									</label>
								</div>
								<div class="radio">
									<label> <input type="radio" name="role" id="role2"
										value="admin" checked> ผู้ดูแล
									</label>
								</div>
								<%
									}
								%>
							</div>
							<%
								}
							%>
							<div class="form-group">
								<div class="col-sm-3"></div>
								<div class="col-sm-4">
									<button type="submit" class="btn btn-success btn-block">บันทึก</button>
								</div>
								<div class="col-sm-4">
									<a href="#" id="btn-cancel" class="btn btn-warning btn-block">ยกเลิก</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>



	<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">คุณกำลังออกจากหน้านี้</h4>
				</div>
				<div class="modal-body">
					<p>การเปลี่ยนแปลงข้อมูลของคุณจะไม่ถูกบันทึก
						หากคุณออกจากหน้าโดยไม่บันทึกการเปลี่ยนแปลงข้อมูล</p>
					<p>คุณต้องการออกจากหน้านี้หรือไม่ ?</p>
				</div>
				<div class="modal-footer">
					<button type="button" id="btn-hide"
						class="btn btn-primary data-dismiss="modal">อยุ่หน้านี้ต่อไป</button>
					<button type="button" id="btn-back" class="btn btn-warning">ออกจากหน้านี้</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<script type="text/javascript">
		$(document).ready(function(e) {
			$('#btn-cancel').on('click', function() {
				$modal = $('#myModal');
				$modal.modal("show");
				$('#btn-hide').on('click', function(e) {
					$modal.modal('hide')
				});
				$('#btn-back').on('click', function(e) {
					location.href = "UserInfo";
				});
			});

			$('#formCreate').validate({
				rules : {
					username : {
						required : true,
						maxlength : 50,
						remote : {
							url : "DuplicateUsername",
							type : "get",
							data : {
								username : function() {
									return $("#username").val();
								},
								checkUsername : function() {
									return $('#checkUsername').val()
									s;
								}
							}
						}
					},
					user_code : {
						maxlength : 50,
						remote : {
							url : "DuplicateUserCode",
							type : "get",
							data : {
								user_code : function() {
									return $("#user_code").val();
								},
								checkUser_code : function() {
									return $('#checkUser_code').val();
								}
							}
						}
					},
					email : {
						maxlength : 100,
						email : true,
						remote : {
							url : "DuplicateEmail",
							type : "get",
							data : {
								email : function() {
									return $("#email").val();
								},
								checkEmail : function() {
									return $('#checkEmail').val();
								}
							}
						}
					},
					password : {
						required : true
					},
					re_password : {
						required : true,
						equalTo : "#password"
					},
					first_name : {
						maxlength : 100,
						required : true
					},
					last_name : {
						maxlength : 100,
						required : true
					},
					address : {
						maxlength : 250
					},
					department_id : {
						required : true
					},
					faculty_id : {
						required : true
					}
				},
				messages : {
					username : {
						required : "ระบุชื่อผู้ใช้",
						maxlength : "สูงสุด 50 ตัวอักษร",
						remote : "ผู้ใช้นี้มีอยู่แล้ว"
					},
					user_code : {
						maxlength : "สูงสุด 50 ตัวอักษร",
						remote : "ไม่สามารถใช้รหัสผู้ใช้นี้ได้"
					},
					password : {
						required : "ระบุรหัสผ่าน",
						maxlength : "สูงสุด 50 ตัวอักษร",
					},
					re_password : {
						required : "ระบุรหัสผ่านอีกซ้ำ",
						maxlength : "สูงสุด 50 ตัวอักษร",
						equalTo : "รหัสผ่านไม่ตรงกัน"
					},
					email : {
						maxlength : "สูงสุด 100 ตัวอักษร",
						email : "กรุณาใส่อีเมล์ที่ถูกต้อง",
						remote : "ไม่สามารถฬช้อีเมลล์นี้ได้"
					},
					address : {
						maxlength : "สูงสุด 250 ตัวอักษร"
					},
					department_id : "เลิอกสาขาวิชา",
					faculty_id : "เลือกคณะ",
					first_name : {
						required : "ระบุชื่อ",
						maxlength : "สูงสุด 100 ตัวอักษร",
					},
					last_name : {
						required : "ระบุนามสกุล",
						maxlength : "สูงสุด 100 ตัวอักษร",
					}
				}
			});
		})
	</script>
</body>
</html>