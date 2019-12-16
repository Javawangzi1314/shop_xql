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
    <script src="${path}/js/jquery.js"></script>
    <script src="${path}/js/bootstrap.js"></script>
    <script src="${path}/js/userSetting.js"></script>
    <script type="text/javascript">

        function queryallOrder(id){
            $.ajax(
                {
                    type:"GET",
                    url:"${path}/Order/queryOne?orderId="+id,
                    success:function(result){
                        $("#ordertable tr:not(:first)").empty(); //清空table（除了第一行以外）
                        $.each(result, function (index, value) {  //arrTmp数组数据
                            $('#tb22').append($('<tr><td>'+value.sumShopname+'</td><td>'+'<img src=${path}'+value.sumAddress+' width=50px></td><td>'+value.sumNum+'</td><td>'+value.sumNum*value.sumPrice+'</td></tr>'));
                        });

                    },
                    error:function(){
                        alert("异常");
                    }
                }
            )
        }

    </script>
</head>

<body>

    <!-- 订单管理 -->
    <div class="panel panel-default" id="adminSet">
        <div class="panel-heading">
            <h3 class="panel-title">订单管理</h3>
        </div>
        <div class="panel-body">

            <div class="show-list" style="position: relative; top: 10px;">
                <table class="table table-bordered table-hover" style='text-align: center;'>
                    <thead>
                        <tr class="text-danger">
                            <th class="text-center">订单序号</th>
                            <th class="text-center">订单金额总计</th>
                            <th class="text-center">订单状态</th>
                            <th class="text-center">收件人编号</th>
                            <th class="text-center">收件人姓名</th>
                            <th class="text-center">收件人地址编号</th>
                            <th class="text-center">收件人详细地址</th>
                            <th class="text-center">创建时间</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                    <c:forEach var="summary" items="${requestScope.showlist}">
                        <tr>
                            <td>${summary.sumId}</td>
                            <td>${summary.sumMoney}元</td>
                            <td>${summary.sumStatus}</td>
                            <td>${summary.sumUserId}</td>
                            <td>${summary.sumName}</td>
                            <td>${summary.sumAddId}</td>
                            <td>${summary.sumAddress}</td>
                            <td>${summary.sumCreatdate}</td>
                            <td class="text-center">
                                <input type="button" onclick="queryallOrder('${summary.sumId}')" class="btn btn-warning btn-sm doMangerModify" value="详情"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <!-- 订单详情 start -->
    <div class="modal fade" tabindex="-1" id="myModal-Manger">
        <!-- 窗口声明 -->
        <div class="modal-dialog">
            <!-- 内容声明 -->
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">订单详情</h4>
                </div>

                <table class="table table-bordered table-hover" id="ordertable" style='text-align: center;'>
                    <thead>
                    <tr class="text-danger">
                        <th class="text-center">商品名称</th>
                        <th class="text-center">商品图片</th>
                        <th class="text-center">商品数量</th>
                        <th class="text-center">小计</th>
                    </tr>
                    </thead>
                    <tbody id="tb22">

                    </tbody>
                </table>

                <div class="modal-footer">
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 修改系统用户 end -->

</body>

</html>