package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Faculty;
import com.util.DbUtil;

public class FacultyDao {
	private Connection con = DbUtil.getConnection();

	public Faculty find(int id) {
		Faculty fc = new Faculty();
		try {
			String sql = "select * from faculty where faculty_id = " + Integer.toString(id);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				fc.setFaculty_id(rs.getInt("faculty_id"));
				fc.setFaculty_name(rs.getString("faculty_name"));
			}
			if (fc.getFaculty_name() != null) {
				return fc;
			} else {
				return fc;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<Faculty> getAll() {
		
		List<Faculty> fc = new ArrayList<Faculty>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from faculty");
			while (rs.next()) {
				Faculty fa = new Faculty();
				fa.setFaculty_id(rs.getInt("faculty_id"));
				fa.setFaculty_name(rs.getString("faculty_name"));
				fc.add(fa);
			}
			if (fc != null) {
				return fc;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return null;
	}

}
