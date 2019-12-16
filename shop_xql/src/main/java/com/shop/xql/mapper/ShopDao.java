package com.shop.xql.mapper;

import com.shop.xql.pojo.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/8
 * @Description: com.shop.xql.mapper 通用Mapper4.0继承接口生成sql  商品数据接口层
 * @version: 1.0
 */
@Repository
public interface ShopDao extends Mapper<Shop> {
    /**
    * 功能描述: * @param: 混用运用mapper.xml文件 模糊查询商品
    * @return: 
    * @auther: 
    * @date:  
    */
    List<Shop> queryByTiao(@Param("shopName") String shopName, @Param("shopType")String shopType);
}
