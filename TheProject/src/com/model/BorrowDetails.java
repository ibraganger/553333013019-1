package com.model;

public class BorrowDetails {

	private int id;
	private int bor_id;
	private String asset_code;
	private String asset_name;
	private int asset_id;
	public int getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(int asset_id) {
		this.asset_id = asset_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBor_id() {
		return bor_id;
	}
	public void setBor_id(int bor_id) {
		this.bor_id = bor_id;
	}
	public String getAsset_code() {
		return asset_code;
	}
	public void setAsset_code(String asset_code) {
		this.asset_code = asset_code;
	}
	public String getAsset_name() {
		return asset_name;
	}
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}
	
}
