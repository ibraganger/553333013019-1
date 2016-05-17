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
 * Servlet implementation class CheckDuplidate
 */
@WebServlet("/DuplicateUsername")
public class DuplicateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private UsersDao userDao;

	public DuplicateUsername() {
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
		String username = request.getParameter("username");
		String checkUsername = request.getParameter("checkUsername"); // huh?
		PrintWriter out = response.getWriter();
		Users user = new Users();
		user = userDao.findUsername(username);
		if (checkUsername != null) {
			if (username.equals(checkUsername)) {
				out.println(true);
			} else {
				if (user != null) {
					out.println(false);
				} else {
					out.println(true);
				}
			}
		} else {
			if (user != null) {
				out.println(false);
			} else {
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

	}

}
