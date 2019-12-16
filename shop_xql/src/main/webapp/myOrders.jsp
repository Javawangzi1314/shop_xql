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
    <title>我的订单</title>
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
                <a class="navbar-brand logo-style" href="http://edu.51cto.com/center/lec/info/index?user_id=12392007">
                        <img class="brand-img" src="${path}/images/com-logo1.png" alt="logo" height="70">
                    </a>
            </div>
            <!-- logo end -->
            <!-- navbar start -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${path}/User/index">商城主页</a>
                    </li>
                    <li class="active">
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
        <table class="table table-hover   orderDetail">
            <c:if test="${sessionScope.orderList.size()>0}">
            <c:forEach items="${sessionScope.orderList}" var="order">
            <tr>
                <td><img src="${path}${order.sumAddress}" alt=""></td>
                <td class="order-content">
                    <p>
                        ${order.sumShopname}
                    </p>
                    <p>颜色：单件粉色上衣</p>
                    <p>尺码：s</p>
                </td>
                <td>
                    ￥${order.sumPrice}
                </td>
                <td>
                    ${order.sumNum}
                </td>
                <td class="text-color">
                    ￥${order.sumPrice*order.sumNum}
                </td>
            </tr>

            <tr>
                <td colspan="5">
                    <span class="pull-right"><button class="btn btn-danger"><a href="${path}/Order/DeleteMyOrder?sumSumId=${order.sumSumId}">删除订单</a></button></span>
                </td>
            </tr>
            </c:forEach>
            </c:if>
        </table>
    </div>
    <!-- content end-->
    <!-- footers start -->
    <div class="footers">

    </div>
    <!-- footers end -->

</body>

</html>