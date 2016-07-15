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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		request.getSession(); // create session.
		HttpSession ss = request.getSession(); // get session data.
		String ssID = ss.getId(); // get session id.
		if (ssID != null) { // check empty ssID if is not empty go to next
							// process.
			Users gobalUser = (Users) ss.getAttribute("gobalUser"); // get
																	// attribute
																	// gobalUser
																	// for check
																	// loging
																	// in.
			if (gobalUser != null) { // if attribute gobalUser is not empty. go
										// to url controller permanent.
				response.sendRedirect(request.getContextPath() + "/Permanent");
			} else { // if attribute gobalUser is empty. go to loin page.
				request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
			}
		} else { // if ssID is empty go to login page.
			request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
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
		String username = request.getParameter("username"); // call parameter
															// username from
															// login page
		String password = request.getParameter("password"); // call parameter
															// password from
															// login page
		Users user = new Users();
		user = uDao.findUsername(username); // get username parameter for find
											// user in database

		HttpSession session = request.getSession(); // call session

		Users gobalUser = (Users) session.getAttribute("gobalUser"); // get
																		// attribute
																		// gobalUser
																		// from
																		// session
																		// for
																		// check
																		// loging
																		// in
		if (gobalUser != null) {
			response.sendRedirect(request.getContextPath() + "/Permanent");
		} else {
			if (user != null) {

				if (user.getUsername().equals(username) && user.getPassword().equals(password)
						&& user.getStatus().equals("active")) {
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
