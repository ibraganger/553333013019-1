package com.dao;

import java.util.ArrayList;

import com.model.TitleName;

public class TitleDao {
	
	public ArrayList<TitleName> getAll() {
		ArrayList<TitleName> list = new ArrayList<TitleName>();
		TitleName tt = new TitleName();
		tt.setTitleName("���");
		list.add(tt);
		tt = new TitleName();
		tt.setTitleName("�ҧ���");
		list.add(tt);
		tt = new TitleName();
		tt.setTitleName("�ҧ");
		list.add(tt);
		return list;
	}

}
