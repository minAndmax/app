package com.army.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

@Component
public class UpLoadImageUtil {

	private static final Logger log = LoggerFactory.getLogger(UpLoadImageUtil.class);

	// 图片文件保存路径
	private static final String UPLOAD_IMAGE_PATH = "/upload/image/";

	// 视频文件保存路径
	private static final String UPLOAD_VEDIO_PATH = "/upload/vedio/";

	// 软件文件保存路径
	private static final String UPLOAD_APP_PATH = "/upload/app/";

	// 音乐文件保存路径
	private static final String UPLOAD_MUSIC_PATH = "/upload/music/";
	
	private static int num = 1;

	public static JSONObject uploadFile(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,png");
		extMap.put("flash", "mp4");
		extMap.put("media", "mp3");

		MultipartHttpServletRequest requestMult = (MultipartHttpServletRequest) request;

		Map<String, List<MultipartFile>> mults = requestMult.getMultiFileMap();

		List<MultipartFile> requestFileName = mults.get("imgFile");

		String fileName = requestFileName.get(0).getOriginalFilename();   //获取传进来的文件名
		String errorTip = checkFile(fileName);

		JSONObject obj = new JSONObject();

		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());		//获取当前文件的扩展名

		String dirName = request.getParameter("dir");
		
		String newName = getFileName(suffix);	//自动生成的文件名
		
		if (Arrays.<String>asList(extMap.get(dirName).split(",")).contains(suffix)) {

			try {
				if (dirName.equals("media")) {
					SaveFileFromInputStream(requestFileName.get(0).getInputStream(), UPLOAD_MUSIC_PATH, newName);
					obj.put(KeyWord.TIPSTATUSCONTEN, "上传成功");
					obj.put("error", 0);
					obj.put("url", UPLOAD_MUSIC_PATH+newName);
					log.info("media:上传{}"+obj);
			} else if (dirName.equals("flash")) {
					SaveFileFromInputStream(requestFileName.get(0).getInputStream(), UPLOAD_VEDIO_PATH, newName);
					obj.put(KeyWord.TIPSTATUSCONTEN, "上传成功");
					obj.put("url", UPLOAD_VEDIO_PATH+newName);
					obj.put("error", 0);
					log.info("vedio:上传{}"+obj);
			} else if (dirName.equals("image")) {
					SaveFileFromInputStream(requestFileName.get(0).getInputStream(), UPLOAD_IMAGE_PATH, newName);
					obj.put(KeyWord.TIPSTATUSCONTEN, "上传成功");
					obj.put("url", UPLOAD_IMAGE_PATH+newName);
					obj.put("error", 0);
					log.info("image:上传{}"+obj);
			}
			} catch (Exception e) {
				obj.put("error", 0);
				obj.put("message", errorTip);
				log.info("上传失败");
			}

		} else {
			obj.put("error", 1);
			obj.put("message", "不支持的扩展名");
			log.info("不支持的扩展名");
		}
		num++;
		return obj;
	}

	@Scheduled(cron = "0 0 00  * * ?") // 每天00点执行执行一次
    public void getToken() {
    	try {
    		num = 1;
			log.info("清空当前num："+new Date()+",num="+num);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private static void SaveFileFromInputStream(InputStream stream, String path, String filename) {
		
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream("D:"+File.separator+path + "/" + filename);
			byte[] buffer = null;
			
			if(path.equals(UPLOAD_MUSIC_PATH)) {
				buffer = new byte[1024 * 1024 * 100];
			} else if(path.equals(UPLOAD_VEDIO_PATH)){
				buffer = new byte[1024 * 1024*1000];
			} else {
				buffer = new byte[1024 * 1024*10];
			}
			
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fs != null) {
					fs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public static String checkFile(String fileName) {

		File uploadApp = new File("D:"+File.separator+UPLOAD_APP_PATH);

		File uploadMusic = new File("D:"+File.separator+UPLOAD_MUSIC_PATH);

		File uploadVedio = new File("D:"+File.separator+UPLOAD_VEDIO_PATH);

		File uploadImage = new File("D:"+File.separator+UPLOAD_IMAGE_PATH);

		String reStr = "";

		if (!uploadApp.canWrite() || !uploadMusic.canWrite() || !uploadVedio.canWrite() || !uploadImage.canWrite()) {
			reStr = "上传目录没有写入权限";
		}

		if(new File("D:"+File.separator+UPLOAD_MUSIC_PATH+fileName).exists()) {
			new File("D:"+File.separator+UPLOAD_MUSIC_PATH+fileName).delete();
		}
		if(new File("D:"+File.separator+UPLOAD_VEDIO_PATH+fileName).exists()) {
			new File("D:"+File.separator+UPLOAD_VEDIO_PATH+fileName).delete();
		}

		if (!uploadApp.exists()) {
			uploadApp.mkdirs();
		}

		if (!uploadMusic.exists()) {
			uploadMusic.mkdirs();
		}

		if (!uploadVedio.exists()) {
			uploadVedio.mkdirs();
		}

		if (!uploadImage.exists()) {
			uploadImage.mkdirs();
		}

		return reStr;

	}

	public static String getFileName(String suffix) {

		String name = "";
		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		name = sm.format(new Date()) + "_" + num + "." + suffix;

		return name;
	}
}