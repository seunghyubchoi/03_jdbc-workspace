package com.pk.model.vo;

public class Pokemon {
	// 필드부
	
	private int pkNo;
	private String pkName;
	private String pkType;
	private String pkClass;
	private double pkHeight;
	private double pkWeight;
	private String pkDetail;
	
	// 생성자부
	public Pokemon() {}

	

	
	public Pokemon(String pkType) {
		super();
		this.pkType = pkType;
	}




	public Pokemon(int pkNo, String pkName, String pkType, String pkClass, double pkHeight, double pkWeight,
			String pkDetail) {
		super();
		this.pkNo = pkNo;
		this.pkName = pkName;
		this.pkType = pkType;
		this.pkClass = pkClass;
		this.pkHeight = pkHeight;
		this.pkWeight = pkWeight;
		this.pkDetail = pkDetail;
	}
	
	public Pokemon(String pkName, String pkType, String pkClass, double pkHeight, double pkWeight,
			String pkDetail) {
		super();
		this.pkName = pkName;
		this.pkType = pkType;
		this.pkClass = pkClass;
		this.pkHeight = pkHeight;
		this.pkWeight = pkWeight;
		this.pkDetail = pkDetail;
	}
	
	// getter setter

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

	public void setPkHeight(Double pkHeight) {
		this.pkHeight = pkHeight;
	}

	public double getPkWeight() {
		return pkWeight;
	}

	public void setPkWeight(Double pkWeight) {
		this.pkWeight = pkWeight;
	}

	public String getPkDetail() {
		return pkDetail;
	}

	public void setPkDetail(String pkDetail) {
		this.pkDetail = pkDetail;
	}

	
	// toString
	
	@Override
	public String toString() {
		return pkNo + ", " + pkName + ", " + pkType + ", " + pkClass
				+ ", " + pkHeight + "m, " + pkWeight + "kg, " + pkDetail;
	}
	
	
	
	
	
	
}
