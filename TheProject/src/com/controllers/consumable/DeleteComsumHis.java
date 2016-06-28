package com.controllers.consumable;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.ConsumDetailsDao;
import com.dao.ConsumableDao;
import com.model.ConsumDetails;
import com.model.Consumable;

/**
 * Servlet implementation class DeleteComsumHis
 */
@WebServlet("/DeleteComsumHis")
public class DeleteComsumHis extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private ConsumDetailsDao cdDao = new ConsumDetailsDao();
	private ConsumableDao conDao = new ConsumableDao();

	public DeleteComsumHis() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		ConsumDetails cd = cdDao.find_id(id);
		if (cd != null) {
			Consumable consum = conDao.find(cd.getCon_id());
			if (consum != null) {
				int sum1 = consum.getImp_amount() - cd.getAmount();
				int sum2 = sum1 - consum.getExp_amount();
				if (sum1 < 0) {
					sum1 = 0;
				}
				if (sum2 < 0) {
					sum2 = 0;
				}
				consum.setImp_amount(sum1);
				consum.setAmount_tt(sum2);
				conDao.update(consum);
			}
			cdDao.delete(cd.getId());
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
