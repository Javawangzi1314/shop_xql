package com.shop.xql.util;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/12
 * @Description: com.shop.xql.util 手机验证码工具类
 * @version: 1.0
 */
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
public class SendSms {

    // 随机验证码
    /**
     * 获取随机验证码
     * @return
     */
    public static String achieveCode() {  //由于数字1 和0 和字母 O,l 有时分不清，所有，没有字母1 、0
        String[] beforeShuffle= new String[] {"2", "3", "4", "5", "6", "7", "8", "9"};
        List<String> list = Arrays.asList(beforeShuffle);//将数组转换为集合
        Collections.shuffle(list);  //打乱集合顺序
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)); //将集合转化为字符串
        }
        return sb.toString().substring(3, 8);  //截取字符串第4到8
    }

    /**
     * @category 向手机号发送验证码
     * @param phoneNum
     * @param authCode
     */
    public static void sendAuthCodeEmail(String phoneNum, String authCode) {
        DefaultProfile profile = DefaultProfile.getProfile("LTAI4Fd5JqZnv1TSFtMdziyS", "LTAI4Fd5JqZnv1TSFtMdziyS", "LuUVhetDzA39yGwOWIOMu0KUtKgrBZ");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", "世纪佳缘");
        request.putQueryParameter("TemplateCode", "SMS_180048390");
        request.putQueryParameter("TemplateParam", "{code:"+authCode+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

     public static void main (String[] args){
         String code = achieveCode();
         sendAuthCodeEmail("18339816631",code);
     }

}
