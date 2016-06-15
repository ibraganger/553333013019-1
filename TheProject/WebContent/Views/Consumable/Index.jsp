<%@page import="com.model.Users"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.Consumable"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	Users gobalUser = (Users) session.getAttribute("gobalUser");
	List<Consumable> conList = (ArrayList<Consumable>) request.getAttribute("conList");
	int count = 1;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>วัสดุสิ้นเปลือง</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">วัสดุสิ้นเปลือง</h1>
					</div>
					<div class="col-lg-12"">
						<form class="form-inline">
							<div class="form-group">
								<input type="text" name="search" id="search"
									value="<%=request.getAttribute("search")%>"
									class="form-control" placeholder="ค้นหารหัสวัสดุ,ชื่อวัสดุ">
							</div>
							<button type="submit" class="btn btn-info">
								<i class="fa fa-search fa-fw"></i>ค้นหา
							</button>
							<a href="<%=request.getContextPath() + "/Consumable"%>"
								class="btn btn-warning"><i class="fa fa-refresh fa-fw"></i>รีเซ็ต</a>
							<%
								if (gobalUser.getRole().equals("admin")) {
							%>
							<a href="<%=request.getContextPath()%>/CreateCon"
								class="btn btn-success"><i class="fa fa-plus fa-fw"></i>เพิ่ม</a>
							<%
								}
							%>
						</form>
					</div>
					<div class="col-sm-12 text-right">
						<a href="<%=request.getContextPath()%>/PrintConsum"><i
							class="fa fa-print fa-2x fa-fw"></i>พิมพ์</a>
					</div>
					<div class="col-lg-12">
						<table class="table table-striped" id="dataTables">
							<thead>
								<tr>
									<th>#</th>
									<th>รหัส</th>
									<th>ชื่อวัสดุ</th>
									<th class="text-center">จำนวน</th>
									<th class="text-center">หน่วย</th>
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
									for (Consumable list : conList) {
								%>
								<tr>
									<td><%=count++%></td>
									<td><%=list.getCon_code()%></td>
									<td><%=list.getCon_name()%></td>
									<td class="text-center"><%=list.getAmount_tt()%></td>
									<td class="text-center"><%=list.getUnit()%></td>
									<%
										if (gobalUser.getRole().equals("admin")) {
									%>
									<td class="text-center"><a
										href="<%=request.getContextPath() + "/ConsumHis?con_id=" + list.getCon_id()%>"
										style="color: lightsalmon;"><i class="fa fa-expand"></i></a> |
										<a class="btn-edit" data-id="<%=list.getCon_id()%>"
										data-code="<%=list.getCon_code()%>"
										data-name="<%=list.getCon_name()%>"
										data-amount_tt="<%=list.getAmount_tt()%>"
										data-unit="<%=list.getUnit()%>"
										data-note="<%=list.getNote()%>"
										data-imp_amount="<%=list.getImp_amount()%>"
										data-exp_amount="<%=list.getExp_amount()%>"
										data-storage="<%=list.getStorage()%>"
										data-price="<%=list.getPrice()%>" style="cursor: pointer;"><i
											class="fa fa-gear"></i></a> | <a class="btn-delete"
										style="color: red; cursor: pointer;"
										data-id="<%=list.getCon_id()%>"
										data-code="<%=list.getCon_code()%>"
										data-name="<%=list.getCon_name()%>"><i class="fa fa-trash"></i></a>
									</td>
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
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {

			$('#dataTables').DataTable({
				responsive : true,
				"searching" : false
			});

			$('.btn-delete').on('click', function() {
				$modal = $('.modal-delete');
				var id = $(this).data('id');
				$modal.find('#set_con_code').text($(this).data('code'));
				$modal.find('#set_con_name').text($(this).data('name'));
				$modal.modal('show');
				$modal.find('.btn-modal-delete').on('click', function() {
					$.ajax({
						type : 'POST',
						url : 'DeleteConsum',
						data : {
							id : id
						}
					}).done(function() {
						location.reload();
					});
				})
			});

			$('.btn-edit').on('click', function() {
				$modal = $('.modal-edit');
				$modal.find('input').val('');
				$modal.find("label[class='error']").hide();
				$modal.find('#con_id').val($(this).data('id'));
				$modal.find('#con_code').val($(this).data('code'));
				$modal.find('#check_code').val($(this).data('code'));
				$modal.find('#con_name').val($(this).data('name'));
				$modal.find('#check_name').val($(this).data('name'));
				$modal.find('#unit').val($(this).data('unit'));
				$modal.find('#note-modal').val($(this).data('note'));
				$modal.find('#imp_amount').val($(this).data('imp_amount'));
				$modal.find('#exp_amount').val($(this).data('exp_amount'));
				$modal.find('#amount_tt').val($(this).data('amount_tt'));
				$modal.find('#storage').val($(this).data('storage'));
				$modal.find('#price').val($(this).data('price'));
				$('.form-editgroup').validate({
					rules : {
						con_code : {
							required : true,
							remote : {
								url : 'DuplicateConsumable',
								type : 'post',
								data : {
									con_code : function() {
										return $('#con_code').val();
									},
									check_code : function() {
										return $('#check_code').val();
									}
								}
							}
						},
						con_name : {
							required : true,
							remote : {
								url : 'DuplicateConName',
								type : 'post',
								data : {
									con_name : function() {
										return $('#con_name').val();
									},
									check_name : function() {
										return $('#check_name').val();
									}
								}
							}
						}
					},
					messages : {
						con_code : {
							required : "ระบุรหัสวัสดุ",
							remote : "รหัสถูกใช้งานแล้ว"
						},
						con_name : {
							required : "ระบุชื่อวัสดุ",
							remote : "ชื่อถูกใช้งานแล้ว"
						}
					}
				})
				$modal.modal('show');
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
					<h4 class="modal-title">ลบวัสดุสิ้นเปลือง</h4>
				</div>
				<div class="modal-body">
					<p>คุณต้องการดำเนินการลบ</p>
					<p id="set_con_code"></p>
					<p id="set_con_name"></p>
					<p>ออกจากฐานข้อมูลของระบบ</p>
					<p>ระบบไม่สามารถกู้คืนข้อมูลที่ถูกลบได้
						หากท่านต้องการดำเนินการลบ กรุณากด "ลบวัสดุ"</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary btn-modal-delete">ลบวัสดุ</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<div class="modal fade modal-edit" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="EditConsumable" method="post" class="form-editgroup">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">แก้ไขวัสดุสิ้นเปลือง</h4>
					</div>
					<div class="modal-body col-lg-12 form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label">รหัสวัสดุ</label> <input
								type="hidden" name="con_id" id="con_id"> <input
								type="hidden" name="check_code" id="check_code"> <input
								type="hidden" name="check_name" id="check_name">
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
							<label class="col-sm-3 control-label">ราคา</label>
							<div class="col-sm-6">
								<input type="text" name="price" id="price" class="form-control"
									placeholder="ราคา">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">จำนวนรับ</label>
							<div class="col-sm-6">
								<input type="text" name="imp_amount" id="imp_amount"
									class="form-control" placeholder="จำนวนรับ">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">จำนวนจ่าย</label>
							<div class="col-sm-6">
								<input type="text" name="exp_amount" id="exp_amount"
									class="form-control" placeholder="จำนวนจ่าย">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">คงเหลือ</label>
							<div class="col-sm-6">
								<input type="text" name="amount_tt" id="amount_tt"
									class="form-control" placeholder="คงเหลือ">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">จัดเก็บ</label>
							<div class="col-sm-6">
								<input type="text" name="storage" id="storage"
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
						<button id="btn-add" type="submit" class="btn btn-warning">บันทึก</button>
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