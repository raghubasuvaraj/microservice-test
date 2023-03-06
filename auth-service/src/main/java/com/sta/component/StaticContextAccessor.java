package com.sta.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * This will be responsible to bring the current login user's username or
 * whatever the identification is decided from the application.
 * 
 * @author r@ghu
 *
 */
@Component
public class StaticContextAccessor {

	private static StaticContextAccessor instance;

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void registerInstance() {
		instance = this;
	}

	public static <T> T getBean(Class<T> clazz) {
		return instance.applicationContext.getBean(clazz);
	}

}
