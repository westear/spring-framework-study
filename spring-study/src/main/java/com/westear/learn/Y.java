package com.westear.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能描述
 *
 * @projectName: spring
 * @package: com.westear.learn
 * @className: Y
 * @author: Qinyunchan
 * @date: 2020/1/14  7:29 下午
 * @version: 1.0
 */
@Component
public class Y {

	@Autowired
	private X x;

	private String prop;

	public String getProp() {
		System.out.println("X.prop = " + prop);
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public void printY(){
		System.out.println("	print Y");
	}
}
