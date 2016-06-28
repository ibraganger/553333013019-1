package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.ExposeDB;
import com.util.DbUtil;

public class ExposeDao {

	private Connection con = DbUtil.getConnection();

	public List<ExposeDB> getAll() {

		String sql = "select * from expose_asset";
		List<ExposeDB> items = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ExposeDB obj = new ExposeDB();
				obj.setDate(rs.getString("date"));
				obj.setDocument_no(rs.getString("document_no"));
				obj.setEx_id(rs.getInt("ex_id"));
				obj.setNote(rs.getString("note"));
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

	public List<ExposeDB> search(String document_no, String date, String status, String username) {
		String sql = "call search_expose('%" + document_no + "%','%" + date + "%','%" + status + "%','%" + username
				+ "%')";
		try {
			List<ExposeDB> items = new ArrayList<>();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ExposeDB obj = new ExposeDB();
				obj.setDate(rs.getString("date"));
				obj.setDocument_no(rs.getString("document_no"));
				obj.setEx_id(rs.getInt("ex_id"));
				obj.setNote(rs.getString("note"));
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

	public ExposeDB findDocument(String document_no) {
		ExposeDB item = new ExposeDB();
		String sql = "select * from expose_asset where document_no like '" + document_no + "'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				item.setDate(rs.getString("date"));
				item.setDocument_no(rs.getString("document_no"));
				item.setEx_id(rs.getInt("ex_id"));
				item.setNote(rs.getString("note"));
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

	public ExposeDB find(int id) {
		ExposeDB item = new ExposeDB();
		String sql = "select * from expose_asset where ex_id = " + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				item.setDate(rs.getString("date"));
				item.setDocument_no(rs.getString("document_no"));
				item.setEx_id(rs.getInt("ex_id"));
				item.setNote(rs.getString("note"));
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

	public void add(ExposeDB item) {
		String sql = "call add_expose ('" + item.getDocument_no() + "'," + item.getUser_id() + ",'" + item.getUse_for()
				+ "','" + item.getDate() + "','" + item.getStatus() + "','" + item.getNote() + "')";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "call delete_expose(" + id + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(ExposeDB item) {
		String sql = "call edit_expose(" + item.getEx_id() + ",'" + item.getDocument_no() + "'," + item.getUser_id()
				+ ",'" + item.getUse_for() + "','" + item.getDate() + "','" + item.getStatus() + "','" + item.getNote()
				+ "')";

		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
