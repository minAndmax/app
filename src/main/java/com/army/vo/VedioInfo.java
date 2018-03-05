package com.army.vo;

public class VedioInfo extends BaseInfo{

	private Long vedioId;
	
	private String vedioName;
	
	private String vedioSrc;
	
	private String vedioMainUser;
	
	private String vedioDirector;
	
	public String getVedioMainUser() {
		return vedioMainUser;
	}

	public void setVedioMainUser(String vedioMainUser) {
		this.vedioMainUser = vedioMainUser;
	}

	public String getVedioDirector() {
		return vedioDirector;
	}

	public void setVedioDirector(String vedioDirector) {
		this.vedioDirector = vedioDirector;
	}

	public Long getVedioId() {
		return vedioId;
	}

	public void setVedioId(Long vedioId) {
		this.vedioId = vedioId;
	}

	public String getVedioName() {
		return vedioName;
	}

	public void setVedioName(String vedioName) {
		this.vedioName = vedioName;
	}

	public String getVedioSrc() {
		return vedioSrc;
	}

	public void setVedioSrc(String vedioSrc) {
		this.vedioSrc = vedioSrc;
	}

}
