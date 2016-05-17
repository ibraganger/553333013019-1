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

import com.dao.ConsumableDao;
import com.model.Consumable;
import com.model.Users;

/**
 * Servlet implementation class Consumable
 */
@WebServlet("/Consumable")
public class ConsumableIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private ConsumableDao conDao = new ConsumableDao();

	public ConsumableIndex() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	private String search = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession ss = request.getSession(false);
		Users user = (Users) ss.getAttribute("gobalUser");
		if (user != null) {

			search = request.getParameter("search");
			List<Consumable> conList = new ArrayList<Consumable>();
			if (search == null) {
				search = "";
				conList = conDao.getAll();
			}else{
				conList = conDao.searchConsum(search);
			}
			request.setAttribute("search", search);
			request.setAttribute("conList", conList);
			request.getRequestDispatcher("Views/Consumable/Index.jsp").forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
