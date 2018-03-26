package com.army.dao;

import java.util.List;

import com.army.vo.ReptileNewsInfo;

public interface ReptileMapper {

	void insertReptileNews(ReptileNewsInfo news)throws Exception;
	
	void updateReptileNews(ReptileNewsInfo news)throws Exception;
	
	List<ReptileNewsInfo> findAllReptileNews(ReptileNewsInfo news)throws Exception;
	
	List<ReptileNewsInfo> findAllReptileNewsManager(ReptileNewsInfo news)throws Exception;
	
	int findCount(ReptileNewsInfo news) throws Exception;
	
	ReptileNewsInfo findByTitle(ReptileNewsInfo news)throws Exception;
	
	ReptileNewsInfo findById(ReptileNewsInfo news);
	
	
}
