package com.shop.xql.service;

import com.shop.xql.pojo.Address;
import com.shop.xql.pojo.User;
import com.shop.xql.util.AllPageUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/10
 * @Description: com.shop.xql.service 用户业务类接口
 * @version: 1.0
 */
public interface UserService {
    /**
    * 功能描述: * @param: 后台查询所有用户  分页
    * @return:  用户列表
    * @auther: 
    * @date:  
    */
    List<User> queryAllUser(AllPageUtil pageUtil);
    /**
    * 功能描述: * @param: 插入用户
    * @return: 
    * @auther: 
    * @date:  
    */
    User insertUser(User user,String addAddress);
    /**
    * 功能描述: * @param: 用户登录
    * @return: 
    * @auther: 
    * @date:  
    */
    User loginUser(User user);
    /**
    * 功能描述: * @param: 手机号登录
    * @return: 
    * @auther: 
    * @date:  
    */
    User loginByphone(User user);
    /**
    * 功能描述: * @param: 修改
    * @return: 
    * @auther: 
    * @date:  
    */
    Address updateMy(User user, User user1, Address address);
    /**
    * 功能描述: * @param: 查询一个用户
    * @return: 
    * @auther: 
    * @date:  
    */
    User queryOne(User user1);
    /**
    * 功能描述: * @param: 修改头像
    * @return: 
    * @auther: 
    * @date:  
    */
    User UserImage(User user1, MultipartFile file, String realPath);
    /**
    * 功能描述: * @param: 禁用用户
    * @return: 
    * @auther: 
    * @date:  
    */
    void banUserById(String userId);
    /**
    * 功能描述: * @param: 启用用户
    * @return: 
    * @auther: 
    * @date:  
    */
    void useUserById(String userId);
    /**
    * 功能描述: * @param: 统计用户统计图
    * @return: 
    * @auther: 
    * @date:  
    */
    Map<String, Integer> queryTongji();
    /**
    * 功能描述: * @param: 用户统计图地图格式
    * @return: 
    * @auther: 
    * @date:  
    */
    Map<String,Integer> querychainTongji();
    /**
    * 功能描述: * @param: 查询数量用来分页
    * @return: 
    * @auther: 
    * @date:  
    */
    Integer queryAllUserCount();

}
