<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>เพิ่มวัสดุถาวร</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">เพิ่มวัสดุถาวร</h1>
					</div>
					<div class="col-sm-12">
						<form id="creat-form">
							<div class="col-sm-12">
								<div class="col-sm-12 text-right">
									<a class="btn btn-success btn-submit">Submit</a>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-4">
									<input type="hidden" name="status" id="status" value="N">
									<div class="form-group">
										<label>รหัสวัสดุ</label> <input name="per_code" id="per_code"
											type="text" class="form-control" placeholder="รหัสวัสดุ">
									</div>
									<div class="form-group">
										<label>ชื่อวัสดุ</label> <input name="per_name" id="per_name"
											type="text" class="form-control" placeholder="ชื่อวัสดุ">
									</div>
									<div class="form-group">
										<label>วันที่นำเข้า</label>
										<div class="input-group date" data-provide="datepicker"
											style="cursor: pointer;">
											<input name="input_date" id="input_date" type="text"
												class="form-control"> <span
												class="input-group-addon" style="cursor: pointer"> <span
												class="fa fa-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label>จำนวน</label> <input name="amount" id="amount"
											type="text" class="form-control" placeholder="จำนวน">
									</div>
									<div class="form-group">
										<label>หน่วยนับ</label> <input name="unit" id="unit"
											type="text" class="form-control" placeholder="หน่วยนับ">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label>ราคาต่อหน่วย</label> <input name="price" id="price"
											type="text" class="form-control" placeholder="ราคาต่อหน่วย">
									</div>
									<div class="form-group">
										<label>อายุการใข้งาน</label> <input name="life_time"
											id="life_time" type="text" class="form-control"
											placeholder="อายุการใช้งาน">
									</div>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="col-sm-12 text-right">
									<a class="btn btn-default btn-add"><i class="fa fa-plus"></i></a>
								</div>
								<div class="col-sm-12">
									<table class="table my-table">
										<thead>
											<tr>
												<th class="text-center">รหัสวัสดุ</th>
												<th class="text-center">ชื่อวัสดุ</th>
												<th class="text-center">จัดเก็บ</th>
												<th class="text-center">*หมายเหตุ</th>
												<th class="text-center">จัดการ</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
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
			$('.btn-add').on('click', function() {
				$('.my-table').append($('#in-table').html());
			});

			$(".my-table").on('click', '.btn-remove', function() {
				$(this).parent().parent().remove();
			});

			$('.btn-check').on('click', function() {
				var text = $('#creat-form').serialize();
				console.log(text);
			})

			$('#creat-form').validate({
				rules : {
					per_code : {
						required : true,
						maxlength : 200,
						remote : {
							url : "DuplicatePerCode",
							type : 'post',
							data : {
								per_code : function() {
									return $("#per_code").val();
								}
							}
						}
					},
					per_name : {
						required : true,
						maxlength : 200,
						remote : {
							url : "DuplicatePerName",
							type : 'post',
							data : {
								per_name : function() {
									return $("#per_name").val();
								}
							}
						}
					},
					amount : {
						required : true,
						number : true
					},
					unit : {
						required : true,
						maxlength : 50
					},
					price : {
						required : true,
						number : true
					},
					life_time : {
						required : true,
						number : true
					},
					input_date : {
						required : true,
						maxlength : 10
					}
				},
				messages : {
					per_code : {
						required : "ระบุรหัสวัสดุ",
						maxlength : "ไม่เกิน 200 ตัวอักษร",
						remote : "รหัสถูกใช้แล้ว"
					},
					per_name : {
						required : "ระบุชื่อวัสดุ",
						maxlength : "ไม่เกิน 200 ตัวอักษร",
						remote : "ชื่อถูกใช้แล้ว"
					},
					amount : {
						required : "ระบุจำนวน",
						number : "เฉพาะตัวเลข"
					},
					unit : {
						required : "ระบุหน่วยนับ",
						maxlength : "ไม่เกิน 50 ตัวอักษร"
					},
					price : {
						required : "ระบุราคาต่อชิ้น",
						number : "เฉพาะตัวเลข"
					},
					life_time : {
						required : "ระบุอายุการใช้งาน",
						number : "เฉพาะตัวเลข"
					},
					input_date : {
						required : "ระบุวันที่นำเข้า",
						maxlength : "ไม่เกิน 10 ตัวอักษร"
					}
				}
			});

			$('.btn-submit').on('click', function() {
				var asset = $(".my-table input").serialize();
				if (asset != "") {
					var xx = $('#creat-form input').valid();
					var formJson = $('#creat-form input,textarea').serialize();
					if (xx != 0) {
						$.ajax({
							type : "POST",
							url : "CreatePer",
							data : formJson,
						}).done(function() {
							window.location.replace("Permanent");
						});
					}
				} else {
					alert('กรุณาระบุตารางรายการวัสดุถาวรที่ต้องการเพิ่ม');
				}
			})

		});
	</script>

	<script id="in-table" type="text/html">
		<tr>
			<td><input type="text" class="form-control" name="asset_code" id="asset_code"></td>
			<td><input type="text" class="form-control" name="asset_name" id="asset_name"></td>
			<td><input type="text" class="form-control" name="storage" id="storage"></td>
			<td><input type="text" class="form-control" name="note" id="note"></td>
			<td><a href="#" class="btn-remove"><i class="fa fa-trash fa-fw"></i>ลบ</a></td>
		</tr>
</script>
</body>
</html>
