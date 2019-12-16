package com.shop.xql.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/8
 * @Description: com.shop.xql.pojo 商品类型实体类
 * @version: 1.0
 */
@Data//生成所有的方法包括getset
@NoArgsConstructor//生成无参构造
@AllArgsConstructor//生成有参构造
@Accessors(chain = true)//链式调用
@Table(name="Type")//用于mapper映射表名称
public class Type implements Serializable {
    @Id//通用mapper表示主键
    @Column(name="type_id")//通用mapper映射数据库字段名称
    private String typeId;//商品类型编号
    @Column(name="type_name")//通用mapper映射数据库字段名称
    private String typeName;//商品类型名称
    @Column(name="type_status")//通用mapper映射数据库字段名称
    private String typeStatus;//商品类型状态
    @Column(name="type_time")//通用mapper映射数据库字段名称
    private Date typeTime;//商品类型创建时间
}
