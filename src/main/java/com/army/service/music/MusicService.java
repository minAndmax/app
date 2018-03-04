package com.army.service.music;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.MusicInfo;

public interface MusicService {

	JSONObject updateMusic(HttpServletRequest request,MusicInfo music)throws Exception; 
	
	JSONArray findAllMusic()throws Exception;
	
	JSONArray findOneMusicByName(MusicInfo musicInfo)throws Exception;
}
