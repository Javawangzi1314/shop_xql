package com.shop.xql.service.impl;

import com.shop.xql.mapper.TypeDao;
import com.shop.xql.pojo.Type;
import com.shop.xql.service.TypeService;
import com.shop.xql.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/8
 * @Description: com.shop.xql.service.impl 商品类型业务层实现类
 * @version: 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)//事务注解
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;//自动注入商品类型接口
    /**
    * 功能描述: * @param: 查询所有商品类型
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public List<Type> queryAllType() {
        Type type = new Type();
        List<Type> typeList = typeDao.select(type);
        return typeList;
    }
    /**
    * 功能描述: * @param: 插入一条商品
    * @return:
    * @auther:
    * @date:
    */
    @Transactional(propagation = Propagation.REQUIRED,timeout = 30)
    @Override
    public String insertType(Type type) {
        String msg = "";
        Type key = typeDao.selectOne(type);
        if(key==null){
            type.setTypeId(UUIDUtil.getshortUUID());
            type.setTypeStatus("启用");
            type.setTypeTime(new Date());
            typeDao.insert(type);
        }else {
            msg="该商品类型已注册";
        }
        return msg;
    }
    /**
    * 功能描述: * @param: 根据id查找修改商品类型
    * @return:
    * @auther:
    * @date:
    */
    @Override
    public Type findTypeById(Type type) {
        return typeDao.selectByPrimaryKey(type);
    }
    /**
     * 功能描述: * @param: 修改商品类型信息
     * @return:
     * @auther:
     * @date:
     */
    @Transactional(propagation = Propagation.REQUIRED,timeout = 30)
    @Override
    public String updateType(Type type) {
        String msg = "";
        Type type1 = new Type();
        type1.setTypeName(type.getTypeName());
        Type key = typeDao.selectOne(type1);
        if(key==null){
            typeDao.updateByPrimaryKeySelective(type);
        }else {
            msg="修改失败,该商品类型已注册";
        }
        return msg;
    }
    /**
     * 功能描述: * @param: 删除商品
     * @return:
     * @auther:
     * @date:
     */
    @Transactional(propagation = Propagation.REQUIRED,timeout = 30)
    @Override
    public void deleteTypeById(Type type) {
        typeDao.deleteByPrimaryKey(type);
    }
    /**
    * 功能描述: * @param: 启用商品类型
    * @return:
    * @auther:
    * @date:
    */
    @Transactional(propagation = Propagation.REQUIRED,timeout = 30)
    @Override
    public void useTypeById(Type type) {
        type.setTypeStatus("启用");
        typeDao.updateByPrimaryKeySelective(type);
    }
    /**
     * 功能描述: * @param: 禁止商品类型
     * @return:
     * @auther:
     * @date:
     */
    @Transactional(propagation = Propagation.REQUIRED,timeout = 30)
    @Override
    public void banTypeById(Type type) {
        type.setTypeStatus("禁用");
        typeDao.updateByPrimaryKeySelective(type);
    }
}
