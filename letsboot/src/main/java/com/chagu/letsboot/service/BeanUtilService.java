package com.chagu.letsboot.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author Pratap Bhanu
 *
 */
@Service
public class BeanUtilService implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}

}
