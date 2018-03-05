package com.army.service.news;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.NewsInfo;

public interface NewsService {

	JSONObject insertNew(HttpServletRequest request,NewsInfo news)throws Exception;
	
	JSONObject updateNew(HttpServletRequest request,NewsInfo news)throws Exception;
	
	JSONArray findAllNews()throws Exception;
	
	JSONArray findAllNewManager(NewsInfo news)throws Exception;
	
	JSONObject findNewById(NewsInfo news) throws Exception;
}
