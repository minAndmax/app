<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../source/js/jquery-2.1.1.js"></script>
<title>Insert title here</title>
</head>
<body>
    <table>
       <thead>
          <tr>
               <th>
              	 用户名
               </th>
               <th><input id="userName" type="text"></th>
          </tr>
          <tr>
             <th id="loginTip" colspan="2"></th>
          </tr>
           <tr>
               <th>
              	 密码
               </th>
               <th><input id="userPassword" type="password"></th>
          </tr>
       </thead>
       <thead>
          <tr>
             <th colspan="2"><button id="loginButton">登录</button> <button id="registButton">注册</button></th>
          </tr>
       </thead>
    </table>
</body>
    <script type="text/javascript">
         $("#loginButton").click(function(){
        	 
        	 var num = $("#userName").val();
        	 var pwd = $("#userPassword").val();
        	 
        	 $.post("${pageContext.request.contextPath}/user/login",{"userName" : num,"userPassword" : pwd},function(date){
        		 alert(date.userRegist);
        		 alert(date.loginStatus);
        	 })
        	 
         })
    </script>
</html>