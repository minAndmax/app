package com.army.util;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.army.dao.ReptileMapper;
import com.army.service.news.impl.NewsServiceImpl;
import com.army.vo.ReptileNewsInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
/**
 * 获取急速新闻
 * @author Administrator
 *
 */
@Component
public class Get extends Thread{
	private final static Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
    public static final String APPKEY = "d603840915de0140";// 你的appkey
    public static final String URL = "http://api.jisuapi.com/news/get";
    public static final String channel = "军事";// utf8  新闻频道(头条,财经,体育,娱乐,军事,教育,科技,NBA,股票,星座,女性,健康,育儿)
    public static final int num = 20;// 数量 默认10，最大40
    public static int n = 0;
    
    @Autowired
	private ReptileMapper reptileMapper;
    
//    @Scheduled(cron = "0 0 8,18 * * ?") // 每天8点18点执行执行一次
    public void getToken() {
    	try {
			newGet();
			log.info("执行极速数据拉取，当前时间"+new Date().toLocaleString()+",n的值n="+n);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
//    public static void main(String[] args) {
//		Get g = new Get();
//		System.out.println(g);
//		try {
//			g.newGet();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
    
//    @Scheduled(cron = "0 0 00 * * ?") 
    public void clearn() {
    	try {
			n=0;
			log.info("凌晨十二点，重置n:"+n+",当前时间:"+new Date().toLocaleString());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public  void newGet() throws Exception {
    	String curtTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String result = null;
        String url = URL + "?channel=" + URLEncoder.encode(channel, "utf-8") + "&num=" + num + "&appkey=" + APPKEY +"&start=" + n;
 
        try {
            result = HttpUtil.sendGet(url, "utf-8");
            JSONObject json = JSONObject.fromObject(result);
            if (json.getInt("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
                JSONObject resultarr = (JSONObject) json.opt("result");
                String channel = resultarr.getString("channel");
                String num = resultarr.getString("num");
                System.out.println(channel + " " + num);
                JSONArray list = resultarr.optJSONArray("list");
                ReptileNewsInfo news = null;
                for (int i = 0; i < list.size(); i++) {
                	news = new ReptileNewsInfo();
                    JSONObject obj = (JSONObject) list.opt(i);
                    if(obj.getString("time").indexOf(curtTime) == -1) {
                    	continue;
                    }
//                    String title = obj.getString("title");
//                    String time = obj.getString("time");
//                    String src = obj.getString("src");
//                    String category = obj.getString("category");
//                    String pic = obj.getString("pic");
//                    String content = obj.getString("content");
                    news.setReptileTime(obj.getString("time"));
                    news.setReptileTitle(obj.getString("title"));
                    news.setPretileImgSrc(obj.getString("pic"));
                    news.setReptileContent(obj.getString("content"));
                    news.setReptileSource(obj.getString("src"));
                    news.setValid(ValidEnum.VALID.getValidStatus());
                    downloadImg(obj.getString("pic"));    //获取的图片写入到文件夹
//                    System.out.println(pic+"<----->"+title);
                    reptileMapper.insertReptileNews(news);
//                    String url1 = obj.getString("url");
//                    String weburl = obj.getString("weburl");
//                    System.out.println( time+ "---" + title);
                }
                
                log.info("添加极速新闻成功,共" + list.size() + "条");
                n += 21;
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("程序异常"+e.getStackTrace());
        }
    }
    
    public static void downloadImg(String ImgUrl) throws IOException {
		if (ImgUrl.indexOf(".jpg") != -1) {
//			System.out.println("ImgUrl：" + ImgUrl.substring(ImgUrl.lastIndexOf("/") + 1, ImgUrl.lastIndexOf(".")));
			// 下载图片
			String str = "D:" + File.separator + "/upload/image/";// 保存下载图片文件夹
			String ss = str + ImgUrl.substring(ImgUrl.lastIndexOf("/") + 1, ImgUrl.lastIndexOf(".")) + ".jpg";// 保存图片路径
			URL url = new URL(ImgUrl); // 构造URL
			URLConnection uc = url.openConnection(); // 打开连接
			uc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			uc.setReadTimeout(50000);
			InputStream is = uc.getInputStream(); // 输入流
			File file = new File(ss); // 创建文件
			if(!new File(str).exists()) {
				new File(str).mkdirs();
			}
			if(file.exists()) {
				file.delete();
			}
			FileOutputStream out = new FileOutputStream(file); // 输出的文件流
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				out.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			out.close();
			is.close();
		}
	}
    
}