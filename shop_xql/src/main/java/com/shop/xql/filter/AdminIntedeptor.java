package com.shop.xql.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/1
 * @Description: com.llq.filter 后台自定义登录拦截器
 * @version: 1.0
 */
public class AdminIntedeptor implements HandlerInterceptor {
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Object obj = request.getSession(true).getAttribute("localAdmin");
        if(obj!=null){
           return true;
        }else{
            response.sendRedirect(request.getContextPath()+"/tiao.jsp");
            return false;
        }
    }
    /**
    * 功能描述: * @param: 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
/**
* 功能描述: * @param: 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
* @return:
* @auther:
* @date:
*/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
