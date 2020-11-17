package com.westear.learn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

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

		/*
			把spring所需要的前提环境准备好:
			1.准备工厂: DefaultListableBeanFactory; 该工厂类在 父类 GenericApplicationContext 中 new DefaultListableBeanFactory();
			2.实例化一个 reader 和 一个 scanner
		 */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		/*
			把一个 Class 转换成 BeanDefinition, 最后 put 到 beanDefinitionMap 中,
			beanDefinitionMap 位于 DefaultListableBeanFactory 中，
			所以可以说是把一个 Class 注册到了 beanFactory 中，
			只不过这个 Class 该项目的 Spring 配置类，当然也可以注册一个普通的 Class

			这个  register(Class<?>... componentClasses) 方法 没有调用 context.refresh(), 所以后续需要手动调用 refresh() 方法
		 */
		context.register(AppConfig.class);

		/*
			初始化spring的环境
		 */
		context.refresh();

		//调用implements Lifecycle, LifecycleProcessor方法
		X x = context.getBean(X.class);
		//bean 实现 SmartLifecycle 接口之后，可以自动调用start方法
//		x.start();
		System.out.println(x.isRunning());
		x.onClose();
		x.stop();
	}
}
