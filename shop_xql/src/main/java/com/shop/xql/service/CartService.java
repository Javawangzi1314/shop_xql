package com.shop.xql.service;



import com.shop.xql.pojo.Cart;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/11/27
 * @Description: com.llq.service 购物车业务层接口
 * @version: 1.0
 */
public interface CartService {
    /**
    * 功能描述: * @param: 添加购物车
    * @return: 
    * @auther: 
    * @date:  
    */
    Map<String, Cart> addCart(String shopId, Map<String, Cart> map);
    /**
    * 功能描述: * @param: 删除所有购物车
    * @return: 
    * @auther: 
    * @date:  
    */
    Map<String, Cart> allDeleteCart(List<String> arr1, Map<String, Cart> map);
    /**
    * 功能描述: * @param: 修改购物车
    * @return: 
    * @auther: 
    * @date:  
    */
    Map<String, Cart> updateCart(String bookId, Integer count, Map<String, Cart> map);
    /**
    * 功能描述: * @param: 恢复购物车
    * @return: 
    * @auther: 
    * @date:  
    */
    Map<String, Cart> huiFuCart(String bookId, Map<String, Cart> map);
    /**
    * 功能描述: * @param: 购买购物车
    * @return: 
    * @auther: 
    * @date:  
    */
    Map<String, Cart> BuyCart(List<String> arr1, Map<String, Cart> map);
    /**
     * @category 移除一级选购购物车里面的商品
     * @param arr1
     * @param map
     * @return
     */
    public Map<String, Cart> RemoveCart(List<String> arr1,
                                        Map<String, Cart> map);
}
