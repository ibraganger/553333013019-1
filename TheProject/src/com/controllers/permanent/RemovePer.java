package com.controllers.permanent;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PerDetailsDao;
import com.dao.PouDao;
import com.model.PerOfUser;

/**
 * Servlet implementation class RemovePer
 */
@WebServlet("/RemovePer")
public class RemovePer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private PerDetailsDao pdDao = new PerDetailsDao();
	private PouDao pouDao = new PouDao();

	public RemovePer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int asset_id = Integer.parseInt(request.getParameter("asset_id"));
		PerOfUser pou = pouDao.findAsset_id(asset_id);
		if (pou != null) {
			pouDao.delete(pou.getId());
		}
		pdDao.delete(asset_id);
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
