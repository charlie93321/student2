package com.spring.beanLoad;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/5  11:13]
 * @DESC:
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {


        return new String[]{"com.spring.basic.domain.Person","com.spring.basic.MainConfig"};
    }
}
