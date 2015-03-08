package com.iwinner.rms.model;

public enum TaskType {

	OFFICE("OFFICE"), PERSONAL("PERSONAL"), TECH("TECH"), OTHERS("OTHERS");
	private final String message;

	private TaskType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
