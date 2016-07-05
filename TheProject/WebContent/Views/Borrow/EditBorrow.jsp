<%@page import="sun.misc.FpUtils"%>
<%@page import="com.model.BorrowDB"%>
<%@page import="com.model.BorrowDetails"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.PerDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	int count = 1;
	BorrowDB borItem = (BorrowDB) request.getAttribute("borItem");
	List<BorrowDetails> borDetailsItem = (List<BorrowDetails>) request.getAttribute("borDetailsItem");
	Users gobalUser = (Users) session.getAttribute("gobalUser");
	Users userItem = (Users)request.getAttribute("userItem");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>แก้ไขเอกสารยืมวัสดุ</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">แก้ไขเอกสารยืมวัสดุ</h1>
					</div>
					<form id="form-edit" action="EditBorrow" method="post">
						<input type="hidden" name="check_doc" id="check_doc"
							value="<%=borItem.getDocument_no()%>"> <input
							type="hidden" name="bor_id" id="bor_id"
							value="<%=borItem.getBor_id()%>">
						<div class="col-sm-12 text-right">
							<button type="button" class="btn btn-success btn-edit">บันทึก</button>
						</div>
						<input type="hidden" name="user_id" id="user_id"
							value="<%=borItem.getUser_id()%>">
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group">
									<label>เลขที่เอกสาร</label>
									<%
										if (gobalUser.getRole().equals("admin")) {
									%>
									<input name="document_no" id="document_no"
										value="<%=borItem.getDocument_no()%>" class="form-control"
										placeholder="เลขที่เอกสาร">
									<%
										} else {
									%>
									<input name="document_no" id="document_no"
										value="<%=borItem.getDocument_no()%>" class="form-control"
										placeholder="เลขที่เอกสาร" readonly="readonly">
									<%
										}
									%>
								</div>
								<div class="form-group">
									<label>ชื่อผู้ยืม</label> <input
										value="<%=userItem.getFirst_name() + " " + userItem.getLast_name()%>"
										class="form-control" placeholder="ชื่อผู้ยืม">
								</div>
								<div class="form-group">
									<label>เพื่อใช้ในงาน</label>
									<textarea class="form-control" rows="" cols=""
										placeholder="เพื่อใช้ในงาน" name="use_for" id="use_for"><%=borItem.getUse_for()%></textarea>

								</div>
								<div class="form-group">
									<label>วันที่ต้องการรับวัสดุ</label>
									<div class="form-group">
										<div class='input-group date' id='datetimepicker1'>
											<input name="date" id="date" value="<%=borItem.getDate()%>"
												type='text' class="form-control"
												placeholder="วันที่ต้องการรับวัสดุ" /> <span
												class="input-group-addon"> <span
												class="fa fa-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label>กำหนดส่งคืนวัสดุ</label>
									<div class="form-group">
										<div class='input-group date' id='datetimepicker2'>
											<input name="return_date" id="return_date"
												value="<%=borItem.getReturn_date()%>" type='text'
												class="form-control" placeholder="กำหนดส่งคืนวัสดุ" /> <span
												class="input-group-addon"> <span
												class="fa fa-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<%
									if (gobalUser.getRole().equals("admin")) {
								%>
								<div class="form-group">
									<label>สถานะ</label>
									<div class="form-group">
										<select class="form-control" name="status" id="status">
											<%
												if (borItem.getStatus().equals("Waiting")) {
											%>
											<option value="Waiting" selected="selected">Waiting</option>
											<option value="Approved">Approved</option>
											<option value="Returned">Returned</option>
											<%
												} else if (borItem.getStatus().equals("Approved")) {
											%>
											<option value="Waiting">Waiting</option>
											<option value="Approved" selected="selected">Approved</option>
											<option value="Returned">Returned</option>
											<%
												} else {
											%>
											<option value="Waiting">Waiting</option>
											<option value="Approved">Approved</option>
											<option value="Returned" selected="selected">Returned</option>
											<%
												}
											%>

										</select>
									</div>
								</div>
								<%
									}else{
								%>
								<input type="hidden" name="status" id="status"
									value="<%=borItem.getStatus()%>">
								<%} %>
							</div>
							<div class="col-sm-8">
								<div class="col-sm-12">
									<label>เลือกวัสดุ</label>
								</div>
								<div class="col-sm-12 form-inline">
									<div class="form-group">
										<input class="form-control" name="search" id="search"
											placeholder="รหัสวัสดุที่ต้องการเลิอก.....">
									</div>
									<div class="form-group">
										<a class="btn btn-warning btn-append" style="cursor: pointer;"><i
											class="fa fa-plus fa-fw"></i>เพิ่ม</a>
									</div>
									<div class="form-group">
										<a class="btn btn-info btn-find" style="cursor: pointer;"><i
											class="fa fa-search fa-fw"></i>ตรวจสอบวัสดุ</a>
									</div>
								</div>
								<div class="col-sm-12">
									<table class="table table-striped" id="myTable">
										<thead>
											<tr>
												<th class="text-center">รหัสวัสดุ</th>
												<th class="text-center">ชื่อวัสดุ</th>
												<th class="text-center">จัดการ</th>
											</tr>
										</thead>
										<%
											for (BorrowDetails item : borDetailsItem) {
										%>
										<tr>
											<input type="hidden" name="asset_id" id="asset_id"
												value="<%=item.getAsset_id()%>">
											<input type="hidden" name="asset_code" id="asset_code"
												value="<%=item.getAsset_code()%>">
											<td class="text-left"><%=item.getAsset_code()%></td>
											<td class="text-left"><%=item.getAsset_name()%></td>
											<td class="text-center"><a style="cursor: pointer;"
												href="javascript:void(0);" class="remCF"> <i
													class="fa fa-trash-o fa-fw"></i>ลบ
											</a></td>
										</tr>
										<%
											}
										%>
									</table>
								</div>
							</div>
						</div>
					</form>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->
	</div>


	<!-- modal find asset list  -->
	<div class="modal fade modal-find" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">รายการวัสดุที่ยืมได้</h4>
				</div>
				<div class="modal-body">
					<div class="input-group">
						<span class="input-group-addon">Filter</span> <input id="filter"
							type="text" class="form-control" placeholder="Type here...">
					</div>
					<div class="asset-list"></div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#datetimepicker1').datepicker();
			$('#datetimepicker2').datepicker();
			var countTable = 1;

			//call modal for show list asset
			$('.btn-find').on('click', function() {
				$modal = $('.modal-find');
				$.ajax({
					url : 'FindAssetList',
					type : 'get',
					data : $('#myTable :input').serialize()
				}).done(function(data) {
					var template = $('#table-modal').html();
					var rendered = Mustache.render(template, data);
					$modal.find('.asset-list').html(rendered);
				});
				//search in table modal
				$modal.find('#filter').keyup(function() {
					var rex = new RegExp($(this).val(), 'i');
					$('.searchable tr').hide();
					$('.searchable tr').filter(function() {
						return rex.test($(this).text());
					}).show();
				});
				//btn select asset code to textbox search

				$modal.modal('show');
			});

			$('#btn-select').on('click', function() {
				alert('sss');
			});

			//remove item from table list
			$("#myTable").on('click', '.remCF', function() {
				$(this).parent().parent().remove();
			});

			//append item to table list
			$('.btn-append').on('click', function() {
				var code = $('#search').val();
				var arr = $('#myTable input').serializeArray();
				var checkCode = true;
				$.each(arr, function(i, val) {
					if (val.value == code) {
						checkCode = false;
					}
				})
				if (checkCode != false) {
					if (code != "" | code != null) {
						$.ajax({
							url : 'FindPerCode',
							type : 'get',
							data : {
								code : code
							}
						}).done(function(data) {
							if (data != 'null') {
								var item = {
									countList : countTable++,
									id : data.asset_id,
									code : data.asset_code,
									name : data.asset_name
								}
								var templete = $('#add-to-table').html();
								var rendered = Mustache.render(templete, item);
								$('#myTable tr:last').after(rendered);
							}
						});
					}
				}
				$('#search').val("");
			});

			$('#form-edit').validate({
				rules : {
					document_no : {
						required : true,

						remote : {
							url : 'DuplicateDocBorrow',
							type : 'get',
							data : {
								document_no : function() {
									return $('#document_no').val();
								},
								check_doc : function() {
									return $('#check_doc').val();
								}
							}
						}
					},
					use_for : {
						maxlength : 200
					},
					date : {
						required : true,
						maxlength : 200
					},
					return_date : {
						required : true,
						maxlength : 200
					}
				},
				messages : {
					document_no : {
						required : 'ระบุเลขที่เอกสาร',
						maxlength : 'ไม่เกิน 200 ตัวอักษร',
						remote : 'เลขที่เอกสารมีอยู่แล้ว'
					},
					use_for : {
						maxlength : 'ไม่เกิน 200 ตัวอักษร'
					},
					date : {
						required : 'ระบุวันที่ต้องการรับวัสดุ',
						maxlength : 'ไม่เกิน 200 ตัวอักษร'
					},
					return_date : {
						required : 'ระบุกำหนดส่งคืนวัสดุ',
						maxlength : 'ไม่เกิน 200 ตัวอักษร'
					}
				}
			});

			$(".btn-edit").on('click', function(event) {
				var asset_id = $('#myTable input').serialize();
				if (asset_id == null | asset_id == "") {
					alert("ระบุวัสดุที่ต้องการยิม");
				} else {
					var check = $('#form-edit').valid();
					if (check != 0) {
						$("#form-edit").submit();
					}
				}
			});

		})
	</script>
	<script type="text/html" id="add-to-table">
	<tr>
		<input type="hidden" name="asset_id" id="asset_id" value="{{id}}">
		<input type="hidden" name="asset_code" id="asset_code" value="{{code}}">
		<td class="text-left">{{code}}
		</td>
		<td class="text-left">{{name}}
		</td>
		<td class="text-center">
			<a style="cursor: pointer;" href="javascript:void(0);" class="remCF">
				<i class="fa fa-trash-o fa-fw"></i>ลบ
			</a>
		</td>
	</tr>
	</script>


	<!--/ template modal show asset list/-->
	<script type="text/html" id="table-modal">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>รหัสวัสดุ</th>
						<th>ชื่อวัสดุ</th>
						<th>จัดการ</th>
					</tr>
				</thead>
				<tbody class="searchable">
					{{#.}}
					<tr>
						<td class="text-center"></td>
						<td class="text-left">{{asset_code}}</td>
						<td class="text-left">{{asset_name}}</td>
						<td class="text-center"><a style="cursor: pointer;"
							id="btn-select" data-code="{{asset_code}}"
							data-name="{{asset_name}}">select</a></td>
					</tr>
					{{/.}}
				</tbody>
			</table>
	</script>

</body>
</html>