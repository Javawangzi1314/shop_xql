package com.shop.xql.service.impl;

import com.shop.xql.mapper.AdminDao;
import com.shop.xql.pojo.Admin;
import com.shop.xql.service.AdminService;
import com.shop.xql.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/4
 * @Description: com.cmfz.xql.service 管理员业务类实现类
 * @version: 1.0
 */
@Slf4j
@Service(value = "AdminServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)//事务注解
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;//自动注入管理员数据库接口
    @Autowired
    private HttpSession session;//自动注入session
    /**
    * 功能描述: * @param: g管理员账号密码登录
    * @return: 
    * @auther: 
    * @date:  
    */
    public Map<String,Object> queryByAccountAndPassword(Admin admin, String SessionId, String code){

        Admin admin1 = null;
        String msg = null;
//        String code1 = validateCodeService.queryCode(SessionId);
        String code1 = (String) session.getAttribute("code");
        log.info("{}和{}",code,code1);
        if(code.equals(code1)){
            admin1 = adminDao.selectByPrimaryKey(admin);
            log.info("查询登录{}",admin1);
            if(admin1!=null){
                boolean md5 = MD5Util.getSaltverifyMD5(admin.getAdminPassword(), admin1.getAdminPassword(), admin1.getSalt());
                if(!md5){
                    msg="密码错误";
                }
            }else{
                msg="账号错误";
            }
        }else{
            msg="验证码错误";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("admin",admin1);
        return map;
    }

}
