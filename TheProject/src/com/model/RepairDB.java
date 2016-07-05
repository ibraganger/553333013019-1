package com.model;

public class RepairDB {
	private int repair_id;
	private String document_no;
	private String date;
	public int getRepair_id() {
		return repair_id;
	}
	public void setRepair_id(int repair_id) {
		this.repair_id = repair_id;
	}
	public String getDocument_no() {
		return document_no;
	}
	public void setDocument_no(String document_no) {
		this.document_no = document_no;
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
	public String getRepair_center() {
		return repair_center;
	}
	public void setRepair_center(String repair_center) {
		this.repair_center = repair_center;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	private String return_date;
	private String repair_center;
	private String note;
	private String user_id;
}
