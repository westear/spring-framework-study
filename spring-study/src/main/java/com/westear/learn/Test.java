package com.westear.learn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 功能描述
 *
 * @projectName: spring
 * @package: com.westear.learn
 * @className: Test
 * @author: Qinyunchan
 * @date: 2020/1/14  7:26 下午
 * @version: 1.0
 */
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	}
}
