package com.shop.xql.service;

import com.shop.xql.pojo.Type;

import java.util.List;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/8
 * @Description: com.shop.xql.service 商品类型业务类接口
 * @version: 1.0
 */
public interface TypeService {
    /**
    * 功能描述: * @param: 查询所有商品类型
    * @return: 
    * @auther: 
    * @date:  
    */
    public List<Type> queryAllType();
    /**
    * 功能描述: * @param: 插入一条商品
    * @return: 
    * @auther: 
    * @date:  
    */
    String insertType(Type type);
    /**
    * 功能描述: * @param: 根据id查找修改的商品类型
    * @return: 
    * @auther: 
    * @date:  
    */
    Type findTypeById(Type type);
    /**
    * 功能描述: * @param: 修改商品类型信息
    * @return: 
    * @auther: 
    * @date:  
    */
    String updateType(Type type);
    /**
    * 功能描述: * @param: 删除商品
    * @return: 
    * @auther: 
    * @date:  
    */
    void deleteTypeById(Type type);
    /**
    * 功能描述: * @param: 启用商品类型
    * @return: 
    * @auther: 
    * @date:  
    */
    void useTypeById(Type type);
    /**
    * 功能描述: * @param: 禁止商品类型
    * @return: 
    * @auther: 
    * @date:  
    */
    void banTypeById(Type type);
}
