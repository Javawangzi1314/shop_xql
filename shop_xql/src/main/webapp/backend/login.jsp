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
<html>
  <head>
    <title>在线商城-后台管理系统</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${path}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${path}/css/mycss.css" />
    <script type="text/javascript" src="${path}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${path}/js/bootstrap.min.js"></script>

      <script type="text/javascript">
          $(function () {
              $("#logdl").click(function () {
                  $.ajax(
                      {
                          type:"POST",
                          url:"${path}/Admin/login",
                          data:$("#login").serialize(),
                          success:function(result){
                              console.log(result)
                              if(result.data=="ok"){
                                  location.href="${path}/backend/main.jsp";
                              }else{
                                  alert(result.data);
                              }
                          },
                          error:function(){
                              alert("异常");
                          }
                      }
                  )
              });
              $("#imgVcode").click(function(){
                  $(this).attr('src',"${path}/Admin/ValidateCode?d="+new Date().getTime());
              });
              $("#vcodeImgBtn").click(function(){
                  $("#imgVcode").attr('src',"${path}/Admin/ValidateCode?d="+Math.random());
              });
          });
      </script>
  </head>
  <body>
  	<!-- 使用自定义css样式 div-signin 完成元素居中-->
    <div class="container div-signin">
      <div class="panel panel-primary div-shadow">
      	<!-- h3标签加载自定义样式，完成文字居中和上下间距调整 -->
	    <div class="panel-heading">
	    	<h3>皮皮磊商城</h3>
	    	<span>ZSHOP Manager System</span>
	    </div>
	    <div class="panel-body">
	      <!-- login form start -->
	      <form action=""  id="login" class="form-horizontal" method="post">
		     <div class="form-group">
		       <label class="col-sm-3 control-label">用户名：</label>
		       <div class="col-sm-9">
		         <input class="form-control" name="adminId" type="text" placeholder="请输入用户名">
		       </div>
		    </div>
		     <div class="form-group">
		       <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
		       <div class="col-sm-9">
		         <input class="form-control" name="adminPassword" type="password" placeholder="请输入密码">
		       </div>
		    </div>
		     <div class="form-group">
		       <label class="col-sm-3 control-label">验证码：</label>
		       <div class="col-sm-4">
		         <input class="form-control" name="ValidateCode" type="text" placeholder="验证码">
		       </div>
		       <div class="col-sm-2">
		       	  <!-- 验证码 -->
                   <a class="code_pic" id="vcodeImgWrap" name="change_code_img" href="javascript:void(0);">
                       <img id="imgVcode" src="${path}/Admin/ValidateCode" style="height: 32px; width: 70px;">
                   </a>
		       </div>
		       <div class="col-sm-2">
                   <a id="vcodeImgBtn" name="change_code_link" class="code_picww" href="javascript:void(0)">
		         <button type="button" class="btn btn-link">看不清</button></a>
		       </div>
		    </div>
		    <div class="form-group">
		       <div class="col-sm-3">
		       </div>
		       <div class="col-sm-9 padding-left-0">
		       	 <div class="col-sm-4">
			         <button type="button" id="logdl" class="btn btn-primary btn-block">登&nbsp;&nbsp;陆</button>
		       	 </div>
		       	 <div class="col-sm-4">
			         <button type="reset" class="btn btn-primary btn-block">重&nbsp;&nbsp;置</button>
		       	 </div>
		       	 <div class="col-sm-4">
			         <button type="button" class="btn btn-link btn-block">忘记密码？</button>
		       	 </div>
		       </div>
		    </div>
	      </form>
	      <!-- login form end -->
	    </div>
	  </div>
    </div>
    <!-- 页尾 版权声明 -->
    <div class="container">
		<div class="col-sm-12 foot-css">
		        <p class="text-muted credit">

		        </p>
	    </div>
    </div>
  
  </body>
</html>
