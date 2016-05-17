package com.check.duplicate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PermanentDao;
import com.model.Permanent;

/**
 * Servlet implementation class DuplicatePerName
 */
@WebServlet("/DuplicatePerName")
public class DuplicatePerName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private PermanentDao perDao = new PermanentDao();

	public DuplicatePerName() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String per_name = request.getParameter("per_name");
		String check_per_name = request.getParameter("check_per_name");
		PrintWriter out = response.getWriter();
		Permanent per = new Permanent();
		per = perDao.findPer_name(per_name);
		if (check_per_name != null) {
			if (check_per_name.equals(per_name)) {
				out.println(true);
			} else {
				if (per != null) {
					out.println(false);
				} else {
					out.println(true);
				}
			}
		} else {
			if (per != null) {
				out.println(false);
			} else {
				out.println(true);
			}
		}
	}

}
