package com.controllers.permanent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PerDetailsDao;
import com.dao.PermanentDao;
import com.dao.PouDao;
import com.dao.UsersDao;
import com.model.PerDetails;
import com.model.PerOfUser;
import com.model.Permanent;
import com.model.Users;

/**
 * Servlet implementation class CreatePer
 */
@WebServlet("/CreatePer")
public class CreatePer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private UsersDao userDao = new UsersDao();
	private PermanentDao perDao = new PermanentDao();
	private PerDetailsDao pDetailsDao = new PerDetailsDao();
	private PouDao pouDao = new PouDao();

	public CreatePer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession ss = request.getSession(false);
		String ssID = ss.getId();
		if (ssID != null) {
			Users gobalUser = (Users) ss.getAttribute("gobalUser");
			if (gobalUser != null) {
				request.getRequestDispatcher("Views/Permanent/CreatePer.jsp").forward(request, response);
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
		String[] asset_code = request.getParameterValues("asset_code");
		String[] asset_name = request.getParameterValues("asset_name");
		String[] storage = request.getParameterValues("storage");
		String[] note = request.getParameterValues("note");
		String ststus = "N";
		Permanent permanent = new Permanent();
		permanent.setAmount(Integer.parseInt(request.getParameter("amount")));
		permanent.setInput_date(request.getParameter("input_date"));
		permanent.setLife_time(Integer.parseInt(request.getParameter("life_time")));
		permanent.setPer_code(request.getParameter("per_code"));
		permanent.setPer_name(request.getParameter("per_name"));
		permanent.setPrice(Double.parseDouble(request.getParameter("price")));
		permanent.setUnit(request.getParameter("unit"));
		permanent.setPeice_sum(permanent.getPrice() * permanent.getAmount());

		perDao.add(permanent);

		permanent.setPer_id(perDao.findPer_code(permanent.getPer_code()).getPer_id());

		if (!asset_code[0].equals("") | asset_code[0] == null) {

			int i = 0;
			while (i < asset_code.length) {
				PerDetails pd = new PerDetails();
				pd.setPer_id(permanent.getPer_id());
				pd.setAsset_code(asset_code[i]);
				pd.setAsset_name(asset_name[i]);
				pd.setStatus(ststus);
				pd.setNote(note[i]);
				pd.setStorage(storage[i]);
				pd.setUse_status(ststus);
				pDetailsDao.add(pd);

				PerOfUser pou = new PerOfUser();
				pou.setAsset_id(pDetailsDao.findAssetCode(pd.getAsset_code()).getAsset_id());
				pou.setUser_id(userDao.findUsername("admin").getUser_id());
				pouDao.add(pou);
				i++;
			}
		}
		response.sendRedirect(request.getContextPath() + "/Permanent");

	}

}
