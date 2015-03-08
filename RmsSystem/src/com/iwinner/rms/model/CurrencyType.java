package com.iwinner.rms.model;

public enum CurrencyType {
	RUPEES("RS"), RINGAT("RM"), DOLLOAR("DOLLAR"), EURO("EU");
	private String cType;

	private CurrencyType(String cType) {
		this.cType = cType;
	}

	public String getCType() {

		return cType;
	}
}
