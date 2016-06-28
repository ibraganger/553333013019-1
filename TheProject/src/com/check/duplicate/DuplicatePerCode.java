package com.check.duplicate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PermanentDao;
import com.model.Permanent;

/**
 * Servlet implementation class DuplicatePerCode
 */
@WebServlet("/DuplicatePerCode")
public class DuplicatePerCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PermanentDao perDao = new PermanentDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DuplicatePerCode() {
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
		String per_code = request.getParameter("per_code");
		String check_per_code = request.getParameter("check_per_code");
		PrintWriter out = response.getWriter();
		Permanent per = new Permanent();
		per = perDao.findPer_code(per_code);
		if (check_per_code != null) {
			if (check_per_code.equals(per_code)) {
				out.println(true);
			} else {
				if (per != null) {
					out.println(false);
				} else {
					out.println(true);
				}
			}
		} else {
			if (per != null) {
				out.println(false);
			} else {
				out.println(true);
			}
		}
	}

}
