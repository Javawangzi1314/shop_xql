package com.shop.xql.service;

import com.shop.xql.pojo.Address;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/12
 * @Description: com.shop.xql.service 地址业务层接口
 * @version: 1.0
 */
public interface AddressService {
    /**
    * 功能描述: * @param: 查询一个用户地址
    * @return: 
    * @auther: 
    * @date:  
    */
    public Address queryOne(Address address);
}
