<%@page import="java.util.ArrayList"%>
<%@page import="com.model.Consumable"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	List<Consumable> list = (ArrayList<Consumable>) request.getAttribute("list");
	int count = 1;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/jspFile/css.jsp"%>
<%@include file="/jspFile/js.jsp"%>
<title>รายงานวัสดุสิ้นเปลือง</title>
</head>
<body>
	<div id="wrapper">
		<%@include file="/jspFile/header.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" style="margin-top: 50px;">
					<div class="col-lg-12">
						<h1 class="page-header">รายงานวัสดุสิ้นเปลือง</h1>
					</div>
					<div class="col-sm-12 text-right">
						<a href="#"
							onclick="tableToExcel('testTable', 'รานงานวัสดุสิ้นเปลือง')"><i
							class="fa fa-print fa-2x fa-fw"></i>พิมพ์</a>
					</div>
					<div class="col-sm-12 ">
						<table class="table table-bordered" id="testTable"
							summary="Code page support in different versions of MS Windows."
							frame="hsides" border="2">
							<caption class="text-center"></caption>
							<thead>
								<tr>
									<th colspan="7" class="text-center">คณะวิศวกรรศาสตร์
										วิทยาเขตขอนแก่น<br> สาขาวิชาวิศวกรรมคอมพิวเตอร์<br>
										รายการวัสดุสิ้นเปลืองคงเหลิอ
									</th>
								</tr>
								<tr>
									<th class="text-center" rowspan="2">ลำดับ</th>
									<th class="text-center" rowspan="2">รายการ</th>
									<th class="text-center" rowspan="2">หน่วยนับ</th>
									<th class="text-center" rowspan="2">จำนวนรับ</th>
									<th class="text-center" rowspan="2">จำนวนจ่าย</th>
									<th class="text-center" colspan="2">คงเหลือ</th>
								</tr>
								<tr>
									<th class="text-center">จำนวน</th>
									<th class="text-center">จำนวนเงิน</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (Consumable item : list) {
								%>
								<tr>
									<td class="text-center"><%=count++%></td>
									<td class="text-left"><%=item.getCon_name()%></td>
									<td class="text-center"><%=item.getUnit()%></td>
									<td class="text-center"><%=item.getImp_amount()%></td>
									<td class="text-center"><%=item.getExp_amount()%></td>
									<td class="text-center"><%=item.getAmount_tt()%></td>
									<td class="text-right"><%=item.getPrice_tt()%></td>
								</tr>
								<%
									}
								%>
							</tbody>

						</table>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
	</div>

	<script type="text/javascript">
		var tableToExcel = (function() {
			var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>', base64 = function(
					s) {
				return window.btoa(unescape(encodeURIComponent(s)))
			}, format = function(s, c) {
				return s.replace(/{(\w+)}/g, function(m, p) {
					return c[p];
				})
			}
			return function(table, name) {
				if (!table.nodeType)
					table = document.getElementById(table)
				var ctx = {
					worksheet : name || 'Worksheet',
					table : table.innerHTML
				}
				window.location.href = uri + base64(format(template, ctx))
			}
		})()
	</script>
</body>
</html>