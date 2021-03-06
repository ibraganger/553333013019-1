package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.BorrowDB;
import com.util.DbUtil;

public class BorrowDao {

	private Connection con = DbUtil.getConnection();

	public List<BorrowDB> getAll() {

		List<BorrowDB> list = new ArrayList<BorrowDB>();
		String sql = "select * from borrow";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				BorrowDB item = new BorrowDB();
				item.setBor_id(rs.getInt("bor_id"));
				item.setDate(rs.getString("date"));
				item.setDocument_no(rs.getString("document_no"));
				item.setNote(rs.getString("note"));
				item.setReturn_date(rs.getString("return_date"));
				item.setStatus(rs.getString("status"));
				item.setUse_for(rs.getString("use_for"));
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

	public List<BorrowDB> serachBorrow(String document_no, String date, String return_date, String status) {
		List<BorrowDB> items = new ArrayList<BorrowDB>();
		String sql = "call search borrow(" + "'%" + document_no + "%'" + "'%" + date + "%'" + "'%" + return_date + "%'"
				+ "'%" + status + "%')";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				BorrowDB obj = new BorrowDB();
				obj.setBor_id(rs.getInt("bor_id"));
				obj.setDate(rs.getString("date"));
				obj.setDocument_no(rs.getString("document_no"));
				obj.setNote(rs.getString("note"));
				obj.setReturn_date(rs.getString("return_date"));
				obj.setStatus(rs.getString("status"));
				obj.setUse_for(rs.getString("use_for"));
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

	public BorrowDB findDoc(String document_no) {
		BorrowDB obj = new BorrowDB();
		String sql = "select * from borrow where document_no like '" + document_no + "';";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				obj.setBor_id(rs.getInt("bor_id"));
				obj.setDate(rs.getString("date"));
				obj.setDocument_no(rs.getString("document_no"));
				obj.setNote(rs.getString("note"));
				obj.setReturn_date(rs.getString("return_date"));
				obj.setStatus(rs.getString("status"));
				obj.setUse_for(rs.getString("use_for"));
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

	public List<BorrowDB> findUserID(int id) {

		String sql = "select * from borrow where user_id = " + id;
		List<BorrowDB> items = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				BorrowDB obj = new BorrowDB();
				obj.setBor_id(rs.getInt("bor_id"));
				obj.setDate(rs.getString("date"));
				obj.setDocument_no(rs.getString("document_no"));
				obj.setNote(rs.getString("note"));
				obj.setReturn_date(rs.getString("return_date"));
				obj.setStatus(rs.getString("status"));
				obj.setUse_for(rs.getString("use_for"));
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

	public BorrowDB findID(int id) {

		String sql = "select * from borrow where bor_id = " + id;
		try {
			BorrowDB item = new BorrowDB();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				item.setBor_id(rs.getInt("bor_id"));
				item.setDate(rs.getString("date"));
				item.setDocument_no(rs.getString("document_no"));
				item.setNote(rs.getString("note"));
				item.setReturn_date(rs.getString("return_date"));
				item.setStatus(rs.getString("status"));
				item.setUse_for(rs.getString("use_for"));
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

	public void add(BorrowDB item) {
		String sql = "call add_borrow(" + "'" + item.getDocument_no() + "'," + "'" + item.getUse_for() + "'," + "'"
				+ item.getDate() + "'," + "'" + item.getReturn_date() + "'," + "'" + item.getStatus() + "'," + "'"
				+ item.getNote() + "'," + item.getUser_id() + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(BorrowDB item) {
		String sql = "call edit_borrow(" + item.getBor_id() + ",'" + item.getDocument_no() + "','" + item.getUse_for()
				+ "','" + item.getDate() + "','" + item.getReturn_date() + "','" + item.getStatus() + "','"
				+ item.getNote() + "'," + item.getUser_id() + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		String sql = "call delete_borrow(" + id + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<BorrowDB> search(String document_no, String date, String status,String username) {
		String sql = "call search_borrow('%" + document_no + "%','%" + date + "%','%" + status + "%','%"+username+"%')";
		
		List<BorrowDB> items = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				BorrowDB obj = new BorrowDB();
				obj.setBor_id(rs.getInt("bor_id"));
				obj.setDate(rs.getString("date"));
				obj.setDocument_no(rs.getString("document_no"));
				obj.setNote(rs.getString("note"));
				obj.setReturn_date(rs.getString("return_date"));
				obj.setStatus(rs.getString("status"));
				obj.setUse_for(rs.getString("use_for"));
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
