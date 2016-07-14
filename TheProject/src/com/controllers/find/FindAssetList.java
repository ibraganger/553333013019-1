package com.controllers.find;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PerDetailsDao;
import com.google.gson.Gson;
import com.model.PerDetails;

/**
 * Servlet implementation class FindAssetList
 */
@WebServlet("/FindAssetList")
public class FindAssetList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private PerDetailsDao pdDao = new PerDetailsDao();

	public FindAssetList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String[] asset_id = request.getParameterValues("asset_id");
		List<PerDetails> items = pdDao.getAll();
		String jsonResult = "";
		if (asset_id != null) {
			if (items != null) {
				for (String number : asset_id) {
					for (int i = 0; i < items.toArray().length; i++) {
						if (items.get(i).getAsset_id() == Integer.parseInt(number)) {
							items.remove(i);
						}
					}
				}
				for (int i = 0; i < items.toArray().length;) {
					if (items.get(i).getUse_status().equals("Using") | items.get(i).getStatus().equals("Repairing")) {
						items.remove(i);
					} else {
						i++;
					}
				}
				jsonResult = new Gson().toJson(items);
				response.getWriter().write(jsonResult);
			} else {
				response.getWriter().write("null");
			}
		} else {

			for (int i = 0; i < items.toArray().length;) {
				if (items.get(i).getUse_status().equals("Using") | items.get(i).getStatus().equals("Repairing")) {
					items.remove(i);
				} else {
					i++;
				}
			}
			jsonResult = new Gson().toJson(items);
			response.getWriter().write(jsonResult);
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
