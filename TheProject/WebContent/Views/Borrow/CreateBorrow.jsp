<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.PerDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	int count = 1;
	List<PerDetails> assetList = (ArrayList<PerDetails>) request.getAttribute("assetList");
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
					<div class="col-sm-12">
						<div class="col-sm-4">
							<div class="form-group">
								<label>เลขที่เอกสาร</label> <input class="form-control"
									placeholder="เลขที่เอกสาร">
							</div>
							<div class="form-group">
								<label>ชื่อผู้ยืม</label> <input class="form-control"
									placeholder="ชื่อผู้ยืม">
							</div>
							<div class="form-group">
								<label>เพื่อใช้ในงาน</label>
								<textarea class="form-control" rows="" cols=""
									placeholder="เพื่อใช้ในงาน"></textarea>

							</div>
							<div class="form-group">
								<label>วันที่ต้องการรับวัสดุ</label>
								<div class="form-group">
									<div class='input-group date' id='datetimepicker1'>
										<input type='text' class="form-control"
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
										<input type='text' class="form-control"
											placeholder="กำหนดส่งคืนวัสดุ" /> <span
											class="input-group-addon"> <span
											class="fa fa-calendar"></span>
										</span>
									</div>
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
							<%
								for (PerDetails item : assetList) {
							%>
							<tr>
								<td class="text-center"><%=count++%></td>
								<td class="text-left"><%=item.getAsset_code()%></td>
								<td class="text-left"><%=item.getAsset_name()%></td>
								<td class="text-center"><a style="cursor: pointer;"
									class="btn-select" data-code="<%=item.getAsset_code()%>"
									data-name="<%=item.getAsset_name()%>" data-dismiss="modal">select</a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
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
				//search in table modal
				$('#filter').keyup(function() {
					var rex = new RegExp($(this).val(), 'i');
					$('.searchable tr').hide();
					$('.searchable tr').filter(function() {
						return rex.test($(this).text());
					}).show();
				});
				//btn select asset code to textbox search
				$('.btn-select').on('click', function() {
					var code = $(this).data('code');
					$('#search').val(code);
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
		})
	</script>
	<script type="text/html" id="add-to-table">
	<tr>
		<td class="text-left">
			<input type="hidden" name="code" id="code" value="{{code}}">{{code}}
		</td>
		<td class="text-left">
			<input type="hidden" name="name" id="name" value="{{name}}">{{name}}
		</td>
		<td class="text-center">
			<a style="cursor: pointer;" href="javascript:void(0);" class="remCF">
				<i class="fa fa-trash-o fa-fw"></i>ลบ
			</a>
		</td>
	</tr>
	</script>
</body>
</html>