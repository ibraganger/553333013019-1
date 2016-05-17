package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.model.Permanent;
import com.util.DbUtil;

public class PermanentDao {

	private Connection connect = DbUtil.getConnection();

	public List<Permanent> getAll() {
		List<Permanent> list = new ArrayList<Permanent>();

		String sql = "select * from permanent";

		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Permanent per = new Permanent();
				per.setAmount(rs.getInt("amount"));
				per.setInput_date(rs.getString("input_date"));
				per.setLife_time(rs.getInt("life_time"));
				per.setNote(rs.getString("note"));
				per.setPeice_sum(rs.getDouble("peice_sum"));
				per.setPer_code(rs.getString("per_code"));
				per.setPer_id(rs.getInt("per_id"));
				per.setPer_name(rs.getString("per_name"));
				per.setPrice(rs.getDouble("price"));
				per.setUnit(rs.getString("unit"));
				list.add(per);
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

	public List<Permanent> searchPermanent(String search, String input_date) {
		List<Permanent> list = new ArrayList<Permanent>();

		String sql = "call searchPermanent(" + "'%" + search + "%','%" + input_date + "%'" + ")";

		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Permanent per = new Permanent();
				per.setAmount(rs.getInt("amount"));
				per.setInput_date(rs.getString("input_date"));
				per.setLife_time(rs.getInt("life_time"));
				per.setNote(rs.getString("note"));
				per.setPeice_sum(rs.getDouble("peice_sum"));
				per.setPer_code(rs.getString("per_code"));
				per.setPer_id(rs.getInt("per_id"));
				per.setPer_name(rs.getString("per_name"));
				per.setPrice(rs.getDouble("price"));
				per.setUnit(rs.getString("unit"));
				list.add(per);
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

	public Permanent findPer_id(int per_id) {
		Permanent item = new Permanent();
		String sql = "select * from permanent where per_id like '" + per_id + "';";
		Statement st;
		try {
			st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				item.setAmount(rs.getInt("amount"));
				item.setInput_date(rs.getString("input_date"));
				item.setLife_time(rs.getInt("life_time"));
				item.setNote(rs.getString("note"));
				item.setPeice_sum(rs.getDouble("peice_sum"));
				item.setPer_code(rs.getString("per_code"));
				item.setPer_id(rs.getInt("per_id"));
				item.setPer_name(rs.getString("per_name"));
				item.setPrice(rs.getDouble("price"));
				item.setUnit(rs.getString("unit"));
			}
			if (item.getPer_code() != null) {
				return item;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Permanent findPer_code(String per_code) {
		Permanent item = new Permanent();
		String sql = "select * from permanent where per_code like '" + per_code + "';";
		Statement st;
		try {
			st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				item.setAmount(rs.getInt("amount"));
				item.setInput_date(rs.getString("input_date"));
				item.setLife_time(rs.getInt("life_time"));
				item.setNote(rs.getString("note"));
				item.setPeice_sum(rs.getDouble("peice_sum"));
				item.setPer_code(rs.getString("per_code"));
				item.setPer_id(rs.getInt("per_id"));
				item.setPer_name(rs.getString("per_name"));
				item.setPrice(rs.getDouble("price"));
				item.setUnit(rs.getString("unit"));
			}
			if (item.getPer_code() != null) {
				return item;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public Permanent findPer_name(String per_name) {
		Permanent item = new Permanent();
		String sql = "select * from permanent where per_name like '" + per_name + "';";
		Statement st;
		try {
			st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				item.setAmount(rs.getInt("amount"));
				item.setInput_date(rs.getString("input_date"));
				item.setLife_time(rs.getInt("life_time"));
				item.setNote(rs.getString("note"));
				item.setPeice_sum(rs.getDouble("peice_sum"));
				item.setPer_code(rs.getString("per_code"));
				item.setPer_id(rs.getInt("per_id"));
				item.setPer_name(rs.getString("per_name"));
				item.setPrice(rs.getDouble("price"));
				item.setUnit(rs.getString("unit"));
			}
			if (item.getPer_name() != null) {
				return item;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void add(Permanent item) {
		String sql = "call add_permanent('" + item.getPer_code() + "','" + item.getPer_name() + "'," + item.getAmount()
				+ ",'" + item.getUnit() + "'," + item.getPrice() + "," + item.getPeice_sum() + ",'"
				+ item.getInput_date() + "'," + item.getLife_time() + ",'" + item.getNote() + "')";
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

	public void delete(int id) {

		String sql = "call delete_permanent (" + id + ")";
		try {
			Statement st = connect.createStatement();
			st.executeQuery(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(Permanent item) {
		String sql = "call edit_permanent(" + item.getPer_id() + ",'" + item.getPer_code() + "','" + item.getPer_name()
				+ "'," + item.getAmount() + ",'" + item.getUnit() + "'," + item.getPrice() + "," + item.getPeice_sum()
				+ ",'" + item.getInput_date() + "'," + item.getLife_time() + ",'" + item.getNote() + "')";
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

}
