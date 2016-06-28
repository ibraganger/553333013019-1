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
 * Servlet implementation class DuplicateUserCode
 */
@WebServlet("/DuplicateUserCode")
public class DuplicateUserCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private UsersDao userDao;

	public DuplicateUserCode() {
		super();
		userDao = new UsersDao();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); // You want world domination,
		String user_code = request.getParameter("user_code");
		String checkUser_code = request.getParameter("checkUser_code");// huh?
		PrintWriter out = response.getWriter();
		Users user = new Users();
		user = userDao.findUserCode(user_code);

		if (checkUser_code != null) {
			if (checkUser_code.equals(user_code)) {
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
