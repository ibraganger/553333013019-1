<%@page import="com.model.PerOfUser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.PerDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.model.Permanent"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<%
	Permanent per = (Permanent) request.getAttribute("per");
	ArrayList<PerDetails> pd = (ArrayList<PerDetails>) request.getAttribute("pd");
	ArrayList<PerOfUser> pou = (ArrayList<PerOfUser>) request.getAttribute("pou");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>แก้ไขวัสดุถาวร</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">แก้ไขวัสดุถาวร</h1>
					</div>
					<div class="col-sm-12">
						<form id="edit-form"
							action="<%=request.getContextPath() + "/EditPer"%>" method="post">
							<input type="hidden" name="check_per_name" id="check_per_name"
								value="<%=per.getPer_name()%>"> <input type="hidden"
								name="check_per_code" id="check_per_code"
								value="<%=per.getPer_code()%>">
							<div class="col-sm-12">
								<div class="col-sm-12 text-right">
									<button class="btn btn-success btn-submit" type="submit">Save
										change</button>
								</div>
							</div>
							<input type="hidden" name="per_id" id="per_id"
								value="<%=per.getPer_id()%>">
							<div class="col-sm-12">
								<div class="col-sm-4">
									<input type="hidden" name="status" id="status" value="N">
									<div class="form-group">
										<label>รหัสวัสดุ</label> <input name="per_code" id="per_code"
											type="text" class="form-control" placeholder="รหัสวัสดุ"
											value="<%=per.getPer_code()%>">
									</div>
									<div class="form-group">
										<label>ชื่อวัสดุ</label> <input name="per_name" id="per_name"
											type="text" class="form-control" placeholder="ชื่อวัสดุ"
											value="<%=per.getPer_name()%>">
									</div>
									<div class="form-group">
										<label>วันที่นำเข้า</label>
										<div class="input-group date" data-provide="datepicker"
											style="cursor: pointer;">
											<input name="input_date" id="input_date" type="text"
												class="form-control" value="<%=per.getInput_date()%>">
											<span class="input-group-addon" style="cursor: pointer">
												<span class="fa fa-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label>จำนวน</label> <input name="amount" id="amount"
											type="text" class="form-control" placeholder="จำนวน"
											value="<%=per.getAmount()%>">
									</div>
									<div class="form-group">
										<label>หน่วยนับ</label> <input name="unit" id="unit"
											type="text" class="form-control" placeholder="หน่วยนับ"
											value="<%=per.getUnit()%>">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label>ราคาต่อหน่วย</label> <input name="price" id="price"
											type="text" class="form-control" placeholder="ราคาต่อหน่วย"
											value="<%=per.getPrice()%>">
									</div>
									<div class="form-group">
										<label>อายุการใข้งาน</label> <input name="life_time"
											id="life_time" type="text" class="form-control"
											placeholder="อายุการใช้งาน" value="<%=per.getLife_time()%>">
									</div>
								</div>
							</div>
						</form>
						<div class="col-sm-12">
							<div class="col-sm-12 text-right">
								<a class="btn btn-default btn-add"><i class="fa fa-plus"></i></a>
							</div>
							<div class="col-sm-12">
								<table class="table my-table table-striped table-hover">
									<thead>
										<tr>
											<th class="text-left">รหัสวัสดุ</th>
											<th class="text-left">ชื่อวัสดุ</th>
											<th class="text-center">จัดเก็บ</th>
											<th class="text-center">*หมายเหตุ</th>
											<th class="text-center">สถานะ</th>
											<th class="text-center">สถานะใช้งาน</th>
											<th class="text-center">จัดการ</th>
										</tr>
									</thead>
									<tbody>
										<%
											if (pd != null) {
												for (PerDetails item : pd) {
										%>
										<tr>
											<td class="text-left"><%=item.getAsset_code()%></td>
											<td class="text-left"><%=item.getAsset_name()%></td>
											<td class="text-center"><%=item.getStorage()%></td>
											<td class="text-center"><%=item.getNote()%></td>
											<td class="text-center"><%=item.getStatus()%></td>
											<td class="text-center"><%=item.getUse_status()%></td>
											<td class="text-center"><a style="cursor: pointer;"><i
													class="fa fa-edit btn-edit"
													data-id="<%=item.getAsset_id()%>"
													data-code="<%=item.getAsset_code()%>"
													data-name="<%=item.getAsset_name()%>"
													data-storage="<%=item.getStorage()%>"
													data-status="<%=item.getStatus()%>"
													data-use_status="<%=item.getUse_status()%>"
													data-note="<%=item.getNote()%>"></i></a> | <a
												style="cursor: pointer; color: red;"><i
													class="fa fa-trash btn-remove"
													data-id="<%=item.getAsset_id()%>"
													data-code="<%=item.getAsset_code()%>"
													data-name="<%=item.getAsset_name()%>"></i></a></td>
										</tr>
										<%
											}
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".btn-add").on("click", function() {
				$modal = $(".modal-add");
				$modal.find("label[class='error']").hide();
				$modal.find("input").val("");
				$modal.find('#per_id').val($('#per_id').val());
				$modal.find('.form-add').validate({
					rules : {
						asset_code : {
							required : true,
							maxlength : 200,
							remote : {
								url : "DuplicateAssetCode",
								type : "get",
								data : {
									asset_code : function() {
										return $("#asset_code").val();
									}
								}
							}
						},
						asset_name : {
							required : true,
							maxlength : 200
						}
					},
					messages : {
						asset_code : {
							required : "ระบุรหัสวัสดุ",
							maxlength : "ไม่เกิน 200 ตัวอักษร",
							remote : "รหัสมีอยู่แล้ว"
						},
						asset_name : {
							required : "ระบุชื่อวัสดุ",
							maxlength : "ไม่เกิน 200 ตัวอักษร"
						}
					}
				});
				$modal.modal("show");
			});

			$(".btn-edit").on("click", function() {
				$modal = $(".modal-edit");
				var check = $(this).data('code');
				$modal.find("label[class='error']").hide();
				$modal.find("input").val("");
				$modal.find('#id').val($(this).data('id'));
				$modal.find('#a_code').val($(this).data('code'));
				$modal.find('#a_name').val($(this).data('name'));
				$modal.find('#a_storage').val($(this).data('storage'));
				$modal.find('#a_note').val($(this).data('note'));
				$modal.find('#a_status').val($(this).data('status'));
				$modal.find('#a_u_status').val($(this).data('use_status'));
				$modal.find('#p_id').val($('#per_id').val());
				$modal.find('.form-edit').validate({
					rules : {
						a_code : {
							required : true,
							maxlength : 200,
							remote : {
								url : "DuplicateAssetCode",
								type : "get",
								data : {
									asset_code : function() {
										return $("#a_code").val();
									},
									check_a_code : function() {
										return check;
									}
								}
							}
						},
						a_name : {
							required : true,
							maxlength : 200
						}
					},
					messages : {
						a_code : {
							required : "ระบุรหัสวัสดุ",
							maxlength : "ไม่เกิน 200 ตัวอักษร",
							remote : "รหัสมีอยู่แล้ว"
						},
						a_name : {
							required : "ระบุชื่อวัสดุ",
							maxlength : "ไม่เกิน 200 ตัวอักษร"
						}
					}
				});
				$modal.modal("show");
			});

			$(".btn-remove").on("click", function() {
				var asset_id = $(this).data("id");
				var perCode = 'รหัสวัสดุ : ', perName = 'ชื่อวัสดุ : ';
				$modal = $(".modal-remove");
				$modal.find('#set-code').text(perCode + $(this).data('code'));
				$modal.find('#set-name').text(perName + $(this).data('name'));
				$modal.find('.btn-confirm').on('click', function() {
					$.ajax({
						url : "RemovePer",
						type : "get",
						data : {
							asset_id : asset_id
						}
					}).done(function() {
						location.reload();
					})
				});
				$modal.modal("show");
			});

			$('#edit-form').validate({
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
								},
								check_per_code : function() {
									return $('#check_per_code').val();
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
								},
								check_per_name : function() {
									return $('#check_per_name').val();
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
			})
		});
	</script>


	<div class="modal fade modal-edit" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<form class="form-edit"
					action="<%=request.getContextPath()%>/EditPerDetails" method="post">
					<input type="hidden" name="p_id" id="p_id"
						value="<%=per.getPer_id()%>"> <input type="hidden"
						name="id" id="id" value=""> <input type="hidden"
						name="a_status" id="a_status" value=""> <input
						type="hidden" name="a_u_status" id="a_u_status" value="">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">แก้ไขรายการวัสดุ</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-sm-2"></div>
							<div class="col-sm-8">
								<div class="form-group">
									<label>รหัสวัสดุ</label> <input type="text"
										class="form-control" name="a_code" id="a_code"
										placeholder="รหัสวัสดุ">
								</div>
								<div class="form-group">
									<label>ชื่อวัสดุ</label> <input type="text"
										class="form-control" name="a_name" id="a_name"
										placeholder="ชื่อวัสดุ">
								</div>
								<div class="form-group">
									<label>จัดเก็บ</label> <input type="text" class="form-control"
										name="a_storage" id="a_storage" placeholder="จัดเก็บ">
								</div>
								<div class="form-group">
									<label>*หมายเหตุ</label> <input type="text"
										class="form-control" name="a_note" id="a_note"
										placeholder="*หมายเหตุ">
								</div>
							</div>
							<div class="col-sm-2"></div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save
							changes</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<div class="modal fade modal-add" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<form class="form-add" action="<%=request.getContextPath()%>/AddPer"
					method="post">
					<input type="hidden" name="per_id" id="per_id"
						value="<%=per.getPer_id()%>">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">เพิ่มวัสดุ</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>รหัสวัสดุ</label> <input type="text" class="form-control"
								name="asset_code" id="asset_code" placeholder="รหัสวัสดุ">
						</div>
						<div class="form-group">
							<label>ชื่อวัสดุ</label> <input type="text" class="form-control"
								name="asset_name" id="asset_name" placeholder="ชื่อวัสดุ">
						</div>
						<div class="form-group">
							<label>จัดเก็บ</label> <input type="text" class="form-control"
								name="per_storage" id="per_storage" placeholder="จัดเก็บ">
						</div>
						<div class="form-group">
							<label>*หมายเหตุ</label> <input type="text" class="form-control"
								name="note" id="note" placeholder="*หมายเหตุ">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Add new
							item</button>
					</div>

				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


	<div class="modal fade modal-remove" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">ลบวัสดุ</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<p>คุณต้องการดำเนินการลบวัสดุ</p>
							<p id="set-code"></p>
							<p id="set-name"></p>
							<p>วัสดุจะถูกลบออกจากฐานข้อมูลของระบบ
								หากคุรต้องการดำเนินการลบกรุณากด "Confirm"</p>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary btn-confirm">Confirm</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>
</html>
