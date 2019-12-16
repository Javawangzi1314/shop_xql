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
<c:set var="currentPageNum" value="${requestScope.page.currentPageNum}"></c:set>
<c:set var="totalRecords" value="${requestScope.page.totalPageNum}"></c:set>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>在线购物商城</title>
    <link rel="stylesheet" href="${path}/css/bootstrap.css" />
    <link rel="stylesheet" href="${path}/css/style.css" />
    <link rel="stylesheet" href="${path}/iconfont/iconfont.css">
    <link rel="stylesheet" href="${path}/css/jquery.pagination.css" />
    <script src="${path}/js/jquery.min.js"></script>
    <script src="${path}/js/bootstrap.js"></script>
    <script src="${path}/js/zshop.js"></script>
    <script type="text/javascript" src="${path}/js/jquery.pagination.min.js" rel="stylesheet"></script>
    <script type="text/javascript">

        function buyShop(id){
            $.ajax({
               url:"${path}/Cart/addCart?shopId="+id,
                success:function () {
                    alert("添加成功");
                },
                error:function () {
                    alert("添加失败");
                }
            });
        }
        $(function () {
            $("#pagination3").pagination({
                currentPage:${currentPageNum},
                totalPage: ${totalRecords},
                isShow: true,
                count: 4,
                homePageText: "首页",
                endPageText: "尾页",
                prevPageText: "上一页",
                nextPageText: "下一页",
                callback: function(current) {
                    $("#current3").text(current)
                }

            });
            $(".ui-pagination-page-item").on("click", function() {
                var vvv = $(this).text();
                if(vvv=='上一页'){
                    vvv =${currentPageNum}-1;
                }else if(vvv=='下一页'){
                    vvv =${currentPageNum}+1;
                }else if(vvv=='首页'){
                    vvv =1;
                }else if(vvv=='尾页'){
                    vvv=${totalRecords};
                }
                location.href="${path}/User/index?currentPageNum="+vvv;
            });
            $("#getPage").on("click", function() {
                var info = $("#pagination3").pagination("getPage");
                alert("当前页数：" + info.current + ",总页数：" + info.total);
            });

            $("#setPage").on("click", function() {
                $("#pagination3").pagination("setPage", 1, ${totalRecords});
            });


            $("#phoneNum").click(function () {
                var zhi = $("#userPhone1").val();
               $.ajax({
                   url:"${path}/User/phoneNum?userPhone="+zhi,
                   dataType:"json",
                   type:"GET",
                   success:function (result) {
                       alert(result);
                      $("#yanzhengma").attr("value",result);
                   }
               })
                setTime();//开始倒计时
            });

            $("#phonelogin").click(function () {
                var yzm =$("#yanzhengma").val();
                var shuru =$("#hashcode").val();
                if(shuru!=yzm){
                    alert("验证码输入错误");
                }else{
                    var zhi = $("#userPhone1").val();
                    window.location.href="${path}/User/loginByphone?userPhone="+zhi;
                }
            });

            $(".zoom").mouseover(function(e){
                var bigImg = $("<img id='bImg' src='"+$(this).attr("src")+"'/>");
                $(bigImg).css({
                    "top" : e.pageY + 5,
                    "left" : e.pageX + 5,
                    "position" : "absolute",
                    "height" : "200px"
                })
                $("body").append(bigImg);
            }).mouseout(function(){
                $("#bImg").remove();
            }).mousemove(function(e){
                $("#bImg").css({
                    "top" : e.pageY + 5,
                    "left" : e.pageX + 5,
                    "position" : "absolute",
                    "height" : "200px"
                })
            });

        });

        //60s倒计时实现逻辑
        var countdown = 60;
        function setTime() {
            if (countdown == 0) {
                $("#phoneNum").prop('disabled', false);
                $("#phoneNum").text("点击获取验证码");
                countdown = 60;//60秒过后button上的文字初始化,计时器初始化;
                return;
            } else {
                $("#phoneNum").prop('disabled', true);
                $("#phoneNum").text("("+countdown+"s)后重新发送") ;
                countdown--;
            }
            setTimeout(function() { setTime() },1000) //每1000毫秒执行一次
        }

    </script>
    <style type="text/css">

        button {
            display: inline-block;
            padding: 6px 12px;
            font-weight: 400;
            line-height: 1.42857143;
            text-align: center;
            vertical-align: middle;
            cursor: pointer;
            border: 1px solid transparent;
            border-radius: 4px;
            border-color: #28a4c9;
            color: #fff;
            background-color: #5bc0de;
            margin: 20px 20px 0 0;
        }

        .box {
            width: 800px;
            margin: 0px auto 0;
            height: 10px;
        }

        .page {
            width: 600px;
        }
        .fl {
            float: left;
        }
    </style>
</head>

<body>
<div id="wrapper">
    <!-- navbar start -->
    <div class="navbar navbar-default clear-bottom">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand logo-style" href="">
                    <img class="brand-img" src="${path}/images/com-logo1.png" alt="logo" height="70">
                </a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="${path}/User/index">商城主页</a>
                    </li>
                    <li>
                        <a href="${path}/Order/QueryMyOrder">我的订单</a>
                    </li>
                    <li>
                        <a href="${path}/cart.jsp">购物车</a>
                    </li>
                    <li class="dropdown">
                        <a href="${path}/User/myCenter">会员中心</a>
                    </li>
                </ul>

                <c:if test="${sessionScope.localUser==null}">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
                    </li>
                    <li>
                        <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
                    </li>

                </ul>
                </c:if>

                <c:if test="${sessionScope.localUser!=null}">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="userName">
                            您好：${sessionScope.localUser.userName}！
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                                <img class="img-circle" src="${path}${sessionScope.localUser.userPath}" height="30" />
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">
                                        <i class="glyphicon glyphicon-off"></i> <a href="${path}/User/loginOut">退出</a>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </c:if>
            </div>
        </div>
    </div>
    <!-- navbar end -->
    <!-- content start -->
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-header" style="margin-bottom: 0px;">
                    <h3>商品列表</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <form class="form-inline hot-search" action="${path}/Shop/queryByTiao">
                    <div class="form-group">
                        <label class="control-label">商品：</label>
                        <input type="text" class="form-control" name="shopName" placeholder="商品名称">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label class="control-label">种类：</label>
                        <select class="form-control input-sm" name="shopType">
                            <option value="all" selected="selected">查询全部</option>
                            <c:forEach items="${requestScope.typeList}" var="type">
                                <option value="${type.typeId}">${type.typeName}</option>
                            </c:forEach>

                        </select>
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <button type="submit" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="content-back">
        <div class="container" id="a">
            <div class="row">
                <c:forEach items="${requestScope.shopList}" var="shop">
                <div class="col-xs-3  hot-item">
                    <div class="panel clear-panel">
                        <div class="panel-body">
                            <div class="art-back clear-back">
                                <div class="add-padding-bottom">
                                    <img src="${path}${shop.shopPath}" class="zoom">
                                </div>
                                <h4><a href="">${shop.shopName}</a></h4>
                                <div class="user clearfix pull-right">
                                    ￥${shop.shopPrice}
                                </div>
                                <div class="desc">${shop.shopDis}
                                </div>
                                <a href="javascript:void(0)" onclick="buyShop('${shop.shopId}')">
                                <div class="attention pull-right" id="buyGif${shop.shopId}">
                                    加入购物车 <i class="icon iconfont icon-gouwuche"></i>
                                </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- content end-->
    <!-- footers start -->
    <div class="footers">
        <div class="box">
            <div id="pagination3" class="page fl"></div>
            <button id="getPage">getPage</button>
            <button id="setPage">setPage</button>
        </div>
    </div>
    <!-- footers end -->
</div>

<!-- 修改密码模态框 start -->
<div class="modal fade" id="modifyPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel1">修改密码</h4>
            </div>
            <form action="" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">原密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">重复密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-warning">修&nbsp;&nbsp;改</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 修改密码模态框 end -->

<!-- 登录模态框 start  -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <!-- 用户名密码登陆 start -->
        <div class="modal-content" id="login-account">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">用户名密码登录</h4>
            </div>
            <form action="${path}/User/loginUser" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input class="form-control" name="userName" type="text" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" name="userPassword" type="password" placeholder="请输入密码">
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <a class="btn-link">忘记密码？</a> &nbsp;
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <button type="submit" class="btn btn-warning">登&nbsp;&nbsp;陆</button> &nbsp;&nbsp;
                    <a class="btn-link" id="btn-sms-back">短信快捷登录</a>
                </div>
            </form>
        </div>
        <!-- 用户名密码登陆 end -->
        <!-- 短信快捷登陆 start -->
        <div class="modal-content" id="login-sms" style="display: none;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">短信快捷登陆</h4>
            </div>
            <form class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号：</label>
                        <div class="col-sm-6">
                            <input class="form-control" name="userPhone" id="userPhone1" type="text" placeholder="请输入手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">验证码：</label>
                        <div class="col-sm-4">
                            <input type="hidden" name="yzm" value="" id="yanzhengma"/>
                            <input class="form-control" type="text" id="hashcode" placeholder="请输入验证码">
                        </div>
                        <div class="col-sm-2">
                            <button class="pass-item-timer" id="phoneNum" type="button">发送验证码</button>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <a class="btn-link">忘记密码？</a> &nbsp;
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <button type="button" id="phonelogin" class="btn btn-warning">登&nbsp;&nbsp;陆</button> &nbsp;&nbsp;
                    <a class="btn-link" id="btn-account-back">用户名密码登录</a>
                </div>
            </form>
        </div>
        <!-- 短信快捷登陆 end -->
    </div>
</div>
<!-- 登录模态框 end  -->


<!-- 注册模态框 start -->
<div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">会员注册</h4>
            </div>
            <form action="${path}/User/insertUser" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户姓名:</label>
                        <div class="col-sm-6">
                            <input class="form-control" name="userName" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登陆账号:</label>
                        <div class="col-sm-6">
                            <input class="form-control" name="userAccount" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录密码:</label>
                        <div class="col-sm-6">
                            <input class="form-control" name="userPassword" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系电话:</label>
                        <div class="col-sm-6">
                            <input class="form-control" name="userPhone" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系地址:</label>
                        <div class="col-sm-6">
                            <input class="form-control" name="addAddress" type="text">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-warning">注&nbsp;&nbsp;册</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 注册模态框 end -->
</body>

</html>