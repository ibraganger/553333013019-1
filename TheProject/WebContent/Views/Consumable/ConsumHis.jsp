<%@page import="javax.rmi.CORBA.Tie"%>
<%@page import="com.model.ConsumDetails"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	ArrayList<ConsumDetails> list = (ArrayList<ConsumDetails>) request.getAttribute("list");
	int count = 1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header"><%=request.getAttribute("conName")%></h1>
						<input type="hidden" name="con_id" id="con_id"
							value="<%=request.getAttribute("con_id")%>">
						<div class="col-sm-12">
							<form class="form-inline">
								<div class="form-group">
									<input type="text" class="form-control" id="exampleInputName2"
										placeholder="Jane Doe">
								</div>
								<button type="submit" class="btn btn-success">
									<i class="fa fa-search fa-fw"></i>ค้นหา
								</button>
							</form>
						</div>
						<div class="col-sm-12">
							<table class="table table-striped">
								<thead>
									<tr>
										<th class="text-center">#</th>
										<th class="text-center">จำนวน</th>
										<th class="text-center">ราคาต่อชิ้น</th>
										<th class="text-center">ราคารวม</th>
										<th class="text-center">วันที่นำเข้า</th>
										<th class="text-center">จัดการ</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (ConsumDetails item : list) {
									%>

									<tr>
										<td class="text-center"><%=count++%></td>
										<td class="text-center"><%=item.getAmount()%></td>
										<td class="text-right"><%=item.getPrice()%></td>
										<td class="text-right"><%=item.getPrice_sum()%></td>
										<td class="text-center"><%=item.getInput_date()%></td>
										<td class="text-center"><a class="btn-edit"
											style="cursor: pointer;" data-id="<%=item.getId()%>"
											data-amount="<%=item.getAmount()%>"
											data-price="<%=item.getPrice()%>"
											data-input_date="<%=item.getInput_date()%>"
											data-note="<%=item.getNote()%>"><i class="fa fa-edit"></i></a>
											| <a class="btn-delete" style="cursor: pointer; color: red;"
											data-id="<%=item.getId()%>"
											data-amount="<%=item.getAmount()%>"
											data-price="<%=item.getPrice()%>"
											data-sum_price="<%=item.getPrice_sum()%>"
											data-input_date="<%=item.getInput_date()%>"><i
												class="fa fa-trash"></i></a></td>
									</tr>

									<%
										}
									%>
								</tbody>
							</table>
						</div>

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

			$('.btn-delete').on('click', function() {
				$modal = $('.modal-delete');
				var amount = "จำนวน : ";
				var price = "ราคา : ";
				var sumPrice = "ราคารวม : ";
				var inputDate = "วันที่นำเข้า : ";
				var id = $(this).data('id');
				$modal.find('#amount').text(amount + $(this).data('amount'));
				$modal.find('#price').text(price + $(this).data('price'));
				$modal.find('#sumPrice').text($(this).data('sum_price'));
				$modal.find('#inputDate').text($(this).data('input_date'));
				$modal.find('.btn-confirm').on('click', function() {
					$.ajax({
						url : "DeleteComsumHis",
						type : 'get',
						data : {
							id : id
						}
					}).done(function() {
						location.reload();
					})
				})
				$modal.modal('show');
			})

			$('.btn-edit').on('click', function() {
				$modal = $('.modal-edit');
				$modal.find('label[class="error"]').hide();
				$modal.find('input').val("");

				$modal.find('#c_id').val($('#con_id'));
				$modal.find('#id').val($(this).data('id'));
				$modal.find('#amount').val($(this).data('amount'));
				$modal.find('#price').val($(this).data('price'));
				$modal.find('#input_date').val($(this).data('input_date'));
				$modal.find('#note').val($(this).data('note'));
				var today = new Date();
				$('#input_date').datepicker({
					endDate : today
				}).on('changeDate', function(e) {
					$(this).datepicker('hide');
				})
				$modal.find('#form-edit').validate({
					rules : {
						amount : {
							required : true
						},
						price : {
							required : true
						},
						input_date : {
							required : true
						}
					},
					messages : {
						amount : {
							required : "ระบุจำนวน"
						},
						price : {
							required : "ระบุราคา"
						},
						input_date : {
							required : "ระบุวันที่"
						}
					}
				});
				$modal.modal('show');
			})

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
					<h4 class="modal-title">ลบวัสดุ</h4>
				</div>
				<div class="modal-body">
					<p>คุรต้องการลบวัสดุ รายละเอียดดังนี้</p>
					<p id="amount"></p>
					<p id="price"></p>
					<p id="sumPrice"></p>
					<p id="inputDate"></p>
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

	<div class="modal fade modal-edit" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">

				<form id="form-edit" action="EditConsumHis" method="post">
					<input type="hidden" name="c_id" id="c_id">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">แก้ไขวัสดุ</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>จำนวน</label> <input type="text" class="form-control"
								name="amount" id="amount" placeholder="จำนวน">
						</div>
						<div class="form-group">
							<label>ราคาต่อชิ้น</label> <input type="text"
								class="form-control" name="price" id="price"
								placeholder="ราคาต่อชิ้น">
						</div>
						<div class="form-group">
							<label>วันที่นำเข้า</label>
							<div class="input-group date" id="date"
								data-date-format="dd/mm/yyyy" style="cursor: pointer;">
								<input name="input_date" id="input_date" type="text"
									class="form-control" value="">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-th"></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label>*หมายเหตุ</label> <input type="text" class="form-control"
								name="note" id="note" placeholder="*หมายเหตุ">
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
</body>
</html>