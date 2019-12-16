package com.shop.xql.util;

import java.security.MessageDigest;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;

/**
 * @category 加密工具类
 *
 */
public class MD5Util {
	/**
	 * @category 获取盐值
	 * @return
	 */
	public static String getSalt(){
		// 生成一个16位的随机数
				char[] code =  "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < 16; i++) {
					sb.append(code[new Random().nextInt(code.length)]);
				}
				// 生成最终的加密盐
				String Salt = sb.toString();
				return Salt;
	}
	/**
	 * @category 加盐MD5加密
	 * @param password
	 * @param Salt
	 * @return
	 */
	public static String getSaltMD5(String password,String Salt) {
		password = md5Hex(password + Salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = password.charAt(i / 3 * 2);
			char c = Salt.charAt(i / 3);
			cs[i + 1] = c;
			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
		}
		return String.valueOf(cs);
	}
	/**
	 * @category 使用Apache的Hex类实现Hex(16进制字符串和)和字节数组的互转
	 * @param str
	 * @return
	 */
	private static String md5Hex(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(str.getBytes());
			return new String(new Hex().encode(digest));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			return "";
		}
	}
	
	/**
	 * @category 判断密码是否一致
	 * @param password 密码
	 * @param md5str 加密过的密码
	 * @return
	 */
	public static boolean getSaltverifyMD5(String password,String md5str,String Salt) {
		return md5str.equals(getSaltMD5(password,Salt));
	}
	
	public static void main(String[] args) {
		int nextInt = new Random().nextInt(1);
		
		System.out.println(nextInt);
		String salt =getSalt();
		System.out.println(salt);
		System.out.println(getSaltMD5("xql123",salt));
	}
	
	
}
