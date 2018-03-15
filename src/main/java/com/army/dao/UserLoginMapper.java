package com.army.dao;

import java.util.List;

import com.army.vo.UserInfo;

public interface UserLoginMapper {

	/**
	 * 用户登录
	 * 
	 * @param userLogin
	 * @return
	 * @throws Exception
	 */
	UserInfo userLogin(UserInfo userLogin) throws Exception;

	/**
	 * 用户注册
	 * 
	 * @param userLogin
	 * @returna
	 * @throws Exception
	 */
	void userRegist(UserInfo userLogin) throws Exception;

	/**
	 * 根据用户名查询，注册时去重
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	UserInfo findUserLoginByUserName(UserInfo uInfo) throws Exception;

	/**
	 * 查找所有用户
	 * 
	 * @param uInfo
	 * @return
	 * @throws Exception
	 */
	List<UserInfo> findAllUserInfo(UserInfo uInfo) throws Exception;

	/**
	 * 修改用戶信息
	 * 
	 * @param uInfo
	 * @throws Exception
	 */
	void updateUser(UserInfo uInfo) throws Exception;
}
