package com.controllers.permanent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PerDetailsDao;
import com.dao.PermanentDao;
import com.dao.PouDao;
import com.model.PerDetails;
import com.model.PerOfUser;
import com.model.Permanent;
import com.model.Users;

/**
 * Servlet implementation class EditPer
 */
@WebServlet("/EditPer")
public class EditPer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private PermanentDao perDao = new PermanentDao();
	private PerDetailsDao pdDao = new PerDetailsDao();
	private PouDao pouDao = new PouDao();

	public EditPer() {
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
		Permanent per = new Permanent();
		HttpSession ss = request.getSession();
		String ssID = ss.getId();
		if (ssID != null) {
			Users gobalUser = (Users) ss.getAttribute("gobalUser");
			if (gobalUser != null) {//if user has login goto page edit permanent for revision  
				List<PerDetails> pd = new ArrayList<PerDetails>();
				List<PerOfUser> pou = new ArrayList<PerOfUser>();
				per = perDao.findPer_id(per_id);
				pd = pdDao.findPer_id(per_id);
				pou = pouDao.getAll();
				request.setAttribute("per", per);
				request.setAttribute("pd", pd);
				request.setAttribute("pou", pou);
				request.getRequestDispatcher("Views/Permanent/EditPer.jsp").forward(request, response);
			} else {   //if no has login go to login path
				response.sendRedirect(request.getContextPath() + "/Login");
			}
		} else {      //if no has login go to login path
			response.sendRedirect(request.getContextPath() + "/Login");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Permanent item = new Permanent();
		item.setAmount(Integer.parseInt(request.getParameter("amount")));
		item.setInput_date(request.getParameter("input_date"));
		item.setLife_time(Integer.parseInt(request.getParameter("life_time")));
		item.setNote(request.getParameter("note"));
		item.setPrice(Double.parseDouble(request.getParameter("price")));
		item.setPeice_sum(item.getPrice() * item.getAmount());
		item.setPer_code(request.getParameter("per_code"));
		item.setPer_id(Integer.parseInt(request.getParameter("per_id")));
		item.setPer_name(request.getParameter("per_name"));
		item.setUnit(request.getParameter("unit"));

		perDao.update(item);

		response.sendRedirect(request.getContextPath() + "/EditPer?per_id=" + item.getPer_id());
	}

}
