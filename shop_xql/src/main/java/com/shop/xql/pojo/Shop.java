package com.shop.xql.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
 * @Date: 2019/12/8
 * @Description: com.shop.xql.pojo
 * @version: 1.0
 */
@Data//生成所有的方法包括getset
@NoArgsConstructor//生成无参构造
@AllArgsConstructor//生成有参构造
@Accessors(chain = true)//链式调用
@Table(name="Shop")//用于mapper映射表名称
@ExcelTarget(value = "Shop")//Easypoi注解生成execl表格
public class Shop implements Serializable {
    @Id//通用mapper表示主键
    @Column(name="shop_id")//通用mapper映射数据库字段名称
    @Excel(name = "商品编号",orderNum ="0",height = 20, width = 30, isImportField = "true_st")//生成execle表格注解
    private String shopId;//商品编号
    @Column(name="shop_name")
    @Excel(name = "商品名称",orderNum ="1", height = 20, width = 30, isImportField = "true_st")
    private String shopName;//商品名称
    @Column(name="shop_price")
    @Excel(name = "商品价格",orderNum ="2",type = 10,height = 20, width = 30, isImportField = "true_st")
    private Double shopPrice;//商品价格
    @Column(name="shop_type")
    @Excel(name = "商品类型",orderNum ="3", height = 20, width = 30, isImportField = "true_st")
    private String shopType;//商品类型
    @Column(name="shop_count")
    @Excel(name = "商品数量",orderNum ="4",type = 10,height = 20, width = 30, isImportField = "true_st")
    private Integer shopCount;//商品数量
    @Column(name="shop_status")
    @Excel(name = "商品状态",orderNum ="5",height = 20, width = 30, isImportField = "true_st")
    private String shopStatus;//商品状态
    @Column(name="shop_dis")
    @Excel(name = "商品描述",orderNum ="6",height = 20, width = 30, isImportField = "true_st")
    private String shopDis;//商品描述
    @Column(name="shop_path")
    @Excel(name = "商品图片", type = 2,imageType=1, orderNum ="7", height = 20, width = 30, isImportField = "true_st")
    private String shopPath;//商品图片地址

}
