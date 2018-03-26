package com.army.service.tv;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.PreFileInfo;
import com.army.vo.TVListInfo;

public interface TVService {

	JSONArray findAllTVManeger(PreFileInfo file)throws Exception;
	
	JSONArray findAllTv(PreFileInfo file)throws Exception;
	
	JSONObject insertPreFile(HttpServletRequest request,PreFileInfo file)throws Exception;

	JSONObject deleteTv(TVListInfo tv);

	JSONObject updateTv(PreFileInfo file);
	
}
