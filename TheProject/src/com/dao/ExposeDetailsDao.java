package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.ExposeDetails;
import com.util.DbUtil;

public class ExposeDetailsDao {
	private Connection con = DbUtil.getConnection();

	public void add(ExposeDetails item) {
		String sql = "call add_expose_details(" + item.getEx_id() + "," + item.getCon_id() + "," + item.getAmount()
				+ ",'" + item.getCon_name() + "','" + item.getCon_code() + "')";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<ExposeDetails> findEx_id(int id) {
		String sql = "select * from expose_details where ex_id = " + id;

		try {
			List<ExposeDetails> items = new ArrayList<>();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ExposeDetails obj = new ExposeDetails();
				obj.setAmount(rs.getInt("amount"));
				obj.setCon_code(rs.getString("con_code"));
				obj.setCon_id(rs.getInt("con_id"));
				obj.setCon_name(rs.getString("con_name"));
				obj.setEx_id(rs.getInt("ex_id"));
				obj.setId(rs.getInt("id"));
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

	public void delete(int id) {
		String sql = "call delete_expose_details(" + id + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(ExposeDetails item) {
		String sql = "call edit_expose_details(" + item.getId() + "," + item.getEx_id() + "," + item.getCon_id() + ","
				+ item.getAmount() + ",'" + item.getCon_name() + "','" + item.getCon_code() + "')";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
