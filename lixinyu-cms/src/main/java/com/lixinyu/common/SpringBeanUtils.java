package com.lixinyu.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtils implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		if(SpringBeanUtils.applicationContext == null) {
			SpringBeanUtils.applicationContext = applicationContext;
		}
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static <T> T getBean(String name,Class<T> clazz) {
		return applicationContext.getBean(name, clazz);
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
}
