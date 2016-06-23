package com.controllers.borrow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BorrowDao;
import com.dao.BorrowDetailsDao;
import com.dao.PerDetailsDao;
import com.model.BorrowDB;
import com.model.BorrowDetails;
import com.model.PerDetails;
import com.model.Users;

/**
 * Servlet implementation class CreateBorrow
 */
@WebServlet("/CreateBorrow")
public class CreateBorrow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private BorrowDao brDao = new BorrowDao();
	private PerDetailsDao pdDao = new PerDetailsDao();
	private BorrowDetailsDao brdDao = new BorrowDetailsDao();

	public CreateBorrow() {
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

		HttpSession ss = request.getSession(false);
		String ssID = ss.getId();
		if (ssID != null) {
			Users gobalUser = (Users) ss.getAttribute("gobalUser");
			if (gobalUser != null) {

				List<PerDetails> assetList = pdDao.searchStatus();
				request.setAttribute("assetList", assetList);
				request.getRequestDispatcher("Views/Borrow/CreateBorrow.jsp").forward(request, response);
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
			
		String input = request.getParameter("input");

	}

}
