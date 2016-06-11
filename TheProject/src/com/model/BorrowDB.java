package com.model;

public class BorrowDB {
	private int bor_id;
	private String document_no;
	private String use_for;
	private String date;
	private String return_date;
	private String status;
	private String note;
	public int getBor_id() {
		return bor_id;
	}
	public void setBor_id(int bor_id) {
		this.bor_id = bor_id;
	}
	public String getDocument_no() {
		return document_no;
	}
	public void setDocument_no(String document_no) {
		this.document_no = document_no;
	}
	public String getUse_for() {
		return use_for;
	}
	public void setUse_for(String use_for) {
		this.use_for = use_for;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	private int user_id;

}
