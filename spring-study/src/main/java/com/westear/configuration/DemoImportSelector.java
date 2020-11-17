package com.westear.configuration;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 通过实现 ImportSelector 注入 bean
 * bean class: SecondImport.java
 */
public class DemoImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		if(importingClassMetadata.hasAnnotation(Import.class.getName())) {
			System.out.println(Boolean.TRUE);
		}
		System.out.println(importingClassMetadata.getAnnotationTypes());
		//注入 SecondImport bean
		return new String[]{SecondImport.class.getName()};
	}
}
