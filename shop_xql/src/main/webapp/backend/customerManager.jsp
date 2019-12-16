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
    <title>backend</title>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <link rel="stylesheet"  href="${path}/css/bootstrap.css" />
    <link rel="stylesheet"  href="${path}/css/index.css" />
    <link rel="stylesheet" href="${path}/css/jquery.pagination.css" />
    <script src="${path}/js/jquery.js"></script>
    <script src="${path}/js/bootstrap.js"></script>
    <script src="${path}/js/userSetting.js"></script>
    <script src="${path}/js/echarts.js"></script>
    <script type="text/javascript" src="${path}/js/jquery.pagination.min.js" rel="stylesheet"></script>
    <script type="text/javascript">
        $(function () {
            $("#pagination3").pagination({
                currentPage: ${currentPageNum},
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
                location.href="${path}/User/queryAllUser?currentPageNum="+vvv;
            });
            $("#getPage").on("click", function() {
                var info = $("#pagination3").pagination("getPage");
                alert("当前页数：" + info.current + ",总页数：" + info.total);
            });

            $("#setPage").on("click", function() {
                $("#pagination3").pagination("setPage", 1, ${totalRecords});
            });

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            $.ajax({
                url:'${path}/User/queryTongji',
                type:'post',
                datatype:'json',
                success:function (result) {
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '皮皮磊商城近三周注册动态统计',
                            link:'http://localhost:8989/shop/User/index',
                            textStyle:{
                                color:'#ff4236'
                            }
                        },
                        tooltip: {},
                        legend: {
                            data:['男','女']
                        },
                        xAxis: {
                            data: ["近一周","近两周","近三周"]
                        },
                        yAxis: {},
                        series: [{
                            name: '男',
                            type: 'bar',
                            data:[result.nan1,result.nan2,result.nan3]
                        },{
                            name: '女',
                            type: 'bar',
                            data:[result.nv1,result.nv2,result.nv3]
                        }]
                    };
                    myChart.setOption(option);
                }
            })
            var goEasy = new GoEasy({
                host:'hangzhou.goeasy.io',
                appkey: "BC-78e854d33820422194e9e5700eadc9ae"
            });
            goEasy.subscribe({
                channel: "shop_xql", //替换为您自己的channel
                onMessage: function (message) {
                    $.ajax({
                        url:'${path}/User/queryTongji',
                        type:'post',
                        datatype:'json',
                        success:function (result) {
                            var option = {
                                series: [{
                                    data:[result.nan1,result.nan2,result.nan3]
                                },{
                                    data:[result.nv1,result.nv2,result.nv3]
                                }]
                            }
                            myChart.setOption(option);
                        }
                    })
                }
            })
        })
        function banUser(id) {
            $.ajax(
                {
                    type:"GET",
                    url:"${path}/User/banUserById?userId="+id,
                    success:function (result) {
                        location.reload(true);
                        window.location.reload();
                    },
                    error:function () {

                    }
                }
            );
        }
        function useUser(id) {
            $.ajax(
                {
                    type:"GET",
                    url:"${path}/User/useUserById?userId="+id,
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
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 800px;height:300px;"></div>

    <div class="panel panel-default" id="userInfo" id="homeSet">
        <div class="panel-heading">
            <h3 class="panel-title">客户管理</h3>
        </div>
        <div class="panel-body">

            
            <div class="show-list" style="position: relative;top: 30px;">
                <table class="table table-bordered table-hover" style='text-align: center;'>
                    <thead>
                        <tr class="text-danger">
                            <th class="text-center">序号</th>
                            <th class="text-center">姓名</th>
                            <th class="text-center">头像</th>
                            <th class="text-center">帐号</th>
                            <th class="text-center">电话</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                    <c:forEach items="${requestScope.list}" var="user">
                        <tr>
                            <td>${pageScope.user.userId}</td>
                            <td>${pageScope.user.userName}</td>
                            <td><img src="${path}${pageScope.user.userPath}" width="50px"></td>
                            <td>${pageScope.user.userAccount}</td>
                            <td>${pageScope.user.userPhone}</td>
                            <td>${pageScope.user.userStatus}</td>
                            <td class="text-center">
                                <c:if test="${pageScope.user.userStatus=='冻结'}">
                                    <input type="button" class="btn btn-success btn-sm doProDisable" onclick="useUser('${pageScope.user.userId}')" value="恢复">
                                </c:if>
                                <c:if test="${pageScope.user.userStatus=='正常'}">
                                    <input type="button" class="btn btn-danger btn-sm doProTypeDisable" onclick="banUser('${pageScope.user.userId}')" value="冻结">
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td  colspan="7">
                            <div class="box">
                                <div id="pagination3" class="page fl"></div>
                                <button id="getPage">getPage</button>
                                <button id="setPage">setPage</button>
                            </div></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- 修改客户信息 start -->
    <div class="modal fade" tabindex="-1" id="myModal">
        <!-- 窗口声明 -->
        <div class="modal-dialog">
            <!-- 内容声明 -->
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改客户</h4>
                </div>
                <div class="modal-body text-center">
                    <div class="row text-right">
                        <label for="id" class="col-sm-4 control-label">编号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="id">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="name" class="col-sm-4 control-label">姓名：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="loginName" class="col-sm-4 control-label">帐号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="loginName">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="phone" class="col-sm-4 control-label">电话：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="phone">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="adrees" class="col-sm-4 control-label">地址：</label>
                        <div class="col-sm-4">
                            <input type="email" class="form-control" id="adrees">
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary updateOne">修改</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 修改客户信息 end -->
</body>

</html>