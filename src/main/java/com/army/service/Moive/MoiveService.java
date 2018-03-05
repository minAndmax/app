package com.army.service.Moive;


import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.VedioInfo;

public interface MoiveService {
	
	JSONObject updateMoive(HttpServletRequest request,VedioInfo vinfo)throws Exception; 
	
	JSONArray findAllMoive()throws Exception;
	
	JSONArray findOneMoiveByName(VedioInfo vedio)throws Exception;
	
	JSONArray findAllVedioManeger(VedioInfo vedio)throws Exception;
	
	JSONObject insertMoive(HttpServletRequest request,VedioInfo vedio)throws Exception;
}
