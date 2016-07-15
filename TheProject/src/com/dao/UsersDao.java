package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Users;
import com.util.DbUtil;

public class UsersDao {

	private Connection connection;
	private int noOfRecords;

	public UsersDao() {
		connection = DbUtil.getConnection();
	}

	public List<Users> getAll() {
		List<Users> list = new ArrayList<Users>();
		try {
			String sql = "select * from users ORDER BY user_code DESC;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Users user = new Users();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_code(rs.getString("user_code"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setTitle(rs.getString("title"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setFaculty_id(rs.getInt("faculty_id"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setLast_update(rs.getString("last_update"));
				user.setStatus(rs.getString("status"));
				list.add(user);
			}

			if (list.toArray().length != 0) {
				return list;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public Users find(int user_id) {

		Users user = new Users();
		try {
			String sql = "select * from users where user_id =" + Integer.toString(user_id);
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_code(rs.getString("user_code"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setTitle(rs.getString("title"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setFaculty_id(rs.getInt("faculty_id"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setLast_update(rs.getString("last_update"));
				user.setStatus(rs.getString("status"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public Users findUsername(String username) {

		Users user = new Users();
		try {
			String sql = "select * from users where username like '" + username + "'";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_code(rs.getString("user_code"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setTitle(rs.getString("title"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setFaculty_id(rs.getInt("faculty_id"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setLast_update(rs.getString("last_update"));
				user.setStatus(rs.getString("status"));
			}
			if (user.getUsername() != null) {
				return user;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Users findUserCode(String user_code) {

		Users user = new Users();
		try {
			String sql = "select * from users where user_code like '" + user_code + "'";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_code(rs.getString("user_code"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setTitle(rs.getString("title"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setFaculty_id(rs.getInt("faculty_id"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setLast_update(rs.getString("last_update"));
			}
			if (user.getUsername() != null) {
				return user;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Users findEmail(String email) {

		Users user = new Users();
		try {
			String sql = "select * from users where email like '" + email + "'";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_code(rs.getString("user_code"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setTitle(rs.getString("title"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setFaculty_id(rs.getInt("faculty_id"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setLast_update(rs.getString("last_update"));
				user.setStatus(rs.getString("status"));
			}
			if (user.getUsername() != null) {
				return user;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void add(Users user) {
		String sql = "call add_user('" + user.getUser_code() + "','" + user.getUsername() + "','" + user.getTitle()
				+ "','" + user.getPassword() + "','" + user.getFirst_name() + "','" + user.getLast_name() + "','"
				+ user.getAddress() + "','" + user.getPhone() + "','" + user.getEmail() + "','" + user.getRole() + "',"
				+ user.getFaculty_id() + "," + user.getDepartment_id() + ",'" + user.getLast_update() + "',"
				+ "'active')";
		try {
			Statement st = connection.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "delete from users where user_id = " + Integer.toString(id) + ";";
		try {
			Statement st = connection.createStatement();
			st.executeLargeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(Users user) {
		String sql = "call edit_user(" + user.getUser_id() + ",'" + user.getUser_code() + "','" + user.getUsername()
				+ "','" + user.getTitle() + "','" + user.getPassword() + "','" + user.getFirst_name() + "','"
				+ user.getLast_name() + "','" + user.getAddress() + "','" + user.getPhone() + "','" + user.getEmail()
				+ "','" + user.getRole() + "'," + user.getFaculty_id() + "," + user.getDepartment_id() + ",'"
				+ user.getLast_update() + "','" + user.getStatus() + "')";
		try {
			Statement st = connection.createStatement();
			st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Users> searchUser(String search, String fa_id, String dp_id, String role) {
		List<Users> list = new ArrayList<Users>();
		if (fa_id == "" | fa_id == null | fa_id.equals("null")) {
			fa_id = "";
		} else {
			fa_id = " and faculty_id = " + fa_id;
		}

		if (dp_id == "" | dp_id == null | dp_id.equals("null")) {
			dp_id = "";
		} else {
			dp_id = " and department_id = " + dp_id;
		}

		if (role == "" | role == null | role.equals("null")) {
			role = "";
		} else {
			role = " and role like '%" + role + "%'";
		}
		if (search == null | search == "" | search.equals("null")) {
			search = "";
		}
		try {
			String sql = "select * from users where " + "(user_code like '%" + search + "%'" + " or username like '%"
					+ search + "%' " + " or first_name like '%" + search + "%' " + " or last_name like '%" + search
					+ "%') " + fa_id + dp_id + role + " ORDER BY user_code DESC;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Users user = new Users();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_code(rs.getString("user_code"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setTitle(rs.getString("title"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setFaculty_id(rs.getInt("faculty_id"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setLast_update(rs.getString("last_update"));
				user.setStatus(rs.getString("status"));
				list.add(user);
			}
			if (list != null) {
				return list;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

}
