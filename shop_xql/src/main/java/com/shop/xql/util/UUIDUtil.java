package com.shop.xql.util;

import java.util.UUID;

/**
 * @category UUID生成工具类
 * @author 许清磊
 *
 */
public class UUIDUtil {
	/**
	 * @category 生成随机UUID
	 * @return
	 */
	public static String getUUID() {
        return UUID.randomUUID().toString();
    }
	/**
	 * @category 生成随机去除-符号的UUID
	 * @return
	 */
	public static String getshortUUID(){
		 return UUIDUtil.getUUID().replace("-", "");
	}
}
