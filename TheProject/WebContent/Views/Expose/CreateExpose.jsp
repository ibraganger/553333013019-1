<%@page import="java.util.ArrayList"%>
<%@page import="com.model.Consumable"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	Users gobalUser = (Users) session.getAttribute("gobalUser");
	List<Consumable> listCon = (ArrayList<Consumable>) request.getAttribute("listCon");
%>

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
					<form action="CreateExpose" method="post" id="form-create">
						<input type="hidden" name="user_id" id="user_id"
							value="<%=gobalUser.getUser_id()%>">
						<div class="col-sm-12 text-right">
							<button class="btn btn-success btn-create">สร้างเอกสาร</button>
						</div>
						<div class="col-sm-12">
							<div class="col-sm-3">
								<div class="form-group">
									<label>เลขที่เอกสาร</label> <input class="form-control"
										name="document_no" id="document_no" placeholder="เลขที่เอกสาร">
								</div>
								<div class="form-group">
									<label>ชื่อผู้ยืม</label> <input class="form-control" name=""
										id=""
										value="<%=gobalUser.getFirst_name() + " " + gobalUser.getLast_name()%>"
										placeholder="ชื่อผู้ยืม" readonly="readonly">
								</div>
								<div class="form-group">
									<label>เพื่อใช้ในงาน</label>
									<textarea class="form-control" name="use_for" id="use_for"
										placeholder="เพื่อใช้ในงาน"></textarea>
								</div>
								<div class="form-group">
									<label>ใช้งานในวันที่</label>
									<div class="input-group date" id="datePicker1">
										<input class="form-control" name="date" id="date"
											placeholder="วันที่ทำการเบิกวัสดุ"> <span
											class="input-group-addon"> <span><i
												class="fa fa-calendar"></i></span>
										</span>
									</div>
								</div>
							</div>
							<div class="col-sm-9">
								<div class="col-sm-12 ">
									<label>เลือกวัสดุ</label>
								</div>
								<div class="col-sm-12">
									<div class="form-inline">
										<div class="form-group">
											<select name="con_id" id="con_id" class="form-control">
												<option value="">---------- เลือกวัสดุ ----------</option>
												<%
													for (Consumable item : listCon) {
												%>
												<option value="<%=item.getCon_id()%>"><%=item.getCon_name()%></option>
												<%
													}
												%>
											</select>
										</div>
										<div class="form-group">
											<input type="number" style="width: 80px" class="form-control"
												title="แจ้งเตือน" data-placement="top" data-trigger="focus"
												data-content="กรุณาระบุข้อมูลให้ครบ" name="amount"
												id="amount" placeholder="จำนวน">
										</div>
										<a type="button" class="btn btn-warning btn-append"
											style="cursor: pointer;"> <i class="fa fa-plus fa-fw"></i>เพิ่ม
										</a> <a class="btn btn-info btn-find" style="cursor: pointer;">
											<i class="fa fa-search fa-fw"></i>ค้นหาวัสดุ
										</a>
									</div>
								</div>
								<div class="col-sm-12">
									<table id="myTable" class="table table-striped">
										<thead>
											<tr>
												<th class="text-center">รหัสวัสดุ</th>
												<th class="text-center">ชื่อวัสดุ</th>
												<th class="text-center">จำนวน</th>
												<th class="text-center">จัดการ</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>



		<div class="modal fade modal-find" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">ค้นหาวัสดุ</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-sm-12">
								<div class="input-group">
									<span class="input-group-addon">Filter</span> <input
										id="filter" type="text" class="form-control"
										placeholder="Type here...">
								</div>
							</div>
							<div class="col-sm-12">
								<table class="table table-striped">
									<thead>
										<th class="text-center">#</th>
										<th class="text-center">รหัสวัสดุ</th>
										<th class="text-center">ชื่อวัสดุ</th>
										<th class="text-center">จำนวน</th>
									</thead>
									<tbody class="searchable">
										<%
											for (Consumable item : listCon) {
										%>
										<tr>
											<td class="text-center">#</td>
											<td class="text-left"><%=item.getCon_code()%></td>
											<td class="text-left"><%=item.getCon_name()%></td>
											<td class="text-center"><%=item.getAmount_tt()%></td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->


		<script type="text/javascript">
			//date picker
			$('#datePicker1').datepicker()

			//find asset list on modal
			$('.btn-find').on('click', function() {
				$modal = $('.modal-find');
				//fillter asset
				$modal.find('#filter').keyup(function() {
					var rex = new RegExp($(this).val(), 'i');
					$('.searchable tr').hide();
					$('.searchable tr').filter(function() {
						return rex.test($(this).text());
					}).show();
				});
				$modal.modal('show');
			})

			//validate create form
			$('#form-create').validate({
				rules : {
					document_no : {
						required : true,
						remote : {
							url : 'DuplicateDocExpose',
							type : 'get',
							data : {
								document_no : function() {
									return $('#document_no').val();
								}
							}
						}
					},
					date : {
						required : true
					}
				},
				messages : {
					document_no : {
						required : 'ระบุเลขที่เอกสาร',
						remote : 'เลขที่วัสดุถูกใช้งานแล้ว'
					},
					date : {
						required : 'ระบุวันที่ต้องการใช้งาน'
					}
				}
			});

			//append asset to table list
			$('.btn-append').on('click', function() {
				var id = $('#con_id').val();
				var amount = $('#amount').val();
				if (amount != "" & id != "") {
					$.ajax({
						url : 'FindConsumable',
						type : 'get',
						data : {
							con_id : id
						}
					}).done(function(result) {
						result = JSON.parse(result);
						if (amount <= 0) {
							alert('ระบุจำนวนวัสดุอย่างน้อย 1  ชิ้น');
						} else if (result.amount_tt < amount) {
							alert('จำนวนวัสดุมีไม่พอ');
						} else {
							var template = $('#table-template').html();
							var data = {
								id : result.con_id,
								code : result.con_code,
								name : result.con_name,
								amount : amount
							};
							var rendered = Mustache.render(template, data);
							$('#myTable tr:last').after(rendered);
							$('#con_id').val("");
							$('#amount').val("");
						}
					})
				} else {
					$('#amount').popover('show');
					setTimeout(function() {
						$('#amount').popover('hide');
					}, 1500);
				}
			});

			//remove item from table list
			$("#myTable").on('click', '.remCF', function() {
				$(this).parent().parent().remove();
			});

			//create form
			$('.btn-create').on('click', function() {
				var check = $('#form-create').valid();
				var con_id = $('#myTable input[name=\"id_con\"]').serialize();
				console.log(con_id);
				if (con_id != "") {
					if (check != false) {
						$('#form-create').submit();
					} else {
						alert('กรุณาระบุข้อมูลให้ครบถ้วน');
					}
				} else {
					alert('กรุณาระบุวัสดุที่ต้องการเบิก');
				}
			})
		</script>

		<script type="text/html" id="table-template">
				<tr>
					<input type="hidden" name="id_con" id="id_con" value="{{id}}">
					<input type="hidden" name="amount_con" id="amount_con" value="{{amount}}">
					<td class="text-center">{{code}}</td>
					<td class="text-center">{{name}}</td>
					<td class="text-center">{{amount}}</td>
					<td class="text-center">
						<a style="cursor: pointer;" href="javascript:void(0);" class="remCF">
							<i class="fa fa-trash-o fa-fw"></i>ลบ
						</a>
					</td>
			 	</tr>
		</script>
</body>
</html>