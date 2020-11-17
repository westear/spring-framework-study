package com.westear.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class RefreshTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(DemoConfig.class);

		//可以加入自定义的 BeanFactoryPostProcessor
		//context.addBeanFactoryPostProcessor(CustomerBeanFactoryPostProcessor);

		context.refresh();

		System.out.println(Arrays.toString(context.getBeanFactory().getBeanDefinitionNames()));

		SecondImport secondImport = context.getBean(SecondImport.class);
		secondImport.print();
	}
}
