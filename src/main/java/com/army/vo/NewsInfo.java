package com.army.vo;

public class NewsInfo extends BaseInfo{

	private Long newId;

	private String newName;

	private String newContent;

	private String newAuthor;
	
	private String newImags;

	private String newRemark;
	
	private Integer newType;
	
	public String getNewAuthor() {
		return newAuthor;
	}

	public void setNewAuthor(String newAuthor) {
		this.newAuthor = newAuthor;
	}

	public Integer getNewType() {
		return newType;
	}

	public void setNewType(Integer newsType) {
		this.newType = newsType;
	}

	public String getNewRemark() {
		return newRemark;
	}

	public void setNewRemark(String newRemark) {
		this.newRemark = newRemark;
	}

	public Long getNewId() {
		return newId;
	}

	public void setNewId(Long newId) {
		this.newId = newId;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewContent() {
		return newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}

	public String getNewImags() {
		return newImags;
	}

	public void setNewImags(String newImags) {
		this.newImags = newImags;
	}

}
