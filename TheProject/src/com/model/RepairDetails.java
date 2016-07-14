package com.model;

public class RepairDetails {
	private int id;
	private int asset_id;
	private String asset_code;
	private String asset_name;
	private String note;
	private int repair_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(int asset_id) {
		this.asset_id = asset_id;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getRepair_id() {
		return repair_id;
	}

	public void setRepair_id(int repair_id) {
		this.repair_id = repair_id;
	}

}
