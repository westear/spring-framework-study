package com.westear.learn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 功能描述
 *
 * @projectName: spring
 * @package: com.westear.learn
 * @className: AppConfig
 * @author: Qinyunchan
 * @date: 2020/1/14  7:23 下午
 * @version: 1.0
 */
@Configuration
@ComponentScan("com.westear.learn")
public class AppConfig {

	/**
	 * 读取国际化配置
	 * When an ApplicationContext is loaded, it automatically searches for a MessageSource bean defined in the context
	 *
	 * resourceBundleMessageSource 读取配置
	 * ReloadableResourceBundleMessageSource allows for reading files from any Spring resource location
	 * 		(not only from the classpath) and supports hot reloading of bundle property files
	 * 		(while efficiently caching them in between)
	 *
	 * 如果 返回的 bean id != messageSource, spring 无法注入 MessageSource , 无法获取配置文件
	 * @see //https://docs.spring.io/spring/docs/5.1.14.BUILD-SNAPSHOT/spring-framework-reference/core.html#context-functionality-messagesource
	 * @return MessageSource
	 */
	@Bean("messageSource")
	public ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setDefaultEncoding("UTF-8");
		resourceBundleMessageSource.setBasenames("i18n/messages","i18n/exceptions");
		return resourceBundleMessageSource;
	}
}
