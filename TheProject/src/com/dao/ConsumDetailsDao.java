package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.ConsumDetails;
import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;
import com.util.DbUtil;

public class ConsumDetailsDao {

	private Connection con = DbUtil.getConnection();

	public List<ConsumDetails> getAll() {
		List<ConsumDetails> list = new ArrayList<ConsumDetails>();
		String sql = "select * from consum_details ORDER BY id ASC";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ConsumDetails consum = new ConsumDetails();
				consum.setAmount(rs.getInt("amount"));
				consum.setCon_id(rs.getInt("con_id"));
				consum.setId(rs.getInt("id"));
				consum.setInput_date(rs.getString("input_date"));
				consum.setNote(rs.getString("note"));
				consum.setPrice(rs.getDouble("price"));
				consum.setPrice_sum(rs.getDouble("price_sum"));
				list.add(consum);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ConsumDetails find_id(int id) {
		String sql = "select * from consum_details where id = " + id;
		ConsumDetails consum = new ConsumDetails();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				consum.setAmount(rs.getInt("amount"));
				consum.setCon_id(rs.getInt("con_id"));
				consum.setId(rs.getInt("id"));
				consum.setInput_date(rs.getString("input_date"));
				consum.setNote(rs.getString("note"));
				consum.setPrice(rs.getDouble("price"));
				consum.setPrice_sum(rs.getDouble("price_sum"));
			}
			if (consum.getId() != 0) {
				return consum;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public List<ConsumDetails> find_Conid(int id) {
		String sql = "select * from consum_details where con_id = " + id;
		List<ConsumDetails> items = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ConsumDetails consum = new ConsumDetails();
				consum.setAmount(rs.getInt("amount"));
				consum.setCon_id(rs.getInt("con_id"));
				consum.setId(rs.getInt("id"));
				consum.setInput_date(rs.getString("input_date"));
				consum.setNote(rs.getString("note"));
				consum.setPrice(rs.getDouble("price"));
				consum.setPrice_sum(rs.getDouble("price_sum"));
				items.add(consum);
			}
			if (items != null) {
				return items;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void add(ConsumDetails consum) {
		String sql = "INSERT INTO consum_details (`amount`,`price`,`input_date`,`note`,`con_id`,`price_sum`)"
				+ "VALUES ('" + consum.getAmount() + "'," + consum.getPrice() + ",'" + consum.getInput_date() + "','"
				+ consum.getNote() + "'," + consum.getCon_id() + "," + consum.getPrice_sum() + ");";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM consum_details WHERE id = " + id;
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
}
