package com.shop.xql.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;


import com.shop.xql.pojo.Cart;
import com.shop.xql.service.CartService;
import com.shop.xql.util.PersonCartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


/**
* 功能描述: * @param: 购物车模块控制层
* @return: 
* @auther: 
* @date:  
*/
@Controller
@RequestMapping(value="/Cart")//设置访问路径前缀
@SessionAttributes(value={"flag","allPrice","num","localCart","CartPrice","BuyCart"})//设置sessiong共享
public class CartController {
	@Autowired
	private HttpSession session;//自动注入session对象
	@Autowired
	private CartService cartService;//自动注入cart接口类对象
//	@Autowired
//	private AddressService addressService;
	private static final String SUCCESS="forward:/cart.jsp";//定义常量
	
	/**
	* 功能描述: * @param: 把商品添加购物车
	* @return: 
	* @auther: 
	* @date:  
	*/
	@RequestMapping(value="addCart")
	@ResponseBody
	public String addCart(String shopId,Model model){
		Object obj = session.getAttribute("localCart");
		Map<String, Cart> map=null;
		if(obj==null){
			map = new ConcurrentHashMap<>();
		}else{
			map =(Map<String,Cart>)obj;
		}
	Map<String,Cart> map1= cartService.addCart(shopId,map);
	Double allPrice = PersonCartUtil.getAllPrice(map1);
	boolean flag = PersonCartUtil.getHuifu(map1);
        int size = map1.keySet().size();
        model.addAttribute("flag", flag);
	model.addAttribute("allPrice", allPrice);
	model.addAttribute("localCart", map1);
	model.addAttribute("num",size);
	return "ok";
	}
	/**
	* 功能描述: * @param: 从购物车批量删除商品
	* @return: 
	* @auther: 
	* @date:  
	*/
	@RequestMapping(value="allDelete")
	public ModelAndView allDelete(String arr){
		System.out.println(arr);
		String[] split = arr.split(",");
		ModelAndView mav = new ModelAndView();
		Object obj = session.getAttribute("localCart");
		List<String> arr1 = new CopyOnWriteArrayList<>();
		for (String s : split) {
			arr1.add(s);
		}
		@SuppressWarnings("unchecked")
		Map<String,Cart> map = (Map<String,Cart>)obj;
		//开始删除
		Map<String,Cart> map1 = cartService.allDeleteCart(arr1,map);
		Double allPrice = PersonCartUtil.getAllPrice(map1);
		boolean flag = PersonCartUtil.getHuifu(map1);
        int size = map1.keySet().size();
		mav.addObject("flag", flag);
		mav.addObject("allPrice", allPrice);
		mav.addObject("localCart", map1);
        mav.addObject("num",size);
		mav.setViewName(SUCCESS);
		return mav;
	}
	
	/**
	 * @category 修改购物车商品 
	 * @return
	 */
	@RequestMapping(value="updateCart")
	public ModelAndView updateCart(String bookId,Integer count){
		ModelAndView mav = new ModelAndView();
		String arr =null;
		if(count==0){
			arr=bookId;
			mav.addObject("arr", arr);
			mav.setViewName("forward:/Card/allDelete");
			return mav;
		}else{
			Object obj = session.getAttribute("localCart");
			@SuppressWarnings("unchecked")
			Map<String,Cart> map = (Map<String,Cart>)obj;
			Map<String,Cart> map1 = cartService.updateCart(bookId,count,map);
			Double allPrice = PersonCartUtil.getAllPrice(map1);
            int size = map1.keySet().size();
			boolean flag = PersonCartUtil.getHuifu(map1);
			mav.addObject("flag", flag);
			mav.addObject("allPrice", allPrice);
			mav.addObject("localCart", map1);
            mav.addObject("num",size);
			mav.setViewName(SUCCESS);
			return mav;
		}
	}
	
	/**
	 * @category 恢复商品
	 * @return
	 */
	@RequestMapping(value="huiFuCart")
	public ModelAndView huiFuCart(String bookId){
		ModelAndView mav = new ModelAndView();
		Object obj = session.getAttribute("localCart");
		@SuppressWarnings("unchecked")
		Map<String,Cart> map = (Map<String,Cart>)obj;
		Map<String,Cart> map1 = cartService.huiFuCart(bookId,map);
		Double allPrice = PersonCartUtil.getAllPrice(map1);
		boolean flag = PersonCartUtil.getHuifu(map1);
        int size = map1.keySet().size();
		mav.addObject("flag", flag);
		mav.addObject("allPrice", allPrice);
		mav.addObject("localCart", map1);
        mav.addObject("num",size);
		mav.setViewName(SUCCESS);
		return mav;
	}
	
	/**
	 * @category 购买商品
	 * @return
	 */
	@RequestMapping(value="BuyCart")
	public ModelAndView BuyCart(String arr){
		ModelAndView mav = new ModelAndView();
		String[] split = arr.split(",");
		List<String> arr1 = new CopyOnWriteArrayList<>();
		for (String s : split) {
			arr1.add(s);
		}
		Object obj = session.getAttribute("localCart");
		@SuppressWarnings("unchecked")
		Map<String,Cart> map = (Map<String,Cart>)obj;
		Map<String,Cart> map1 = cartService.BuyCart(arr1,map);
		Double allPrice1 = PersonCartUtil.getAllPrice(map1);

//		List<Address> list = addressService.queryAllAddress();
		int size = map1.keySet().size();
		mav.addObject("CartPrice", allPrice1);
		mav.addObject("BuyCart", map1);
        mav.addObject("num",size);
//        mav.addObject("alist",list);
		mav.setViewName("forward:/order.jsp");
		return mav;
	}
	/**
	* 功能描述: * @param: 销毁购物车商品
	* @return: 
	* @auther: 
	* @date:  
	*/
	@RequestMapping("xiaohui")
	public ModelAndView xiaohui(HttpSession session){
		ModelAndView mav = new ModelAndView();
		Map<String, Cart> map = new ConcurrentHashMap<>();
		mav.addObject("localCart", map);
		mav.setViewName(SUCCESS);
		return mav;
	}

}
