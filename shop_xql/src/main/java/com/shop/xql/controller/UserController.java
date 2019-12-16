package com.shop.xql.controller;

import com.shop.xql.mapper.TypeDao;
import com.shop.xql.pojo.Address;
import com.shop.xql.pojo.Shop;
import com.shop.xql.pojo.Type;
import com.shop.xql.pojo.User;
import com.shop.xql.service.AddressService;
import com.shop.xql.service.ShopService;
import com.shop.xql.service.UserService;
import com.shop.xql.util.AllPageUtil;
import com.shop.xql.util.PageUtil;
import com.shop.xql.util.SendSms;
import io.goeasy.GoEasy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/10
 * @Description: com.shop.xql.controller 用户模块控制层
 * @version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("User")//设置访问路径前缀
public class UserController {
    @Autowired
    private UserService userService;//自动注入用户业务层接口
    @Autowired
    private ShopService shopService;//自动注入商品业务层接口
    @Autowired
    private AddressService addressService;//自动注入地址业务层接口
    @Autowired
    private TypeDao typeDao;//自动注入商品类型业务层接口
    /**
    * 功能描述: * @param: 查询所有用户
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("queryAllUser")
    public ModelAndView queryAllUser(Integer currentPageNum){
        ModelAndView mav = new ModelAndView();

        Integer Count = userService.queryAllUserCount();
        if(currentPageNum==null){
            currentPageNum=1;
        }
        AllPageUtil pageUtil = new AllPageUtil(currentPageNum,Count);
        List<User> list = userService.queryAllUser(pageUtil);
        mav.addObject("list",list);
        mav.addObject("page", pageUtil);
        mav.setViewName("forward:/backend/customerManager.jsp");
        return mav;
    }
    /**
    * 功能描述: * @param: 前台首页
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("index")
    public ModelAndView queryAllShop(Integer currentPageNum){
        ModelAndView mav = new ModelAndView();

        Integer Count = shopService.queryAllShopCount();
        if(currentPageNum==null){
            currentPageNum=1;
        }
        PageUtil pageUtil = new PageUtil(currentPageNum,Count);

        List<Shop> shopList = shopService.queryAllShop(pageUtil);
        List<Type> typeList = typeDao.selectAll();
        mav.addObject("shopList",shopList);
        mav.addObject("typeList",typeList);
        mav.addObject("page", pageUtil);
        mav.setViewName("forward:/main.jsp");
        return mav;
    }
    /**
    * 功能描述: * @param: 插入用户
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("insertUser")
    public String insertUser(User user, HttpSession session,String addAddress){
        User localUser =userService.insertUser(user,addAddress);
        session.setAttribute("localUser",localUser);
        GoEasy goEasy = new GoEasy("rest-hangzhou.goeasy.io","BC-78e854d33820422194e9e5700eadc9ae");
        goEasy.publish("shop_xql", "添加成功");
        return "redirect:/User/index";
    }
    /**
    * 功能描述: * @param: 用户登录
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("loginUser")
    public String loginUser(User user, HttpSession session){
        User localUser =userService.loginUser(user);
        if(localUser!=null){
            session.setAttribute("localUser",localUser);
        }
        return "redirect:/User/index";
    }

    /**
    * 功能描述: * @param: 用户手机验证码登录
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("phoneNum")
    @ResponseBody
    public String phoneNum(HttpSession session,String userPhone){
        String code = SendSms.achieveCode();
        SendSms.sendAuthCodeEmail(userPhone,code);
        System.out.println(code);
        session.setAttribute("code",code);
        return code;
    }
    /**
     * 功能描述: * @param: 验证码登录
     * @return:
     * @auther:
     * @date:
     */
    @RequestMapping("loginByphone")
    public String loginByphone(User user, HttpSession session){
        User localUser =userService.loginByphone(user);
        if(localUser!=null){
            session.setAttribute("localUser",localUser);
        }
        return "redirect:/User/index";
    }

    /**
    * 功能描述: * @param: 退出登录
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("loginOut")
    public String loginOut( HttpSession session){
            session.removeAttribute("localUser");
        return "redirect:/User/index";
    }

    /**
    * 功能描述: * @param: 个人中心页面
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("myCenter")
    public String myCenter(HttpSession session, HttpServletRequest request){
        Object obj = session.getAttribute("localUser");
        User user =null;
        if(obj!=null){
            user =(User)obj;
        }
        Address address = new Address();
        address.setAddUserId(user.getUserId());
        Address address1 = addressService.queryOne(address);
        session.setAttribute("address",address1);
        return "redirect:/center.jsp";
    }
    /**
    * 功能描述: * @param: 修改个人信息
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("updateMy")
    public String updateMy(HttpSession session, HttpServletRequest request,Address address,User user){
        Object obj = session.getAttribute("localUser");
        User user1 =null;
        if(obj!=null){
            user1 =(User)obj;
        }
        Address address3=userService.updateMy(user,user1,address);
        User user2=userService.queryOne(user1);
        session.setAttribute("address",address3);
        session.setAttribute("localUser",user2);
        return "redirect:/center.jsp";
    }
    /**
    * 功能描述: * @param: 修改头像
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("UserImage")
    @ResponseBody
    public String UserImage(MultipartFile file,HttpSession session,HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        Object obj = session.getAttribute("localUser");
        User user1 =null;
        if(obj!=null){
            user1 =(User)obj;
        }
        User user=userService.UserImage(user1,file,realPath);
        session.setAttribute("localUser",user);
        return "";
    }
    /**
    * 功能描述: * @param: 解冻用户
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("banUserById")
    @ResponseBody
    public String banUserById(String userId){
        userService.banUserById(userId);
        return "";
    }
    /**
    * 功能描述: * @param: 冻结用户
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("useUserById")
    @ResponseBody
    public String useUserById(String userId){
        userService.useUserById(userId);
        return "";
    }
    /**
    * 功能描述: * @param: 用户统计图
    * @return:
    * @auther:
    * @date:
    */
    @RequestMapping("queryTongji")
    @ResponseBody
    public Map<String,Integer> queryTongji(){
        Map<String,Integer> map = userService.queryTongji();
        return map;
    }
    /**
     * 功能描述: * @param: 用户地图统计图
     * @return:
     * @auther:
     * @date:
     */
    @RequestMapping("querychainTongji")
    @ResponseBody
    public Map<String,Integer> querychainTongji(){
        Map<String,Integer> map = userService.querychainTongji();
        return map;
    }

}
