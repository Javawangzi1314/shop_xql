package com.shop.xql.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.shop.xql.mapper.ShopDao;
import com.shop.xql.mapper.TypeDao;
import com.shop.xql.pojo.Shop;
import com.shop.xql.pojo.Type;
import com.shop.xql.service.ShopService;
import com.shop.xql.service.TypeService;
import com.shop.xql.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/8
 * @Description: com.shop.xql.controller 商品模块控制层
 * @version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("Shop")//设置访问路径前缀
public class ShopController {

    @Autowired
    private ShopService shopService;//注入商品业务接口类
    @Autowired
    private TypeService typeService;//注入商品类型业务接口类
    @Autowired
    private ShopDao shopDao;//注入商品数据库接口类
    @Autowired
    private TypeDao typeDao;//注入商品类型数据库接口类
    /**
     * 功能描述: * @param: 查询所有商品
     * @return:
     * @auther:
     * @date:
     */
    @RequestMapping("queryAllShop")
    public ModelAndView queryAllShop(){
        ModelAndView mav = new ModelAndView();
        List<Shop> shopList = shopService.queryAll1Shop();

        List<Type> allType = typeService.queryAllType();
        mav.addObject("shopList",shopList);
        mav.addObject("allType",allType);
        mav.setViewName("forward:/backend/productManager.jsp");
        return mav;
    }
    /**
    * 功能描述: * @param: 插入一条商品
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("insertShop")
    @ResponseBody
    public String insertShop(Shop shop, MultipartFile file,HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String msg = shopService.insertShop(shop,file,realPath);
        return "";
    }
    /**
    * 功能描述: * @param: 查询商品
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("findShopById")
    @ResponseBody
    public Shop findShopById(Shop shop, HttpServletRequest request){
        Shop shop1 = shopService.findShopById(shop);
        return shop1;
    }
    /**
    * 功能描述: * @param: 修改商品
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("updateShop")
    @ResponseBody
    public String updateShop(Shop shop,MultipartFile file,HttpServletRequest request){

        String realPath = request.getSession().getServletContext().getRealPath("/");
        shopService.updateShop(shop,file,realPath);
        return "";
    }
    /**
    * 功能描述: * @param: 删除商品
    * @return: 
    * @auther:
    * @date:  
    */
    @RequestMapping("deleteShopById")
    @ResponseBody
    public String deleteShopById(Shop shop){
        log.info("删除商品类型{}",shop);
        shopService.deleteShopById(shop);
        return "";
    }
    /**
    * 功能描述: * @param: 商品Excel导出
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("LeadOutShop")
    @ResponseBody
    public String LeadOutShop(HttpServletResponse response, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        List<Shop> shopList = shopDao.selectAll();
        List<Shop> list1=new ArrayList<>();
        for (Shop shop : shopList) {
            shop.setShopPath(realPath+shop.getShopPath());
            list1.add(shop);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("皮皮磊商品Execle","商品大全"),
                Shop.class, list1);

        try {
            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("商品报表.xls", "utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
    * 功能描述: * @param: 商品Excel导入
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("LeadInShop")
    @ResponseBody
    public String LeadInShop(HttpServletRequest request, MultipartFile file){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println(realPath);
        shopService.LeadInShop(file,realPath);
        return "";
    }
    /**
    * 功能描述: * @param: 商品前台模糊查询
    * @return: 
    * @auther: 
    * @date:  
    */
    @RequestMapping("queryByTiao")
    public ModelAndView queryByTiao(String shopName,String maxshopPrice,String minshopPrice,String shopType){
        ModelAndView mav = new ModelAndView();
        PageUtil pageUtil = new PageUtil(1,3);
        System.out.println(shopName+maxshopPrice+minshopPrice+shopType);
        if(shopType.equals("all")){
            shopType=null;
        }
        List<Shop> shopList = shopService.queryByTiao(shopName,shopType);
        List<Type> typeList = typeDao.selectAll();
        mav.addObject("shopList",shopList);
        mav.addObject("typeList",typeList);
        mav.addObject("page", pageUtil);
        mav.setViewName("forward:/main.jsp");
        return mav;
    }

}
