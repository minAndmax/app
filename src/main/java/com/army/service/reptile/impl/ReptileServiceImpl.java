package com.army.service.reptile.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.OperateMapper;
import com.army.dao.ReptileMapper;
import com.army.service.news.impl.NewsServiceImpl;
import com.army.service.reptile.ReptileService;
import com.army.util.FindNews;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.util.ValidEnum;
import com.army.vo.OperateInfo;
import com.army.vo.ReptileNewsInfo;

@Service("reptileService")
public class ReptileServiceImpl implements ReptileService {

	private final static Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Autowired
	private ReptileMapper reptileMapper;

	@Autowired
	private OperateMapper operateMapper;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	@Scheduled(cron = "0 0 8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23 * * ?") // 每天8点18点执行执行一次
	public JSONObject insertReptileNews() {
		
		JSONObject obj = new JSONObject();
		int n = 0 ;
		try {
			FindNews fw = new FindNews();
			
			List<ReptileNewsInfo> arr = fw.getNews();
			Thread.sleep(1000);
			for(ReptileNewsInfo reptileNewsInfo : arr) {
				
				if(reptileNewsInfo.getReptileContent() == null) {
					continue;
				}
				ReptileNewsInfo re = reptileMapper.findByTitle(reptileNewsInfo);
				if(re != null) {
					continue;
				}
				reptileNewsInfo.setValid(ValidEnum.VALID.getValidStatus());
				
				String[] imgs = reptileNewsInfo.getPretileImgSrc().split(",");
				
				if(imgs != null) {
					for(int i = 0 ; i < imgs.length ; i++) {
						if(reptileNewsInfo.getReptileContent().indexOf(imgs[i]) != -1) {
							reptileNewsInfo.setReptileContent(reptileNewsInfo.getReptileContent().replace(imgs[i], "/upload/image/"+imgs[i]));
						}
					}
				}
				reptileNewsInfo.setReptileSource("新华网");
				reptileMapper.insertReptileNews(reptileNewsInfo);
	
				OperateInfo opt = new OperateInfo();
				opt.setOptType("insert");
				opt.setOptName("添加新闻");
				opt.setOptUserId((long)1);
				opt.setOptRemark("系统获取新闻,自动添加,标题《"
						+ reptileNewsInfo.getReptileTitle()+ "》新闻时间:" + reptileNewsInfo.getReptileTime());
	
				operateMapper.inserObject(opt);
				n++;
			}
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());
			obj.put("insertNum", n);
			if(n == 0) {
				log.info("暂无新数据[ {} ]" + obj);
			} else {
				log.info("系统获取新闻添加成功[ {} ]" + obj);
			}
		} catch (Exception e) {
            e.printStackTrace();
            obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			log.error("程序异常，系统获取新闻添加失败[ {} ]" + e.getMessage());
		}
		return obj;
		
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject updateReptileNews(HttpServletRequest request,ReptileNewsInfo news) {
		
		JSONObject obj = new JSONObject();
		ReptileNewsInfo in = reptileMapper.findById(news);
		try {
			reptileMapper.updateReptileNews(news);
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			OperateInfo opt = new OperateInfo();
			opt.setOptType("update");
			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("修改系统新闻");
			StringBuilder sb = new StringBuilder();
			sb.append(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName"));
			sb.append("，修改系统新闻《"+in.getReptileTitle()+"》");
			if(news.getValid() != null) {
				sb.append(news.getValid().equals("Y") ? ",状态为:有效" : ",状态为:无效"+"");
			}
			opt.setOptRemark(sb.toString());

			operateMapper.inserObject(opt);
			
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());
			
			log.info("系统新闻修改成功[ {} ]" + obj);

		} catch (Exception e) {

			obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			log.info("程序异常，系统新闻修改失败[ {} ]" + e.getMessage());
		}

		return obj;
	}

	@Override
	public JSONArray findAllReptileNews(ReptileNewsInfo news) throws Exception {

		int pages = reptileMapper.findCount(news);

		double db = Math.ceil((double) pages / (double) news.getSize());

		JSONArray arr = new JSONArray();
		List<ReptileNewsInfo> nws = reptileMapper.findAllReptileNews(news);

		if (nws.size() != 0) {
			nws.get(0).setTotalPages((int) db);
			nws.get(0).setPage(news.getPage());
		} else {
			ReptileNewsInfo ws = new ReptileNewsInfo();
			ws.setTotalPages(-1);
			ws.setSize(0);
			nws.add(ws);
		}

		for (ReptileNewsInfo info : nws) {
			arr.add(info);
		}
		// log.info("线上所有新闻，[ {} ]" + arr);
		return arr;
	}

	@Override
	public JSONArray findAllReptileNewsManager(ReptileNewsInfo news) throws Exception {
		if (news.getCreateName().equals("admin")) {
			news.setCreateName(null);
			news.setValid(null);
		}

		int pages = reptileMapper.findCount(news);

		double db = Math.ceil((double) pages / (double) news.getSize());

		JSONArray arr = new JSONArray();
		List<ReptileNewsInfo> nws = reptileMapper.findAllReptileNewsManager(news);
		if (nws.size() != 0) {
			nws.get(0).setTotalPages((int) db);
			nws.get(0).setPage(news.getPage());
		} else {
			ReptileNewsInfo ws = new ReptileNewsInfo();
			ws.setTotalPages(-1);
			ws.setSize(0);
			nws.add(ws);
		}

		for (ReptileNewsInfo info : nws) {
			arr.add(info);
		}
		// log.info("后台管理员查看所有新闻，[ {} ]" + arr);
		return arr;
	}

	@Override
	public JSONObject findById(ReptileNewsInfo news) {
		ReptileNewsInfo in = reptileMapper.findById(news);
		JSONObject obj = new JSONObject();
		obj.put("obj", in);
		return obj;
	}
	public JSONObject findpullNewsById(ReptileNewsInfo news) throws Exception {

		ReptileNewsInfo rnews = reptileMapper.findByTitle(news);

		JSONObject obj = new JSONObject();
		obj.put("jsonobejct", rnews);
		return obj;
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void insertJiSuNew(ReptileNewsInfo reptileNewsInfo) {
		try {
			
				
				ReptileNewsInfo re = reptileMapper.findByTitle(reptileNewsInfo);
				reptileNewsInfo.setValid(ValidEnum.VALID.getValidStatus());
				
				String[] imgs = reptileNewsInfo.getPretileImgSrc().split(",");
				
				if(imgs != null) {
					for(int i = 0 ; i < imgs.length ; i++) {
						if(reptileNewsInfo.getReptileContent().indexOf(imgs[i]) != -1) {
							reptileNewsInfo.setReptileContent(reptileNewsInfo.getReptileContent().replace(imgs[i], "/upload/image/"+imgs[i]));
						}
					}
				}
				reptileMapper.insertReptileNews(reptileNewsInfo);
	
				OperateInfo opt = new OperateInfo();
				opt.setOptType("insert");
				opt.setOptName("添加极速新闻");
				opt.setOptUserId((long)1);
				opt.setOptRemark("系统获取极速新闻,自动添加,标题《"
						+ reptileNewsInfo.getReptileTitle()+ "》新闻时间:" + reptileNewsInfo.getReptileTime());
	
				operateMapper.inserObject(opt);
				log.info("系统获取极速新闻添加成功");
		} catch (Exception e) {
            e.printStackTrace();
			log.error("程序异常，系统获取极速新闻添加失败[ {} ]" + e.getMessage());
		}
		
	}

}
