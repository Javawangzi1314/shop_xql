package com.shop.xql.controller;

import java.util.List;


import com.shop.xql.pojo.Order;
import com.shop.xql.pojo.Summary;
import com.shop.xql.pojo.User;
import com.shop.xql.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
* 功能描述: * @param: 订单详情模块
* @return: 
* @auther: 
* @date:  
*/
@Controller
@RequestMapping(value="/Order")//设置访问路径前缀
public class OrderController {
	@Autowired
	private OrderService orderService;//自动注入order的接口类
	/**
	* 功能描述: * @param: 查询所有订单详情
	* @return: 
	* @auther: 
	* @date:  
	*/
	@RequestMapping(value="/QueryAllOrder")
	public ModelAndView QueryAllOrder(String sumId,Double sumMoney,String status){
		ModelAndView mav = new ModelAndView();
		List<Order> list = orderService.QueryAllOrder(sumId);
		mav.addObject("xianglist", list);
		mav.addObject("dingdanhao", sumId);
		mav.addObject("zongjin", sumMoney);
		mav.addObject("ztai", status);
		mav.setViewName("forward:/order_detail.jsp");
		return mav;
	}
	/**
	* 功能描述: * @param: 查询一个总订单的详情订单
	* @return: 
	* @auther: 
	* @date:  
	*/
	@RequestMapping(value="/QueryMyOrder")
	public ModelAndView QueryMyOrder(HttpSession session){
		ModelAndView mav = new ModelAndView();
		Object obj = session.getAttribute("localUser");
		User user =null;
		if(obj!=null){
			user =(User)obj;
		}
		List<Order> orderList = orderService.QueryMyOrder(user);
		session.setAttribute("orderList",orderList);
		mav.setViewName("redirect:/myOrders.jsp");
		return mav;
	}
	/**
	* 功能描述: * @param: 根据总订单编号删除详情订单
	* @return: 
	* @auther: 
	* @date:  
	*/
	@RequestMapping(value="/DeleteMyOrder")
	public String DeleteMyOrder(String sumSumId){
		orderService.DeleteMyOrder(sumSumId);
		return "redirect:/Order/QueryMyOrder";
	}
	/**
	* 功能描述: * @param: 根据总订单编号查询详情订单
	* @return: 订单集合
	* @auther: 
	* @date:  
	*/
	@RequestMapping(value="/queryOne")
	@ResponseBody
	public List<Order> queryOne(String orderId,HttpSession session){
		List<Order> orderList = orderService.queryOne(orderId);

		return orderList;
	}

}
