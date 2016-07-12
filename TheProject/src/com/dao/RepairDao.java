package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.RepairDB;
import com.util.DbUtil;

public class RepairDao {
	private Connection con = DbUtil.getConnection();

	public List<RepairDB> getAll() {

		List<RepairDB> list = new ArrayList<RepairDB>();
		String sql = "select * from repair_permanent";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				RepairDB item = new RepairDB();
				item.setRepair_id(rs.getInt("repair_id"));
				item.setDocument_no(rs.getString("document_no"));
				item.setDate(rs.getString("date"));
				item.setReturn_date(rs.getString("return_date"));
				item.setRepair_center(rs.getString("repair_center"));
				item.setNote(rs.getString("note"));
				item.setStatus(rs.getString("status"));
				item.setUser_id(rs.getInt("user_id"));
				list.add(item);
			}

			if (list != null) {
				return list;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<RepairDB> serachRepair(String document_no, String date, String return_date) {
		List<RepairDB> items = new ArrayList<RepairDB>();
		String sql = "call search borrow(" + "'%" + document_no + "%'" + "'%" + date + "%'" + "'%" + return_date + "%'"
				+ "'%";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				RepairDB obj = new RepairDB();
				obj.setRepair_id(rs.getInt("repair_id"));
				obj.setDocument_no(rs.getString("document_no"));
				obj.setDate(rs.getString("date"));
				obj.setReturn_date(rs.getString("return_date"));
				obj.setRepair_center(rs.getString("repair_center"));
				obj.setNote(rs.getString("note"));
				obj.setStatus(rs.getString("status"));
				obj.setUser_id(rs.getInt("user_id"));
				items.add(obj);
			}
			if (items != null) {
				return items;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public RepairDB findDoc(String document_no) {
		RepairDB obj = new RepairDB();
		String sql = "select * from repair_permanent where document_no like '" + document_no + "';";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				obj.setRepair_id(rs.getInt("repair_id"));
				obj.setDocument_no(rs.getString("document_no"));
				obj.setDate(rs.getString("date"));
				obj.setReturn_date(rs.getString("return_date"));
				obj.setRepair_center(rs.getString("repair_center"));
				obj.setNote(rs.getString("note"));
				obj.setStatus(rs.getString("status"));
				obj.setUser_id(rs.getInt("user_id"));
			}
			if (obj.getDocument_no() != null) {
				return obj;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<RepairDB> findUserID(int id) {

		String sql = "select * from repair_permanent where user_id = " + id;
		List<RepairDB> items = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				RepairDB obj = new RepairDB();
				obj.setRepair_id(rs.getInt("repair_id"));
				obj.setDocument_no(rs.getString("document_no"));
				obj.setDate(rs.getString("date"));
				obj.setReturn_date(rs.getString("return_date"));
				obj.setRepair_center(rs.getString("repair_center"));
				obj.setNote(rs.getString("note"));
				obj.setStatus(rs.getString("status"));
				obj.setUser_id(rs.getInt("user_id"));
				items.add(obj);
			}
			if (items != null) {
				return items;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public RepairDB findID(int id) {

		String sql = "select * from repair_permanent where bor_id = " + id;
		try {
			RepairDB item = new RepairDB();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				item.setRepair_id(rs.getInt("repair_id"));
				item.setDocument_no(rs.getString("document_no"));
				item.setDate(rs.getString("date"));
				item.setReturn_date(rs.getString("return_date"));
				item.setRepair_center(rs.getString("repair_center"));
				item.setNote(rs.getString("note"));
				item.setStatus(rs.getString("status"));
				item.setUser_id(rs.getInt("user_id"));
			}
			if (item.getDocument_no() != null) {
				return item;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public void add(RepairDB item) {
		String sql = "call add_repair_permanent('" + item.getDocument_no() + "','" + item.getDate() + "','"
				+ item.getReturn_date() + "','" + item.getRepair_center() + "','" + item.getNote() + "',"
				+ item.getUser_id() + ",'" + item.getStatus() + "')";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(RepairDB item) {
		String sql = "call edit_repair_permanent(" + item.getRepair_id() + ",'" + item.getDocument_no() + "','"
				+ item.getDate() + "','" + item.getReturn_date() + "','" + item.getRepair_center() + "','"
				+ item.getNote() + "'," + item.getUser_id() + ",'" + item.getStatus() + "')";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		String sql = "call delete_repair_permanent(" + id + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<RepairDB> search(String document_no, String date, String return_date, String username) {
		String sql = "call search_repair('%" + document_no + "%','%" + date + "%','%" + return_date + "%','%" + username
				+ "%')";

		List<RepairDB> items = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				RepairDB obj = new RepairDB();
				obj.setRepair_id(rs.getInt("repair_id"));
				obj.setDocument_no(rs.getString("document_no"));
				obj.setDate(rs.getString("date"));
				obj.setReturn_date(rs.getString("return_date"));
				obj.setRepair_center(rs.getString("repair_center"));
				obj.setNote(rs.getString("note"));
				obj.setStatus(rs.getString("status"));
				obj.setUser_id(rs.getInt("user_id"));
				items.add(obj);
			}
			if (items != null) {
				return items;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
