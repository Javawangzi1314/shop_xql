package com.shop.xql.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;



import com.shop.xql.pojo.Cart;
import com.shop.xql.pojo.Summary;
import com.shop.xql.pojo.User;

import com.shop.xql.service.CartService;
import com.shop.xql.service.SummaryService;

import com.shop.xql.util.PersonCartUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


/**
* 功能描述: * @param: 总订单模块
* @return: 
* @auther: 
* @date:  
*/
@Controller
@RequestMapping(value="/Summary")//设置访问路径前缀
@SessionAttributes(value={"flag","allPrice","allChaPrice","localCart","BuyCart","sssummary"})
public class SummaryController {
	@Autowired
	private SummaryService summaryService;//自动注入总订单业务层接口
	@Autowired
	private CartService cartService;//自动注入购物车业务层接口

	/**
	 * @category 添加总订单
	 * @return
	 */
	@RequestMapping(value="/insertSummary")
	@ResponseBody
	public String insertSummary(HttpSession session, String addId){
		Object obj = session.getAttribute("localUser");
		User user =null;
		if(obj!=null){
			user =(User)obj;
		}
		Object obj1 = session.getAttribute("BuyCart");
		Map<String, Cart> map =null;
		if(obj1!=null){
			map = (Map<String,Cart>)obj1;
		}
		Object obj2 = session.getAttribute("CartPrice");
		Double zongjia = (Double)obj2;
		Object obj3 = session.getAttribute("Cartjiesheng");
		Double jiesheng =(Double)obj3;
		Summary summary = summaryService.insertSummary(map,user,zongjia,jiesheng,addId);
		if(summary==null){
			//错误页面
			return "no";
		}else{
			/**
			 * 1.清空购买商品
			 */
			System.out.println(summary);
			session.setAttribute("sssummary",summary);
			Object obj5 = session.getAttribute("BuyCart");
			Map<String,Cart> map2 =null;
			if(obj5!=null){
				map2 = (Map<String,Cart>)obj5;
			}
			List<String> arr1 = new CopyOnWriteArrayList<>();
			Set<String> set = map2.keySet();
			for (String str : set) {
				arr1.add(str);
			}
			Object obj6 = session.getAttribute("localCart");
			Map<String,Cart> map5 = (Map<String,Cart>)obj6;

			Map<String,Cart> map1=cartService.RemoveCart(arr1,map5);

			Double allPrice = PersonCartUtil.getAllPrice(map1);
			boolean flag = PersonCartUtil.getHuifu(map1);
			session.removeAttribute("BuyCart");
			Summary localsummary = (Summary)session.getAttribute("sssummary");
			System.out.println(localsummary);
			System.out.println(localsummary);
			return localsummary.getSumId();
		}
	}
	/**
	* 功能描述: * @param: 查询所有总订单
	* @return: 
	* @auther: 
	* @date:  
	*/
	@RequestMapping(value="/QueryAllSummary")
	public ModelAndView QueryAllSummary(){
		ModelAndView mav =new ModelAndView();
		List<Summary> showlist = summaryService.QueryAllSummary();
		mav.addObject("showlist", showlist);
		mav.setViewName("forward:/backend/sysuserManager.jsp");
		return mav;
	}
	
}
