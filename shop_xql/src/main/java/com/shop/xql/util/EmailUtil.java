package com.shop.xql.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
/**
* 功能描述: * @param: 邮箱工具类
* @return: 
* @auther: 
* @date:  
*/
public class EmailUtil {
	// 随机验证码
	/**
	 * 获取随机验证码
	 * @return
	 */
	public static String achieveCode() {  //由于数字1 和0 和字母 O,l 有时分不清，所有，没有字母1 、0
		String[] beforeShuffle= new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
		"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
		"b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
		"w", "x", "y", "z" };
		List<String> list = Arrays.asList(beforeShuffle);//将数组转换为集合
		Collections.shuffle(list);  //打乱集合顺序
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
		sb.append(list.get(i)); //将集合转化为字符串
		}
		return sb.toString().substring(3, 8);  //截取字符串第4到8
	}
	/**
	 * @category 向邮箱发送验证码
	 * @param email
	 * @param authCode
	 */
	public static void sendAuthCodeEmail(String email, String authCode) {
		try {
	 	SimpleEmail mail = new SimpleEmail();
	 	mail.setHostName("smtp.163.com");//发送邮件的服务器(smtp.qq.com)smtp.163.com
	 	//mail.setAuthentication("xql15936517793", "xqlwoaini1314");//登录邮箱的密码，是开启SMTP的密码
	 	mail.setAuthentication("xql15936517793@163.com", "xqlwoaini1314");
	 	mail.setFrom("xql15936517793@163.com","你好你验证码为");  //发送邮件的邮箱和发件人
	 	mail.setSSLOnConnect(true); //使用安全链接
	 	mail.addTo(email);//接收的邮箱
	 	mail.setSubject("平台验证码");//设置邮件的主题
		mail.setMsg("尊敬的用户:你好!\n 平台登陆验证码为:" + authCode+"\n"+"     (有效期为一分钟)");//设置邮件的内容
		mail.send();//发送
		} catch (EmailException e) {
			e.printStackTrace();
		}  
	}
	public static void main(String[] args) {
		
		sendAuthCodeEmail("1941663523@qq.com",achieveCode());
	}
}