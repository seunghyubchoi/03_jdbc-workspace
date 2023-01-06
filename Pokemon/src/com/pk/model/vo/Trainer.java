package com.pk.model.vo;

public class Trainer {
	private int trNo;
	private String trId;
	private String trPwd;
	private String trName;
	
	public Trainer() {
		
	}
	
	public Trainer(int trNo, String trId, String trPwd, String trName) {
		super();
		this.trNo = trNo;
		this.trId = trId;
		this.trPwd = trPwd;
		this.trName = trName;
	}

	public int getTrNo() {
		return trNo;
	}

	public void setTrNo(int trNo) {
		this.trNo = trNo;
	}

	public String getTrId() {
		return trId;
	}

	public void setTrId(String trId) {
		this.trId = trId;
	}

	public String getTrPwd() {
		return trPwd;
	}

	public void setTrPwd(String trPwd) {
		this.trPwd = trPwd;
	}

	public String getTrName() {
		return trName;
	}

	public void setTrName(String trName) {
		this.trName = trName;
	}

	@Override
	public String toString() {
		return "Trainer [trNo=" + trNo + ", trId=" + trId + ", trPwd=" + trPwd + ", trName=" + trName + "]";
	}

}
