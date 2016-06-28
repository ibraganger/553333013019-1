package com.controllers.permanent;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.PerDetailsDao;
import com.dao.PermanentDao;
import com.dao.PouDao;
import com.model.PerDetails;

/**
 * Servlet implementation class DeletePer
 */
@WebServlet("/DeletePer")
public class DeletePer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private PermanentDao perDao = new PermanentDao();
	private PerDetailsDao pdDao = new PerDetailsDao();
	private PouDao pouDao = new PouDao();

	public DeletePer() {
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
		int per_id = Integer.parseInt(request.getParameter("per_id"));
		List<PerDetails> pdList = pdDao.findPer_id(per_id);
		if (pdList != null) {
			for (PerDetails item : pdList) {
				pouDao.delete(pouDao.findAsset_id(item.getAsset_id()).getId());
				pdDao.delete(item.getAsset_id());
			}
		}
		perDao.delete(per_id);
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
