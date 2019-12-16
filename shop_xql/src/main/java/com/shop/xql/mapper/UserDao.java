package com.shop.xql.mapper;

import com.shop.xql.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/10
 * @Description: com.shop.xql.mapper 通用Mapper4.0继承接口生成sql  用户数据接口层
 * @version: 1.0
 */
@Repository
@org.apache.ibatis.annotations.Mapper
public interface UserDao extends Mapper<User> {
    @org.apache.ibatis.annotations.Select("select COUNT(user_id) from user where YEARWEEK(DATE_FORMAT(user_time,'%Y-%m-%d')) = YEARWEEK(NOW())-#{week}")
    public Integer queryCount(@Param("week")Integer week);
}
