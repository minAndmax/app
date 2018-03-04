package com.army.service.notice.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.NoticeMapper;
import com.army.dao.OperateMapper;
import com.army.service.notice.NoticeService;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.util.ValidEnum;
import com.army.vo.NoticeInfo;
import com.army.vo.OperateInfo;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	private final static Logger log = LoggerFactory.getLogger(NoticeServiceImpl.class);

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private OperateMapper operateMapper;

	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject insertNotice(HttpServletRequest request, NoticeInfo notice) throws Exception{

		JSONObject obj = new JSONObject();

		try {
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
			
			notice.setValid(ValidEnum.VALID.getValidStatus());
			notice.setRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "添加通知，内容《"	+ "》,通知人：" + notice.getNoticeUser());
			notice.setCreateName(sessionObj.getString("userName"));
			notice.setUserId(sessionObj.getLong("userId"));
			noticeMapper.insertNotice(notice);
            
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());


			OperateInfo opt = new OperateInfo();

			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("添加通知");
			opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "添加通知，内容《"
					+ notice.getNoticeContent() + "》，通知人:" + notice.getNoticeUser());
			opt.setTypeId(notice.getNoticeId());

			operateMapper.inserObject(opt);

			log.info("通知添加成功[ {} ]" + obj);

		} catch (Exception e) {
            e.printStackTrace();
            obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			log.error("程序异常，通知添加失败[ {} ]" + e.getMessage());
		}

		
		return obj;
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject updateNotice(HttpServletRequest request, NoticeInfo notice) throws Exception {

		JSONObject obj = new JSONObject();
		
		
		try {
			NoticeInfo find = noticeMapper.findNoticeById(notice);

			noticeMapper.updateNotice(notice);

			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			OperateInfo opt = new OperateInfo();

			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("修改通知");
			StringBuilder sb = new StringBuilder();
			
			sb.append(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName"));
			sb.append("修改通知《"+find.getNoticeContent()+"》:");
			if(notice.getValid() != null) {
				sb.append(notice.getValid() == "Y" ? "状态为:有效" : "状态为:无效"+"");
			}
			if(notice.getValid() == null && notice.getNoticeContent() != null) {
				sb.append("修改内容:"+notice.getNoticeContent());
			}
			sb.append("修改拟定人:"+notice.getNoticeUser());
			opt.setOptRemark(sb.toString());
			
			opt.setTypeId(notice.getNoticeId());

			operateMapper.inserObject(opt);
			
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());
			
			log.info("通知修改成功[ {} ]" + obj);

		} catch (Exception e) {

			obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			log.info("程序异常，通知修改失败[ {} ]" + e.getMessage());
		}
		
		return obj;
	}

	@Override
	public JSONArray findAllNotice() throws Exception {

		List<NoticeInfo> notices = noticeMapper.findAllNotice();
		JSONArray arr = new JSONArray();
		for (NoticeInfo notice : notices) {
			arr.add(notice);
		}

		return arr;
	}

	@Override
	public JSONArray findAllNoticeManager(NoticeInfo notice) throws Exception {
		JSONArray arr = new JSONArray();
		int pages = noticeMapper.findAllNoticeCount(notice);
		double db = Math.ceil((double) pages / (double) notice.getSize());
		List<NoticeInfo> ns = noticeMapper.findAllNoticeManager(notice);
		if (ns.size() > 0) {
			ns.get(0).setTotalPages((int) db);
			ns.get(0).setPage(notice.getPage());
		} else {
			NoticeInfo f = new NoticeInfo();
			f.setTotalPages(0);
			ns.add(f);
		}
		for (NoticeInfo os : ns) {
			arr.add(os);
		}

		return arr;
	}

	@Override
	public JSONObject findNotice(NoticeInfo notice) throws Exception {
		
		NoticeInfo find = noticeMapper.findNoticeById(notice);
		JSONObject obj = new JSONObject();
		obj.put("jsonobejct", find);
		
		return obj;
	}

}
