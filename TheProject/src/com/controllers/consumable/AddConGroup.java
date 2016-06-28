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
 * Servlet implementation class AddConGroup
 */
@WebServlet("/AddConGroup")
public class AddConGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private ConsumableDao consumDao = new ConsumableDao();
    public AddConGroup() {
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
		response.setCharacterEncoding("utf-8");
		Consumable consum = new Consumable();
		consum.setCon_code(request.getParameter("con_code"));
		consum.setCon_name(request.getParameter("con_name"));
		consum.setNote(request.getParameter("note-modal"));
		consum.setUnit(request.getParameter("unit"));
		consum.setAmount_tt(0);
		consum.setPicture(null);
		consum.setImp_amount(0);
		consum.setExp_amount(0);
		consum.setPrice_tt(0);
		consum.setStorage(request.getParameter("storage"));
		if (consum != null) {
			consumDao.add(consum);
		}
		response.sendRedirect(request.getContextPath()+"/CreateCon");
	}

}
