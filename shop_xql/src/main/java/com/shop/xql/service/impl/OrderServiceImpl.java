package com.shop.xql.service.impl;

import java.util.List;


import com.shop.xql.mapper.OrderDao;
import com.shop.xql.pojo.Order;
import com.shop.xql.pojo.User;
import com.shop.xql.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述: * @param: 订单业务层实现类
* @return: 
* @auther: 
* @date:  
*/

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)//事务注解
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;//自动注入订单数据库接口
	/**
	* 功能描述: * @param: 查询所有订单
	* @return: 
	* @auther: 
	* @date:  
	*/
	@Override
	public List<Order> QueryAllOrder(String sumId) {
		Order order = new Order();
		order.setSumSumId(sumId);
		return orderDao.select(order);
	}
	/**
	* 功能描述: * @param: 查询个人订单
	* @return: 
	* @auther: 
	* @date:  
	*/
	@Override
	public List<Order> QueryMyOrder(User user) {
		Order order = new Order();
		Order order1 = order.setUserId(user.getUserId());
		List<Order> orderList = orderDao.select(order1);
		return orderList;
	}
	/**
	* 功能描述: * @param: 删除订单
	* @return: 
	* @auther: 
	* @date:  
	*/
	@Override
	public void DeleteMyOrder(String sumSumId) {

		orderDao.deleteMyOrder(sumSumId);
	}
	/**
	* 功能描述: * @param: 查询订单
	* @return: 
	* @auther: 
	* @date:  
	*/
	@Override
	public List<Order> queryOne(String orderId) {
		Order order = new Order();
		order.setSumSumId(orderId);
		return orderDao.select(order);
	}

}
