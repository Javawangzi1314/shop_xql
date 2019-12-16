package com.shop.xql.service;

import com.shop.xql.pojo.Shop;
import com.shop.xql.pojo.Type;
import com.shop.xql.util.PageUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/8
 * @Description: com.shop.xql.service 商品业务层接口
 * @version: 1.0
 */
public interface ShopService {
    /**
    * 功能描述: * @param: 查询所有商品前台分页
    * @return: 
    * @auther: 
    * @date:  
    */
    List<Shop> queryAllShop(PageUtil pageUtil);
    /**
    * 功能描述: * @param: 插入商品
    * @return: 
    * @auther: 
    * @date:  
    */
    String insertShop(Shop shop, MultipartFile file,String realPath);
    /**
    * 功能描述: * @param: 查询one商品
    * @return: 
    * @auther: 
    * @date:  
    */
    Shop findShopById(Shop shop);
    /**
    * 功能描述: * @param: 修改商品
    * @return: 
    * @auther: 
    * @date:  
    */
    void updateShop(Shop shop,MultipartFile file,String realPath);
    /**
    * 功能描述: * @param: 删除商品
    * @return: 
    * @auther: 
    * @date:  
    */
    void deleteShopById(Shop shop);
    /**
    * 功能描述: * @param: 导入execl表格
    * @return:
    * @auther:
    * @date:
    */
    void LeadInShop(MultipartFile file, String realPath);
    /**
    * 功能描述: * @param: 查询数量总条数用来分页
    * @return:
    * @auther:
    * @date:
    */
    Integer queryAllShopCount();
    /**
    * 功能描述: * @param: 后台查询所有商品
    * @return:
    * @auther:
    * @date:
    */
    List<Shop> queryAll1Shop();
    /**
    * 功能描述: * @param: 模糊查询
    * @return:
    * @auther:
    * @date:
    */
    List<Shop> queryByTiao(String shopName,String shopType);
}
