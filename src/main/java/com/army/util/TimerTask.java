package com.army.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.army.dao.NewsMapper;
import com.army.service.news.NewsService;

@Component
public class TimerTask implements CommandLineRunner{
	
	private final static Logger log = LoggerFactory.getLogger(TimerTask.class);
	
	private static final int M5 = 90 * 1000;
	
	@Autowired
	private NewsService newsService;
	
	public void start() {
		
//		CountThread ct = new CountThread();
//		UncaughtExceptionHandler eh = new UncaughtExceptionHandler() {
//			public void uncaughtException(Thread t, Throwable e) {
//				start();
//			}
//		};
//		ct.setUncaughtExceptionHandler(eh);
//		ct.start();
		
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		
	}

}
