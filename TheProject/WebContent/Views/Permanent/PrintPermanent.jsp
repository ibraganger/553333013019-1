<%@page import="com.model.PerDetails"%>
<%@page import="com.model.Permanent"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	List<Permanent> perList = (List<Permanent>) request.getAttribute("perList");
	List<PerDetails> pdList = (List<PerDetails>) request.getAttribute("pdList");
	double sumPrice = 0.0;
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
						<h1 class="page-header">รายงานวัสดุถาวร</h1>
					</div>
					<div class="col-sm-12 text-right">
						<a style="cursor: pointer;" onclick="tableToExcel('testTable', 'รายงานวัสดุถาวร')"><i
							class="fa fa-print fa-2x fa-fw"></i>พิมพ์</a>
					</div>
					<div class="col-sm-12">
						<table class="table table-bordered" id="testTable"
							summary="Code page support in different versions of MS Windows."
							frame="hsides" border="2">
							<thead>
								<tr>
									<th class="text-center" rowspan="2">วันที่ซื้อ</th>
									<th class="text-center" rowspan="2">หมายเลขครุภัณฑ์</th>
									<th class="text-center" rowspan="2">รายการ</th>
									<th class="text-center" rowspan="2">จำนวน</th>
									<th class="text-center" rowspan="2">หน่วยนับ</th>
									<th class="text-center" rowspan="2">ราคาต่อหน่วย</th>
									<th class="text-center" rowspan="2">ราคารวม</th>
									<th class="text-center" colspan="2">สภาพ</th>
									<th class="text-center" rowspan="2">สถานที่จัดเก็บ /
										ใช้งาน</th>
									<th class="text-center" rowspan="2">หมายเหตุ</th>
								</tr>
								<tr>
									<th class="text-center">ปกติ</th>
									<th class="text-center">ชำรุด</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="11" class="text-center">สาขาวิชาวิศวกรรมคอมพิวเตอร์</td>
								</tr>
								<%
									for (Permanent list : perList) {
										sumPrice = sumPrice + list.getPeice_sum();
								%>
								<tr>
									<td class="text-center"><%=list.getInput_date()%></td>
									<td class="text-left"><%=list.getPer_code()%></td>
									<td class="text-left"><%=list.getPer_name()%></td>
									<td class="text-center"><%=list.getAmount()%></td>
									<td class="text-center"><%=list.getUnit()%></td>
									<td class="text-right"><%=list.getPrice()%></td>
									<td class="text-right"><%=list.getPeice_sum()%></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<th>ประกอบด้วย</th>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>

								<%
									for (PerDetails item : pdList) {
											if (item.getPer_id() == list.getPer_id()) {
								%>
								<tr>
									<td></td>
									<td class="text-left"><%=item.getAsset_code()%></td>
									<td class="text-left"><%=item.getAsset_name()%></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<%
										if (item.getStatus().equals("Normal")) {
									%>
									<td class="text-center">/</td>
									<td class="text-center"></td>
									<%
										} else {
									%>
									<td class="text-center"></td>
									<td class="text-center">/</td>
									<%
										}
									%>
									<td></td>
									<td></td>
								</tr>
								<%
									}
										}
								%>


								<%
									}
								%>
								<tr>
									<td colspan="5"></td>
									<th class="text-center">รวมทั้งสิ้น</th>
									<td class="text-right"><%=sumPrice%></td>
									<td colspan="4"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->
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