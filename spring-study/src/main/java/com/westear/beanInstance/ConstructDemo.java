package com.westear.beanInstance;

import org.springframework.beans.factory.annotation.Autowired;

public class ConstructDemo {

	private String str;
	private Integer number;
	private ConstructArg constructArg;
	private SecondArg secondArg;

	public ConstructDemo(){}

//	@Autowired
	public ConstructDemo(String str, Integer number) {
		this.str = str;
		this.number = number;
	}

//	public ConstructDemo(ConstructArg constructArg) {
//		this.constructArg = constructArg;
//	}

//	@Autowired
	public ConstructDemo(ConstructArg constructArg, SecondArg secondArg) {
		this.constructArg = constructArg;
		this.secondArg = secondArg;
	}

	@Autowired
	public void setConstructArg(ConstructArg constructArg) {
		this.constructArg = constructArg;
	}

	@Autowired
	public void setSecondArg(SecondArg secondArg) {
		this.secondArg = secondArg;
	}

	public void printValue() {
		System.out.println(ConstructDemo.class.getSimpleName());
		System.out.println(str);
		System.out.println(number);
	}

	public void printClassName() {
		System.out.println(ConstructDemo.class.getSimpleName());
		constructArg.print();
		secondArg.print();
	}

}
