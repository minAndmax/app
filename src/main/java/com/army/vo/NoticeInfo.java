package com.army.vo;

public class NoticeInfo extends BaseInfo{
	
	private Long noticeId;
	
	private String noticeContent;
	
	private String noticeUser;   //谁通知
	
	private Long userId;   //表中创建此通知的人
	
	private String remark;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeUser() {
		return noticeUser;
	}

	public void setNoticeUser(String noticeUser) {
		this.noticeUser = noticeUser;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
