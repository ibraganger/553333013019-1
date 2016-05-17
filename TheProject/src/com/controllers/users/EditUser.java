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
import com.dao.TitleDao;
import com.dao.UsersDao;
import com.model.Department;
import com.model.Faculty;
import com.model.TitleName;
import com.model.Users;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private FacultyDao fcDao = new FacultyDao();
	private DepartmentDao dpDao = new DepartmentDao();
	private UsersDao userDao = new UsersDao();
	private TitleDao titleDao = new TitleDao();

	public EditUser() {
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
		Users user = (Users) ss.getAttribute("gobalUser"), userEdit = new Users();

		String code = request.getParameter("username");
		userEdit = userDao.findUsername(code);
		ArrayList<TitleName> titleList = new ArrayList<TitleName>();
		titleList = titleDao.getAll();
		
		if (user != null & userEdit != null) {
			ArrayList<Faculty> listFc = (ArrayList<Faculty>) fcDao.getAll();
			ArrayList<Department> listDp = (ArrayList<Department>) dpDao.getall();
			request.setAttribute("listFc", listFc);
			request.setAttribute("listDp", listDp);
			request.setAttribute("userEdit", userEdit);
			request.setAttribute("titleList", titleList);
			request.getRequestDispatcher("/Views/Users/EditUser.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/UserInfo");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Users user = new Users();
		user.setUser_id(Integer.parseInt(request.getParameter("user_id")));
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
			userDao.update(user);
			response.sendRedirect(request.getContextPath() + "/UserInfo");
		}
	}

}
