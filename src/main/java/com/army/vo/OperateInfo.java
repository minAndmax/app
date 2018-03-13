package com.army.vo;

public class OperateInfo extends BaseInfo{

	private Long optId;
	
	private String optName;
	
	private String optRemark;
	
	private Long optUserId;
	
	private Long typeId;  //操作新闻 || 音乐等的id
	
	private String optType;
	
	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public Long getOptId() {
		return optId;
	}

	public void setOptId(Long optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getOptRemark() {
		return optRemark;
	}

	public void setOptRemark(String optRemark) {
		this.optRemark = optRemark;
	}

	public Long getOptUserId() {
		return optUserId;
	}

	public void setOptUserId(Long optUserId) {
		this.optUserId = optUserId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	
	
	
}
