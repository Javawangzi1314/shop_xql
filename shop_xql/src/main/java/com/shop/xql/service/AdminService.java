package com.shop.xql.service;

import com.shop.xql.pojo.Admin;

import java.util.Map;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/4
 * @Description: com.cmfz.xql.service 管理员业务层接口
 * @version: 1.0
 */
public interface AdminService {
    /**
    * 功能描述: * @param: 管理员登录
    * @return: 
    * @auther: 
    * @date:  
    */
    public Map<String,Object> queryByAccountAndPassword(Admin admin, String SessionId, String code);
}
