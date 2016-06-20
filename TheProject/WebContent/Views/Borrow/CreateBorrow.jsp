<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.PerDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	int count = 1;
	List<PerDetails> assetList = (ArrayList<PerDetails>) request.getAttribute("assetList");
	Users gobalUser = (Users) session.getAttribute("gobalUser");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>สร้างเอกสารยืมวัสดุ</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">สร้างเอกสารยืมวัสดุ</h1>
					</div>
					<form method="post" id="form-create">
					<input type="hidden" name="user_id" id="user_id" value="<%=gobalUser.getUser_id()%>">
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group">
									<label>เลขที่เอกสาร</label> <input name="document_no"
										id="document_no" class="form-control"
										placeholder="เลขที่เอกสาร">
								</div>
								<div class="form-group">
									<label>ชื่อผู้ยืม</label> <input
										value="<%=gobalUser.getFirst_name() + " " + gobalUser.getLast_name()%>"
										class="form-control" placeholder="ชื่อผู้ยืม">
								</div>
								<div class="form-group">
									<label>เพื่อใช้ในงาน</label>
									<textarea class="form-control" rows="" cols=""
										placeholder="เพื่อใช้ในงาน" name="use_for" id="use_for"></textarea>

								</div>
								<div class="form-group">
									<label>วันที่ต้องการรับวัสดุ</label>
									<div class="form-group">
										<div class='input-group date' id='datetimepicker1'>
											<input name="date" id="date" type='text' class="form-control"
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
											<input name="return_date" id="return_date" type='text'
												class="form-control" placeholder="กำหนดส่งคืนวัสดุ" /> <span
												class="input-group-addon"> <span
												class="fa fa-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-6"
										style="padding-left: 0; padding-right: 0;">
										<button type="button" class="btn btn-success btn-block btn-create">สร้างเอกสาร</button>
									</div>
								</div>
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
						</tbody>
					</table>
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
					$modal.find('.searchable').html(rendered);
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
				$('.btn-select').on('click', function() {
					var code = $(this).data('code');
					alert(code);
				});
				$modal.modal('show');
			});

			//remove item from table list
			$("#myTable").on('click', '.remCF', function() {
				$(this).parent().parent().remove();
				countTable--;
			});

			//append item to table list
			$('.btn-append').on('click', function() {
				var code = $('#search').val();
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
				$('#search').val("");
			});

			$('#form-create').validate({
				rules : {
					document_no : {
						required : true,

						remote : {
							url : 'DuplicateDocBorrow',
							type : 'get',
							data : {
								document_no : function() {
									return $('#document_no').val();
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
			
			
			//create borrow form
			$('.btn-create').on('click',function(){
				var asset_id = $('#myTable input[id=\"asset_id\"]').serialize();
				if(asset_id != ''){
 					var validateForm =	$('#form-create').valid();
					if(validateForm != 0){
						var formJson = $('#form-create input,textarea').serialize();
						alert(formJson);
						$.ajax({
							url : 'CreateBorrow',
							type : 'POST',
							date : formJson
						}).done(function(){
							window.location.replace("Borrow");
						})
					} 
				}else{
					alert('กรุณาเลือกวัสดุที่ต้องการยืม');
				}
			});
		})
	</script>
	<script type="text/html" id="add-to-table">
	<tr>
		<input type="hidden" name="asset_id" id="asset_id" value="{{id}}">{{id}}
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
					{{#.}}
					<tr>
						<td class="text-center"></td>
						<td class="text-left">{{asset_code}}</td>
						<td class="text-left">{{asset_name}}</td>
						<td class="text-center"><a style="cursor: pointer;"
							class="btn-select" data-code="{{asset_code}}"
							data-name="{{asset_name}}" data-dismiss="modal">select</a></td>
					</tr>
					{{/.}}
	</script>

</body>
</html>