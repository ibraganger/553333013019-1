package com.check.duplicate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsersDao;
import com.model.Users;

/**
 * Servlet implementation class DuplicateEmail
 */
@WebServlet("/DuplicateEmail")
public class DuplicateEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private UsersDao userDao;

	public DuplicateEmail() {
		super();
		// TODO Auto-generated constructor stub
		userDao = new UsersDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8"); // You want world domination,
		String email = request.getParameter("email");
		String checkEmail = request.getParameter("checkEmail");// huh?
		PrintWriter out = response.getWriter();
		Users user = new Users();
		user = userDao.findEmail(email);
		if (checkEmail != null) {
			if (checkEmail.equals(email)) {
				out.println(true);
			} else {
				if (user != null) {
					/* text = "ผู้ใช้นี้มีอยู่แล้ว"; */
					out.println(false);
				} else {
					/* text = ""; */
					out.println(true);
				}
			}
		} else {
			if (user != null) {
				/* text = "ผู้ใช้นี้มีอยู่แล้ว"; */
				out.println(false);
			} else {
				/* text = ""; */
				out.println(true);
			}
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
