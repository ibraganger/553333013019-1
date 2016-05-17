package com.check.duplicate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ConsumableDao;
import com.model.Consumable;

/**
 * Servlet implementation class DuplicateConsumable
 */
@WebServlet("/DuplicateConsumable")
public class DuplicateConsumable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private ConsumableDao consumDao = new ConsumableDao();

	public DuplicateConsumable() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String con_code = request.getParameter("con_code");
		String check_code = request.getParameter("check_code");
		PrintWriter out = response.getWriter();
		Consumable consum = new Consumable();
		consum = consumDao.findCode(con_code);
		if (check_code != null) {
			if (check_code.equals(con_code)) {
				out.println(true);
			} else {
				if (consum != null) {
					out.println(false);
				} else {
					out.println(true);
				}
			}
		} else {
			if (consum != null) {
				/* text = "ผู้ใช้นี้มีอยู่แล้ว"; */
				out.println(false);
			} else {
				/* text = ""; */
				out.println(true);
			}
		}
	}

}
