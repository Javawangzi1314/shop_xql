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
    <title>确认订单</title>
    <link rel="stylesheet" href="${path}/css/bootstrap.css" />
    <link rel="stylesheet" href="${path}/css/style.css" />
    <script src="${path}/js/jquery.min.js"></script>
    <script src="${path}/js/bootstrap.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#summary").click(function () {
                $.ajax({
                    url:"${path}/Summary/insertSummary",
                    success:function (result) {
                        $("#bianhao").html(result);
                    }
                });
            });
        });

    </script>
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
                    <h3>我的购物车</h3>
                </div>
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

            <c:forEach var="map" items="${sessionScope.BuyCart}"  begin="0" step="1"  varStatus="i">>
            <tr>
                <td>${i.count}</td>
                <td>${map.value.shop.shopName }</td>
                <td><img src="${path}${map.value.shop.shopPath }" alt="" width="60" height="60"></td>
                <td>${map.value.count }</td>
                <td>${map.value.countPrice }</td>
            </tr>
            </c:forEach>
            <tr>
                <td colspan="5" class="foot-msg">
                    总计：<b> <span>${sessionScope.CartPrice}</span></b>元
                    <a href="${path}/cart.jsp">
                        <button class="btn btn-warning pull-right ">返回</button>
                    </a>
                    <button class="btn btn-warning pull-right margin-right-15" id="summary" data-toggle="modal" data-target="#buildOrder">生成订单</button>
                    <a href="${path}/User/index"><button class="btn btn-warning margin-right-15" type="button"> 继续购物</button></a>
                </td>
            </tr>
        </table>
    </div>
    <!-- content end-->
    <div class="modal fade" id="buildOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">提示消息</h4>
                </div>
                <div class="orderMsg">
                    <p>
                        订单生成成功！！
                    </p>
                    <p>
                        订单号：<span id="bianhao"></span>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!-- footers start -->
    <div class="footers">

    </div>
    <!-- footers end -->
    <!-- 修改密码模态框 -->
    <div class="modal fade" id="modifyPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">修改密码</h4>
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
    <!-- 登录模态框 -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel6">登&nbsp;陆</h4>
                </div>
                <form action="" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">登录帐号：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">密码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">验证码：</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                            <div class="col-sm-2 input-height">
                                1234
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                        <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                        <button type="submit" class="btn btn-warning">登&nbsp;&nbsp;陆</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- 注册模态框 -->
    <div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel8">会员注册</h4>
                </div>
                <form action="" class="form-horizontal" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户姓名:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">登陆账号:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">登录密码:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">联系电话:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">联系地址:</label>
                            <div class="col-sm-6">
                                <input class="form-control" type="text">
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
</body>

</html>