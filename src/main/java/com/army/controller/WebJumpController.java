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
import com.army.service.notice.NoticeService;
import com.army.service.user.UserLoginService;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.vo.NewsInfo;
import com.army.vo.NoticeInfo;
import com.army.vo.UserInfo;

@RestController
@RequestMapping("/data")
public class WebJumpController {

	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject userLogin(HttpServletRequest request,UserInfo userLogin)throws Exception{
		
		JSONObject obj = userLoginService.userLogin(request,userLogin);
		
		return obj;
		
	}
	//新闻开始
	@RequestMapping(value = "/manager/insertNew", method = RequestMethod.POST)
	public JSONObject insertNew(HttpServletRequest request, NewsInfo news)throws Exception{
		
		JSONObject obj = newsService.insertNew(request,news);
		
		return obj;
		
	}
	
	/**
	 * 线上新闻
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/findAllNews", method = RequestMethod.POST)
	public JSONArray findAllNews()throws Exception{
		
		JSONArray arr = newsService.findAllNews();
		
		return arr;
		
	}
	
	@RequestMapping(value = "/manager/findNewById", method = RequestMethod.POST)
	public JSONObject findNewById(NewsInfo news)throws Exception{
		
		JSONObject obj = newsService.findNewById(news);
		
		return obj;
		
	}

	@RequestMapping(value = "/manager/findAllNewManager", method = RequestMethod.POST)
	public JSONArray findAllNewManager(NewsInfo news)throws Exception{
		
		JSONArray arr = newsService.findAllNewManager(news);
		
		return arr;
		
	}
	
	@RequestMapping(value = "/manager/change", method = RequestMethod.POST)
	public JSONObject changerValid(HttpServletRequest request, NewsInfo news)throws Exception{
		
		JSONObject obj = newsService.updateNew(request, news);
		
		return obj;
		
	}
	
	//新闻结束
	
	//通知开始
	/**
	 * 后台通知
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager/insertNotice", method = RequestMethod.POST)
	public JSONObject insertNotice(HttpServletRequest request,NoticeInfo info)throws Exception{
		
		JSONObject obj = noticeService.insertNotice(request,info);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/manager/findNotice", method = RequestMethod.POST)
	public JSONObject findNotice(NoticeInfo info)throws Exception{
		
		JSONObject obj = noticeService.findNotice(info);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/manager/updateNotice", method = RequestMethod.POST)
	public JSONObject updateNotice(HttpServletRequest request,NoticeInfo info)throws Exception{
		
		JSONObject obj = noticeService.updateNotice(request,info);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/manager/findAllNoticeManager", method = RequestMethod.POST)
	public JSONArray findAllNoticeManager(NoticeInfo info)throws Exception{
		
		JSONArray arr = noticeService.findAllNoticeManager(info);
		
		return arr;
		
	}
	
	/**
	 * 线上通知
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findAllNotice", method = RequestMethod.POST)
	public JSONArray findAllNotice()throws Exception{
		
		JSONArray arr = noticeService.findAllNotice();
		
		return arr;
		
	}
	
	//通知结束
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public JSONObject userRegist(UserInfo userLogin)throws Exception{
		
		JSONObject obj = userLoginService.userRegist(userLogin);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/manager/getUser", method = RequestMethod.POST)
	public JSONObject getUser(HttpServletRequest request)throws Exception{
		
		JSONObject obj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/manager/outLogin", method = RequestMethod.POST)
	public JSONObject outLogin(HttpServletRequest request)throws Exception{
		
		JSONObject obj = new JSONObject(); 
		if(request.getSession().getAttribute(KeyWord.USERSESSION) != null) {
			request.getSession().removeAttribute(KeyWord.USERSESSION);
			obj.put("outTip", StatusEnum.SSUCCESS.getNum());
		} else {
			obj.put("outTip", StatusEnum.FAIL.getNum());
		}
		
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












