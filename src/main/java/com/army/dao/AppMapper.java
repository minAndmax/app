package com.army.dao;

import java.util.List;

import com.army.vo.AppInfo;

public interface AppMapper {

	List<AppInfo> findAllApp()throws Exception;
	
	List<AppInfo> findOneAppByName(AppInfo ainfo)throws Exception;
	
	void updateApp(AppInfo ainfo)throws Exception;
}
