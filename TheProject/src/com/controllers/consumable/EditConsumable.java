package com.controllers.consumable;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ConsumableDao;
import com.model.Consumable;

/**
 * Servlet implementation class EditConsumable
 */
@WebServlet("/EditConsumable")
public class EditConsumable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private ConsumableDao consumDao = new ConsumableDao();
    public EditConsumable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		Consumable consum = new Consumable();
		consum.setCon_id(Integer.parseInt(request.getParameter("con_id")));
		consum.setCon_code(request.getParameter("con_code"));
		consum.setCon_name(request.getParameter("con_name"));
		consum.setNote(request.getParameter("note-modal"));
		consum.setUnit(request.getParameter("unit"));
		consum.setImp_amount(Integer.parseInt(request.getParameter("imp_amount")));
		consum.setExp_amount(Integer.parseInt(request.getParameter("exp_amount")));
		consum.setAmount_tt(consum.getImp_amount()-consum.getExp_amount());
		consum.setPrice(Double.parseDouble(request.getParameter("price")));
		consum.setPrice_tt(consum.getAmount_tt()*consum.getPrice());
		if (consum != null) {
			consumDao.update(consum);
		}
		response.sendRedirect(request.getContextPath()+"/Consumable");
	}

}
