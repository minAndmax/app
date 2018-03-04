package com.army.service.notice;


import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.NoticeInfo;

public interface NoticeService {

	JSONObject insertNotice(HttpServletRequest request,NoticeInfo notice) throws Exception;

	JSONObject updateNotice(HttpServletRequest request,NoticeInfo notice) throws Exception;

	JSONArray findAllNotice() throws Exception;
	
	JSONArray findAllNoticeManager(NoticeInfo notice)throws Exception;
	
	JSONObject findNotice(NoticeInfo notice) throws Exception;
}
