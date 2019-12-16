package com.shop.xql;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/4
 * @Description: com.shop.xql  springboot项目启动类
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("com.shop.xql.mapper")
public class ShopApplication {
    /**
    * 功能描述: * @param: 程序启动入口
    * @return: 
    * @auther: 
    * @date:  
    */
    public static void main(String[] args) {

        SpringApplication.run(ShopApplication.class);
    }
}
