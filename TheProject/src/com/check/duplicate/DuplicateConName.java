package com.check.duplicate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ConsumableDao;
import com.model.Consumable;

/**
 * Servlet implementation class DuplicateConName
 */
@WebServlet("/DuplicateConName")
public class DuplicateConName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private ConsumableDao consumDao = new ConsumableDao();
    public DuplicateConName() {
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
		String con_name = request.getParameter("con_name");
		String check_name = request.getParameter("check_name");
		PrintWriter out = response.getWriter();
		Consumable consum = new Consumable();
		consum = consumDao.findName(con_name); 
		if (check_name != null) {
			if (check_name.equals(con_name)) {
				out.println(true);
			} else {
				if (consum != null) {
					out.println(false);
				} else {
					out.println(true);
				}
			}
		} else {
			if (consum != null) {
				/* text = "ผู้ใช้นี้มีอยู่แล้ว"; */
				out.println(false);
			} else {
				/* text = ""; */
				out.println(true);
			}
		}
	}

}
