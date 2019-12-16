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
    <title>订单详情</title>
    <link rel="stylesheet" href="${path}/css/bootstrap.css" />
    <link rel="stylesheet" href="${path}/css/style.css" />
    <script src="${path}/js/jquery.min.js"></script>
    <script src="${path}/js/bootstrap.js"></script>
</head>

<body>
<div class="navbar navbar-default clear-bottom">
    <div class="container">
        <!-- logo start -->
        <div class="navbar-header">
            <a class="navbar-brand logo-style">
                <img class="brand-img" src="${path}/images/com-logo1.png" alt="logo" height="70">
            </a>
        </div>
        <!-- logo end -->
        <!-- navbar start -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="main.html">商城主页</a>
                </li>
                <li>
                    <a href="myOrders.jsp">我的订单</a>
                </li>
                <li>
                    <a href="cart.jsp">购物车</a>
                </li>
                <li class="dropdown">
                    <a href="center.jsp">会员中心</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
                </li>
                <li>
                    <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
                </li>
                <li class="userName">
                    您好：user！
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                        <img class="img-circle" src="${path}/images/user.jpeg" height="30" />
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                                <i class="glyphicon glyphicon-cog"></i>修改密码
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="glyphicon glyphicon-off"></i> 退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- navbar end -->
    </div>
</div>
            <!-- navbar end -->
        </div>
    </div>
    <!-- content start -->
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-header" style="margin-bottom: 0px;">
                    <h3>我的订单</h3>
                </div>
            </div>
        </div>
        <div class="row head-msg">
            <div class="col-md-12">
                用户:<b><span>user</span></b>
            </div>
            <div class="col-md-12">
                订单: <b><span>123456</span></b>
            </div>
        </div>
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>序号</th>
                <th>商品名称</th>
                <th>商品图片</th>
                <th>商品数量</th>
                <th>商品总价</th>
            </tr>
            <tr>
                <td>1</td>
                <td>aaa</td>
                <td> <img src="images/hotaddress1.jpeg" alt="" width="60" height="60"></td>
                <td>2</td>
                <td>20</td>
            </tr>
            <tr>
                <td>1</td>
                <td>aaa</td>
                <td> <img src="images/hotaddress1.jpeg" alt="" width="60" height="60"></td>
                <td>2</td>
                <td>20</td>
            </tr>
            <tr>
                <td>1</td>
                <td>aaa</td>
                <td> <img src="images/hotaddress1.jpeg" alt="" width="60" height="60"></td>
                <td>2</td>
                <td>20</td>
            </tr>
            <tr>
                <td colspan="5" class="foot-msg">
                    共<b><span>10</span></b>条&nbsp; &nbsp; 总计
                    <b><span>1000</span></b>元
                </td>
            </tr>
        </table>
    </div>
    <!-- content end-->
    <!-- footers start -->
    <div class="footers">

    </div>

</body>

</html>