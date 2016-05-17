package com.model;

public class ConsumDetails {
	private int id;
	private int amount;
	private double price;
	private String input_date;
	private String note;
	private int con_id;
	private double price_sum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getInput_date() {
		return input_date;
	}
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getCon_id() {
		return con_id;
	}
	public void setCon_id(int con_id) {
		this.con_id = con_id;
	}
	public double getPrice_sum() {
		return price_sum;
	}
	public void setPrice_sum(double price_sum) {
		this.price_sum = price_sum;
	}
}
