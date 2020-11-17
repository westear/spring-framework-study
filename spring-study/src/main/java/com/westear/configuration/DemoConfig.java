package com.westear.configuration;

import com.westear.anno.ImportSelectorAnnotation;
import com.westear.learn.AppConfig;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.westear.learn"},
		excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {AppConfig.class})})
@ImportSelectorAnnotation
@Import(ThirdImport.class)
public class DemoConfig {

	/**
	 * 注入 DemoInnerBean实例 到 beanFactory，
	 * 第一次 new DemoInnerBean();
	 * @return DemoInnerBean实例
	 */
	@Bean
	public DemoInnerBean demoInnerBean() {
		return new DemoInnerBean();
	}

	/**
	 * 使用了 @Configuration 全注解的配置类 (full) ,会生成一个代理类， 在 @Bean 生成实例的依赖调用时，会使用代理方法
	 * @see ConfigurationClassPostProcessor#enhanceConfigurationClasses(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
	 * @see org.springframework.context.annotation class: ConfigurationClassEnhancer
	 * @see class: ConfigurationClassEnhancer method: BeanMethodInterceptor  #resolveBeanReference
	 * @return 注入 DemoOuterBean实例 到 beanFactory
	 */
	@Bean
	public DemoOuterBean demoOuterBean() {
		//第一次 new DemoInnerBean(); 获取的是 beanFactory 的 bean
		// DemoInnerBean 类中的 static 方法只执行了一次，
		// 说明这次的 new 并不是重新实例化，而是从 beanFactory 取得之前已经实例化的 bean
		new DemoInnerBean();
		return new DemoOuterBean();
	}
}
