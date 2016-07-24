package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.PerDetails;
import com.util.DbUtil;

public class PerDetailsDao {

	private Connection connect = DbUtil.getConnection();

	public List<PerDetails> getAll() {

		List<PerDetails> list = new ArrayList<PerDetails>();
		String sql = "call getAll_perDetails();";
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				PerDetails perDetails = new PerDetails();
				perDetails.setAsset_code(rs.getString("asset_code"));
				perDetails.setAsset_id(rs.getInt("asset_id"));
				perDetails.setAsset_name(rs.getString("asset_name"));
				perDetails.setNote(rs.getString("note"));
				perDetails.setPer_id(rs.getInt("per_id"));
				perDetails.setStatus(rs.getString("per_status"));
				perDetails.setStorage(rs.getString("per_storage"));
				perDetails.setUse_status(rs.getString("use_status"));
				list.add(perDetails);
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

	public List<PerDetails> searchStatus() {
		List<PerDetails> list = new ArrayList<PerDetails>();
		String sql = "SELECT * FROM per_details where per_status like 'N';";
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				PerDetails perDetails = new PerDetails();
				perDetails.setAsset_code(rs.getString("asset_code"));
				perDetails.setAsset_id(rs.getInt("asset_id"));
				perDetails.setAsset_name(rs.getString("asset_name"));
				perDetails.setNote(rs.getString("note"));
				perDetails.setPer_id(rs.getInt("per_id"));
				perDetails.setStatus(rs.getString("per_status"));
				perDetails.setStorage(rs.getString("per_storage"));
				perDetails.setUse_status(rs.getString("use_status"));
				list.add(perDetails);
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

	public PerDetails findAssetCode(String asset_code) {

		String sql = "SELECT * FROM per_details where asset_code like '" + asset_code + "';";
		try {

			PerDetails perDetails = new PerDetails();
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				perDetails.setAsset_code(rs.getString("asset_code"));
				perDetails.setAsset_id(rs.getInt("asset_id"));
				perDetails.setAsset_name(rs.getString("asset_name"));
				perDetails.setNote(rs.getString("note"));
				perDetails.setPer_id(rs.getInt("per_id"));
				perDetails.setStatus(rs.getString("per_status"));
				perDetails.setStorage(rs.getString("per_storage"));
				perDetails.setUse_status(rs.getString("use_status"));
			}
			if (perDetails.getAsset_code() != null) {
				return perDetails;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PerDetails findAssetID(int id) {

		String sql = "SELECT * FROM per_details where asset_id like " + id + ";";
		try {

			PerDetails perDetails = new PerDetails();
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				perDetails.setAsset_code(rs.getString("asset_code"));
				perDetails.setAsset_id(rs.getInt("asset_id"));
				perDetails.setAsset_name(rs.getString("asset_name"));
				perDetails.setNote(rs.getString("note"));
				perDetails.setPer_id(rs.getInt("per_id"));
				perDetails.setStatus(rs.getString("per_status"));
				perDetails.setStorage(rs.getString("per_storage"));
				perDetails.setUse_status(rs.getString("use_status"));
			}
			if (perDetails.getAsset_code() != null) {
				return perDetails;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PerDetails findAssetName(String asset_name) {

		String sql = "SELECT * FROM per_details where asset_name like '" + asset_name + "';";
		try {

			PerDetails perDetails = new PerDetails();
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				perDetails.setAsset_code(rs.getString("asset_code"));
				perDetails.setAsset_id(rs.getInt("asset_id"));
				perDetails.setAsset_name(rs.getString("asset_name"));
				perDetails.setNote(rs.getString("note"));
				perDetails.setPer_id(rs.getInt("per_id"));
				perDetails.setStatus(rs.getString("per_status"));
				perDetails.setStorage(rs.getString("per_storage"));
				perDetails.setUse_status(rs.getString("use_status"));
			}
			if (perDetails.getAsset_name() != null) {
				return perDetails;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<PerDetails> findPer_id(int id) {

		List<PerDetails> list = new ArrayList<PerDetails>();
		String sql = "select * from per_details where per_id = " + id;
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				PerDetails perDetails = new PerDetails();
				perDetails.setAsset_code(rs.getString("asset_code"));
				perDetails.setAsset_id(rs.getInt("asset_id"));
				perDetails.setAsset_name(rs.getString("asset_name"));
				perDetails.setNote(rs.getString("note"));
				perDetails.setPer_id(rs.getInt("per_id"));
				perDetails.setStatus(rs.getString("per_status"));
				perDetails.setStorage(rs.getString("per_storage"));
				perDetails.setUse_status(rs.getString("use_status"));
				list.add(perDetails);
			}

			if (list.toArray().length != 0) {
				return list;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void add(PerDetails item) {

		String sql = "call add_perDetails('" + item.getAsset_code() + "','" + item.getAsset_name() + "','"
				+ item.getStatus() + "','" + item.getStorage() + "','" + item.getNote() + "'," + item.getPer_id() + ",'"
				+ item.getUse_status() + "')";
		try {
			Statement st = connect.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		String sql = "call delete_perDetails(" + id + ")";
		try {
			Statement st = connect.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(PerDetails item) {
		String sql = "call edit_perDetails(" + item.getAsset_id() + ",'" + item.getAsset_code() + "','"
				+ item.getAsset_name() + "','" + item.getStatus() + "','" + item.getStorage() + "','" + item.getNote()
				+ "'," + item.getPer_id() + ",'" + item.getUse_status() + "'" + ")";
		if (item != null) {
			try {
				Statement st = connect.createStatement();
				st.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getCountNormal(int id) {
		String sql = "SELECT COUNT(per_id) as count FROM per_details where per_id = " + id
				+ " and per_status like 'Normal' and use_status like 'Normal'";
		int count = 0;
		try {

			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
