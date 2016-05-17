package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.PerOfUser;
import com.util.DbUtil;

public class PouDao {
	private Connection connect = DbUtil.getConnection();

	public void add(PerOfUser items) {
		String sql = "call add_perOfUser(" + items.getAsset_id() + "," + items.getUser_id() + ")";

		Statement st;
		try {
			st = connect.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<PerOfUser> getAll() {
		String sql = "select * from per_of_user";
		List<PerOfUser> items = new ArrayList<PerOfUser>();
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				PerOfUser item = new PerOfUser();
				item.setId(rs.getInt("id"));
				item.setAsset_id(rs.getInt("asset_id"));
				item.setUser_id(rs.getInt("user_id"));
				items.add(item);
			}
			if (items.toArray().length != 0) {
				return items;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PerOfUser findAsset_id(int id) {
		String sql = "select * from per_of_user where asset_id = " + id;
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			PerOfUser item = new PerOfUser();
			while (rs.next()) {
				item.setId(rs.getInt("id"));
				item.setAsset_id(rs.getInt("asset_id"));
				item.setUser_id(rs.getInt("user_id"));
			}
			if (item.getAsset_id() != 0) {
				return item;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void delete(int id) {
		String sql = "call delete_perOfUser(" + id + ")";
		try {
			Statement st = connect.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
