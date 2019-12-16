package com.shop.xql.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.shop.xql.mapper.ShopDao;
import com.shop.xql.pojo.Shop;
import com.shop.xql.pojo.Type;
import com.shop.xql.pojo.User;
import com.shop.xql.service.ShopService;
import com.shop.xql.util.PageUtil;
import com.shop.xql.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/8
 * @Description: com.shop.xql.service.impl 商品业务类实现类
 * @version: 1.0
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)//事务注解
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;//自动注入商品数据库接口
    /**
    * 功能描述: * @param: 查询所有商品
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public List<Shop> queryAllShop(PageUtil pageUtil) {
        Shop shop = new Shop();
        RowBounds rowsBounds = new RowBounds(pageUtil.getStartIndex(),pageUtil.getPageSize());
        List<Shop> list = shopDao.selectByRowBounds(shop, rowsBounds);
        return list;
    }
    /**
     * 功能描述: * @param: 插入商品
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public String insertShop(Shop shop, MultipartFile file,String realPath) {
        java.io.File file1 = new  java.io.File(realPath+"upload");
        if(!file1.exists()){
            file1.mkdir();
        }
        //获取源文件名称
        String fileName = file.getOriginalFilename();
        //获取源文件类型
        String fileType = file.getContentType();
        //或追
        String houzhui = FilenameUtils.getExtension(fileName);

        //新名称
        String fileNamew = (UUID.randomUUID().toString()+"."+houzhui);
        try {
            file.transferTo(new java.io.File(file1.getPath(),fileNamew));
            shop.setShopId(UUIDUtil.getshortUUID());
            shop.setShopCount(9996);
            shop.setShopPath("\\upload"+"\\"+fileNamew);
            shop.setShopStatus("有效商品");
            log.info("插入一条商品",shop);
            System.out.println(shop);
            shopDao.insert(shop);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 功能描述: * @param: 查询一条商品
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public Shop findShopById(Shop shop) {
        return shopDao.selectOne(shop);
    }
    /**
     * 功能描述: * @param: 修改商品
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public void updateShop(Shop shop,MultipartFile file,String realPath) {
        log.info("修改商品{}",shop);
        if(file.isEmpty()){
            shopDao.updateByPrimaryKeySelective(shop);
        }else{
            java.io.File file1 = new  java.io.File(realPath+"upload");
            if(!file1.exists()){
                file1.mkdir();
            }
            //获取源文件名称
            String fileName = file.getOriginalFilename();
            //获取源文件类型
            String fileType = file.getContentType();
            //或追
            String houzhui = FilenameUtils.getExtension(fileName);

            //新名称
            String fileNamew = (UUID.randomUUID().toString()+"."+houzhui);
            try {
                file.transferTo(new java.io.File(file1.getPath(),fileNamew));
                shop.setShopPath("\\upload"+"\\"+fileNamew);
                shopDao.updateByPrimaryKeySelective(shop);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * 功能描述: * @param: 删除商品
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public void deleteShopById(Shop shop) {
        shopDao.deleteByPrimaryKey(shop);
    }
    /**
     * InputStream 转 File
     * @param ins
     * @param file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
    * 功能描述: * @param: 导入execle表格
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public void LeadInShop(MultipartFile file, String realPath) {
        ImportParams params = new ImportParams();
        params.setNeedSave(true);
        params.setTitleRows(1);
        params.setHeadRows(1);
        long start = new Date().getTime();
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            try {
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<Shop> list = ExcelImportUtil.importExcel(
                toFile,
                Shop.class, params);

        System.out.println(list.size());
        toFile.delete();
        for (Shop shop : list) {
            try {
                String substring = realPath.substring(0, 2);
                System.out.println(substring);
                InputStream in=new FileInputStream(substring+"\\"+shop.getShopPath());
                byte[] b= new byte[0];
                b = new byte[in.available()];
                in.read(b);
                String s = shop.getShopPath().substring(shop.getShopPath().lastIndexOf("\\"));
                System.out.println(s);
                OutputStream out=new FileOutputStream(realPath+"\\save"+"\\"+s);
                out.write(b);
                in.close();
                out.close();
                File file2 = new File(substring+"\\"+shop.getShopPath());
                boolean b1 = file2.delete();
                System.out.println(b1);
                shop.setShopId(UUIDUtil.getshortUUID());
                shop.setShopPath("\\save"+"\\"+s);
                shop.setShopStatus("execl导入商品");
                shopDao.insert(shop);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
    * 功能描述: * @param: 查询所有商品数量
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public Integer queryAllShopCount() {
        Shop shop = new Shop();
        return shopDao.selectCount(shop);
    }

    @Override
    public List<Shop> queryAll1Shop() {
        return shopDao.selectAll();
    }
    /**
    * 功能描述: * @param: 前台模糊查询
    * @return: 
    * @auther: 
    * @date:  
    */
    @Override
    public List<Shop> queryByTiao(String shopName,String shopType) {

        return shopDao.queryByTiao("%"+shopName+"%",shopType);
    }
}
