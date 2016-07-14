package com.controllers.repair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PerDetailsDao;
import com.dao.PouDao;
import com.dao.RepairDao;
import com.dao.RepairDetailsDao;
import com.dao.UsersDao;
import com.model.PerDetails;
import com.model.PerOfUser;
import com.model.RepairDB;
import com.model.RepairDetails;
import com.model.Users;

/**
 * Servlet implementation class DeleteRepair
 */
@WebServlet("/DeleteRepair")
public class DeleteRepair extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RepairDao rpDao = new RepairDao();
	private RepairDetailsDao rpdDao = new RepairDetailsDao();
	private UsersDao uDao = new UsersDao();
	private PouDao pouDao = new PouDao();
	private PerDetailsDao pdDao = new PerDetailsDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRepair() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int repair_id = Integer.parseInt(request.getParameter("repair_id"));
		RepairDB item = (RepairDB) rpDao.findID(repair_id);
		List<RepairDetails> list = (ArrayList<RepairDetails>) rpdDao.findRepairID(repair_id);
		Users adminUser= (Users) uDao.findUsername("admin");
		for(RepairDetails obj : list){
			PerDetails per = (PerDetails)pdDao.findAssetID(obj.getAsset_id());
			per.setStatus("Normal");
			pdDao.update(per);
			rpdDao.delete(obj.getId());
		}
		rpDao.delete(repair_id);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
