package com.model;

public class Permanent {
	
	private int per_id;
	private String per_code;
	private String per_name;
	private int amount;
	private String unit;
	private double price;
	private double peice_sum;
	private String input_date;
	public int getPer_id() {
		return per_id;
	}
	public void setPer_id(int per_id) {
		this.per_id = per_id;
	}
	public String getPer_code() {
		return per_code;
	}
	public void setPer_code(String per_code) {
		this.per_code = per_code;
	}
	public String getPer_name() {
		return per_name;
	}
	public void setPer_name(String per_name) {
		this.per_name = per_name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPeice_sum() {
		return peice_sum;
	}
	public void setPeice_sum(double peice_sum) {
		this.peice_sum = peice_sum;
	}
	public String getInput_date() {
		return input_date;
	}
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	public int getLife_time() {
		return life_time;
	}
	public void setLife_time(int life_time) {
		this.life_time = life_time;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	private int life_time;
	private String note;

}
