package com.army.dao;

import java.util.List;

import com.army.vo.MusicInfo;

public interface MusicMapper {
	
	List<MusicInfo> findAllMusic(MusicInfo info)throws Exception;
	
	void insertMusics(MusicInfo info)throws Exception;
	
	MusicInfo findOneMusicByName(MusicInfo musicInfo)throws Exception;
	
	void updateMusic(MusicInfo musicInfo)throws Exception;
	
	int findMusicCount(MusicInfo musicInfo)throws Exception;
	
	List<MusicInfo> findAllMusicManeger(MusicInfo musicInfo)throws Exception;

	void deleteMusic(MusicInfo tv);

}
