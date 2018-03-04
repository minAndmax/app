package com.army.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

public class IPAdressInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        boolean flag = true;
		if (request.getRequestURI().indexOf("manager") != -1) {
			JSONObject obj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			if (obj == null) {
				response.sendRedirect("/manager/login.html");
				flag = false;
			}
		} else {
			flag = true;
		}
		return flag;

	}
}
