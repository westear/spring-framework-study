package com.westear.learn;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Objects;

/**
 * 功能描述
 *
 * @projectName: spring
 * @package: com.westear.learn
 * @className: X
 * @author: Qinyunchan
 * @date: 2020/1/14  7:28 下午
 * @version: 1.0
 */
@Component
public class X implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ResourceLoaderAware,
		MessageSourceAware, ApplicationContextAware, InitializingBean, LoadTimeWeaverAware {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private Y y;

	public X(){
		System.out.println("create x");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("step 1: implements BeanNameAware, current bean name = " + name);
		name = "changeX";
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("step 2: implements BeanClassLoaderAware, setBeanClassLoader method");
		System.out.println("	" + classLoader.getClass().getName());;
		try {
			readResource(classLoader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("step 3: implements BeanFactoryAware, setBeanFactory method");
		Y y = (Y) beanFactory.getBean("y");
		y.setProp("		implements BeanFactoryAware, change prop");
		y.getProp();
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		System.out.println("step 4: implements ResourceLoaderAware, setResourceLoader method");
		System.out.println("	" + Objects.requireNonNull(resourceLoader.getClassLoader()).getClass().getName());;
		try {
			readResource(Objects.requireNonNull(resourceLoader.getClassLoader()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		System.out.println("step 5: implements MessageSourceAware, setMessageSource method");

		String chinese = messageSource.getMessage("zk001", new Object[0],"none", Locale.getDefault());
		System.out.println("	Message (Chinese) : " + chinese);

		String english = messageSource.getMessage("zk001", new Object[0], "none", Locale.US);
		System.out.println("	Message (English) : " + english);

		String defaultException = messageSource.getMessage("argument.required", new Object[]{"bean y"}, "none", null);
		System.out.println("	" + defaultException);

		String englishException = messageSource.getMessage("argument.required", new Object[]{"bean y"}, "none", Locale.US);
		System.out.println("	" + englishException);

		//When no message is found for the specified locale, the default message is used
		String noneKey = messageSource.getMessage("argument.isnull", null, "none", Locale.US);
		System.out.println("	" + noneKey);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("step 6: implements ApplicationContextAware, setApplicationContext method");
//		this.applicationContext = applicationContext;
		Y y = applicationContext.getBean(Y.class);
		y.printY();
	}

	@PostConstruct
	public void postConstructTest(){
		System.out.println("step 7: invoke @PostConstruct now");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("step 8: implements InitializingBean, afterPropertiesSet method");
		Y y = applicationContext.getBean(Y.class);
		y.setProp("		afterPropertiesSet method change prop");
		y.getProp();
	}

	@Override
	public void setLoadTimeWeaver(LoadTimeWeaver loadTimeWeaver) {
		System.out.println("implements LoadTimeWeaverAware, setLoadTimeWeaver method");
		System.out.println("定义的编织器，用于在加载时处理类定义。没有切面，不执行");
	}



	private void readResource(ClassLoader classLoader) throws IOException {
		Enumeration<URL> urls  = classLoader.getResources("resource.properties");
		while (urls.hasMoreElements()){
			URL url = urls.nextElement();
			InputStreamReader is = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(is);
			String line;
			while ((line = br.readLine()) != null){
				System.out.println("	read resource.properties : "+line);
			}
		}
	}
}
