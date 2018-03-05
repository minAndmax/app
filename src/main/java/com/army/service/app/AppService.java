package com.army.service.app;


import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.AppInfo;

public interface AppService {
	
    JSONObject updateApp(HttpServletRequest request,AppInfo ainfo)throws Exception; 
	
	JSONArray findAllApp()throws Exception;
	
	JSONArray findOneAppByName(AppInfo ainfo)throws Exception;
}
