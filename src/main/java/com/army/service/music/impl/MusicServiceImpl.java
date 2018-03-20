package com.army.service.music.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject updateMusic(HttpServletRequest request, MusicInfo music)  {
		JSONObject obj = new JSONObject();
		try {
			musicMapper.updateMusic(music);
			MusicInfo mi = musicMapper.findOneMusicByName(music);
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());
			
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			OperateInfo opt = new OperateInfo();
			opt.setOptType("update");
			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("修改音乐");
			
			StringBuilder sb = new StringBuilder();
			sb.append(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + ",修改音乐《"+mi.getMusicName()+"》，");
			sb.append(music.getValid().equals("Y")? "修改状态为：有效": "修改状态为：无效 ");
			opt.setOptRemark(sb.toString());
			
			opt.setTypeId(music.getMusicId());

			operateMapper.inserObject(opt);

			log.info("音乐修改成功[ {} ]" + obj);
		} catch (Exception e) {
			obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
				log.error("程序异常，音乐修改失败[ {} ]" + e.getMessage());
		}
		return obj;
	}

	@Override
	public JSONArray findAllMusic(MusicInfo info) throws Exception {
		JSONArray arry = new JSONArray();
		
		List<MusicInfo> minfo = musicMapper.findAllMusic(info);
		for(MusicInfo m : minfo) {
			arry.add(m);
		}
		return arry;
	}

	@Override
	public JSONArray findOneMusicByName(MusicInfo musicInfo) throws Exception {
		JSONArray arry = new JSONArray();
		
//		List<MusicInfo> minfo = musicMapper.findOneMusicByName(musicInfo);
//		for(MusicInfo m : minfo) {
//			arry.add(m);
//		}
		return arry;
	}

	@Override
	public JSONArray findAllMusicManeger(MusicInfo musicInfo) throws Exception {
		
		if(musicInfo.getCreateName().equals("admin")) {
			musicInfo.setCreateName(null);
			musicInfo.setValid(null);
		}
		
		int pages = musicMapper.findMusicCount(musicInfo);
		
		double db = Math.ceil((double) pages / (double) musicInfo.getSize());
		
		JSONArray arry = new JSONArray();
	
		List<MusicInfo> minfo = musicMapper.findAllMusicManeger(musicInfo);
		if(minfo.size() > 0) {
			minfo.get(0).setTotalPages((int)db);
		} else {
			MusicInfo f = new MusicInfo();
			f.setTotalPages(-1);
			minfo.add(f);
		}
		for(MusicInfo m : minfo) {
			arry.add(m);
		}
		return arry;
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject insertMusics(HttpServletRequest request,MusicInfo info) {
		JSONObject obj = new JSONObject();
		
		try {
			
			
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
			
			info.setValid(ValidEnum.VALID.getValidStatus());
			info.setCreateName(sessionObj.getString("userName"));
			musicMapper.insertMusics(info);
            
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());


			OperateInfo opt = new OperateInfo();
			opt.setOptType("insert");
			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("添加音乐");
			opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "添加音乐，名字《"+ info.getMusicName() + "》,演唱人：" + info.getMusicSinger());
			opt.setTypeId(info.getMusicId());

			operateMapper.inserObject(opt);

			log.info("音乐添加成功[ {} ]" + obj);
		} catch (Exception e) {
            e.printStackTrace();
            obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			log.error("程序异常，音乐添加失败[ {} ]" + e.getMessage());
		}

		
		return obj;
	}

	

}
