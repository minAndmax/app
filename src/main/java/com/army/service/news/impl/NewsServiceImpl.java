package com.army.service.news.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.NewsMapper;
import com.army.dao.OperateMapper;
import com.army.service.news.NewsService;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.util.ValidEnum;
import com.army.vo.NewsInfo;
import com.army.vo.OperateInfo;

@Service("/newsService")
public class NewsServiceImpl implements NewsService {

	private final static Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private OperateMapper operateMapper;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject insertNew(HttpServletRequest request, NewsInfo news) throws Exception {

		JSONObject obj = new JSONObject();

		try {
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
			
			news.setValid(ValidEnum.VALID.getValidStatus());
            news.setNewRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + ",添加新闻，标题《"
					+ news.getNewName() + "》作者:" + news.getNewAuthor());
            news.setCreateName(sessionObj.getString("userName"));
			newsMapper.insertNews(news);
            
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());


			OperateInfo opt = new OperateInfo();

			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("添加新闻");
			opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + ",添加新闻，标题《"
					+ news.getNewName() + "》作者:" + news.getNewAuthor());
			opt.setTypeId(news.getNewId());

			operateMapper.inserObject(opt);

			log.info("新闻添加成功[ {} ]" + obj);

		} catch (Exception e) {
            e.printStackTrace();
            obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			log.error("程序异常，新闻添加失败[ {} ]" + e.getMessage());
		}

		return obj;

	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject updateNew(HttpServletRequest request, NewsInfo news) throws Exception {

		JSONObject obj = new JSONObject();

		try {
			NewsInfo find = newsMapper.findNewById(news);
			newsMapper.updateNews(news);

			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			OperateInfo opt = new OperateInfo();

			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("修改新闻");
			StringBuilder sb = new StringBuilder();
			
			sb.append(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName"));
			sb.append("修改新闻《"+find.getNewName()+"》");
			if(news.getValid() == null) {
				sb.append(news.getNewName() == null ? "": ",标题:"+news.getNewName());
			}
			sb.append(news.getNewAuthor() == null ? "": ",作者:"+news.getNewAuthor());
			sb.append(news.getNewContent() == null ? "": ",内容 ");
			if(news.getValid() != null) {
				sb.append(news.getValid().equals("Y") ? ",状态为:有效" : ",状态为:无效"+"");
			}
			opt.setOptRemark(sb.toString());
			
			opt.setTypeId(news.getNewId());

			operateMapper.inserObject(opt);
			
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());
			
			log.info("新闻修改成功[ {} ]" + obj);

		} catch (Exception e) {

			obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			log.info("程序异常，新闻修改失败[ {} ]" + e.getMessage());
		}

		return obj;

	}

	@Override
	public JSONArray findAllNews() throws Exception {

		
		JSONArray arr = new JSONArray();
		List<NewsInfo> nws = newsMapper.findAllNews();

		for (NewsInfo info : nws) {
			arr.add(info);
		}
//		log.info("线上所有新闻，[ {} ]" + arr);
		return arr;
	}

	@Override
	public JSONArray findAllNewManager(NewsInfo news) throws Exception {
		
		int pages = newsMapper.findCount(news);
		
		double db = Math.ceil((double) pages / (double) news.getSize());
		
		JSONArray arr = new JSONArray();
		List<NewsInfo> nws = newsMapper.findAllNewManager(news);
		if(nws.size() != 0) {
			nws.get(0).setTotalPages((int) db);
			nws.get(0).setPage(news.getPage());
		} else {
			NewsInfo ws = new NewsInfo(); 
			ws.setTotalPages(-1);
			ws.setSize(0);
			nws.add(ws);
		}
		
		for (NewsInfo info : nws) {
			arr.add(info);
		}
//		log.info("后台管理员查看所有新闻，[ {} ]" + arr);
		return arr;
	}

	@Override
	public JSONObject findNewById(NewsInfo news) throws Exception {
		
		NewsInfo nw = newsMapper.findNewById(news);
		
		JSONObject obj = new JSONObject();
		obj.put("jsonobejct", nw);
		
		return obj;
	}

}
