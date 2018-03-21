package com.army.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.army.service.news.impl.NewsServiceImpl;
import com.army.vo.ReptileNewsInfo;

@SuppressWarnings("rawtypes")
public class FindNews {
	
	private final static Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
	private static final String PATNER = "yyyy-MM/dd";
	
	public List<ReptileNewsInfo> getNews() {
		SimpleDateFormat s = new SimpleDateFormat(PATNER);
		String date = s.format(new Date());
		
		FindNews newCl = new FindNews();
		LinkedList linkurls = new LinkedList();// 用于存放url链接
		long startTime = System.currentTimeMillis();
		ReptileNewsInfo object = null;
		List<ReptileNewsInfo> arr = new ArrayList<ReptileNewsInfo>();
		try {
			String url = "http://www.xinhuanet.com/mil/index.htm";
			Document doc = Jsoup.connect(url).timeout(50000).userAgent(
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
					.get();
			linkurls = newCl.getlinkurl(doc);// 获取网页链接
			log.info("["+new Date().toLocaleString()+"]开始拉取数据......");
			for (Object link : linkurls) {
				Thread.sleep(100);
				object = new ReptileNewsInfo();
				url = link.toString();
				if(url.indexOf(date) == -1) {
					continue;
				}
				doc = Jsoup.connect(url).timeout(50000).userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
						.get();
				if(newCl.getnewTitle(doc) == null || newCl.getnewTitle(doc) == "") {
					continue;
				}
				 object.setReptileTime(newCl.getTime(doc));// 获取新闻发布时间
				 object.setReptileTitle(newCl.getnewTitle(doc));// 获取新闻标题
				 object.setReptileContent(newCl.getNewtext(doc));// 获取新闻内容
				 object.setPretileImgSrc(newCl.getImgurl(doc));
				arr.add(object);
			}
			long endTime = System.currentTimeMillis();
			log.info("数据拉取完成,耗时:"+(endTime-startTime)/1000+"秒");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据拉取失败:"+e.getStackTrace());
		}

		return arr;
	}
//	public static void main(String[] args) {
//		FindNews f = new FindNews();
//		f.getNews();
//	}

	public String getTime(Document doc) {
		// 获取新闻发布的时间
		String time = null;
		Elements element = doc.select("span.time");// 此处是根据百度新闻的网页形式解析的新闻时间
		String rex = "^(((20\\d{2})-(\\d{2})-(\\d{2}))) ((\\d{2}):(\\d{2}):(\\d{2}))$";// 正则表达式用于匹配时间
		Pattern pattern = Pattern.compile(rex);
		for (Element el : element) {
			String content = el.text();
			Matcher matcher = pattern.matcher(content);
			if (!matcher.matches()) {
				time = content;
			}
		}
		
		return time.replace("年", "-").replace("月", "-").replace("日", " ");
	}
	
	public String getnewTitle(Document doc) {
		// 获取网页的标题
		String title = doc.title();
		return title;
	}

	@SuppressWarnings("unchecked")
	public LinkedList getlinkurl(Document doc) throws IOException {
		LinkedList linkurls = new LinkedList();// 用于存放url链接
		Elements href = doc.select("a[href]");
		for (Element hre : href) {
			String linkUrl = hre.attr("abs:href");// 获取网页的绝对地址
			if (!linkurls.contains(linkUrl)) {
				linkurls.add(linkUrl);
			}
		}
		return linkurls;
	}

	public String getImgurl(Document doc) throws IOException {
		StringBuffer sb = new StringBuffer();
		int count = 0;
		Elements pngs = doc.select("img[src]");
		for (Element img : pngs) {
			count++;
			String imgUrl = img.attr("abs:src");// 获取图片的绝对路径
			if(imgUrl.indexOf(".jpg")!=-1) {
				sb.append(imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length()) + ",");
			}
			downloadImg(imgUrl, count);
		}
		return sb.toString().substring(0, sb.lastIndexOf(","));
	}

	public void downloadImg(String ImgUrl, int count) throws IOException {
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

	public String getNewtext(Document doc) {
		// 获取新闻内容
		String text = doc.select("div.article").toString();
		text = text.replace(Jsoup.parse("   ").text(), "");
		return text;
	}


	// public static void downloadPage(String str) {
	// // 下载网页
	// String filestr = "F:/test";// 设置网页的保存地址
	// try {
	// URL pageUrl = new URL(str);
	// URLConnection uc = pageUrl.openConnection(); // 打开连接
	// String path = pageUrl.getPath();// 获取网页的相对路径
	// // System.out.println(path);
	// if (path.length() == 0) {// 对网页的相对路径进行处理
	// path = "index.html";
	// } else {
	// if (path.endsWith("/")) {
	// path = path + "index.html";
	// } else {
	// int lastSlash = path.lastIndexOf("/");
	// int lastPoint = path.lastIndexOf(".");
	// if (lastPoint < lastSlash) {
	// path = path + ".html";
	// }
	// }
	// }
	// filestr = filestr + path;// 网页保存路径
	// System.out.println("网页保存路径：" + filestr);
	// // 设置网页保存目录，当保存网页的文件夹不存在时，需要先创建文件夹，然后在保存网页
	// int lastSlash2 = filestr.lastIndexOf("/");
	// String filestrr = filestr.substring(0, lastSlash2);
	// // System.out.println(filestrr);
	// File file2 = new File(filestrr); // 创建文件目录
	// file2.mkdirs();
	// File file = new File(filestr); // 创建文件
	// InputStream is = uc.getInputStream(); // 输入流
	// FileOutputStream out = new FileOutputStream(file); // 输出的文件流
	// byte[] bs = new byte[1024];
	// // 读取到的数据长度
	// int len;
	// // 开始读取
	// while ((len = is.read(bs)) != -1) {
	//
	// out.write(bs, 0, len);
	// }
	// // 完毕，关闭所有链接
	// out.close();
	// is.close();
	// } catch (Exception ex) {
	// }
	// }
}