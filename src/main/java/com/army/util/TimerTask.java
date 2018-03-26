package com.army.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused") // 抑制没被使用过的代码的警告
@Component
public class TimerTask implements CommandLineRunner {

	private final static Logger log = LoggerFactory.getLogger(TimerTask.class);

	private static final int M5 = 90 * 1000;

	@Override
	public void run(String... arg0) throws Exception {
		log.info("start.............");
		// start();
	}

}
