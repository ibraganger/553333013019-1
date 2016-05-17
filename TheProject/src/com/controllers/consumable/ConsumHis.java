package com.controllers.consumable;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ConsumHis
 */
@WebServlet("/ConsumHis")
public class ConsumHis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private ConsumableDao consunDao = new ConsumableDao();
	private ConsumDetailsDao conDetailDao = new ConsumDetailsDao(); 
    public ConsumHis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int con_id	= Integer.parseInt(request.getParameter("con_id"));
		Consumable consum = consunDao.find(con_id);
		List<ConsumDetails> list = conDetailDao.getAll();
		request.setAttribute("list", list);
		request.setAttribute("conName", consum.getCon_name());
		request.getRequestDispatcher("Views/Consumable/ConsumHis.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
