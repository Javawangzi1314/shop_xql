package com.shop.xql.service;



import com.shop.xql.pojo.Cart;
import com.shop.xql.pojo.Summary;
import com.shop.xql.pojo.User;
import com.shop.xql.util.AllPageUtil;

import java.util.List;
import java.util.Map;
/**
* 功能描述: * @param: 总订单业务层业务类接口
* @return: 
* @auther: 
* @date:  
*/
public interface SummaryService {
	/**
	 * @category 生成订单
	 * @param map
	 * @param user
	 * @param zongjia
	 * @param jiesheng
	 * @param addId
	 * @return
	 */
	Summary insertSummary(Map<String, Cart> map, User user, Double zongjia,
						  Double jiesheng, String addId);
	/**
	* 功能描述: * @param: 查询所有订单
	* @return:
	* @auther:
	* @date:
	*/
	List<Summary> QueryAllSummary();

}
