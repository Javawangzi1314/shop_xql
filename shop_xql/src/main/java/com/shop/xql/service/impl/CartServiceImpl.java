package com.shop.xql.service.impl;


import com.shop.xql.mapper.ShopDao;
import com.shop.xql.pojo.Cart;
import com.shop.xql.pojo.Shop;
import com.shop.xql.service.CartService;
import com.shop.xql.util.PersonCartUtil;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/11/27
 * @Description: com.llq.service 购物车业务层实现类
 * @version: 1.0
 */
@Service
@Slf4j
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true)//事务注解
public class CartServiceImpl implements CartService {
    @Autowired
    private HttpSession session;//自动注入session
    @Autowired
    private ShopDao shopDao;//自动注入商品数据库实现类
    /**
    * 功能描述: * @param: 添加商品网购物车
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public Map<String, Cart> addCart(String shopId, Map<String,Cart> map) {
        Map<String, Cart> newmap = null;
        log.info("加入购物车{}",shopId);
        try{

            Shop shop = shopDao.selectByPrimaryKey(shopId);
            newmap = PersonCartUtil.addCart(shop, map);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newmap;
    }
    /**
     * @category 批量删除购物车商品
     * @param arr1
     * @param map
     * @return
     */
    @Override
    public Map<String, Cart> allDeleteCart(List<String> arr1,
                                           Map<String, Cart> map) {
        Map<String, Cart> newmap = null;
        try{
            newmap = PersonCartUtil.allDeleteCart(arr1, map);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newmap;
    }
    /**
     * @category 修改购物车商品数量
     * @param shopId
     * @param count
     * @param map
     * @return
     */
    @Override
    public Map<String, Cart> updateCart(String shopId, Integer count,
                                                Map<String, Cart> map) {
        Map<String, Cart> newmap = null;
        try{
            newmap = PersonCartUtil.updateCart(shopId, count, map);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return newmap;
    }
    /**
     * @category 恢复购物车
     * @param map
     * @return
     */
    @Override
    public Map<String, Cart> huiFuCart(String shopId,
                                               Map<String, Cart> map) {
        Map<String, Cart> newmap = null;
        try{
            newmap = PersonCartUtil.huiFuCart(shopId, map);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newmap;
    }
    /**
     * @category 购买的商品
     * @param arr1
     * @param map
     * @return
     */
    @Override
    public Map<String, Cart> BuyCart(List<String> arr1,
                                             Map<String, Cart> map) {
        Map<String, Cart> newmap = null;
        try{
            newmap = PersonCartUtil.getBuyCart(arr1, map);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newmap;
    }
    /**
     * @category 移除一级选购购物车里面的商品
     * @param arr1
     * @param map
     * @return
     */
    @Override
    public Map<String, Cart> RemoveCart(List<String> arr1,
                                                Map<String, Cart> map) {
        Map<String, Cart> newmap = null;
        try{
            newmap=PersonCartUtil.getRemoveCart(arr1, map);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newmap;
    }

}
