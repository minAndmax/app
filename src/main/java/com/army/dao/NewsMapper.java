package com.army.dao;

import java.util.List;

import com.army.vo.NewsInfo;

public interface NewsMapper {

	void insertNews(NewsInfo news)throws Exception;
	
	void updateNews(NewsInfo news)throws Exception;
	
	List<NewsInfo> findAllNews()throws Exception;
	
	List<NewsInfo> findAllNewManager(NewsInfo news)throws Exception;
	
	int findCount(NewsInfo news) throws Exception;
	
	NewsInfo findNewById(NewsInfo news) throws Exception;
	
}
