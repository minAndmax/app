package com.army.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.service.news.NewsService;
import com.army.service.user.UserLoginService;
import com.army.vo.NewsInfo;
import com.army.vo.UserInfo;

@RestController
@RequestMapping("/data")
public class WebJumpController {

	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject userLogin(HttpServletRequest request,UserInfo userLogin)throws Exception{
		
		JSONObject obj = userLoginService.userLogin(request,userLogin);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/insertNew", method = RequestMethod.POST)
	public JSONObject insertNew(HttpServletRequest request, NewsInfo news)throws Exception{
		
		JSONObject obj = newsService.insertNew(request,news);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/updateNew", method = RequestMethod.POST)
	public JSONObject updateNew(HttpServletRequest request, NewsInfo news)throws Exception{
		
		JSONObject obj = newsService.updateNew(request,news);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/findAllNews", method = RequestMethod.POST)
	public JSONArray findAllNews()throws Exception{
		
		JSONArray arr = newsService.findAllNews();
		
		return arr;
		
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public JSONObject userRegist(UserInfo userLogin)throws Exception{
		
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












