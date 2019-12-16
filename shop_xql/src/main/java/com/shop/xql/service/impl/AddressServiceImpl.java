package com.shop.xql.service.impl;

import com.shop.xql.mapper.AddressDao;
import com.shop.xql.pojo.Address;
import com.shop.xql.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/12
 * @Description: com.shop.xql.service.impl 地址业务层实现类
 * @version: 1.0
 */
@Slf4j
@Service(value = "AddressServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)//事务注解
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;//自动注入地址数据库接口类
    /**
    * 功能描述: * @param: 查询当前用户地址
    * @return:
    * @auther:
    * @date:
    */
    public Address queryOne(Address address){
        return addressDao.selectOne(address);
    }
}
