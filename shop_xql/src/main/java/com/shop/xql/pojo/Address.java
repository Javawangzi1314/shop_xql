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
 * @Date: 2019/12/12
 * @Description: com.shop.xql.pojo
 * @version: 1.0
 */
@Data//生成所有的方法包括getset
@NoArgsConstructor//生成无参构造
@AllArgsConstructor//生成有参构造
@Accessors(chain = true)//链式调用
@Table(name="Address")//用于mapper映射表名称
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id//通用mapper表示主键
    @Column(name="add_id")//通用mapper映射数据库字段名称
    private String addId;//地址编号
    @Column(name="add_userid")//通用mapper映射数据库字段名称
    private String addUserId;//收件人编号
    @Column(name="add_name")//通用mapper映射数据库字段名称
    private String addName;//收件人姓名
    @Column(name="add_address")//通用mapper映射数据库字段名称
    private String addAddress;//收件人地址
    @Column(name="add_Postalcode")//通用mapper映射数据库字段名称
    private String addPostalcode;//收件人邮编
    @Column(name="add_phone")//通用mapper映射数据库字段名称
    private String addPhone;//收件人手机号
}
