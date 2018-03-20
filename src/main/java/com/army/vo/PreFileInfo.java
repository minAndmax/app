package com.army.vo;

import java.util.List;

public class PreFileInfo extends BaseInfo{
	
	private long preFileId;
	
	private String preFileName;
	
	private List<TVListInfo> tvlist;
	
	public List<TVListInfo> getTvlist() {
		return tvlist;
	}

	public void setTvlist(List<TVListInfo> tvlist) {
		this.tvlist = tvlist;
	}

	public long getPreFileId() {
		return preFileId;
	}

	public void setPreFileId(long preFileId) {
		this.preFileId = preFileId;
	}

	public String getPreFileName() {
		return preFileName;
	}

	public void setPreFileName(String preFileName) {
		this.preFileName = preFileName;
	}

}
