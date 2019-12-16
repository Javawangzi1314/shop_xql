package com.shop.xql.service.impl;

import com.shop.xql.mapper.AddressDao;
import com.shop.xql.mapper.UserDao;
import com.shop.xql.pojo.Address;
import com.shop.xql.pojo.User;
import com.shop.xql.service.UserService;
import com.shop.xql.util.AllPageUtil;
import com.shop.xql.util.MD5Util;
import com.shop.xql.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/10
 * @Description: com.shop.xql.service.impl 用户业务层实现类
 * @version: 1.0
 */
@Service
@Slf4j//启用log日志
@Transactional(propagation = Propagation.SUPPORTS,readOnly =true)//事务
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;//自动注入用户数据库接口
    @Autowired
    private AddressDao addressDao;//自动注入地址数据库接口
    /**
    * 功能描述: * @param: 查询所有用户分页
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public List<User> queryAllUser(AllPageUtil pageUtil) {
        User user = new User();
        RowBounds rowsBounds = new RowBounds(pageUtil.getStartIndex(),pageUtil.getPageSize());
        List<User> list = userDao.selectByRowBounds(user, rowsBounds);
        return list;
    }
    @Transactional(propagation = Propagation.REQUIRED,timeout = 30)
    /**
    * 功能描述: * @param: 注册用户
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public User insertUser(User user,String addAddress) {
        User user1 = user.setUserId(UUIDUtil.getshortUUID());
        String salt = MD5Util.getSalt();
        user.setSalt(salt);
        user.setUserPassword(MD5Util.getSaltMD5(user.getUserPassword(),salt));
        user.setUserTime(new Date());
        user.setUserStatus("正常");
        user.setUserPath("\\upload\\db34d029-cdc9-4ca6-a170-d6c0bab2ec6b.jpg");
        log.info("注册员工用户{}",user);
        userDao.insert(user);
        Address address = new Address(UUIDUtil.getshortUUID(),user.getUserId(),user.getUserName(),addAddress,"51000","15936517793");
        addressDao.insert(address);
        return user;
    }
    /**
     * 功能描述: * @param: 用户登录
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public User loginUser(User user) {
        User user1 = new User();
        user1.setUserName(user.getUserName());
        User selectOne = userDao.selectOne(user1);
        boolean md5 = MD5Util.getSaltverifyMD5(user.getUserPassword(), selectOne.getUserPassword(), selectOne.getSalt());
        if(md5){
            return selectOne;
        }else{
            return null;
        }
    }
    /**
    * 功能描述: * @param: 手机号登录
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public User loginByphone(User user) {
        User selectOne = userDao.selectOne(user);
        return selectOne;
    }
    /**
    * 功能描述: * @param: 修改用户信息
    * @return: 
    * @auther: 
    * @date:  
    */
    @Transactional(propagation = Propagation.REQUIRED,timeout = 30)
    @Override
    public Address updateMy(User user, User user1, Address address) {
        user1.setUserName(user.getUserName());
        user1.setUserAccount(user.getUserAccount());
        user1.setUserPhone(user.getUserPhone());
        userDao.updateByPrimaryKey(user1);
        Address address2 = new Address();
        address2.setAddUserId(user1.getUserId());
        Address address3 = addressDao.selectOne(address2);
        address3.setAddAddress(address.getAddAddress());
        addressDao.updateByPrimaryKey(address3);
        return address3;
    }
    /**
     * 功能描述: * @param: 查询一个用户
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public User queryOne(User user1) {
        User user6 = new User();
        user6.setUserId(user1.getUserId());
        return userDao.selectOne(user6);
    }
    /**
    * 功能描述: * @param: 修改用户头像
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public User UserImage(User user1, MultipartFile file, String realPath) {
        java.io.File file1 = new  java.io.File(realPath+"upload");
        if(!file1.exists()){
            file1.mkdir();
        }
        //获取源文件名称
        String fileName = file.getOriginalFilename();
        //获取源文件类型
        String fileType = file.getContentType();
        //或追
        String houzhui = FilenameUtils.getExtension(fileName);

        //新名称
        String fileNamew = (UUID.randomUUID().toString()+"."+houzhui);
        try {
            file.transferTo(new java.io.File(file1.getPath(),fileNamew));
            user1.setUserPath("\\upload"+"\\"+fileNamew);
            userDao.updateByPrimaryKey(user1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user1;
    }
    /**
     * 功能描述: * @param: 禁用用户
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public void banUserById(String userId) {
        User user = userDao.selectByPrimaryKey(userId);
        user.setUserStatus("冻结");
        userDao.updateByPrimaryKey(user);
    }
    /**
     * 功能描述: * @param: 启用用户
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public void useUserById(String userId) {
        User user = userDao.selectByPrimaryKey(userId);
        user.setUserStatus("正常");
        userDao.updateByPrimaryKey(user);
    }
    /**
     * 功能描述: * @param: 统计用户统计图
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public Map<String, Integer> queryTongji() {
        Integer count1 = userDao.queryCount( 0);
        Integer count2 = userDao.queryCount( 1);
        Integer count3 = userDao.queryCount(2);
        Integer count4 = userDao.queryCount( 0);
        Integer count5 = userDao.queryCount( 1);
        Integer count6 = userDao.queryCount( 2);
        Map<String,Integer> map = new HashMap<>();
        map.put("nan1",count1);
        map.put("nan2",count2);
        map.put("nan3",count3);
        map.put("nv1",count4);
        map.put("nv2",count5);
        map.put("nv3",count6);
        return map;
    }
    /**
     * 功能描述: * @param: 用户统计图地图格式
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public Map<String, Integer> querychainTongji() {
        List<Address> list = addressDao.selectAll();
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> map1 = new HashMap<>();
        for (Address address : list) {
            Integer integer = map.get(address.getAddAddress());
                if(integer==null){
                    map1.put(address.getAddAddress(),1);
                }else{
                    map1.put(address.getAddAddress(),integer+1);
                }
        }
        return map1;
    }
    /**
     * 功能描述: * @param: 查询数量用来分页
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public Integer queryAllUserCount() {
        User user = new User();
        return userDao.selectCount(user);
    }

}
