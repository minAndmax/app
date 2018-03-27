package com.army.dao;

import java.util.List;

import com.army.vo.NoticeInfo;

public interface NoticeMapper {

	void insertNotice(NoticeInfo notice) throws Exception;

	void updateNotice(NoticeInfo notice) throws Exception;

	List<NoticeInfo> findAllNotice() throws Exception;

	List<NoticeInfo> findAllNoticeManager(NoticeInfo notice) throws Exception;

	int findAllNoticeCount(NoticeInfo notice) throws Exception;

	NoticeInfo findNoticeById(NoticeInfo info) throws Exception;

}
