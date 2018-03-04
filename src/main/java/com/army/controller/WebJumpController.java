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
import com.army.service.Moive.MoiveService;
import com.army.service.app.AppService;
import com.army.service.music.MusicService;
import com.army.service.news.NewsService;
import com.army.service.user.UserLoginService;
import com.army.vo.AppInfo;
import com.army.vo.MusicInfo;
import com.army.vo.NewsInfo;
import com.army.vo.UserInfo;
import com.army.vo.VedioInfo;

@RestController
@RequestMapping("/data")
public class WebJumpController {

	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private MusicService musicService;
	
	@Autowired
	private MoiveService moiveService;
	
	@Autowired
	private AppService appService;
	
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

	@RequestMapping(value = "/updateMusic", method = RequestMethod.POST)
	public JSONObject updateMusic(HttpServletRequest request, MusicInfo music)throws Exception{
		
		JSONObject obj = musicService.updateMusic(request, music);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/updateMoive", method = RequestMethod.POST)
	public JSONObject updateMoive(HttpServletRequest request, VedioInfo vedio)throws Exception {
	
		JSONObject obj = moiveService.updateMoive(request, vedio);
		
		return obj;
	}
	
	@RequestMapping(value = "/updateApp", method = RequestMethod.POST)
	public JSONObject updateApp(HttpServletRequest request, AppInfo app)throws Exception {
		
		JSONObject obj = appService.updateApp(request, app);
		
		return obj;
	}
	
	@RequestMapping(value ="/findAllMusic", method = RequestMethod.GET)
	public JSONArray findAllMusic()throws Exception{
		
		JSONArray arry = musicService.findAllMusic();
		
		return arry;
	}
	
	@RequestMapping(value ="/findAllMusicByName", method = RequestMethod.GET)
	public JSONArray findAllMusicByName(MusicInfo music)throws Exception{
		
		JSONArray arry = musicService.findOneMusicByName(music);
		
		return arry;
	}
	
	@RequestMapping(value ="/findAllMoive", method = RequestMethod.GET)
	public JSONArray findAllMoive()throws Exception{
		
		JSONArray arry = moiveService.findAllMoive();
				
		return arry;
	}
	
	@RequestMapping(value ="/findAllMoiveByName", method = RequestMethod.GET)
	public JSONArray findAllMoiveByName(VedioInfo vedio)throws Exception{
		
		JSONArray arry = moiveService.findOneMoiveByName(vedio);
		
		return arry;
	}
	
	@RequestMapping(value ="/findAllApp", method = RequestMethod.GET)
	public JSONArray findAllApp()throws Exception{
		
		JSONArray arry = appService.findAllApp();
		
		return arry;
	}
	
	@RequestMapping(value ="/findAllAppByName", method = RequestMethod.GET)
	public JSONArray findAllAppByName(AppInfo app)throws Exception{
		
		JSONArray arry = appService.findOneAppByName(app);
		
		return arry;
	}
	
}












