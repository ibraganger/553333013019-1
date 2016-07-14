package com.controllers.repair;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PerDetailsDao;
import com.dao.RepairDao;
import com.dao.RepairDetailsDao;
import com.dao.UsersDao;
import com.model.PerDetails;
import com.model.RepairDB;
import com.model.RepairDetails;
import com.model.Users;

/**
 * Servlet implementation class EditRepair
 */
@WebServlet("/EditRepair")
public class EditRepair extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditRepair() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	private RepairDao rpDao = new RepairDao();
	private UsersDao uDao = new UsersDao();
	private RepairDetailsDao rpdDao = new RepairDetailsDao();
	private PerDetailsDao pdDao = new PerDetailsDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession ss = request.getSession();
		String ssID = ss.getId();
		if (ssID != null) {
			Users gobalUser = (Users) ss.getAttribute("gobalUser");
			if (gobalUser != null) {
				int repair_id = Integer.parseInt(request.getParameter("repair_id"));
				RepairDB item = (RepairDB) rpDao.findID(repair_id);
				Users user = (Users) uDao.find(item.getUser_id());
				List<RepairDetails> itemList = (ArrayList<RepairDetails>) rpdDao.findRepairID(repair_id);
				request.setAttribute("item", item);
				request.setAttribute("itemList", itemList);
				request.setAttribute("user", user);
				request.getRequestDispatcher("Views/Repair/EditRepair.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/Login");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/Login");
		}

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
		String[] asset_id = request.getParameterValues("asset_id");
		RepairDB item = new RepairDB();
		item.setRepair_id(Integer.parseInt(request.getParameter("repair_id")));
		item.setDate(request.getParameter("date"));
		item.setDocument_no(request.getParameter("document_no"));
		item.setNote(request.getParameter("note"));
		item.setReturn_date(request.getParameter("return_date"));
		item.setRepair_center(request.getParameter("repair_center"));
		item.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		item.setStatus(request.getParameter("status"));

		List<RepairDetails> listItem = (ArrayList<RepairDetails>) rpdDao.findRepairID(item.getRepair_id());
		for (RepairDetails obj : listItem) {
			PerDetails per = pdDao.findAssetID(obj.getAsset_id());
			per.setStatus("Normal");
			pdDao.update(per);
			rpdDao.delete(obj.getId());
		}
		

		if (item.getStatus().equals("Repairing")) {
			for (String id : asset_id) {
				PerDetails per = pdDao.findAssetID(Integer.parseInt(id));
				per.setStatus("Repairing");
				pdDao.update(per);

				RepairDetails obj = new RepairDetails();
				obj.setAsset_code(per.getAsset_code());
				obj.setAsset_id(per.getAsset_id());
				obj.setAsset_name(per.getAsset_name());
				obj.setNote("");
				obj.setRepair_id(item.getRepair_id());
				rpdDao.add(obj);
			}
		} else {
			for (String id : asset_id) {
				PerDetails per = pdDao.findAssetID(Integer.parseInt(id));
				per.setStatus("Normal");
				pdDao.update(per);

				RepairDetails obj = new RepairDetails();
				obj.setAsset_code(per.getAsset_code());
				obj.setAsset_id(per.getAsset_id());
				obj.setAsset_name(per.getAsset_name());
				obj.setNote("");
				obj.setRepair_id(item.getRepair_id());
				rpdDao.add(obj);
			}
		}
		response.sendRedirect(request.getContextPath()+"/Repair");
	}
	

}
