package com.westear.beanInstance;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConstructAutowireTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		//setter 方法自动注入
		ConstructDemo constructDemo = (ConstructDemo) context.getBean("constructDemo");
		constructDemo.printClassName();

		//构造器注入
		ConstructDemo constructDemo1 = (ConstructDemo) context.getBean("constructDemo1","String111", 100);
		constructDemo1.printValue();

		//构造器注入
		ConstructDemo constructDemo2 = (ConstructDemo) context.getBean("constructDemo2");
		constructDemo2.printClassName();
	}
}
