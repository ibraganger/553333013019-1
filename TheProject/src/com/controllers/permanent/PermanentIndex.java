package com.controllers.permanent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PermanentDao;
import com.model.Permanent;
import com.model.Users;

/**
 * Servlet implementation class PermanentIndex
 */
@WebServlet("/Permanent")
public class PermanentIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private PermanentDao perDao = new PermanentDao();

	public PermanentIndex() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	private String search = "";
	private String input_date = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Permanent> list = new ArrayList<Permanent>();
		HttpSession ss = request.getSession(false);
		Users gobalUser = (Users) ss.getAttribute("gobalUser");
		if (gobalUser != null & ss != null) {
			search = request.getParameter("search");
			input_date = request.getParameter("input_date");
			if (search == null) {
				search = "";
			}

			if (input_date == null) {
				input_date = "";
			}

			if (search.equals("") & input_date.equals("")) {
				list = perDao.getAll();
			} else {
				list = perDao.searchPermanent(search, input_date);
			}
			request.setAttribute("list", list);
			request.setAttribute("search", search);
			request.setAttribute("input_date", input_date);
			request.getRequestDispatcher("Views/Permanent/Index.jsp").forward(request, response);
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
