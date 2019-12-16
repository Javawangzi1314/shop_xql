<%--
  Created by IntelliJ IDEA.
  User: 许清磊
  Date: 2019/12/4
  Time: 23:13
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
    <title>个人中心</title>
    <link rel="stylesheet" href="${path}/css/bootstrap.css" />
    <link rel="stylesheet" href="${path}/css/style.css" />
    <script src="${path}/js/jquery.min.js"></script>
    <script src="${path}/js/bootstrap.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#imagetou").click(function () {
                var form = new FormData(document.getElementById("tou"));
                $.ajax(
                    {
                        type:"POST",
                        url:"${path}/User/UserImage",
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
        })

    </script>
</head>

<body>
<div class="navbar navbar-default clear-bottom">
    <div class="container">
        <!-- logo start -->
        <div class="navbar-header">
            <a class="navbar-brand logo-style" href="">
                <img class="brand-img" src="images/com-logo1.png" alt="logo" height="70">
            </a>
        </div>
        <!-- logo end -->
        <!-- navbar start -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="${path}/User/index">商城主页</a>
                </li>
                <li>
                    <a href="${path}/Order/QueryMyOrder">我的订单</a>
                </li>
                <li>
                    <a href="cart.jsp">购物车</a>
                </li>
                <li class="active">
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
                <h3>基本资料</h3>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <form class="form-horizontal" action="${path}/User/updateMy">
        <div class="form-group">
            <label for="name" class="col-md-2  col-sm-2 control-label">用户姓名:</label>
            <div class="col-md-8 col-sm-10">
                <input type="text" class="form-control" name="userName" id="name" placeholder="用户姓名" readonly="readonly" value="${sessionScope.localUser.userName}">
            </div>
        </div>
        <div class="form-group">
            <label for="loginName" class="col-md-2 col-sm-2 control-label">登陆账号:</label>
            <div class="col-md-8  col-sm-10">
                <input type="text" class="form-control" name="userAccount" id="loginName" placeholder="登陆账号" readonly="readonly" value="${sessionScope.localUser.userAccount}">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-md-2  col-sm-2 control-label">联系电话:</label>
            <div class="col-md-8 col-sm-10">
                <input type="text" class="form-control" name="userPhone" id="phone" placeholder="联系电话" value="${sessionScope.localUser.userPhone}">
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-md-2   col-sm-2  control-label">联系地址:</label>
            <div class="col-md-8 col-sm-10">
                <input type="text" class="form-control" name="addAddress" id="address" placeholder="联系地址" value="${sessionScope.address.addAddress}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-warning">确认修改</button>
            </div>
        </div>
    </form>
</div>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header" style="margin-bottom: 0px;">
                <h3>修改头像</h3>
            </div>
        </div>
    </div>
    <form class="form-horizontal" id="tou">
        <div class="form-group">
            <label for="address" class="col-md-2   col-sm-2  control-label">选择头像:</label>
            <div class="col-md-10 col-sm-10">
                <img src="${path}${sessionScope.localUser.userPath}" id="showImg" class="togeImg" onclick="openFile()" alt="" width="100" height="100">
                <input id="file" type="file" style="display: none;" name="file" />
                <script>
                    function openFile() {
                        $("#file").click();
                    }
                    $('#file').change(function() {
                        $("#showImg").attr("src", window.URL.createObjectURL(this.files[0]));
                    });
                </script>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" id="imagetou" class="btn btn-warning">确认修改</button>
            </div>
        </div>
    </form>
</div>
<!-- content end-->
<!-- footers start -->
<div class="footers">

</div>
<!-- footers end -->

</body>

</html>
