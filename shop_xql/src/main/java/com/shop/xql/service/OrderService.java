package com.shop.xql.service;

import com.shop.xql.pojo.Order;
import com.shop.xql.pojo.User;

import java.util.List;


/**
* 功能描述: * @param: 订单业务层接口
* @return: 
* @auther: 
* @date:  
*/
public interface OrderService {
	/**
	* 功能描述: * @param: 查询所有订单
	* @return: 
	* @auther: 
	* @date:  
	*/
	List<Order> QueryAllOrder(String sumId);
	/**
	* 功能描述: * @param: 查询我的订单
	* @return: 
	* @auther: 
	* @date:  
	*/
	List<Order> QueryMyOrder(User user);
	/**
	* 功能描述: * @param: 删除我的订单
	* @return: 
	* @auther: 
	* @date:  
	*/
	void DeleteMyOrder(String sumSumId);
	/**
	* 功能描述: * @param: 查询一个订单
	* @return: 
	* @auther: 
	* @date:  
	*/
    List<Order> queryOne(String orderId);
}
