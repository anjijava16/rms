package com.iwinner.rms.spring.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iwinner.rms.constants.RMSConstants;

public class ApplicationContextUtil {
	public static ApplicationContext getContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext(RMSConstants.SPRING_CONFIG);
		return context;
	}

	public static void main(String[] args) {
		ApplicationContext context = getContext();
	}
}
