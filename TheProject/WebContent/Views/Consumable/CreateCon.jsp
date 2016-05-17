<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.model.Consumable"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	List<Consumable> consumList = (ArrayList<Consumable>) request.getAttribute("consumList");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>

<%@include file="/jspFile/js.jsp"%>
<title>เพิ่มวัสดุสิ้นเปลือง</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">เพิ่มวัสดุสิ้นเปลือง</h1>
					</div>
					<div class="col-lg-12">
						<form action="<%=request.getContextPath()%>/CreateCon"
							method="post" id="form-create-con" class="form-horizontal">
							<div class="col-sm-12">
								<div class="form-group">
									<label class="col-sm-4 control-label">เลือกหมวด</label>
									<div class="col-sm-4">
										<select name="con_id" id="con_id" class="form-control">
											<option value="">เลือกหมวด...</option>
											<%
												for (Consumable consum : consumList) {
											%>
											<option value="<%=consum.getCon_id()%>"><%=consum.getCon_code() + "," + consum.getCon_name()%></option>
											<%
												}
											%>
										</select>
									</div>
									<a class="btn btn-warning" id="btn-addgroup"> <i
										class="fa fa-plus fa-fw"></i>เพิ่มหมวด
									</a>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">จำนวน</label>
									<div class="col-sm-4">
										<input name="amount" id="amount" type="text"
											class="form-control" placeholder="จำนวน">
									</div>
									<label class="col-sm-4">หน่วย</label>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">ราคาต่อชิ้น</label>
									<div class="col-sm-4">
										<input name="price" id="price" type="text"
											class="form-control" placeholder="ราคา">
									</div>
									<label class="col-sm-4">บาท</label>

								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">วันที่นำเข้า</label>
									<div class="col-sm-4">
										<div class="input-group date" id="date"
											data-date-format="dd/mm/yyyy" style="cursor: pointer;">
											<input name="input_date" id="input_date" type="text"
												class="form-control" value="">
											<div class="input-group-addon">
												<span class="fa fa-calendar"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">*หมายเหตุ</label>
									<div class="col-sm-4">
										<textarea name="note" id="note" type="text"
											class="form-control" placeholder="หมายเหตุ"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"></label>
									<div class="col-sm-4">
										<button type="submit" class="btn btn-success">เพิ่ม</button>
										<a class="btn btn-danger">ยกเลิก</a>
									</div>
								</div>
							</div>

						</form>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			var today = new Date();
			$('#date').datepicker({
				endDate : today
			}).on('changeDate', function(e) {
				$(this).datepicker('hide');
			})

			$.validator.addMethod("regx", function(value, element, regexpr) {
				return regexpr.test(value);
			}, "Please enter a valid pasword.");
			$('#form-create-con').validate({
				rules : {
					con_id : {
						required : true
					},
					amount : {
						required : true,
						number : true
					},
					price : {
						required : true,
					},
					input_date : {
						required : true,
					}
				},
				messages : {
					con_id : {
						required : 'เลือกหมวดของวัสดุ'
					},
					amount : {
						required : 'ระบุจำนวน',
						number : 'เฉพาะตัวเลข'
					},
					price : {
						required : 'ระบุราคา',
					},
					input_date : "ระบุวันที่"
				}
			});
			$('#btn-addgroup').on('click', function() {
				$modal = $('.modal-addgroup');
				$('#form-addgroup').validate({
					rules : {
						con_code : {
							remote : {
								url : 'DuplicateConsumable',
								type : 'post',
								data : {
									con_code : function() {
										return $('#con_code').val();
									}
								}
							}
						},
						con_name : {
							required : true,
							remote : {
								url : "DuplicateConName",
								type : 'post',
								data : {
									con_name : function() {
										return $('#con_name').val();
									}
								}
							}
						},
						unit : {
							required : true
						}
					},
					messages : {
						con_code : {
							remote : 'รหัสถูกใช้แล้ว'
						},
						con_name : {
							required : 'ระบุชื่อวัสดุ',
							remote : 'ชื่อถูกใช้แล้ว'
						},
						unit : {
							required : 'ระบหน่วยวัสดุ'
						}
					}

				});

				$modal.modal('show');
				$('#btn-close-modal').on('click', function() {
					$('.modal-addgroup').find("label[class='error']").hide();
					$('#con_code').val("");
					$('#con_name').val("");
					$('#unit').val("");
					$('#note-modal').val("");
				})
			});
		});
	</script>


	<div class="modal fade modal-addgroup" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="<%=request.getContextPath()%>/AddConGroup"
					method="post" id="form-addgroup">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">เพิ่มหมวด</h4>
					</div>
					<div class="modal-body col-lg-12 form-horizontal">

						<div class="form-group">
							<label class="col-sm-3 control-label">รหัสวัสดุ</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="con_code"
									id="con_code" placeholder="รหัสวัสดุ">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">ชื่อวัสดุ</label>
							<div class="col-sm-6">
								<input type="text" name="con_name" id="con_name"
									class="form-control" placeholder="ชื่อวัสดุ">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">หน่วยนับ</label>
							<div class="col-sm-6">
								<input type="text" name="unit" id="unit" class="form-control"
									placeholder="หน่วยนับ">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">จัดเก็บ</label>
							<div class="col-sm-6">
								<input name="storage" id="storage" type="text"
									class="form-control" placeholder="จัดเก็บ">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">*หมายเหตุ</label>
							<div class="col-sm-6">
								<textarea name="note-modal" id="note-modal" type="text"
									class="form-control" placeholder="หมายเหตุ"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button id="btn-add" type="submit" class="btn btn-success">เพิ่ม</button>
						<button type="button" class="btn btn-primary" id="btn-close-modal"
							data-dismiss="modal">ปิด</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>
</html>