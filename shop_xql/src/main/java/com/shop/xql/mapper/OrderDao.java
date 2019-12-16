package com.shop.xql.mapper;

import com.shop.xql.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/11
 * @Description: com.shop.xql.mapper 通用Mapper4.0继承接口生成sql  订单数据接口层
 * @version: 1.0
 */
@Repository
@org.apache.ibatis.annotations.Mapper//基于mybatis 混用mapper注解
public interface OrderDao extends Mapper<Order> {
    /**
    * 功能描述: * @param: 根据总订单id删除订单
    * @return: 
    * @auther: 
    * @date:  
    */
    @Delete("DELETE FROM shop_Order WHERE sum_sumid = #{sumSumId}")
    void deleteMyOrder(@Param("sumSumId")String sumSumId);
}
