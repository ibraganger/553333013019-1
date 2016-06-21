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
import com.dao.RoleDao;
import com.dao.UsersDao;
import com.model.Department;
import com.model.Faculty;
import com.model.Role;
import com.model.Users;

/**
 * Servlet implementation class Users
 */
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private FacultyDao fcDao = new FacultyDao();
	private DepartmentDao dpDao = new DepartmentDao();
	private UsersDao userDao;
	private RoleDao roleDao = new RoleDao();
	String search = "";
	String role = "";
	String fa_id = "";
	String dp_id = "";

	public UserInfo() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession ss = request.getSession(false);
		String ssID = ss.getId();
		if (ssID != null) {
			Users gobalUser = (Users) ss.getAttribute("gobalUser");
			if (gobalUser != null) {
				if (gobalUser.getRole().equals("admin")) {

					search = request.getParameter("search");
					role = request.getParameter("role");
					fa_id = request.getParameter("faculty_id");
					dp_id = request.getParameter("department_id");

					ArrayList<Users> listUser = new ArrayList<Users>();
					if (search == null)
						search = "";
					if (role == null)
						role = "";
					if (fa_id == null)
						fa_id = "";
					if (dp_id == null)
						dp_id = "";

					if (search == "" & role == "" & fa_id == "" & dp_id == "") {
						listUser = (ArrayList<Users>) userDao.getAll();
					} else {
						listUser = (ArrayList<Users>) userDao.searchUser(search, fa_id, dp_id, role);
					}
					ArrayList<Faculty> listFc = (ArrayList<Faculty>) fcDao.getAll();
					ArrayList<Department> listDp = (ArrayList<Department>) dpDao.getall();
					ArrayList<Role> listRole = (ArrayList<Role>) roleDao.getAll();
					request.setAttribute("listRole", listRole);
					request.setAttribute("listFc", listFc);
					request.setAttribute("listDp", listDp);
					request.setAttribute("listUser", listUser);

					request.setAttribute("search", search);
					request.setAttribute("role", role);
					request.setAttribute("faculty_id", fa_id);
					request.setAttribute("department_id", dp_id);
					request.getRequestDispatcher("Views/Users/index.jsp").forward(request, response);
				} else {
					response.sendRedirect(request.getContextPath() + "/Permanent");
				}
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
