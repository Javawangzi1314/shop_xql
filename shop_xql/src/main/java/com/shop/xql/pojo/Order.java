package com.shop.xql.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/11/29
 * @Description: com.llq.pojo
 * @version: 1.0
 */
@Data//生成所有的方法包括getset
@NoArgsConstructor//生成无参构造
@AllArgsConstructor//生成有参构造
@Accessors(chain = true)//链式调用
@Table(name="shop_Order")//用于mapper映射表名称
public class Order implements Serializable {
    /**
     * 定义程序序列化ID
     */
    private static final long serialVersionUID = 1L;
    @Id//通用mapper表示主键
    @Column(name="order_id")//通用mapper映射数据库字段名称
    private String orderId;//个人详细订单ID
    @Column(name="user_id")//通用mapper映射数据库字段名称
    private String userId;//用户编号
    @Column(name="sum_sumid")//通用mapper映射数据库字段名称
    private String sumSumId;//总订单编号
    @Column(name="sum_soopid")//通用mapper映射数据库字段名称
    private String sumShopId;//商品编号
    @Column(name="sum_shopname")//通用mapper映射数据库字段名称
    private String sumShopname;//商品名称
    @Column(name="sum_price")//通用mapper映射数据库字段名称
    private Double sumPrice;
    @Column(name="sum_num")//通用mapper映射数据库字段名称
    private Integer sumNum;//商品数量
    @Column(name="sum_address")//通用mapper映射数据库字段名称
    private String sumAddress;//商品地址
    @Column(name="sum_status")//通用mapper映射数据库字段名称
    private String sumStatus;//订单状态
}
