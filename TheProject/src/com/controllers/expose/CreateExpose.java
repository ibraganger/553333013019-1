package com.controllers.expose;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ConsumableDao;
import com.dao.ExposeDao;
import com.dao.ExposeDetailsDao;
import com.model.ConsumDetails;
import com.model.Consumable;
import com.model.ExposeDB;
import com.model.ExposeDetails;
import com.model.Users;

/**
 * Servlet implementation class CreateExpost
 */
@WebServlet("/CreateExpose")
public class CreateExpose extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private ConsumableDao conDao = new ConsumableDao();
	private ExposeDao exDao = new ExposeDao();
	private ExposeDetailsDao exdDao = new ExposeDetailsDao();

	public CreateExpose() {
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
		if (ssID != null) {
			Users gobalUser = (Users) ss.getAttribute("gobalUser");
			if (gobalUser != null) {
				List<Consumable> listCon = (ArrayList<Consumable>) conDao.getAll();
				request.setAttribute("listCon", listCon);
				request.getRequestDispatcher("Views/Expose/CreateExpose.jsp").forward(request, response);

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
		ExposeDB item = new ExposeDB();
		item.setDate(request.getParameter("date"));
		item.setDocument_no(request.getParameter("document_no"));
		item.setNote(request.getParameter("note"));
		item.setStatus("Waiting");
		item.setUse_for(request.getParameter("use_for"));
		item.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		String[] con_id = request.getParameterValues("id_con");
		String[] amount = request.getParameterValues("amount_con");

		exDao.add(item);
		item = exDao.findDocument(item.getDocument_no());
		int i = 0;
		for (String obj : con_id) {
			Consumable con = new Consumable();
			con = conDao.find(Integer.parseInt(obj));
			ExposeDetails ex = new ExposeDetails();
			ex.setAmount(Integer.parseInt(amount[i]));
			ex.setCon_code(con.getCon_code());
			ex.setCon_id(con.getCon_id());
			ex.setCon_name(con.getCon_name());
			ex.setEx_id(item.getEx_id());
			exdDao.add(ex);
			i++;
		}
		response.sendRedirect(request.getContextPath() + "/Expose");
	}

}
