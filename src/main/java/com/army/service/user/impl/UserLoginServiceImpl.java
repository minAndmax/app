package com.army.service.user.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.OperateMapper;
import com.army.dao.UserLoginMapper;
import com.army.service.user.UserLoginService;
import com.army.util.KeyWord;
import com.army.util.MD5Util;
import com.army.util.StatusEnum;
import com.army.vo.OperateInfo;
import com.army.vo.UserInfo;

@Service(value = "userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

	private static final Logger log = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Autowired
	private UserLoginMapper userLoginMapper;
	
	@Autowired
	private OperateMapper operateMapper;

	public JSONObject userLogin(HttpServletRequest request, UserInfo userLogin) throws Exception {

		JSONObject obj = new JSONObject();

		userLogin.setUserPassword(MD5Util.getMD5(userLogin.getUserPassword()));

		UserInfo user = userLoginMapper.userLogin(userLogin);

		if (user == null) {

			obj.put("loginTip", "登录失败，用户名或密码错误");
			obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum()); // 0表示登录失败
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());

		} else {

			obj.put("userName", user.getUserName());
			obj.put("userId", user.getUserId());
			obj.put("roleName", user.getRoleName());

			obj.put("loginTip", "登录成功");
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum()); // 1表示登录成功
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());

			request.getSession().setAttribute(KeyWord.USERSESSION, obj);
		}
		
		log.info("执行了 userLogin: " + obj);
		return obj;
	}
	@Transactional(rollbackFor=Exception.class)
	public JSONObject userRegist(UserInfo userLogin) throws Exception {

		JSONObject obj = new JSONObject();

		UserInfo ul = userLoginMapper.findUserLoginByUserName(userLogin);

		if (ul != null) {

			obj.put("userRegist", StatusEnum.FAIL.getNum());
			obj.put("registTip", "用户名已存在，请重新输入");

			log.info("执行了 userRegist,用户注册:注册失败，用户名已存在");

		} else {

			userLogin.setUserPassword(MD5Util.getMD5(userLogin.getUserPassword()));

			userLoginMapper.userRegist(userLogin);

			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum()); // 1表示登录成功
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());

			log.info("执行了 userRegist,用户注册:注册成功");
		}

		return obj;
	}

	@Override
	public JSONArray findAllUserInfo(UserInfo uInfo) throws Exception {
		JSONArray arry = new JSONArray();
		
		List<UserInfo> uinfo = userLoginMapper.findAllUserInfo(uInfo);
		for(UserInfo u : uinfo) {
			arry.add(u);
		}
		return arry;
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject updateUser(HttpServletRequest request, UserInfo uInfo) throws Exception {
		if(uInfo.getUserPassword() != null) {
			uInfo.setUserPassword(MD5Util.getMD5(uInfo.getUserPassword()));
		}
		JSONObject obj = new JSONObject();
		try {
			 userLoginMapper.updateUser(uInfo);
			 
			 UserInfo u = userLoginMapper.findUserLoginByUserName(uInfo);
			 obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			 obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());
				
				JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

				OperateInfo opt = new OperateInfo();
				opt.setOptType("update");
				opt.setOptUserId(sessionObj.getLong("userId"));
				opt.setOptName("修改用户");
				
				StringBuilder sb = new StringBuilder();
				sb.append(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + ",修改《"+u.getUserName()+"》，");
				if(uInfo.getValid() != null) {
					sb.append(uInfo.getValid().equals("Y")? "修改状态为：有效": "修改状态为：无效 ");
				}
				opt.setOptRemark(sb.toString());
				
				opt.setTypeId(uInfo.getUserId());

				operateMapper.inserObject(opt);

				log.info("用户信息修改成功[ {} ]" + obj);
			} catch (Exception e) {
				obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
				obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
					log.error("程序异常，用户信息修改失败[ {} ]" + e.getMessage());
			}
			return obj;
	}
	@Override
	public JSONObject findUserById(UserInfo uInfo) throws Exception {
		UserInfo ui = userLoginMapper.findUserLoginByUserName(uInfo);
		
		JSONObject obj = new JSONObject();
		obj.put("jsonobejct", ui);
		
		return obj;
	}

}
