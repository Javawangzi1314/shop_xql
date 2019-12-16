package com.shop.xql.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/4
 * @Description: com.cmfz.xql.configuration 自定义获取工厂对象类
 * @version: 1.0
 */
@Component
public class MyApplicationContent implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public static Object getBeanclass(Class clazz){
        return applicationContext.getBean(clazz);
    }
    public static Object getBeanname(String name){
        return applicationContext.getBean(name);
    }

}
