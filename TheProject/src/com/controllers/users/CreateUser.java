package com.controllers.users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DepartmentDao;
import com.dao.FacultyDao;
import com.dao.UsersDao;
import com.model.Department;
import com.model.Faculty;
import com.model.Users;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private FacultyDao fcDao = new FacultyDao();
	private DepartmentDao dpDao = new DepartmentDao();
	private UsersDao userDao = new UsersDao();

	public CreateUser() {
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
			Users user = (Users) ss.getAttribute("gobalUser");
			if (user != null) {
				ArrayList<Faculty> listFc = (ArrayList<Faculty>) fcDao.getAll();
				ArrayList<Department> listDp = (ArrayList<Department>) dpDao.getall();
				request.setAttribute("listFc", listFc);
				request.setAttribute("listDp", listDp);
				request.getRequestDispatcher("/Views/Users/CreateUser.jsp").forward(request, response);
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

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Users user = new Users();
		user.setAddress(request.getParameter("address"));
		user.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
		user.setEmail(request.getParameter("email"));
		user.setFaculty_id(Integer.parseInt(request.getParameter("faculty_id")));
		user.setFirst_name(request.getParameter("first_name"));
		user.setLast_name(request.getParameter("last_name"));
		user.setLast_update("");
		user.setPassword(request.getParameter("password"));
		user.setPhone(request.getParameter("phone"));
		user.setRole(request.getParameter("role"));
		user.setTitle(request.getParameter("title"));
		user.setUser_code(request.getParameter("user_code"));
		user.setUsername(request.getParameter("username"));

		if (user != null) {
			userDao.add(user);
			response.sendRedirect(request.getContextPath() + "/UserInfo");
		}
	}

}
