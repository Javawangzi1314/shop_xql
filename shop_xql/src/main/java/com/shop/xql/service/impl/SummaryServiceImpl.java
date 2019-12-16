package com.shop.xql.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.shop.xql.mapper.OrderDao;
import com.shop.xql.mapper.ShopDao;
import com.shop.xql.mapper.SummaryDao;
import com.shop.xql.pojo.*;
import com.shop.xql.service.AddressService;
import com.shop.xql.service.SummaryService;
import com.shop.xql.util.AllPageUtil;
import com.shop.xql.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation=Propagation.REQUIRED,timeout=30)//事务注解
public class SummaryServiceImpl implements SummaryService {
	@Autowired
	private SummaryDao summaryDao;//自动注入总订单接口
	//	@Autowired
//	private AddressMapper addressMapper;
	@Autowired
	private ShopDao shopDao;//自动注入商品数据库接口
	@Autowired
	private OrderDao orderDao;//自动注入订单接口
	@Autowired
	private AddressService addressService;//自动注入地址接口
	/**
	 * @category 生成订单
	 * @param map
	 * @param user
	 * @param zongjia
	 * @param jiesheng
	 * @param addId
	 * @return
	 */
	@Override
	public Summary insertSummary(Map<String, Cart> map, User user, Double zongjia,
								 Double jiesheng, String addId) {
		Summary summary = null;
		/**
		 * 1查看地址是否有地址编号 没有存入数据库
		 *
		 */

//		//获取地址
//		List<Address> list = addressMapper.queryAllAddress();
//		Address address =list.get(0);
		/**
		 * 2.生成总订单表  对象数据封装
		 */
		summary = new Summary();
		/**
		 * 1.编号使用雪花算法
		 */
		summary.setSumId(SnowflakeIdWorker.getSnowflakeIdWorker());
		summary.setSumMoney(zongjia);
		summary.setSumStatus("未支付");
		summary.setSumUserId(user.getUserId());
		summary.setSumName(user.getUserName());
		Address addressnow = new Address();
		addressnow.setAddUserId(user.getUserId());
		Address address = addressService.queryOne(addressnow);
		summary.setSumAddId(address.getAddId());
		summary.setSumAddress(address.getAddAddress());
		summary.setSumCreatdate(new Date());

		summaryDao.insert(summary);

		/**
		 * 3.生成详细订单表 封装数据
		 *
		 */
		Collection<Cart> collection = map.values();
		for (Cart cart : collection) {
			Order order = new Order();
			order.setOrderId(SnowflakeIdWorker.getSnowflakeIdWorker());
			order.setUserId(user.getUserId());
			order.setSumSumId(summary.getSumId());
			order.setSumShopId(cart.getShop().getShopId());
			order.setSumShopname(cart.getShop().getShopName());
			order.setSumPrice(cart.getShop().getShopPrice());
			order.setSumNum(cart.getCount());
			order.setSumAddress(cart.getShop().getShopPath());
			order.setSumStatus(summary.getSumStatus());
			orderDao.insert(order);
		}
		return summary;
	}
	/**
	* 功能描述: * @param: 查询所有订单
	* @return:
	* @auther:
	* @date:
	*/
	@Override
	public List<Summary> QueryAllSummary() {

		return summaryDao.selectAll();
	}
}
