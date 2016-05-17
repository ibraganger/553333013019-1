package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Department;
import com.util.DbUtil;

public class DepartmentDao {

	private Connection con = DbUtil.getConnection();

	public Department find(int id) {
		Department dp = new Department();

		try {
			String sql = "select * from department where department_id = " + Integer.toString(id);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				dp.setDepartment_id(rs.getInt("department_id"));
				dp.setDepartment_name(rs.getString("department_name"));
			}
			if (dp != null) {
				return dp;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<Department> getall() {
		
		List<Department> dp = new ArrayList<Department>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from department");
			while (rs.next()) {
				Department de = new Department();
				de.setDepartment_id(rs.getInt("department_id"));
				de.setDepartment_name(rs.getString("department_name"));
				dp.add(de);
			}
			if (dp != null) {
				return dp;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
