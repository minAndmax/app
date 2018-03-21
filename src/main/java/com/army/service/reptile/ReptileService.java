package com.army.service.reptile;


import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.ReptileNewsInfo;

public interface ReptileService {

	JSONObject insertReptileNews(HttpServletRequest request)throws Exception;
	
	JSONObject updateReptileNews(HttpServletRequest request,ReptileNewsInfo news)throws Exception;
	
	JSONArray findAllReptileNews(ReptileNewsInfo news)throws Exception;
	
	JSONArray findAllReptileNewsManager(ReptileNewsInfo news)throws Exception;
	
	JSONObject findpullNewsById(ReptileNewsInfo news) throws Exception;
}
