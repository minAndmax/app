package com.army.service.opt.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.MoiveMapper;
import com.army.dao.MusicMapper;
import com.army.dao.NewsMapper;
import com.army.dao.NoticeMapper;
import com.army.dao.OperateMapper;
import com.army.service.opt.OperateService;
import com.army.util.KeyWord;
import com.army.util.ValidEnum;
import com.army.vo.MusicInfo;
import com.army.vo.NewsInfo;
import com.army.vo.NoticeInfo;
import com.army.vo.OperateInfo;
import com.army.vo.VedioInfo;

@Service("operateService")
public class OperateServiceImpl implements OperateService {

	@Autowired
	private OperateMapper operateMapper;

	@Autowired
	private NewsMapper newsMapper;
	
	@Autowired
	private MusicMapper musicMapper;
	
	@Autowired
	private MoiveMapper moiveMapper;
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public JSONArray findOpt(HttpServletRequest request,String tip) throws Exception {
		
		OperateInfo op = new OperateInfo();
		JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
		if(!sessionObj.getString("userName").equals("admin")) {
			op.setOptUserId(sessionObj.getLong("userId"));
		}

		if (tip.equals("update")) {
			
			op.setOptType("update");
			
		} else if (tip.equals("insert")) {
			
			op.setOptType("insert");
			
		}

		List<OperateInfo> isf = operateMapper.findOpt(op);
		JSONArray arr = new JSONArray();
		for (OperateInfo s : isf) {
			arr.add(s);
		}

		return arr;
	}

	@Override
	public JSONArray findAllCount(HttpServletRequest request) throws Exception {
		
		JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
		NewsInfo n = new NewsInfo();
		NoticeInfo b = new NoticeInfo();
		MusicInfo c = new MusicInfo();
		VedioInfo v = new VedioInfo();
		if(!sessionObj.getString("userName").equals("admin")) {
			n.setCreateName(sessionObj.getString("userName"));
			b.setCreateName(sessionObj.getString("userName"));
			c.setCreateName(sessionObj.getString("userName"));
			v.setCreateName(sessionObj.getString("userName"));
		}
		n.setValid(ValidEnum.VALID.getValidStatus());
		int newNum = newsMapper.findCount(n);
		
		b.setValid(ValidEnum.VALID.getValidStatus());
		int noticeNum = noticeMapper.findAllNoticeCount(b);
		
		c.setValid(ValidEnum.VALID.getValidStatus());
		int musicNum = musicMapper.findMusicCount(c);
		
		v.setValid(ValidEnum.VALID.getValidStatus());
		int vedioNum = moiveMapper.findVedioCount(v);
		
		JSONObject obj = new JSONObject();
		obj.put("newNum", newNum);
		obj.put("noticeNum", noticeNum);
		obj.put("musicNum", musicNum);
		obj.put("vedioNum", vedioNum);
		
		JSONArray arr = new JSONArray();
		
		arr.add(obj);
		
		
		return arr;
	}

}
