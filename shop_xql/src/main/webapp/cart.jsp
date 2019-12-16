<%--
  Created by IntelliJ IDEA.
  User: 许清磊
  Date: 2019/12/4
  Time: 23:10
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
    <title>我的购物车</title>
    <link rel="stylesheet" href="${path}/css/bootstrap.css" />
    <link rel="stylesheet" href="${path}/css/style.css" />
    <script src="${path}/js/jquery.min.js"></script>
    <script src="${path}/js/bootstrap.js"></script>
    <script type="text/javascript">
        function onChange(id){
            var reg =/^[0-9]+$/;
            if(reg.test($("#bian"+id).val())==false){
                alert("非法字符集");
                $("#bian"+id).val("");
                return;
            }
            var ku =$("#ku"+id).val();
            var count=$("#bian"+id).val();
            if(parseInt(count)>parseInt(ku)){
                alert("库存不足，您购买的数量大于库存数量");
                $("#bian"+id).val("");
                return;
            }
            location.href="${path}/Cart/updateCart?bookId="+id+"&count="+count;
        }

        $(function(){
            $("#pishan").click(function(){
                var arr = new Array();
                $("input[type='checkbox']:checked").each(function(i){
                    arr[i] = $(this).val();
                });
                if(arr[0]==undefined){
                    alert("请至少勾选一件删除商品");
                }
                if(arr[0]!=undefined){
                    window.location.href="${path}/Cart/allDelete?arr="+arr;
                }
            });
        });
        function jiezhang(){
            var arr = new Array();
            $("input[type='checkbox']:checked").each(function(i){
                arr[i] = $(this).val();
            });
            if(arr[0]==undefined){
                alert("请至少选择一件结账商品");
            }
            if(arr[0]!=undefined){
                window.location.href="${path}/Cart/BuyCart?arr="+arr;
            }
        }
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
                alert(yzm);
                var shuru =$("#hashcode").val();
                if(shuru!=yzm){
                    alert("验证码输入错误");
                }else{
                    var zhi = $("#userPhone1").val();
                    window.location.href="${path}/User/loginByphone?userPhone="+zhi;
                }
            });

        })



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
</head>

<body>
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
                <li>
                    <a href="${path}/User/index">商城主页</a>
                </li>
                <li>
                    <a href="${path}/Order/QueryMyOrder">我的订单</a>
                </li>
                <li  class="active">
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
            <th>

            </th>
            <th>序号</th>
            <th>商品名称</th>
            <th>商品图片</th>
            <th>商品数量</th>
            <th>商品总价</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${sessionScope.localCart}" var="map">
            <c:if test="${pageScope.map.value.status }">
        <tr>
            <td>
                <input type="checkbox" value="${pageScope.map.value.shop.shopId }">
            </td>
            <td>${pageScope.map.value.shop.shopId }</td>
            <td>${pageScope.map.value.shop.shopName }</td>
            <td> <img src="${path}${pageScope.map.value.shop.shopPath }" alt="" width="60" height="60"></td>
            <td>
                <input type="text" id="bian${pageScope.map.value.shop.shopId}" value="${pageScope.map.value.count }" size="5">
            </td>
            <td>${pageScope.map.value.countPrice }</td>
            <td>
                <input id="ku${pageScope.map.value.shop.shopId}" type="hidden" value="${pageScope.map.value.shop.shopCount}"/>
                <a href="#" onclick="onChange('${pageScope.map.value.shop.shopId }')"><button class="btn btn-success" type="button"> <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改</button></a>
                <a href="${path }/Cart/allDelete?arr=${pageScope.map.value.shop.shopId}"><button class="btn btn-danger" type="button">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                </button></a>
            </td>
        </tr>
            </c:if>
        </c:forEach>
        <tr>
            <td colspan="7" align="right">
                <button class="btn btn-warning margin-right-15" id="pishan" type="button"> 删除选中项</button>
                <a href="${path}/Cart/xiaohui"><button class="btn btn-warning  margin-right-15" type="button"> 清空购物车</button></a>
                <a href="${path}/User/index"><button class="btn btn-warning margin-right-15" type="button"> 继续购物</button></a>
                <a name='checkout' href='javascript:void(0)' onclick="jiezhang()" >
                <button class="btn btn-warning " type="button"> 结算</button>
                </a>
            </td>
        </tr>
        <tr>
            <td colspan="7" align="right" class="foot-msg">
                总计： <b><span>${sessionScope.allPrice}</span> </b>元
            </td>
        </tr>
    </table>
</div>
<!-- content end-->
<!-- footers start -->
<div class="footers">

</div>
<!-- footers end -->
<!-- 修改密码模态框 -->

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