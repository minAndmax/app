package com.army.dao;

import java.util.List;

import com.army.vo.VedioInfo;

public interface MoiveMapper {

	List<VedioInfo> findAllMoive()throws Exception;
	
	List<VedioInfo> findOneMoiveByName(VedioInfo vedio)throws Exception;
	
	void updateMoive(VedioInfo vedio)throws Exception;
}
