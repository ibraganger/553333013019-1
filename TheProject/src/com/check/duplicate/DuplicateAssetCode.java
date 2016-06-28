package com.check.duplicate;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.PerDetailsDao;
import com.model.PerDetails;

/**
 * Servlet implementation class DuplicateAssetCode
 */
@WebServlet("/DuplicateAssetCode")
public class DuplicateAssetCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private PerDetailsDao pdDao = new PerDetailsDao();

	public DuplicateAssetCode() {
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
		String asset_code = request.getParameter("asset_code");
		String check_a_code = request.getParameter("check_a_code");
		PrintWriter out = response.getWriter();
		PerDetails per = pdDao.findAssetCode(asset_code);
		if (check_a_code != null) {
			if (check_a_code.equals(asset_code)) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
