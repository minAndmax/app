package com.army.dao;

import java.util.List;

import com.army.vo.OperateInfo;
import com.army.vo.TVListInfo;

public interface OperateMapper {
	
	void inserObject(OperateInfo operate)throws Exception;
	
	List<OperateInfo> findOpt(OperateInfo operate)throws Exception;

}
