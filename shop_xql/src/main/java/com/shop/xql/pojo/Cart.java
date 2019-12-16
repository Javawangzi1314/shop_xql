package com.shop.xql.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/11/27
 * @Description: com.llq.pojo 购物车DTO对象
 * @version: 1.0
 */
public class Cart implements Serializable {
    /**
     * 定义程序序列化ID
     */
    private static final long serialVersionUID = 1L;
    private Shop shop; //商品对象
    private Integer count; //商品数量
    private Boolean status;//是否删除
    private Double CountPrice;//原价总计
    /**
    * 功能描述: * @param: 无参构造
    * @return: 
    * @auther: 
    * @date:  
    */
    public Cart() {
    }
    /**
    * 功能描述: * @param: 有参构造
    * @return: 
    * @auther: 
    * @date:  
    */
    public Cart(Shop shop, Integer count, Boolean status,Double CountPrice) {
        this.shop = shop;
        this.count = count;
        this.status = status;
        this.CountPrice = shop.getShopPrice()*count;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getCountPrice() {
        return CountPrice;
    }

    public void setCountPrice() {
        this.CountPrice = shop.getShopPrice()*count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "shop=" + shop +
                ", count=" + count +
                ", status=" + status +
                ", CountPrice=" + CountPrice +
                '}';
    }
}
