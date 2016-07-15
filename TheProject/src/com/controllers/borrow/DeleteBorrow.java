package com.controllers.borrow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class DeleteBorrow
 */
@WebServlet("/DeleteBorrow")
public class DeleteBorrow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private BorrowDao brDao = new BorrowDao();
	private BorrowDetailsDao brdDao = new BorrowDetailsDao();
	private UsersDao uDao = new UsersDao();
	private PouDao pouDao = new PouDao();
	private PerDetailsDao pdDao = new PerDetailsDao();

	public DeleteBorrow() {
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
		int bor_id = Integer.parseInt(request.getParameter("bor_id"));
		BorrowDB item = (BorrowDB) brDao.findID(bor_id);
		List<BorrowDetails> list = (ArrayList<BorrowDetails>) brdDao.findBorID(bor_id);
		Users adminUser = (Users) uDao.findUsername("admin");

		for (BorrowDetails obj : list) {
			PerOfUser pou = (PerOfUser) pouDao.findAsset_id(obj.getAsset_id());
			PerDetails per = (PerDetails) pdDao.findAssetID(obj.getAsset_id());			
			if (per != null) {
				pou.setUser_id(adminUser.getUser_id());
				pouDao.update(pou);
				per.setUse_status("Normal");
				pdDao.update(per);
			}
			brdDao.delete(obj.getId());
		}
		brDao.delete(bor_id);
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
