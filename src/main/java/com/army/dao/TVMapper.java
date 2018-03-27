package com.army.dao;

import java.util.List;

import com.army.vo.PreFileInfo;
import com.army.vo.TVListInfo;

public interface TVMapper {
	
	List<PreFileInfo> findAllTVManeger(PreFileInfo file)throws Exception;
	
	int findTvCount(PreFileInfo file)throws Exception;
	
	List<PreFileInfo> findAllTv(PreFileInfo file)throws Exception;
	
	void insertPreFile(PreFileInfo file)throws Exception;
	
	void insertTv(TVListInfo tv)throws Exception;

	void deleteTv(TVListInfo tv);
	
	void updateTv(PreFileInfo file);

	TVListInfo findTVById(TVListInfo tv) throws Exception;
	
}
