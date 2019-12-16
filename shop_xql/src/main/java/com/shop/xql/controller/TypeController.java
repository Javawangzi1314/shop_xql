package com.shop.xql.controller;

import com.shop.xql.pojo.Type;
import com.shop.xql.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/8
 * @Description: com.shop.xql.controller 商品类型模块控制层
 * @version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("Type")//设置访问路径前缀
public class TypeController {
    @Autowired
    private TypeService typeService;//自动注入类型业务层接口
    /**
    * 功能描述: * @param: 查询所有商品类型
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("queryAllType")
    public ModelAndView queryAllType(){
        ModelAndView mav = new ModelAndView();
        List<Type> typeList = typeService.queryAllType();
        mav.addObject("typeList",typeList);
        mav.setViewName("forward:/backend/productTypeManager.jsp");
        return mav;
    }
    /**
    * 功能描述: * @param: 添加类型
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("insertType")
    @ResponseBody
    public String insertType(Type type){
        String msg = typeService.insertType(type);
        return msg;
    }
    /**
    * 功能描述: * @param: 根据id查询类型
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("findTypeById")
    @ResponseBody
    public Type findTypeById(Type type, HttpServletRequest request){
        Type type1 = typeService.findTypeById(type);
        return type1;
    }
    /**
    * 功能描述: * @param: 修改类型
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("updateType")
    @ResponseBody
    public String updateType(Type type){
        log.info("修改商品类型{}",type);
        String msg = typeService.updateType(type);
        return msg;
    }
    /**
    * 功能描述: * @param: 删除类型
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("deleteTypeById")
    @ResponseBody
    public String deleteTypeById(Type type){
        log.info("删除商品类型{}",type);
        typeService.deleteTypeById(type);
        return "";
    }
    /**
    * 功能描述: * @param: 启动商品类型
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("useTypeById")
    @ResponseBody
    public String useTypeById(Type type){
        typeService.useTypeById(type);
        return "";
    }
    /**
    * 功能描述: * @param: 禁止商品类型
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("banTypeById")
    @ResponseBody
    public String banTypeById(Type type){
        typeService.banTypeById(type);
        return "";
    }

}
