package com.controllers.repair;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.RepairDao;
import com.model.Users;

/**
 * Servlet implementation class Repair
 */
@WebServlet("/Repair")
public class Repair extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private String search = "";
	private String input_date = "";
	private String return_date = "";
	private RepairDao rpDao = new RepairDao();

	public Repair() {
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

				search = request.getParameter("search");
				input_date = request.getParameter("input_date");
				return_date = request.getParameter("return_date");

				if (search == null) {
					search = "";
				}
				if (input_date == null) {
					input_date = "";
				}
				if (return_date == null) {
					return_date = "";
				}

				request.setAttribute("search", search);
				request.setAttribute("input_date", input_date);
				request.setAttribute("return_date", return_date);
				
				if (gobalUser.getRole().equals("admin")) {
					request.setAttribute("list", rpDao.getAll());
				} else {
					request.setAttribute("list", rpDao.getAll());
				}
				request.getRequestDispatcher("Views/Repair/Index.jsp").forward(request, response);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
