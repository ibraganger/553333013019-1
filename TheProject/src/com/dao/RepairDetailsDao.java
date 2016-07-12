package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.model.RepairDetails;
import com.util.DbUtil;

public class RepairDetailsDao {
	private Connection con = DbUtil.getConnection();

	public List<RepairDetails> getAll() {
		String sql = "select * from repair_details";
		List<RepairDetails> items = new ArrayList<RepairDetails>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				RepairDetails item = new RepairDetails();
				item.setNote(rs.getString("note"));
				item.setAsset_code(rs.getString("asset_code"));
				item.setAsset_name(rs.getString("asset_name"));
				item.setPer_id(rs.getInt("Per_id"));
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

	public List<RepairDetails> findRepairID(int id) {
		String sql = "select * from repair_details where repair_id = " + id;
		try {
			List<RepairDetails> items = new ArrayList<RepairDetails>();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				RepairDetails item = new RepairDetails();
				item.setNote(rs.getString("note"));
				item.setAsset_code(rs.getString("asset_code"));
				item.setAsset_name(rs.getString("asset_name"));
				item.setPer_id(rs.getInt("Per_id"));
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

	public void add(RepairDetails item) {
		String sql = "call add_repair_details(" + item.getId() + "," + "'" + item.getAsset_code() + "'," + "'"
				+ item.getAsset_name() + "'," + item.getPer_id() + "'," + "'" + item.getNote() + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(RepairDetails item) {
		String sql = "call edit_repair_details(" + item.getId() + "," + item.getId() + ",'" + item.getAsset_code()
				+ "','" + item.getAsset_name() + "'," + item.getPer_id() + "'," + "'" + item.getNote() + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		String sql = "call delete_repair_details(" + id + ")";
		try {
			Statement st = con.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
