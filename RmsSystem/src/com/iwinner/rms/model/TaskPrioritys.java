package com.iwinner.rms.model;

public enum TaskPrioritys {

	PRIORITY_1("URGENT"), PRIORITY_2("HIGH"),PRIORITY_3("MEDIUM"),PRIORITY_4("MINOR"), PRIORITY_5("LOW");

	private final String priority;

	private TaskPrioritys(String priority) {
		this.priority = priority;
	}

	public String getPriority() {
		return priority;
	}

}
