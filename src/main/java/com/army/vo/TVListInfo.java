package com.army.vo;

public class TVListInfo extends BaseInfo{

	private Long tvId;
	
	private String tvName;
	
	private String tvSrc;
	
	private Long preFileId;
	
	private int tvLevel;

	public int getTvLevel() {
		return tvLevel;
	}

	public void setTvLevel(int tvLevel) {
		this.tvLevel = tvLevel;
	}

	public long getTvId() {
		return tvId;
	}

	public void setTvId(long tvId) {
		this.tvId = tvId;
	}

	public String getTvName() {
		return tvName;
	}

	public void setTvName(String tvName) {
		this.tvName = tvName;
	}

	public String getTvSrc() {
		return tvSrc;
	}

	public void setTvSrc(String tvSrc) {
		this.tvSrc = tvSrc;
	}

	public Long getPreFileId() {
		return preFileId;
	}

	public void setPreFileId(Long preFileId) {
		this.preFileId = preFileId;
	}

	
	
	
}
