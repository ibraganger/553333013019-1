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

}
