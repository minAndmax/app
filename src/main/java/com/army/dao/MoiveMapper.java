package com.army.dao;

import java.util.List;

import com.army.vo.VedioInfo;

public interface MoiveMapper {

	List<VedioInfo> findAllMoive(VedioInfo vedio)throws Exception;
	
	List<VedioInfo> findOneMoiveByName(VedioInfo vedio)throws Exception;
	
	void updateMoive(VedioInfo vedio)throws Exception;
	
	void insertMoive(VedioInfo vedio)throws Exception;
	
	int findVedioCount(VedioInfo vedio)throws Exception;
	
	List<VedioInfo> findAllVedioManeger(VedioInfo vedio)throws Exception;
	
	VedioInfo findVedioById(VedioInfo vedio)throws Exception;
}
