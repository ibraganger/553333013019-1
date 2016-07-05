package com.controllers.borrow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BorrowDao;
import com.dao.BorrowDetailsDao;
import com.dao.PerDetailsDao;
import com.dao.PouDao;
import com.dao.UsersDao;
import com.model.BorrowDB;
import com.model.BorrowDetails;
import com.model.PerDetails;
import com.model.PerOfUser;
import com.model.Users;

/**
 * Servlet implementation class EditBorrow
 */
@WebServlet("/EditBorrow")
public class EditBorrow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private BorrowDao brDao = new BorrowDao();
	private BorrowDetailsDao brdDao = new BorrowDetailsDao();
	private PerDetailsDao pdDao = new PerDetailsDao();
	private UsersDao uDao = new UsersDao();
	private PouDao pouDao = new PouDao();

	public EditBorrow() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession ss = request.getSession();
		String ssID = ss.getId();
		if (ssID != null) {
			Users gobalUser = (Users) ss.getAttribute("gobalUser");
			int id = Integer.parseInt(request.getParameter("bor_id"));
			if (gobalUser != null) {
				BorrowDB borItem = (BorrowDB) brDao.findID(id);
				List<BorrowDetails> borDetailsItem = (List<BorrowDetails>) brdDao.findBorID(id);
				Users userItem = (Users)uDao.find(borItem.getUser_id());
				request.setAttribute("borItem", borItem);
				request.setAttribute("borDetailsItem", borDetailsItem);
				request.setAttribute("userItem", userItem);
				request.getRequestDispatcher("/Views/Borrow/EditBorrow.jsp").forward(request, response);
				;
			}
		} else {
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

		String[] asset_id = request.getParameterValues("asset_id");
		BorrowDB item = new BorrowDB();
		item.setBor_id(Integer.parseInt(request.getParameter("bor_id")));
		item.setDate(request.getParameter("date"));
		item.setDocument_no(request.getParameter("document_no"));
		item.setNote(request.getParameter("note"));
		item.setReturn_date(request.getParameter("return_date"));
		item.setStatus(request.getParameter("status"));
		item.setUse_for(request.getParameter("use_for"));
		item.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		brDao.update(item);

		Users adminUser = (Users) uDao.findUsername("admin");
		List<BorrowDetails> list = new ArrayList<>();
		list = brdDao.findBorID(item.getBor_id());

		for (BorrowDetails obj : list) {
			brdDao.delete(obj.getId());
		}
		for (String obj : asset_id) {
			PerDetails per = new PerDetails();
			BorrowDetails bor = new BorrowDetails();
			PerOfUser pou = (PerOfUser) pouDao.findAsset_id(Integer.parseInt(obj));

			per = pdDao.findAssetID(Integer.parseInt(obj));
			bor.setAsset_code(per.getAsset_code());
			bor.setAsset_id(per.getAsset_id());
			bor.setAsset_name(per.getAsset_name());
			bor.setBor_id(item.getBor_id());

			if (item.getStatus().equals("Approved")) {
				per.setUse_status("Using");
				pdDao.update(per);
				pou.setAsset_id(bor.getAsset_id());
				pou.setUser_id(item.getUser_id());
				pouDao.update(pou);

			} else {
				per.setUse_status("Normal");
				pdDao.update(per);
				pou.setAsset_id(bor.getAsset_id());
				pou.setUser_id(adminUser.getUser_id());
				pouDao.update(pou);
			}
			brdDao.add(bor);
		}
		response.sendRedirect(request.getContextPath() + "/Borrow");
	}

}
