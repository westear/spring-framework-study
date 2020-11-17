package com.westear.configuration;

public class DemoOuterBean {

	private DemoInnerBean demoInnerBean;

	public DemoOuterBean() {
		//并不是重新实例化，而是从 beanFactory 取得之前已经实例化的 bean
		demoInnerBean = new DemoInnerBean();
	}

	static {
		System.out.println("static: "+ DemoOuterBean.class.getName());
	}

	public void print() {
		System.out.println("print: "+ DemoOuterBean.class.getName());
	}
}
