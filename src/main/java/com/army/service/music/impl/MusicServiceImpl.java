package com.army.service.music.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.MusicMapper;
import com.army.dao.OperateMapper;
import com.army.service.music.MusicService;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.util.ValidEnum;
import com.army.vo.MusicInfo;
import com.army.vo.OperateInfo;

@Service("/musicService")
public class MusicServiceImpl implements MusicService{
	
	private final static Logger log = LoggerFactory.getLogger(MusicServiceImpl.class);
	
	@Autowired
	private MusicMapper musicMapper;
	
	@Autowired
	private OperateMapper operateMapper;

	@Override
	public JSONObject updateMusic(HttpServletRequest request, MusicInfo music) throws Exception {
		JSONObject obj = new JSONObject();
		try {
			musicMapper.updateMusic(music);
			
			obj.put(KeyWord.TIPSTATUS, ValidEnum.VALID.getValidStatus());
			obj.put(KeyWord.TIPSTATUSCONTEN, ValidEnum.VALID.getValidStatusName());
			
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			OperateInfo opt = new OperateInfo();

			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("修改音乐");
			opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "修改音乐，修改:"
					+ music.getMusicName() == null
							? ""
							: "音乐名称 " + music.getMusicName() == null ? ""
									: "制作人 " + music.getMusicAuthor() == null ? ""
											: "歌手 " + music.getMusicSinger() == null ? ""
													: "编曲 " + music.getMusicArrangement() == null ? "" : "状态");
			opt.setTypeId(music.getMusicId());

			operateMapper.inserObject(opt);

			log.info("音乐修改成功[ {} ]" + obj);
		} catch (Exception e) {
	            obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
				obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
				log.error("程序异常，音乐修改失败[ {} ]" + e.getMessage());
		}
		return obj;
	}

	@Override
	public JSONArray findAllMusic() throws Exception {
		JSONArray arry = new JSONArray();
		
		List<MusicInfo> minfo = musicMapper.findAllMusic();
		for(MusicInfo m : minfo) {
			arry.add(m);
		}
		return arry;
	}

	@Override
	public JSONArray findOneMusicByName(MusicInfo musicInfo) throws Exception {
		JSONArray arry = new JSONArray();
		
		List<MusicInfo> minfo = musicMapper.findOneMusicByName(musicInfo);
		for(MusicInfo m : minfo) {
			arry.add(m);
		}
		return arry;
	}

	

}
