package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.BorrowDetails;
import com.util.DbUtil;

public class BorrowDetailsDao {

	private Connection con = DbUtil.getConnection();

	public List<BorrowDetails> getAll() {
		String sql = "select * from borrow_details";
		List<BorrowDetails> items = new ArrayList<BorrowDetails>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				BorrowDetails item = new BorrowDetails();
				item.setAsset_code(rs.getString("asset_code"));
				item.setAsset_name(rs.getString("asset_name"));
				item.setBor_id(rs.getInt("bor_id"));
				item.setId(rs.getInt("id"));
				items.add(item);
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

	public List<BorrowDetails> findBorID(int id) {
		String sql = "select * from borrow_details where bor_id = " + id;
		try {
			List<BorrowDetails> items = new ArrayList<BorrowDetails>();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				BorrowDetails item = new BorrowDetails();
				item.setAsset_id(rs.getInt("asset_id"));
				item.setAsset_code(rs.getString("asset_code"));
				item.setAsset_name(rs.getString("asset_name"));
				item.setBor_id(rs.getInt("bor_id"));
				item.setId(rs.getInt("id"));
				items.add(item);
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

	public void add(BorrowDetails item) {
		String sql = "call add_borrow_details(" + item.getBor_id() + "," + "'" + item.getAsset_code() + "'," + "'"
				+ item.getAsset_name() + "'," + item.getAsset_id() + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(BorrowDetails item) {
		String sql = "call edit_borrow_details(" + item.getId() + "," + item.getBor_id() + ",'" + item.getAsset_code()
				+ "','" + item.getAsset_name() + "'," + item.getAsset_id() + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		String sql = "call delete_borrow_details(" + id + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


}
