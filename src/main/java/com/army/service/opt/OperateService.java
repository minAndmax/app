package com.army.service.opt;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;

public interface OperateService {

	JSONArray findOpt(HttpServletRequest request,String tip)throws Exception;
	
	JSONArray findAllCount(HttpServletRequest request)throws Exception;//获取新闻 ,音乐,视频,通知等状态为有效的数量
	
}
