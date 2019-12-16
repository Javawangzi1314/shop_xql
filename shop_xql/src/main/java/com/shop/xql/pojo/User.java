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
 * @Date: 2019/12/10
 * @Description: com.shop.xql.pojo 用户实体类
 * @version: 1.0
 */
@Data//生成所有的方法包括getset
@NoArgsConstructor//生成无参构造
@AllArgsConstructor//生成有参构造
@Accessors(chain = true)//链式调用
@Table(name="User")//用于mapper映射表名称
public class User implements Serializable {
    @Id//通用mapper表示主键
    @Column(name="user_id")//通用mapper映射数据库字段名称
    private String userId;//用户编号
    @Column(name="user_path")//通用mapper映射数据库字段名称
    private String userPath;//用户头像
    @Column(name="user_name")//通用mapper映射数据库字段名称
    private String userName;//用户用户名
    @Column(name="user_account")//通用mapper映射数据库字段名称
    private String userAccount;//用户账号
    @Column(name="user_password")//通用mapper映射数据库字段名称
    private String userPassword;//用户MD5加密密码
    @Column(name="salt")//通用mapper映射数据库字段名称
    private String salt;//用户密码验证
    @Column(name="user_phone")//通用mapper映射数据库字段名称
    private String userPhone;//用户手机号
    @Column(name="user_time")//通用mapper映射数据库字段名称
    private Date userTime;//用户创建时间
    @Column(name="user_status")//通用mapper映射数据库字段名称
    private String userStatus;//用户状态
}
