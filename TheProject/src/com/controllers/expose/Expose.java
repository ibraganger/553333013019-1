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

import com.dao.ExposeDao;
import com.model.ExposeDB;
import com.model.Users;

/**
 * Servlet implementation class Expose
 */
@WebServlet("/Expose")
public class Expose extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private String search = "";
	private String input_date = "";
	private String status = "";
	private ExposeDao exDao = new ExposeDao();

	public Expose() {
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
				status = request.getParameter("status");
				if (search == null) {
					search = "";
				}
				if (input_date == null) {
					input_date = "";
				}
				if (status == null) {
					status = "";
				}

				request.setAttribute("search", search);
				request.setAttribute("input_date", input_date);
				request.setAttribute("status", status);
				List<ExposeDB> exposeList = new ArrayList<>();
				if (gobalUser.getUsername().equals("admin")) {
					exposeList = exDao.search(search, input_date, status, "");
					request.setAttribute("exposeList", exposeList);
				} else {
					exposeList = exDao.search(search, input_date, status, gobalUser.getUsername());
					request.setAttribute("exposeList", exposeList);
				}

				request.getRequestDispatcher("Views/Expose/Index.jsp").forward(request, response);

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
