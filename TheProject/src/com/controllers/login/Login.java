package com.controllers.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UsersDao;
import com.model.Users;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 * 
	 */

	private UsersDao uDao = new UsersDao();

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession ss = request.getSession(false);
		String ssID = ss.getId();
		if (ssID != null) {
			Users gobalUser = (Users) request.getAttribute("gobalUser");
			if (gobalUser != null) {
				response.sendRedirect(request.getContextPath() + "/Permanent");
			} else {
				request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Users user = new Users();
		user = uDao.findUsername(username);

		HttpSession session = request.getSession(false);

		Users gobalUser = (Users) session.getAttribute("gobalUser");
		if (gobalUser != null) {
			response.sendRedirect(request.getContextPath() + "/Permanent");
		} else {
			if (user.getUsername() != null) {

				if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
					session.setAttribute("gobalUser", user);
					if (user.getRole().equals("admin")) {
						response.sendRedirect(request.getContextPath() + "/Permanent");
					} else {
						response.sendRedirect(request.getContextPath() + "/Permanent");
					}
				} else {
					response.sendRedirect(request.getContextPath() + "/LoginFail");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/LoginFail");
			}
		}
	}

}
