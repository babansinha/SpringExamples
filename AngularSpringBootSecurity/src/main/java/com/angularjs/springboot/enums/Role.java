package com.angularjs.springboot.enums;

public enum Role {
	ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");
	
	private final String value;
	Role(String role) {
		this.value = role;
	}
	
	public String getValue() {
		return value;
	}
}
