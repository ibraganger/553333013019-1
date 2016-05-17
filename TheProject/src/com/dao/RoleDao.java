package com.dao;

import java.util.ArrayList;

import com.model.Role;

public class RoleDao {
	public ArrayList<Role> getAll() {
		ArrayList<Role> list = new ArrayList<Role>();
		Role tt = new Role();
		tt.setRole("user");
		list.add(tt);
		tt = new Role();
		tt.setRole("admin");
		list.add(tt);
		return list;
	}
}
