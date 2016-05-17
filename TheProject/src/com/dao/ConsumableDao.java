package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Consumable;
import com.util.DbUtil;

public class ConsumableDao {
	private Connection con = DbUtil.getConnection();
	private int noOfRecords;

	public List<Consumable> getAll() {

		List<Consumable> list = new ArrayList<Consumable>();
		String sql = "select * from consumable ORDER BY con_code ASC;";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Consumable consum = new Consumable();
				consum.setCon_id(rs.getInt("con_id"));
				consum.setCon_code(rs.getString("con_code"));
				consum.setCon_name(rs.getString("con_name"));
				consum.setUnit(rs.getString("unit"));
				consum.setNote(rs.getString("note"));
				consum.setPicture(null);
				consum.setAmount_tt(rs.getInt("amount_tt"));
				consum.setImp_amount(rs.getInt("imp_amount"));
				consum.setExp_amount(rs.getInt("exp_amount"));
				consum.setPrice_tt(rs.getDouble("price_tt"));
				consum.setStorage(rs.getString("storage"));
				consum.setPrice(rs.getDouble("price"));
				list.add(consum);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<Consumable> searchConsum(String search) {

		List<Consumable> list = new ArrayList<Consumable>();
		String sql = "select * from consumable where con_code like '%" + search + "%' or con_name like '%" + search
				+ "%'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Consumable consum = new Consumable();
				consum.setCon_id(rs.getInt("con_id"));
				consum.setCon_code(rs.getString("con_code"));
				consum.setCon_name(rs.getString("con_name"));
				consum.setUnit(rs.getString("unit"));
				consum.setNote(rs.getString("note"));
				consum.setPicture(null);
				consum.setAmount_tt(rs.getInt("amount_tt"));
				consum.setImp_amount(rs.getInt("imp_amount"));
				consum.setExp_amount(rs.getInt("exp_amount"));
				consum.setPrice_tt(rs.getDouble("price_tt"));
				consum.setStorage(rs.getString("storage"));
				consum.setPrice(rs.getDouble("price"));
				list.add(consum);
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

	public Consumable find(int id) {
		Consumable consum = new Consumable();
		String sql = "select * from consumable where con_id = " + id + ";";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				consum.setCon_id(rs.getInt("con_id"));
				consum.setCon_code(rs.getString("con_code"));
				consum.setCon_name(rs.getString("con_name"));
				consum.setUnit(rs.getString("unit"));
				consum.setNote(rs.getString("note"));
				consum.setPicture(null);
				consum.setAmount_tt(rs.getInt("amount_tt"));
				consum.setImp_amount(rs.getInt("imp_amount"));
				consum.setExp_amount(rs.getInt("exp_amount"));
				consum.setPrice_tt(rs.getDouble("price_tt"));
				consum.setStorage(rs.getString("storage"));
				consum.setPrice(rs.getDouble("price"));
			}
			if (consum.getCon_code() != null) {
				return consum;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public Consumable findCode(String code) {

		String sql = "select * from consumable where con_code like '" + code + "'";
		Consumable consum = new Consumable();
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				consum.setCon_id(rs.getInt("con_id"));
				consum.setCon_code(rs.getString("con_code"));
				consum.setCon_name(rs.getString("con_name"));
				consum.setUnit(rs.getString("unit"));
				consum.setNote(rs.getString("note"));
				consum.setPicture(null);
				consum.setAmount_tt(rs.getInt("amount_tt"));
				consum.setImp_amount(rs.getInt("imp_amount"));
				consum.setExp_amount(rs.getInt("exp_amount"));
				consum.setPrice_tt(rs.getDouble("price_tt"));
				consum.setStorage(rs.getString("storage"));
				consum.setPrice(rs.getDouble("price"));
			}
			if (consum.getCon_code() != null) {
				return consum;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public Consumable findName(String name) {

		String sql = "select * from consumable where con_name like '" + name + "'";
		Consumable consum = new Consumable();
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				consum.setCon_id(rs.getInt("con_id"));
				consum.setCon_code(rs.getString("con_code"));
				consum.setCon_name(rs.getString("con_name"));
				consum.setUnit(rs.getString("unit"));
				consum.setNote(rs.getString("note"));
				consum.setPicture(null);
				consum.setAmount_tt(rs.getInt("amount_tt"));
				consum.setImp_amount(rs.getInt("imp_amount"));
				consum.setExp_amount(rs.getInt("exp_amount"));
				consum.setPrice_tt(rs.getDouble("price_tt"));
				consum.setStorage(rs.getString("storage"));
				consum.setPrice(rs.getDouble("price"));
			}
			if (consum.getCon_name() != null) {
				return consum;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public void add(Consumable item) {
		String sql = "INSERT INTO consumable ("
				+ "`con_code`,`con_name`,`unit`,`note`,`amount_tt`,`imp_amount`,`exp_amount`,`price_tt`,`storage`,`price`)"
				+ " VALUES (" + "'" + item.getCon_code() + "'," + "'" + item.getCon_name() + "'," + "'" + item.getUnit()
				+ "'," + "'" + item.getNote() + "'," + item.getAmount_tt() + "," + item.getImp_amount() + ","
				+ item.getExp_amount() + "," + item.getPrice_tt() + "," + "'" + item.getStorage() + "'," + "0" + ");";
		if (item != null) {
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void update(Consumable item) {
		String sql = "UPDATE consumable SET " + "`con_code` = '" + item.getCon_code() + "'," + "`con_name` = '"
				+ item.getCon_name() + "'," + "`unit` = '" + item.getUnit() + "'," + "`note` = '" + item.getNote()
				+ "'," + "`picture` = " + item.getPicture() + "," + "`amount_tt` = " + item.getAmount_tt() + ","
				+ "`imp_amount` = " + item.getImp_amount() + "," + "`exp_amount` = " + item.getExp_amount() + ","
				+ "`price_tt` = " + item.getPrice_tt() + "," + "`storage` = '" + item.getStorage() + "'," + "`price` = "
				+ item.getPrice() + " WHERE `con_id` = " + item.getCon_id();
		if (item != null) {
			try {
				Statement st = con.createStatement();
				st.execute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void delete(int id) {
		String sql = "DELETE FROM consumable WHERE con_id = " + id;
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}
}
