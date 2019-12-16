package com.shop.xql.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/4
 * @Description: com.cmfz.xql.pojo
 * @version: 1.0
 */
@Data//生成所有的方法包括getset
@NoArgsConstructor//生成无参构造
@AllArgsConstructor//生成有参构造
@Accessors(chain = true)//链式调用
@Table(name="Admin")//用于mapper映射表名称
public class Admin implements Serializable {
    @Id//通用mapper表示主键
    @Column(name="admin_id")//通用mapper映射数据库字段名称
    private String adminId;//管理员编号
    @Column(name="admin_account")//通用mapper映射数据库字段名称
    private String adminAccount;//管理员账号
    @Column(name="admin_password")//通用mapper映射数据库字段名称
    private String adminPassword;//管理员MD5加密密码
    @Column(name="salt")//通用mapper映射数据库字段名称
    private String salt;//管理员盐值
    @Column(name="admin_sign")//通用mapper映射数据库字段名称
    private String adminSign;//管理员身份
}
