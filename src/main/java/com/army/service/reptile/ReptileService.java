package com.army.service.reptile;


import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.ReptileNewsInfo;

public interface ReptileService {

	JSONObject insertReptileNews()throws Exception;
	
	JSONObject updateReptileNews(HttpServletRequest request,ReptileNewsInfo news)throws Exception;
	
	JSONArray findAllReptileNews(ReptileNewsInfo news)throws Exception;
	
	JSONArray findAllReptileNewsManager(ReptileNewsInfo news)throws Exception;
	
	JSONObject findById(ReptileNewsInfo news);
	
	JSONObject findpullNewsById(ReptileNewsInfo news) throws Exception;
<<<<<<< HEAD
	
	void insertJiSuNew(ReptileNewsInfo news)throws Exception;
=======

>>>>>>> 4eee81994e89e0a76fd6116f8dcb567db8103e8f
}
