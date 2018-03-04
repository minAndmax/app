package com.army.service.Moive.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.MoiveMapper;
import com.army.dao.OperateMapper;
import com.army.service.Moive.MoiveService;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.util.ValidEnum;
import com.army.vo.OperateInfo;
import com.army.vo.VedioInfo;

@Service("/MoiveService")
public class MoiveServiceImpl implements MoiveService{

	private final static Logger log = LoggerFactory.getLogger(MoiveServiceImpl.class);
	
	@Autowired
	private OperateMapper operateMapper;
	
	@Autowired
	private MoiveMapper moiveMapper;
	
	@Override
	public JSONObject updateMoive(HttpServletRequest request, VedioInfo vinfo) throws Exception {
		JSONObject obj = new JSONObject();
		try {
			moiveMapper.updateMoive(vinfo);
			
			obj.put(KeyWord.TIPSTATUS, ValidEnum.VALID.getValidStatus());
			obj.put(KeyWord.TIPSTATUSCONTEN, ValidEnum.VALID.getValidStatusName());
			
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			OperateInfo opt = new OperateInfo();

			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("修改影视");
			opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "修改影视，修改:"
					+ vinfo.getVedioName() == null
							? ""
							: "影视名称 " + vinfo.getVedioName() == null ? ""
									:"状态");
			opt.setTypeId(vinfo.getVedioId());

			operateMapper.inserObject(opt);

			log.info("影视修改成功[ {} ]" + obj);
		} catch (Exception e) {
	            obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
				obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
				log.error("程序异常，影视修改失败[ {} ]" + e.getMessage());
		}
		return obj;
	}

	@Override
	public JSONArray findAllMoive() throws Exception {
		JSONArray arry = new JSONArray();
		
		List<VedioInfo> vinfo = moiveMapper.findAllMoive();
		for(VedioInfo v : vinfo) {
			arry.add(v);
		}
		return arry;
	}

	@Override
	public JSONArray findOneMoiveByName(VedioInfo vedio) throws Exception {
		JSONArray arry = new JSONArray();
		
		List<VedioInfo> vinfo = moiveMapper.findOneMoiveByName(vedio);
		for(VedioInfo v : vinfo){
			arry.add(v);
		}
		return arry;
	}

}
