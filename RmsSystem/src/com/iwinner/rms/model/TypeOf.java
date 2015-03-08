package com.iwinner.rms.model;

public enum TypeOf {
	CREDIT("CREDIT"), DEBIT("DEBIT");
	private String typeOf;

	private TypeOf(String typeOf) {
		this.typeOf = typeOf;
	}

	public String typeOf() {

		return typeOf;
	}
}
