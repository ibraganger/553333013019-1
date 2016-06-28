package com.controllers.permanent;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PerDetailsDao;
import com.dao.PouDao;
import com.dao.UsersDao;
import com.model.PerDetails;
import com.model.PerOfUser;
import com.model.Users;

/**
 * Servlet implementation class AddPer
 */
@WebServlet("/AddPer")
public class AddPer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private PerDetailsDao pdDao = new PerDetailsDao();
	private PouDao pouDao = new PouDao();
	private UsersDao uDao = new UsersDao();

	public AddPer() {
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
		item.setAsset_code(request.getParameter("asset_code"));
		item.setAsset_name(request.getParameter("asset_name"));
		item.setNote(request.getParameter("note"));
		item.setStatus("N");
		item.setStorage(request.getParameter("per_storage"));
		item.setUse_status("N");
		item.setPer_id(Integer.parseInt(request.getParameter("per_id")));

		pdDao.add(item);
		item.setAsset_id(pdDao.findAssetCode(item.getAsset_code()).getAsset_id());
		Users user = uDao.findUsername("admin");

		PerOfUser pou = new PerOfUser();
		pou.setAsset_id(item.getAsset_id());
		pou.setUser_id(user.getUser_id());
		pouDao.add(pou);
		response.sendRedirect(request.getContextPath() + "/EditPer?per_id=" + item.getPer_id());

	}

}
