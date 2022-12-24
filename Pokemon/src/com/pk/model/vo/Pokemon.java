package com.pk.model.vo;

public class Pokemon {
	private int pkNo;
	private String pkName;
	private String pkType;
	private String pkClass;
	private double pkHeight;
	private double pkWeight;
	private String pkDetail;
	private int trNo;

	public Pokemon() {
		
	}
	
	public Pokemon(int pkNo, String pkName, String pkType, String pkClass, double pkHeight, double pkWeight,
			String pkDetail, int trNo) {
		super();
		this.pkNo = pkNo;
		this.pkName = pkName;
		this.pkType = pkType;
		this.pkClass = pkClass;
		this.pkHeight = pkHeight;
		this.pkWeight = pkWeight;
		this.pkDetail = pkDetail;
		this.trNo = trNo;
	}

	public Pokemon(String pkName, String pkType, String pkClass, double pkHeight, double pkWeight, String pkDetail,
			int trNo) {
		super();
		this.pkName = pkName;
		this.pkType = pkType;
		this.pkClass = pkClass;
		this.pkHeight = pkHeight;
		this.pkWeight = pkWeight;
		this.pkDetail = pkDetail;
		this.trNo = trNo;
	}

	public int getPkNo() {
		return pkNo;
	}

	public void setPkNo(int pkNo) {
		this.pkNo = pkNo;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public String getPkType() {
		return pkType;
	}

	public void setPkType(String pkType) {
		this.pkType = pkType;
	}

	public String getPkClass() {
		return pkClass;
	}

	public void setPkClass(String pkClass) {
		this.pkClass = pkClass;
	}

	public double getPkHeight() {
		return pkHeight;
	}

	public void setPkHeight(double pkHeight) {
		this.pkHeight = pkHeight;
	}

	public double getPkWeight() {
		return pkWeight;
	}

	public void setPkWeight(double pkWeight) {
		this.pkWeight = pkWeight;
	}

	public String getPkDetail() {
		return pkDetail;
	}

	public void setPkDetail(String pkDetail) {
		this.pkDetail = pkDetail;
	}

	public int getTrNo() {
		return trNo;
	}

	public void setTrNo(int trNo) {
		this.trNo = trNo;
	}

	@Override
	public String toString() {
		return pkName + ", " + pkType + ", " + pkClass
				+ ", " + pkHeight + "(m), " + pkWeight + "(kg), " + pkDetail + " " + trNo;
	}

}
