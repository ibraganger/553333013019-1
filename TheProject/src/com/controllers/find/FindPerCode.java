package com.controllers.find;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PerDetailsDao;
import com.google.gson.Gson;
import com.model.PerDetails;

/**
 * Servlet implementation class FindPerCode
 */
@WebServlet("/FindPerCode")
public class FindPerCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private PerDetailsDao perDao = new PerDetailsDao();

	public FindPerCode() {
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
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String code = request.getParameter("code");
		PerDetails item = perDao.findAssetCode(code);
		if (item != null & item.getUse_status().equals("Normal") & item.getStatus().equals("Normal")) {
			String jsonResult = new Gson().toJson(item);
			response.getWriter().write(jsonResult);
		}else{
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
