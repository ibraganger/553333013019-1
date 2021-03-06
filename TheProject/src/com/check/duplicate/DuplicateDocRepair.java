package com.check.duplicate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BorrowDao;
import com.dao.RepairDao;
import com.model.BorrowDB;
import com.model.RepairDB;

/**
 * Servlet implementation class DuplicateDocRepair
 */
@WebServlet("/DuplicateDocRepair")
public class DuplicateDocRepair extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DuplicateDocRepair() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		RepairDao reDao = new RepairDao();

		String document_no = request.getParameter("document_no");
		String check_doc = request.getParameter("check_doc");
		PrintWriter out = response.getWriter();
		RepairDB item = reDao.findDoc(document_no);
		if (check_doc != null) {
			if (check_doc.equals(document_no)) {
				out.println(true);
			} else {
				if (item != null) {
					out.println(false);
				} else {
					out.println(true);
				}
			}
		} else {
			if (item != null) {
				out.println(false);
			} else {
				out.println(true);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
