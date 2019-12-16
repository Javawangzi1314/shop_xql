package com.shop.xql.conf;

import com.shop.xql.filter.AdminIntedeptor;
import com.shop.xql.filter.LoginIntedeptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/1
 * @Description: 把过滤器交给工厂管理
 * @version: 1.0
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

      registry.addInterceptor(new LoginIntedeptor()).addPathPatterns("/Order/QueryMyOrder").addPathPatterns("/User/myCenter");
      registry.addInterceptor(new AdminIntedeptor()).addPathPatterns("/Type/queryAllType")
      .addPathPatterns("/Shop/queryAllShop").addPathPatterns("/User/queryAllUser")
      .addPathPatterns("/Summary/QueryAllSummary").addPathPatterns("/backend/user-chain.jsp");

    }

}
