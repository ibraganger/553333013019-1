package com.controllers.repair;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PerDetailsDao;
import com.dao.RepairDao;
import com.dao.RepairDetailsDao;
import com.model.PerDetails;
import com.model.RepairDB;
import com.model.RepairDetails;
import com.model.Users;

/**
 * Servlet implementation class CreateRepair
 */
@WebServlet("/CreateRepair")
public class CreateRepair extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private RepairDao prDao = new RepairDao();
	private PerDetailsDao pdDao = new PerDetailsDao();
	private RepairDetailsDao rpdDao = new RepairDetailsDao();

	public CreateRepair() {
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
		HttpSession ss = request.getSession();
		String ssID = ss.getId();
		Users gobalUser = (Users) ss.getAttribute("gobalUser");
		if (ssID != null) {
			if (gobalUser != null) {
				request.getRequestDispatcher("Views/Repair/CreateRepair.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/Login");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/Login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String[] asset_id = request.getParameterValues("asset_id");
		RepairDB item = new RepairDB();
		item.setDate(request.getParameter("date"));
		item.setDocument_no(request.getParameter("document_no"));
		item.setNote(request.getParameter("note"));
		item.setReturn_date(request.getParameter("return_date"));
		item.setRepair_center(request.getParameter("repair_center"));
		item.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		item.setStatus("Repairing");
		prDao.add(item);
		item.setRepair_id(prDao.findDoc(item.getDocument_no()).getRepair_id());
		for (String list : asset_id) {
			PerDetails per = (PerDetails) pdDao.findAssetID(Integer.parseInt(list));
			RepairDetails obj = new RepairDetails();
			obj.setAsset_code(per.getAsset_code());
			obj.setAsset_id(Integer.parseInt(list));
			obj.setAsset_name(per.getAsset_name());
			obj.setNote(per.getNote());
			obj.setRepair_id(item.getRepair_id());
			rpdDao.add(obj);
		}
		response.sendRedirect(request.getContextPath() + "/Repair");
		
	}

}
