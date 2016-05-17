package com.model;

public class Consumable {
	private int con_id;
	private String con_code;
	private String con_name;
	private String unit;
	private String note;
	private String picture;
	private int amount_tt;
	private int imp_amount;
	private int exp_amount;
	private double price_tt;
	private String storage;
	private double price;
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public int getCon_id() {
		return con_id;
	}
	public void setCon_id(int con_id) {
		this.con_id = con_id;
	}
	public String getCon_code() {
		return con_code;
	}
	public void setCon_code(String con_code) {
		this.con_code = con_code;
	}
	public String getCon_name() {
		return con_name;
	}
	public void setCon_name(String con_name) {
		this.con_name = con_name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getAmount_tt() {
		return amount_tt;
	}
	public void setAmount_tt(int amount_tt) {
		this.amount_tt = amount_tt;
	}
	public int getImp_amount() {
		return imp_amount;
	}
	public void setImp_amount(int imp_amount) {
		this.imp_amount = imp_amount;
	}
	public int getExp_amount() {
		return exp_amount;
	}
	public void setExp_amount(int exp_amount) {
		this.exp_amount = exp_amount;
	}
	public double getPrice_tt() {
		return price_tt;
	}
	public void setPrice_tt(double price_tt) {
		this.price_tt = price_tt;
	}
}
