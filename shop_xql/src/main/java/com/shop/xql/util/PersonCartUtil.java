package com.shop.xql.util;



import com.shop.xql.pojo.Cart;
import com.shop.xql.pojo.Shop;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



/**
 * @category 购物车工具类增删改查
 *
 */
public class PersonCartUtil {
	
	/**
	 * 1.添加
	 * 1.1参数 图书信息
	 * 返回值 是一个map集合<id,Cart>
	 */
	public static Map<String, Cart> addCart(Shop shop, Map<String,Cart> map){
		/**
		 * 1.根据建找值找不到，放入一个
		 * 2.找到了则，修改数量+1
		 */
		Cart cart = map.get(shop.getShopId());
		if(cart==null){
			Cart newcart = new Cart();
			newcart.setShop(shop);
			newcart.setCount(1);
			newcart.setStatus(true);
			newcart.setCountPrice();
			map.put(shop.getShopId(), newcart);
		}else{
			cart.setCount(cart.getCount()+1);
			cart.setCountPrice();
			map.put(shop.getShopId(), cart);
		}
		return map;
	}
	/**
	 * @category 批量删除购物车
	 * @param arr1
	 * @param map
	 * @return
	 */
	public static Map<String,Cart> allDeleteCart(List<String> arr1,
			Map<String, Cart> map){
		Map<String, Cart> map1 = map;
		for (String ShopId : arr1) {
			map1.remove(ShopId);
		}
		return map1;
	}
	/**
	 * @category 修改购物车商品数量
	 * @param ShopId
	 * @param count
	 * @param map
	 * @return
	 */
	public static Map<String,Cart> updateCart(String ShopId, Integer count,
			Map<String, Cart> map){
		Cart cart = map.get(ShopId);
		cart.setCount(count);
		cart.setCountPrice();
		map.put(ShopId, cart);
		return map;
	}
	
	/**
	 * @category 恢复删除
	 * @param ShopId
	 * @param map
	 * @return
	 */
	public static Map<String,Cart> huiFuCart(String ShopId,
			Map<String, Cart> map){
		Cart cart = map.get(ShopId);
			cart.setStatus(true);
			map.put(ShopId, cart);
			return map;
	}
	
	
	/**
	 * 1.辅助方法
	 * 1.参数列表 集合
	 * 2.返回值为差价或者其他辅助值
	 *   节省价格  总计当当价
	 * 
	 */
	
	
	/**
	 * @category 商品当当总价
	 * @param map
	 * @return
	 */
	public static Double getAllPrice(Map<String, Cart> map){
		Double allprice=0.0;
		Collection<Cart> collection = map.values();
		for (Cart cart : collection) {
			if(cart.getStatus()){
				allprice+=cart.getCountPrice();
			}
		}
		return allprice;
	}

	/**
	 * @category 判断是否有恢复商品
	 * @param map
	 * @return
	 */
	public static boolean getHuifu(Map<String, Cart> map){
		Boolean flag = false;
		Collection<Cart> collection = map.values();
		for (Cart cart : collection) {
			if(!cart.getStatus()){
				flag=true;
			}
		}
		return flag;
	}
	/**
	 * @category 获取选购的商品
	 * @param arr1
	 * @param map
	 * @return
	 */
	public static Map<String, Cart> getBuyCart(List<String> arr1,
			Map<String, Cart> map){
		Map<String, Cart> newmap = new ConcurrentHashMap<>();
		for (String str : arr1) {
			Cart cart = map.get(str);
			newmap.put(str, cart);
		}
		return newmap;
	}
	/**
	 * @category 移除购物车商品
	 * @param arr1
	 * @param map
	 * @return
	 */
	public static Map<String, Cart> getRemoveCart(List<String> arr1,
			Map<String, Cart> map){
		for (String str : arr1) {
			map.remove(str);
		}
		return map;
	}
	
}
