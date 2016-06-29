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
import com.dao.UsersDao;
import com.model.Consumable;
import com.model.ExposeDB;
import com.model.ExposeDetails;
import com.model.Users;

/**
 * Servlet implementation class EditExpose
 */
@WebServlet("/EditExpose")
public class EditExpose extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditExpose() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private ConsumableDao conDao = new ConsumableDao();
	private ExposeDao exDao = new ExposeDao();
	private ExposeDetailsDao exdDao = new ExposeDetailsDao();
	private UsersDao uDao = new UsersDao();

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
				int ex_id = Integer.parseInt(request.getParameter("ex_id"));
				ExposeDB item = exDao.find(ex_id);
				List<ExposeDetails> list = (ArrayList<ExposeDetails>) exdDao.findEx_id(ex_id);
				List<Consumable> listCon = (ArrayList<Consumable>) conDao.getAll();
				Users userEx = uDao.find(item.getUser_id());
				request.setAttribute("item", item);
				request.setAttribute("list", list);
				request.setAttribute("listCon", listCon);
				request.setAttribute("userEx", userEx);
				request.getRequestDispatcher("/Views/Expose/EditExpose.jsp").forward(request, response);
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
		item.setEx_id(Integer.parseInt(request.getParameter("ex_id")));
		item.setDate(request.getParameter("date"));
		item.setDocument_no(request.getParameter("document_no"));
		item.setNote(request.getParameter("note"));
		item.setStatus(request.getParameter("status"));
		item.setUse_for(request.getParameter("use_for"));
		item.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		String[] con_id = request.getParameterValues("id_con");
		String[] amount = request.getParameterValues("amount_con");

		ExposeDB expose = exDao.find(item.getEx_id());
		List<ExposeDetails> exDetail = exdDao.findEx_id(item.getEx_id());

		if (expose.getStatus().equals("Waiting")) {
			int i = 0;
			for (ExposeDetails obj : exDetail) {
				exdDao.delete(obj.getId());
			}
			if (item.getStatus().equals("Waiting")) {
				i = 0;
				for (String object : con_id) {
					Consumable con = new Consumable();
					con = conDao.find(Integer.parseInt(object));
					ExposeDetails ex = new ExposeDetails();
					ex.setAmount(Integer.parseInt(amount[i]));
					ex.setCon_code(con.getCon_code());
					ex.setCon_id(con.getCon_id());
					ex.setCon_name(con.getCon_name());
					ex.setEx_id(item.getEx_id());
					exdDao.add(ex);
					i++;
				}
			} else {
				i = 0;
				for (String object : con_id) {
					Consumable con = new Consumable();
					con = conDao.find(Integer.parseInt(object));
					ExposeDetails ex = new ExposeDetails();

					ex.setAmount(Integer.parseInt(amount[i]));
					ex.setCon_code(con.getCon_code());
					ex.setCon_id(con.getCon_id());
					ex.setCon_name(con.getCon_name());
					ex.setEx_id(item.getEx_id());

					con.setAmount_tt(con.getAmount_tt() - Integer.parseInt(amount[i]));
					con.setExp_amount(con.getExp_amount() + Integer.parseInt(amount[i]));
					conDao.update(con);
					exdDao.add(ex);
					i++;
				}
			}
		} else {
			int i = 0;
			for (ExposeDetails obj : exDetail) {
				Consumable con = conDao.find(obj.getCon_id());
				con.setAmount_tt(con.getAmount_tt() + obj.getAmount());
				con.setExp_amount(con.getExp_amount() - obj.getAmount());
				conDao.update(con);
				exdDao.delete(obj.getId());
			}
			if (item.getStatus().equals("Waiting")) {
				i = 0;
				for (String object : con_id) {
					Consumable con = new Consumable();
					con = conDao.find(Integer.parseInt(object));
					ExposeDetails ex = new ExposeDetails();
					ex.setAmount(Integer.parseInt(amount[i]));
					ex.setCon_code(con.getCon_code());
					ex.setCon_id(con.getCon_id());
					ex.setCon_name(con.getCon_name());
					ex.setEx_id(item.getEx_id());
					exdDao.add(ex);
					i++;
				}
			} else {
				i = 0;
				for (String object : con_id) {
					Consumable con = new Consumable();
					con = conDao.find(Integer.parseInt(object));
					ExposeDetails ex = new ExposeDetails();

					ex.setAmount(Integer.parseInt(amount[i]));
					ex.setCon_code(con.getCon_code());
					ex.setCon_id(con.getCon_id());
					ex.setCon_name(con.getCon_name());
					ex.setEx_id(item.getEx_id());

					con.setAmount_tt(con.getAmount_tt() - Integer.parseInt(amount[i]));
					con.setExp_amount(con.getExp_amount() + Integer.parseInt(amount[i]));
					conDao.update(con);
					exdDao.add(ex);
					i++;
				}
			}
		}
		exDao.update(item);
		response.sendRedirect(request.getContextPath() + "/Expose");

	}

}
