package com.westear.anno;

import com.westear.configuration.DemoImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DemoImportSelector.class)
public @interface ImportSelectorAnnotation {

	String name = "";
}
