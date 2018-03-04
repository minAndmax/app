package com.army.dao;

import java.util.List;

import com.army.vo.MusicInfo;

public interface MusicMapper {
	
	List<MusicInfo> findAllMusic()throws Exception;
	
	List<MusicInfo> findOneMusicByName(MusicInfo musicInfo)throws Exception;
	
	void updateMusic(MusicInfo musicInfo)throws Exception;
}
