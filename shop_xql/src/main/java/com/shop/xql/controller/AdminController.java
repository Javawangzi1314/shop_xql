package com.shop.xql.controller;

import com.shop.xql.pojo.Admin;
import com.shop.xql.service.AdminService;
import com.shop.xql.util.CreateValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/4
 * @Description: com.cmfz.xql.controller 管理员模块
 * @version: 1.0
 */
@Slf4j
@Controller
@RequestMapping("Admin")//设置访问路径前缀
@SessionAttributes(value = {"localAdmin"})//设置sessiong共享
public class AdminController {
    @Autowired
    private AdminService adminService;//自动注入admin业务层接口类
    /**
    * 功能描述: * @param: 用户登录
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> queryByAccountAndPassword(Admin admin, String ValidateCode, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        log.info("管理员登录{}",admin);
        String id = request.getSession().getId();
        Map<String, Object> map1 = adminService.queryByAccountAndPassword(admin, id, ValidateCode.toLowerCase());
        System.out.println(admin);
        log.info("登录返回值{}",map1.get("msg"));
        String msg = (String) map1.get("msg");
        if (msg==null){
            request.getSession().setAttribute("localAdmin",map1.get("admin"));
            msg="ok";
        }
        map.put("data",msg);
        return map;
    }

    /**
    * 功能描述: * @param: 后台登录验证码生成
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("ValidateCode")
    @ResponseBody
    public String ValidateCode(HttpServletResponse response,HttpServletRequest request) throws IOException {
        CreateValidateCode createValidateCode = new CreateValidateCode();
        String code = createValidateCode.getString();
        log.info("生成验证码{}",code);
        String id = request.getSession().getId();
        request.getSession().setAttribute("code",code);
//        validateCodeService.insertValidateCode(code,id);
        BufferedImage image = createValidateCode.getImage();
        ServletOutputStream stream = response.getOutputStream();
        try {
            ImageIO.write(image,"PNG",stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * 功能描述: * @param: 后台退出登录
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("loginoutAdmin")
    public String loginoutAdmin(HttpSession session){
        session.removeAttribute("localAdmin");
        return "redirect:/backend/login.jsp";
    }

    }
