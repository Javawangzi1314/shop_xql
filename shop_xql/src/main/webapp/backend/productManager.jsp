<%--
Created by IntelliJ IDEA.
User: 许清磊
Date: 2019/12/4
Time: 23:15
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet"  href="${path}/css/bootstrap.css" />
    <link rel="stylesheet"  href="${path}/css/index.css" />
    <link rel="stylesheet"  href="${path}/css/file.css" />
    <script src="${path}/js/jquery-1.8.3.min.js"></script>
    <script src="${path}/js/jquery.js"></script>
    <script src="${path}/js/bootstrap.js"></script>
    <script src="${path}/js/userSetting.js"></script>

    <script>
        $(function () {
            //上传图像预览
            $('#product-image').on('change',function() {
                $('#img').attr('src', window.URL.createObjectURL(this.files[0]));
            });
            $('#pro-image').on('change',function() {
                $('#img2').attr('src', window.URL.createObjectURL(this.files[0]));
            });
            $("#addShop").click(function () {
                var form = new FormData(document.getElementById("addShopdata"));
                $.ajax(
                    {
                        type:"POST",
                        url:"${path}/Shop/insertShop",
                        data:form,
                        processData: false,    //不需要对数据做任何预处理
                        contentType: false,    //不设置数据格式
                        success:function (result) {
                            location.reload(true);
                            window.location.reload();
                        },
                        error:function () {

                        }
                    }
                );
            });
            $("#updateShop").click(function () {
                var form = new FormData(document.getElementById("updateShopdata"));
                $.ajax(
                    {
                        type:"POST",
                        url:"${path}/Shop/updateShop",
                        data:form,
                        processData: false,    //不需要对数据做任何预处理
                        contentType: false,    //不设置数据格式
                        success:function (result) {
                            location.reload(true);
                            window.location.reload();
                        },
                        error:function () {

                        }
                    }
                );
            });

            $("#LeadOut").click(function () {
                window.location.href="${path}/Shop/LeadOutShop"
            })
            
            
            $("#LeadIn").click(function () {
                var form = new FormData(document.getElementById("loginIn"));
                $.ajax(
                    {
                        type:"POST",
                        url:"${path}/Shop/LeadInShop",
                        data:form,
                        processData: false,    //不需要对数据做任何预处理
                        contentType: false,    //不设置数据格式
                        success:function(result){
                           alert("成功");
                            location.reload(true);
                            window.location.reload();
                        },
                        error:function(){
                            alert("异常");
                        }
                    }
                )

            })
            
        });

        function updateShop1(id) {
            $.ajax(
                {
                    type:"GET",
                    url:"${path}/Shop/findShopById?shopId="+id,
                    success:function (result) {
                        $("#pro-num").val(result.shopId);
                        $("#pro-name").val(result.shopName);
                        $("#pro-price").val(result.shopPrice);
                        $("#img2").attr("src","${path}"+result.shopPath);
                        $("#pro-type").val(result.shopType);
                    },
                    error:function () {

                    }
                }
            );
        }
        function deleteShop(id) {
            $.ajax(
                {
                    type:"GET",
                    url:"${path}/Shop/deleteShopById?shopId="+id,
                    success:function (result) {
                        location.reload(true);
                        window.location.reload();
                    },
                    error:function () {

                    }
                }
            );
        }


    </script>
</head>

<body>
    <div class="panel panel-default" id="userPic">
        <div class="panel-heading">
            <h3 class="panel-title">商品管理</h3>
        </div>
        <div class="panel-body">
            <input type="button" value="添加商品" class="btn btn-primary" id="doAddPro">
            <input type="button" value="Execl导出" class="btn btn-primary" id="LeadOut">
            <hr/>
            <form class="form-horizontal" id="loginIn" role="form">
                <input type="file" class="form-control" name="file" id="file" required="required">
                <input type="button" value="Execl导入" class="btn btn-primary" id="LeadIn">
            </form>
            <hr/>

            <div class="show-list">
                <table class="table table-bordered table-hover" style='text-align: center;'>
                    <thead>
                        <tr class="text-danger">
                            <th class="text-center">编号</th>
                            <th class="text-center">商品</th>
                            <th class="text-center">图片</th>
                            <th class="text-center">价格</th>
                            <th class="text-center">产品类型编号</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                        <c:forEach var="shop" items="${requestScope.shopList}">
                        <tr>
                            <td>${shop.shopId}</td>
                            <td>${shop.shopName}</td>
                            <td><img src="${path}${shop.shopPath}" width="50px"></td>
                            <td>${shop.shopPrice}</td>
                            <td>${shop.shopType}</td>
                            <td>${shop.shopStatus}</td>
                            <td class="text-center">
                                <input type="button" class="btn btn-warning btn-sm doProModify" onclick="updateShop1('${pageScope.shop.shopId}')" value="修改">
                                <input type="button" class="btn btn-warning btn-sm doProDelete" onclick="deleteShop('${pageScope.shop.shopId}')" value="删除">
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- 添加商品 start -->     
    <div class="modal fade" tabindex="-1" id="Product">
        <!-- 窗口声明 -->
        <div class="modal-dialog modal-lg">
            <!-- 内容声明 -->
            <form action="#" method="post" id="addShopdata" enctype="multipart/form-data">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加商品</h4>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <div class="form-group">
                            <label for="product-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="shopName" id="product-name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="shopPrice" id="product-price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-disc" class="col-sm-4 control-label">商品描述：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="shopDis" id="pro-disc">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-8">
                                <a href="javascript:;" class="file">选择文件
                                    <input type="file" name="file" id="product-image">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" name="shopType">
                                    <c:forEach items="${requestScope.allType}" var="type">
                                    <option value="${type.typeId}">${type.typeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>  
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 160px;height: 180px;" id="img">
                    </div>  
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="addShop">添加</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
            </form>
        </div>
    </div>
    <!-- 添加商品 end -->  
    
    <!-- 修改商品 start -->  
    <div class="modal fade" tabindex="-1" id="myProduct">
        <!-- 窗口声明 -->
        <div class="modal-dialog modal-lg">
            <!-- 内容声明 -->
            <form action="#" method="post" id="updateShopdata" enctype="multipart/form-data">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改商品</h4>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <div class="form-group">
                            <label for="pro-num" class="col-sm-4 control-label">商品编号：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="shopId" id="pro-num" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="shopName" id="pro-name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="shopPrice" id="pro-price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-8">
                                <a class="file">
                                    选择文件 <input type="file" name="file" id="pro-image">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="pro-type" name="shopType">
                                    <c:forEach items="${requestScope.allType}" var="type">
                                        <option value="${type.typeId}">${type.typeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 160px;height: 180px;" id="img2">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary updatePro" id="updateShop">修改</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
            </form>
        </div>
    <!-- 修改商品 end -->
</body>

</html>