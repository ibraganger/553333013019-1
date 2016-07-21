<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="application/pdf" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%
	Connection con = null;
	try {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/project_asset?autoReconnect=true&useSSL=false";
		//String url = "jdbc:mysql://localhost:3306:3306/project_asset?useUnicode=true&characterEncoding=utf-8";
		String user = "root";
		String password = "1234";
		Class.forName(driver);
		//connection = DriverManager.getConnection(url, user, password);
		con = DriverManager.getConnection(url, user, password);

		String jrxmlFile = session.getServletContext().getRealPath("/Reporting/PermanentReport.jrxml");
		InputStream input = new FileInputStream(new File(jrxmlFile));
		JasperReport jasperReport = JasperCompileManager.compileReport(input);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);

		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (con != null) {
			con.close();
		}
	}
%>
