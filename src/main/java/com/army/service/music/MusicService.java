package com.army.service.music;


import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.MusicInfo;

public interface MusicService {

	JSONObject updateMusic(HttpServletRequest request,MusicInfo music)throws Exception; 
	
	JSONObject insertMusics(HttpServletRequest request,MusicInfo info)throws Exception;
	
	JSONArray findAllMusic(MusicInfo info)throws Exception;
	
	JSONArray findOneMusicByName(MusicInfo musicInfo)throws Exception;
	
	JSONArray findAllMusicManeger(MusicInfo musicInfo)throws Exception;

	JSONObject deletemusic(HttpServletRequest request,MusicInfo musicInfo);
}
