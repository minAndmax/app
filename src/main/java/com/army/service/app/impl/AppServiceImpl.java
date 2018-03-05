package com.army.service.app.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.AppMapper;
import com.army.dao.OperateMapper;
import com.army.service.app.AppService;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.util.ValidEnum;
import com.army.vo.AppInfo;
import com.army.vo.OperateInfo;

@Service("AppService")
public class AppServiceImpl implements AppService{
	
	private final static Logger log = LoggerFactory.getLogger(AppServiceImpl.class);
	
	@Autowired
	private AppMapper appMapper;
	
	@Autowired
	private OperateMapper operateMapper;
	
	@Override
	public JSONObject updateApp(HttpServletRequest request, AppInfo ainfo) throws Exception {
		JSONObject obj = new JSONObject();
		try {
			
			appMapper.updateApp(ainfo);
			
			obj.put(KeyWord.TIPSTATUS, ValidEnum.VALID.getValidStatus());
			obj.put(KeyWord.TIPSTATUSCONTEN, ValidEnum.VALID.getValidStatusName());
			
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			OperateInfo opt = new OperateInfo();

			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("修改软件APP");
			opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "修改APP，修改:"
					+ ainfo.getAppName() == null
							? ""
							: "软件名称 " + ainfo.getAppName() == null ? ""
									: "软件描述 " + ainfo.getAppRemark() == null ? ""
											 : "状态");
			opt.setTypeId(ainfo.getAppId());

			operateMapper.inserObject(opt);

			log.info("软件修改成功[ {} ]" + obj);
		} catch (Exception e) {
	            obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
				obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
				log.error("程序异常，软件修改失败[ {} ]" + e.getMessage());
		}
		return obj;
	}

	@Override
	public JSONArray findAllApp() throws Exception {
		
		JSONArray arry = new JSONArray();
		
		List<AppInfo> ainfo = appMapper.findAllApp();
		for(AppInfo a : ainfo) {
			arry.add(a);
		}
		return arry;
	}

	@Override
	public JSONArray findOneAppByName(AppInfo ainfo) throws Exception {
		
		JSONArray arry = new JSONArray();
		
		List<AppInfo> ai = appMapper.findOneAppByName(ainfo);
		for(AppInfo a : ai) {
			arry.add(a);
		}
		return arry;
	}


}
