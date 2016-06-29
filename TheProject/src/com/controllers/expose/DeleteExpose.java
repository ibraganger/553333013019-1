package com.controllers.expose;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ConsumableDao;
import com.dao.ExposeDao;
import com.dao.ExposeDetailsDao;
import com.model.Consumable;
import com.model.ExposeDB;
import com.model.ExposeDetails;

/**
 * Servlet implementation class DeleteExpose
 */
@WebServlet("/DeleteExpose")
public class DeleteExpose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private ExposeDao exDao = new ExposeDao();
	private ExposeDetailsDao exdDao = new ExposeDetailsDao();
	private ConsumableDao conDao = new ConsumableDao();
	
    public DeleteExpose() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("ex_id"));
		ExposeDB item = new ExposeDB();
		List<ExposeDetails> list = new ArrayList<>();
		item = exDao.find(id);
		list = exdDao.findEx_id(id);
		for(ExposeDetails obj : list){
			exdDao.delete(obj.getId());
		}
		exDao.delete(item.getEx_id());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
