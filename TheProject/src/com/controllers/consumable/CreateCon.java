package com.controllers.consumable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ConsumDetailsDao;
import com.dao.ConsumableDao;
import com.model.ConsumDetails;
import com.model.Consumable;
import com.model.Users;

/**
 * Servlet implementation class CreateCon
 */
@WebServlet("/CreateCon")
public class CreateCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private ConsumableDao consumDao = new ConsumableDao();
	private ConsumDetailsDao condetailsDao = new ConsumDetailsDao();

	public CreateCon() {
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
		HttpSession ss = request.getSession(false);
		String ssID = ss.getId();
		if (ssID != null) {
			Users gobalUser = (Users) ss.getAttribute("gobalUser");
			if (gobalUser != null) {
				if (gobalUser.getRole().equals("admin")) {
					List<Consumable> consumList = new ArrayList<Consumable>();
					consumList = consumDao.getAll();
					request.setAttribute("consumList", consumList);
					request.getRequestDispatcher("Views/Consumable/CreateCon.jsp").forward(request, response);
				} else {
					response.sendRedirect(request.getContextPath() + "/NullPage");
				}
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
		ConsumDetails consum = new ConsumDetails();
		consum.setAmount(Integer.parseInt(request.getParameter("amount")));
		consum.setCon_id(Integer.parseInt(request.getParameter("con_id")));
		consum.setInput_date(request.getParameter("input_date"));
		consum.setNote(request.getParameter("note"));
		consum.setPrice(Double.parseDouble(request.getParameter("price")));
		consum.setPrice_sum(consum.getPrice() * consum.getAmount());
		if (consum != null) {
			condetailsDao.add(consum);
			Consumable sum = consumDao.find(consum.getCon_id());
			if (sum != null) {
				sum.setImp_amount(sum.getImp_amount() + consum.getAmount());
				sum.setAmount_tt(sum.getImp_amount() - sum.getExp_amount());
				sum.setPrice(consum.getPrice());
				sum.setPrice_tt(sum.getAmount_tt() * sum.getPrice());
				consumDao.update(sum);
			}
		}
		response.sendRedirect(request.getContextPath() + "/Consumable");

	}

}
