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
 * @Date: 2019/11/29
 * @Description: com.llq.pojo 总订单实体类
 * @version: 1.0
 */
@Data//生成所有的方法包括getset
@NoArgsConstructor//生成无参构造
@AllArgsConstructor//生成有参构造
@Accessors(chain = true)//链式调用
@Table(name = "Summary")//用于mapper映射表名称
public class Summary implements Serializable {
    /**
     * 定义程序序列化ID
     */
    private static final long serialVersionUID = 1L;
    @Id//通用mapper表示主键
    @Column(name="sum_id")//通用mapper映射数据库字段名称
    private String sumId;//订单编号
    @Column(name="sum_money")//通用mapper映射数据库字段名称
    private Double sumMoney;//订单金额总计
    @Column(name="sum_status")//通用mapper映射数据库字段名称
    private String sumStatus;//订单状态
    @Column(name="sum_userid")//通用mapper映射数据库字段名称
    private String sumUserId;//收件人编号
    @Column(name="sum_name")//通用mapper映射数据库字段名称
    private String sumName;//收件人姓名
    @Column(name="sum_addid")//通用mapper映射数据库字段名称
    private String sumAddId;//收件人地址编号
    @Column(name="sum_address")//通用mapper映射数据库字段名称
    private String sumAddress;//收件人详细地址
    @Column(name="sum_creatdate")//通用mapper映射数据库字段名称
    private Date sumCreatdate;//创建时间
}
