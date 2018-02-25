package com.army.util;

public enum StatusEnum {

	SSUCCESS(1),FAIL(0);     //状态1表示成功  0表示失败
	
	private int num;
	
	private StatusEnum(int num) {
		this.num = num;
	}
	
	private StatusEnum() {
		
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
}
