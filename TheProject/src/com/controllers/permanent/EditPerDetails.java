package com.controllers.permanent;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PerDetailsDao;
import com.model.PerDetails;

/**
 * Servlet implementation class EditPerDetails
 */
@WebServlet("/EditPerDetails")
public class EditPerDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private PerDetailsDao pdDao = new PerDetailsDao();

	public EditPerDetails() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PerDetails item = new PerDetails();
		item.setAsset_id(Integer.parseInt(request.getParameter("id")));
		item.setAsset_code(request.getParameter("a_code"));
		item.setAsset_name(request.getParameter("a_name"));
		item.setNote(request.getParameter("a_note"));
		item.setStorage(request.getParameter("a_storage"));
		item.setStatus(request.getParameter("a_status"));
		item.setUse_status(request.getParameter("a_u_status"));
		item.setPer_id(Integer.parseInt(request.getParameter("p_id")));
		pdDao.update(item);
		response.sendRedirect(request.getContextPath() + "/EditPer?per_id=" + item.getPer_id());

	}

}
