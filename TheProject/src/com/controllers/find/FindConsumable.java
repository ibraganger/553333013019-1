package com.controllers.find;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ConsumableDao;
import com.google.gson.Gson;
import com.model.Consumable;

/**
 * Servlet implementation class FindConsumable
 */
@WebServlet("/FindConsumable")
public class FindConsumable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private ConsumableDao conDao = new ConsumableDao();

	public FindConsumable() {
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

		int con_id = Integer.parseInt(request.getParameter("con_id"));
		Consumable item = new Consumable();
		item = conDao.find(con_id);

		if (item != null) {
			String jsonResult = new Gson().toJson(item);
			response.getWriter().write(jsonResult);
		} else {
			response.getWriter().write("null");
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
