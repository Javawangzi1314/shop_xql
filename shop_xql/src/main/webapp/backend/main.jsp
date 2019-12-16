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
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>在线商城-后台管理系统</title>
    <link rel="stylesheet"  href="${path}/css/bootstrap.css" />
    <link rel="stylesheet"  href="${path}/css/index.css" />
    <script src="${path}/js/jquery.js"></script>
    <script src="${path}/js/bootstrap.js"></script>
    <script src="${path}/js/userSetting.js"></script>
    <script type="text/javascript">
    $(function(){
    	// 点击切换页面
	     $("#product-type-set").click(function() {
            $("#frame-id").attr("src", "${path}/Type/queryAllType");
        });
        $("#product-set").click(function() {
            $("#frame-id").attr("src", "${path}/Shop/queryAllShop");
        });
        $("#user-set").click(function() {
            $("#frame-id").attr("src", "${path}/User/queryAllUser");
        });
        $("#manager-set").click(function() {
            $("#frame-id").attr("src", "${path}/Summary/QueryAllSummary");
        });
        $("#chain-set").click(function() {
            $("#frame-id").attr("src", "${path}/backend/user-chain.jsp");
        });
    });
    </script>
</head>

<body>
    <div class="wrapper-cc clearfix">
        <div class="content-cc">
            <!-- header start -->
            <div class="clear-bottom head">
                <div class="container head-cc">
                    <p>在线商城<span>后台管理系统</span></p>
                    <div class="welcome">
                        <div class="left">欢迎您：</div>
                        <div class="right">${localAdmin.adminId}</div>
                        <a href="${path}/Admin/loginoutAdmin"><div class="exit"><font style="color: red">退出</font></div></a>
                    </div>
                </div>
            </div>
            <!-- header end -->
            <!-- content start -->
            <div class="container-flude flude-cc" id="a">
                <div class="row user-setting">
                    <div class="col-xs-2 user-wrap">
                        <ul class="list-group">
                            <li class="list-group-item active" name="userSet" id="product-type-set">
                                <i class="glyphicon glyphicon-lock"></i> &nbsp;商品类型管理
                            </li>
                            <li class="list-group-item" name="userPic" id="product-set">
                                <i class="glyphicon glyphicon-facetime-video"></i> &nbsp;商品管理
                            </li>
                            <li class="list-group-item" name="userInfo" id="user-set">
                                <i class="glyphicon glyphicon-user"></i> &nbsp;客户管理
                            </li>
                            <li class="list-group-item" name="adminSet" id="manager-set">
                                <i class="glyphicon glyphicon-globe"></i> &nbsp;订单管理
                            </li>
                            <li class="list-group-item" name="chainSet" id="chain-set">
                                <i class="glyphicon glyphicon-globe"></i> &nbsp;全球用户分布
                            </li>
                        </ul>
                    </div>
                    <div class="col-xs-10" id="userPanel">
                        <iframe id="frame-id" src="${path}/Type/queryAllType" width="100%" height="100%" frameborder="0" scrolling="no">
                        </iframe>
                    </div>

                </div>
            </div>
            <!-- content end-->
        </div>
    </div>
    <!-- footers start -->
    <div class="footer">

    </div>
    <!-- footers end -->
</body>

</html>