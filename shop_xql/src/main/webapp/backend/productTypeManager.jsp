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
        $(function () {
            $("#addType").click(function () {
                $.ajax(
                    {
                        type:"POST",
                        url:"${path}/Type/insertType",
                        data:$("#addTypedata").serialize(),
                        success:function (result) {
                            if(result!=""){

                            }
                            location.reload(true);
                            window.location.reload();
                        },
                        error:function () {

                        }
                    }
                );
            });
            $("#updateType").click(function () {
                $.ajax(
                    {
                        type:"POST",
                        url:"${path}/Type/updateType",
                        data:$("#updateTypedata").serialize(),
                        success:function (result) {
                            if(result!=""){

                            }
                            location.reload(true);
                            window.location.reload();
                        },
                        error:function () {

                        }
                    }
                );
            });

        });
        function deleteType(id) {
            $.ajax(
                {
                    type:"GET",
                    url:"${path}/Type/deleteTypeById?typeId="+id,
                    success:function (result) {
                        location.reload(true);
                        window.location.reload();
                    },
                    error:function () {

                    }
                }
            );
        }

        function useType(id) {
            $.ajax(
                {
                    type:"GET",
                    url:"${path}/Type/useTypeById?typeId="+id,
                    success:function (result) {
                        location.reload(true);
                        window.location.reload();
                    },
                    error:function () {

                    }
                }
            );
        }

        function banType(id) {
            $.ajax(
                {
                    type:"GET",
                    url:"${path}/Type/banTypeById?typeId="+id,
                    success:function (result) {
                        location.reload(true);
                        window.location.reload();
                    },
                    error:function () {

                    }
                }
            );
        }

        function updateType(id) {
            $.ajax(
                {
                    type:"GET",
                    url:"${path}/Type/findTypeById?typeId="+id,
                    success:function (result) {
                       $("#proTypeNum").val(result.typeId);
                       $("#proTypeName").val(result.typeName);
                    },
                    error:function () {

                    }
                }
            );
        }
    </script>
</head>

<body>
    <div class="panel panel-default" id="userSet">
        <div class="panel-heading">
            <h3 class="panel-title">商品类型管理</h3>
        </div>
        <div class="panel-body">
            <input type="button" value="添加商品类型" class="btn btn-primary" id="doAddProTpye">
            <br>
            <br>
            <div class="show-list">
                <table class="table table-bordered table-hover" style='text-align: center;'>
                    <thead>
                        <tr class="text-danger">
                            <th class="text-center">编号</th>
                            <th class="text-center">类型名称</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                    <c:forEach var="type" items="${requestScope.typeList}">
                        <tr>
                            <td>${pageScope.type.typeId}</td>
                            <td>${pageScope.type.typeName}</td>
                            <td>${pageScope.type.typeStatus}</td>
                            <td class="text-center">
                                <input type="button" class="btn btn-warning btn-sm doProTypeModify" onclick="updateType('${pageScope.type.typeId}')" value="修改">
                                <input type="button" class="btn btn-warning btn-sm doProTypeDelete" onclick="deleteType('${pageScope.type.typeId}')" value="删除">
                                <c:if test="${pageScope.type.typeStatus=='禁用'}">
                                <input type="button" class="btn btn-success btn-sm doProDisable" onclick="useType('${pageScope.type.typeId}')" value="启用">
                                </c:if>
                                <c:if test="${pageScope.type.typeStatus=='启用'}">
                                <input type="button" class="btn btn-danger btn-sm doProTypeDisable" onclick="banType('${pageScope.type.typeId}')" value="禁用">
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <!-- 添加商品类型 start -->     
    <div class="modal fade" tabindex="-1" id="ProductType">
        <!-- 窗口声明 -->
        <div class="modal-dialog modal-lg">
            <!-- 内容声明 -->
            <div class="modal-content">
                <form action="#" method="post" id="addTypedata">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加商品类型</h4>
                </div>
                <div class="modal-body text-center">
                    <div class="row text-right">
                        <label for="productTypeName" class="col-sm-4 control-label">类型名称：</label>
                        <div class="col-sm-4">
                            <input type="text" name="typeName" class="form-control" id="productTypeName">
                        </div>
                    </div>
                    <br>
                </div>
                </form>
                <div class="modal-footer">
                    <button class="btn btn-primary addProductType" id="addType">添加</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 添加商品类型 end -->
    
    <!-- 修改商品类型 start -->
    <div class="modal fade" tabindex="-1" id="myProductType">
        <!-- 窗口声明 -->
        <div class="modal-dialog modal-lg">
            <!-- 内容声明 -->
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改商品类型</h4>
                </div>
                <form action="#" method="post" id="updateTypedata">
                <div class="modal-body text-center">
                    <div class="row text-right">
                        <label for="proTypeNum" class="col-sm-4 control-label">编号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="typeId" id="proTypeNum"  readonly>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="proTypeName" class="col-sm-4 control-label">类型名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" name="typeName" id="proTypeName">
                        </div>
                    </div>
                </div>
                </form>
                <div class="modal-footer">
                    <button class="btn btn-warning updateProType" id="updateType">修改</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 修改商品类型 end -->
</body>

</html>