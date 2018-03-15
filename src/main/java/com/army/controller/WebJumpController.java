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
import com.army.service.notice.NoticeService;
import com.army.service.opt.OperateService;
import com.army.service.user.UserLoginService;
import com.army.vo.AppInfo;
import com.army.vo.MusicInfo;
import com.army.util.GetUserInfo;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.util.UpLoadImageUtil;
import com.army.vo.NewsInfo;
import com.army.vo.NoticeInfo;
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

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private OperateService operateService;

	/**
	 * 登录
	 * 
	 * @param request
	 * @param userLogin
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject userLogin(HttpServletRequest request, UserInfo userLogin) throws Exception {

		JSONObject obj = userLoginService.userLogin(request, userLogin);

		return obj;

	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager/uploadFile", method = RequestMethod.POST)
	public JSONObject uploadFile(HttpServletRequest request) throws Exception {

		JSONObject obj = UpLoadImageUtil.uploadFile(request);

		return obj;

	}

	/**
	 * 查询所有操作记录
	 * 
	 * @param request
	 * @param opt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager/findOpt", method = RequestMethod.POST)
	public JSONArray findOpt(HttpServletRequest request, String opt) throws Exception {

		JSONArray arr = operateService.findOpt(request, opt);

		return arr;

	}

	/**
	 * 查询所有信息
	 * 
	 * @param uInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager/findAllUser", method = RequestMethod.POST)
	public JSONArray findAllUser(UserInfo uInfo) throws Exception {
		JSONArray arry = userLoginService.findAllUserInfo(uInfo);

		return arry;
	}

	/**
	 * 根据ID 查找用户
	 * @param uInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager/findUserById", method = RequestMethod.POST)
	public JSONObject findUserById(UserInfo uInfo) throws Exception {

		JSONObject obj = userLoginService.findUserById(uInfo);

		return obj;

	}
	
	/**
	 * 用户信息修改
	 * 
	 * @param request
	 * @param uInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager/updateUser", method = RequestMethod.POST)
	public JSONObject updateUser(HttpServletRequest request, UserInfo uInfo) throws Exception {

		JSONObject obj = userLoginService.updateUser(request, uInfo);

		return obj;

	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager/findAllCount", method = RequestMethod.POST)
	public JSONArray findAllCount(HttpServletRequest request) throws Exception {

		JSONArray arr = operateService.findAllCount(request);

		return arr;

	}

	// 新闻开始
	@RequestMapping(value = "/manager/insertNew", method = RequestMethod.POST)
	public JSONObject insertNew(HttpServletRequest request, NewsInfo news) throws Exception {

		JSONObject obj = newsService.insertNew(request, news);

		return obj;

	}

	/**
	 * 线上新闻
	 * 
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/findAllNews", method = RequestMethod.POST)
	public JSONArray findAllNews(NewsInfo news) throws Exception {

		JSONArray arr = newsService.findAllNews(news);

		return arr;

	}

	/**
	 * 根据ID查询新闻
	 * 
	 * @param news
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager/findNewById", method = RequestMethod.POST)
	public JSONObject findNewById(NewsInfo news) throws Exception {

		JSONObject obj = newsService.findNewById(news);

		return obj;

	}

	/**
	 * web 根据ID查询新闻
	 * 
	 * @param news
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findNewById", method = RequestMethod.POST)
	public JSONObject findwebNewById(NewsInfo news) throws Exception {

		JSONObject obj = newsService.findNewById(news);

		return obj;

	}

	/**
	 * 新闻管理
	 * 
	 * @param request
	 * @param news
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager/findAllNewManager", method = RequestMethod.POST)
	public JSONArray findAllNewManager(HttpServletRequest request, NewsInfo news) throws Exception {
		news.setCreateName(GetUserInfo.getUserName(request));
		JSONArray arr = newsService.findAllNewManager(news);

		return arr;

	}

	@RequestMapping(value = "/manager/change", method = RequestMethod.POST)
	public JSONObject changerValid(HttpServletRequest request, NewsInfo news) throws Exception {

		JSONObject obj = newsService.updateNew(request, news);

		return obj;

	}

	// 新闻结束

	// 通知开始
	/**
	 * 后台通知
	 * 
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/manager/insertNotice", method = RequestMethod.POST)
	public JSONObject insertNotice(HttpServletRequest request, NoticeInfo info) throws Exception {

		JSONObject obj = noticeService.insertNotice(request, info);

		return obj;

	}

	@RequestMapping(value = "/manager/findNotice", method = RequestMethod.POST)
	public JSONObject findNotice(NoticeInfo info) throws Exception {

		JSONObject obj = noticeService.findNotice(info);

		return obj;

	}

	@RequestMapping(value = "/manager/updateNotice", method = RequestMethod.POST)
	public JSONObject updateNotice(HttpServletRequest request, NoticeInfo info) throws Exception {

		JSONObject obj = noticeService.updateNotice(request, info);

		return obj;

	}

	@RequestMapping(value = "/manager/findAllNoticeManager", method = RequestMethod.POST)
	public JSONArray findAllNoticeManager(HttpServletRequest request, NoticeInfo info) throws Exception {
		info.setCreateName(GetUserInfo.getUserName(request));
		JSONArray arr = noticeService.findAllNoticeManager(info);

		return arr;

	}

	/**
	 * 线上通知
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findAllNotice", method = RequestMethod.POST)
	public JSONArray findAllNotice() throws Exception {

		JSONArray arr = noticeService.findAllNotice();

		return arr;

	}


	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public JSONObject userRegist(UserInfo userLogin) throws Exception {

		JSONObject obj = userLoginService.userRegist(userLogin);

		return obj;

	}

	@RequestMapping(value = "/manager/getUser", method = RequestMethod.POST)
	public JSONObject getUser(HttpServletRequest request) throws Exception {

		JSONObject obj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

		return obj;

	}

	@RequestMapping(value = "/manager/outLogin", method = RequestMethod.POST)
	public JSONObject outLogin(HttpServletRequest request) throws Exception {

		JSONObject obj = new JSONObject();
		if (request.getSession().getAttribute(KeyWord.USERSESSION) != null) {
			request.getSession().removeAttribute(KeyWord.USERSESSION);
			obj.put("outTip", StatusEnum.SSUCCESS.getNum());
		} else {
			obj.put("outTip", StatusEnum.FAIL.getNum());
		}

		return obj;

	}

	@RequestMapping("/page/{preFile}/{pageName}")
	public ModelAndView pageUrl(@PathVariable("preFile") String preFile, @PathVariable("pageName") String pageName) {

		ModelAndView mv = new ModelAndView();

		mv.setViewName(preFile + "/" + pageName);

		return mv;
	}

	@RequestMapping("/page/{pageName}")
	public ModelAndView pageUrl(@PathVariable("pageName") String pageName) {

		ModelAndView mv = new ModelAndView();

		mv.setViewName(pageName);

		return mv;
	}

	// music开始
	@RequestMapping(value = "/manager/updateMusic", method = RequestMethod.POST)
	public JSONObject updateMusic(HttpServletRequest request, MusicInfo music) throws Exception {

		JSONObject obj = musicService.updateMusic(request, music);

		return obj;

	}

	@RequestMapping(value = "/manager/insertMusics", method = RequestMethod.POST)
	public JSONObject insertMusics(HttpServletRequest request, MusicInfo music) throws Exception {

		JSONObject obj = musicService.insertMusics(request, music);

		return obj;

	}

	@RequestMapping(value = "/findAllMusic", method = RequestMethod.POST)
	public JSONArray findAllMusic(MusicInfo music) throws Exception {

		JSONArray arry = musicService.findAllMusic(music);

		return arry;
	}

	@RequestMapping(value = "/manager/findAllMusicManeger", method = RequestMethod.POST)
	public JSONArray findAllMusicManeger(HttpServletRequest request, MusicInfo info) throws Exception {
		info.setCreateName(GetUserInfo.getUserName(request));
		JSONArray arry = musicService.findAllMusicManeger(info);

		return arry;
	}

	@RequestMapping(value = "/manager/findAllMusicByName", method = RequestMethod.POST)
	public JSONArray findAllMusicByName(MusicInfo music) throws Exception {

		JSONArray arry = musicService.findOneMusicByName(music);

		return arry;
	}
	// music结束

	// vedio begin
	@RequestMapping(value = "/manager/updateMoive", method = RequestMethod.POST)
	public JSONObject updateMoive(HttpServletRequest request, VedioInfo vedio) throws Exception {

		JSONObject obj = moiveService.updateMoive(request, vedio);

		return obj;
	}

	@RequestMapping(value = "/manager/insertMoive", method = RequestMethod.POST)
	public JSONObject insertMoive(HttpServletRequest request, VedioInfo vedio) throws Exception {

		JSONObject obj = moiveService.insertMoive(request, vedio);

		return obj;
	}

	@RequestMapping(value = "/findAllMoive", method = RequestMethod.POST)
	public JSONArray findAllMoive(VedioInfo vedio) throws Exception {

		JSONArray arry = moiveService.findAllMoive(vedio);

		return arry;
	}

	@RequestMapping(value = "/manager/findAllVedioManeger", method = RequestMethod.POST)
	public JSONArray findAllVedioManeger(HttpServletRequest request, VedioInfo vedio) throws Exception {
		vedio.setCreateName(GetUserInfo.getUserName(request));
		JSONArray arry = moiveService.findAllVedioManeger(vedio);

		return arry;
	}

	@RequestMapping(value = "/manager/findAllMoiveByName", method = RequestMethod.POST)
	public JSONArray findAllMoiveByName(VedioInfo vedio) throws Exception {

		JSONArray arry = moiveService.findOneMoiveByName(vedio);

		return arry;
	}

	// vedio end

	@RequestMapping(value = "/manager/updateApp", method = RequestMethod.POST)
	public JSONObject updateApp(HttpServletRequest request, AppInfo app) throws Exception {

		JSONObject obj = appService.updateApp(request, app);

		return obj;
	}

	@RequestMapping(value = "/manager/findAllApp", method = RequestMethod.POST)
	public JSONArray findAllApp() throws Exception {

		JSONArray arry = appService.findAllApp();

		return arry;
	}

	@RequestMapping(value = "/manager/findAllAppByName", method = RequestMethod.POST)
	public JSONArray findAllAppByName(AppInfo app) throws Exception {

		JSONArray arry = appService.findOneAppByName(app);

		return arry;
	}

}
