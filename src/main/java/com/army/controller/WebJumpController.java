package com.army.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.army.service.user.UserLoginService;
import com.army.vo.UserLogin;

@RestController
@RequestMapping("/data")
public class WebJumpController {

	@Autowired
	private UserLoginService userLoginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject userLogin(UserLogin userLogin)throws Exception{
		
		JSONObject obj = userLoginService.userLogin(userLogin);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public JSONObject userRegist(UserLogin userLogin)throws Exception{
		
		JSONObject obj = userLoginService.userRegist(userLogin);
		
		return obj;
		
	}
	
	@RequestMapping("/page/{preFile}/{pageName}")
	public ModelAndView pageUrl(@PathVariable("preFile") String preFile,@PathVariable("pageName") String pageName) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName(preFile+"/"+pageName);
		
		return mv;
	}
	
	@RequestMapping("/page/{pageName}")
	public ModelAndView pageUrl(@PathVariable("pageName") String pageName) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName(pageName);
		
		return mv;
	}
	
}












