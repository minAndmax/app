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
    <input type="text" id="unm"/>
    <input type="text" id="urnm"/>
    <input type="text" id="phone"/>
    <input type="password" id="pwd"/>
    <button id="regist">注册</button>
</body>
  <script type="text/javascript">
  
         $("#regist").click(function(){
        	 var num = $("#unm").val();
        	 var urnm = $("#urnm").val();
        	 var phone = $("#phone").val();
        	 var pwd = $("#pwd").val();
        	 
        	 $.post("${pageContext.request.contextPath}/user/regist",
        			 {"userName" : num,
        		 	  "userPassword" : pwd,
        		 	  "userRealName" : urnm, 
        		 	  "userPhone" : phone},function(date){
        		 alert(date.userName);
        		 alert(date.registTip);
        	 })
         })     
  
    </script>
</html>