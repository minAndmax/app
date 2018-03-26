package com.army.service.tv.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.OperateMapper;
import com.army.dao.TVMapper;
import com.army.service.tv.TVService;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.util.ValidEnum;
import com.army.vo.OperateInfo;
import com.army.vo.PreFileInfo;
import com.army.vo.TVListInfo;

@Service("tVService")
public class TVServiceImpl implements TVService {

	private final static Logger log = LoggerFactory.getLogger(TVServiceImpl.class);
	@Autowired
	private TVMapper tVMapper;
	
	@Autowired
	private OperateMapper operateMapper;
	
	@Override
	public JSONArray findAllTVManeger(PreFileInfo file) throws Exception {
		
		if(file.getCreateName().equals("admin")){
			file.setCreateName(null);
			file.setValid(null);
		}
		
		JSONArray arr = new JSONArray();
		
		List<PreFileInfo> ns = tVMapper.findAllTVManeger(file);
		for (PreFileInfo os : ns) {
			arr.add(os);
		}
		log.info("电视:"+arr);
		return arr;
	}

	@Override
	public JSONArray findAllTv(PreFileInfo file) throws Exception {
		
		JSONArray arry = new JSONArray();
		
		List<PreFileInfo> minfo = tVMapper.findAllTv(file);
		for(PreFileInfo m : minfo) {
			arry.add(m);
		}
		return arry;
	}

	@Override
	public JSONObject insertPreFile(HttpServletRequest request,@RequestBody PreFileInfo file){
		
		TVListInfo tv = new TVListInfo();
		JSONObject obj = new JSONObject();
		String slip = file.getPreFileName();
		try {
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
			if(file.getPreFileId() == 0) {
				file.setValid(ValidEnum.VALID.getValidStatus());
				file.setPreFileName(file.getPreFileName().split(",")[0]);
				file.setCreateName(sessionObj.getString("userName"));
				tVMapper.insertPreFile(file);
				
				OperateInfo opt = new OperateInfo();
				opt.setOptType("insert");
				opt.setOptUserId(sessionObj.getLong("userId"));
				opt.setOptName("添加电视目录");
				opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + ",添加电视目录，名称《"
						+ file.getPreFileName().split(",")[0] + "》");
				opt.setTypeId(file.getPreFileId());

				operateMapper.inserObject(opt);
			}
			tv.setPreFileId(file.getPreFileId());
			tv.setCreateName(sessionObj.getString("userName"));
			tv.setValid(ValidEnum.VALID.getValidStatus());
			tv.setPreFileId(file.getPreFileId());
			tv.setTvLevel(Integer.parseInt(slip.split(",")[2]));
			tv.setTvSrc(slip.split(",")[1]);
			tv.setTvName(slip.split(",")[0]);
			tVMapper.insertTv(tv);
			
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());


			OperateInfo opt1 = new OperateInfo();
			opt1.setOptType("insert");
			opt1.setOptUserId(sessionObj.getLong("userId"));
			opt1.setOptName("添加电视");
			opt1.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + ",添加电视，名称《"
					+ slip.split(",")[0] + "》,第"+Integer.parseInt(slip.split(",")[2])+"集");
			opt1.setTypeId(file.getPreFileId());

			operateMapper.inserObject(opt1);

			log.info("电视添加成功[ {} ]" + obj);

		} catch (Exception e) {
            e.printStackTrace();
            obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			log.error("程序异常，电视目录添加失败[ {} ]" + e.getMessage());
		}
		
		return obj;
	}
	
	
	
	@Override
	public JSONObject deleteTv(TVListInfo tv) {
		
		JSONObject obj = new JSONObject();
		try {
			
			tVMapper.deleteTv(tv);
			
			if(new File("D:"+File.separator+tv.getTvSrc()).exists()) {
				new File("D:"+File.separator+tv.getTvSrc()).delete();
			}
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
		}
		
		return obj;
	}

	@Override
	public JSONObject updateTv(PreFileInfo file) {
		JSONObject obj = new JSONObject();
		try {
			tVMapper.updateTv(file);
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
		}
		return obj;
	}

}
