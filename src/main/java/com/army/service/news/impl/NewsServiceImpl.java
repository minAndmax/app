package com.army.service.news.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

	@Override
	public JSONObject insertNew(HttpServletRequest request, NewsInfo news) throws Exception {

		JSONObject obj = new JSONObject();

		try {
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
			
			news.setValid(ValidEnum.VALID.getValidStatus());
            news.setNewRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "添加新闻，标题《"
					+ news.getNewName() + "》作者:" + news.getNewAuthor());
            news.setCreateName(sessionObj.getString("userName"));
			newsMapper.insertNews(news);
            
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());


			OperateInfo opt = new OperateInfo();

			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("添加新闻");
			opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "添加新闻，标题《"
					+ news.getNewName() + "》作者:" + news.getNewAuthor());
			opt.setTypeId(news.getNewId());

			operateMapper.inserObject(opt);

			log.info("新闻添加成功[ {} ]" + obj);

		} catch (Exception e) {
            e.printStackTrace();
            obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("程序异常，新闻添加失败[ {} ]" + e.getMessage());
		}

		return obj;

	}
	
	@Override
	public JSONObject updateNew(HttpServletRequest request, NewsInfo news) throws Exception {

		JSONObject obj = new JSONObject();

		try {

			newsMapper.updateNews(news);

			obj.put(KeyWord.TIPSTATUS, ValidEnum.VALID.getValidStatus());
			obj.put(KeyWord.TIPSTATUSCONTEN, ValidEnum.VALID.getValidStatusName());

			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			OperateInfo opt = new OperateInfo();

			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("修改新闻");
			opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "修改新闻:"
					+ news.getNewName() == null
							? ""
							: "新闻标题 " + news.getNewAuthor() == null ? ""
									: "新闻作者 " + news.getNewContent() == null ? ""
											: "新闻内容 " + news.getNewImags() == null ? ""
													: "新闻图片 " + news.getValid() == null ? "" : "状态");
			opt.setTypeId(news.getNewId());

			operateMapper.inserObject(opt);

			log.info("新闻修改成功[ {} ]" + obj);

		} catch (Exception e) {

			obj.put(KeyWord.TIPSTATUS, ValidEnum.NOT_VALID.getValidStatus());
			obj.put(KeyWord.TIPSTATUSCONTEN, ValidEnum.NOT_VALID.getValidStatusName());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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

		return arr;
	}

}
