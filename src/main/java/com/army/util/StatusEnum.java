package com.army.util;

public enum StatusEnum {

	SSUCCESS(1,"成功"),FAIL(0,"失败");     //状态1表示成功  0表示失败
	
	private int num;
	
	private String value;
	
	private StatusEnum(int num,String value) {
		this.num = num;
		this.value = value;
	}
	
	private StatusEnum() {
		
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
