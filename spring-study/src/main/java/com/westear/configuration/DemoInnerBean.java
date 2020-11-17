package com.westear.configuration;

public class DemoInnerBean {

	static {
		System.out.println("static: "+ DemoInnerBean.class.getName());
	}

	public void print() {
		System.out.println("print: "+ DemoInnerBean.class.getName());
	}
}
