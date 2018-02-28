package com.army.util;

public enum ValidEnum {
	
	VALID("Y","有效"),NOT_VALID("N","无效");
	
	private String validStatus;
	
	private String validStatusName;
	
	private ValidEnum(String validStatus,String validStatusName) {

		this.validStatus = validStatus;
		this.validStatusName = validStatusName;
		
	}

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	public String getValidStatusName() {
		return validStatusName;
	}

	public void setValidStatusName(String validStatusName) {
		this.validStatusName = validStatusName;
	}
	
	

}
