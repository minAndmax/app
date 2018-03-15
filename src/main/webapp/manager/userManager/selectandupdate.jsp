<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>查看与修改</title>
	
	<link rel="stylesheet" href="../kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="../kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="../kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="../kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="../kindeditor/plugins/code/prettify.js"></script>
	
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/select.css" rel="stylesheet" type="text/css" />
<link href="../css/sweetalert/sweetalert.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="../js/select-ui.min.js"></script>
<script type="text/javascript" src="../js/sweetalert/sweetalert.min.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/date/WebCalendar.js"></script>

<style type="text/css">
  .tablelink{
  	cursor: pointer;
  }
</style>
  
</head>

<body>
<input type="hidden" id="id" value='<%= request.getParameter("userId") %>'/>
	<div class="place">
    <span class="sp">位置：</span>
    <ul class="placeul">
    <li><a href="newstab.html">用户管理</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
<!--     <li><a href="#tab2" class="selected">预览用户</a></li>  -->
    <li><a href="#tab1">修改用户</a></li> 
  	</ul>
    </div> 
    
  	<div id="tab1" class="tabson">
    
    <ul class="forminfo">
    <li>
    	<label><b>* &nbsp;</b>密码</label>
    	<input name="" type="password" class="dfinput" id="userPassword"  style="width:518px;"/>
    </li>
    
     <li>
    	<label><b>* &nbsp;</b>确认密码</label>
    	<input name="" type="password" class="dfinput" id="reuserPassword"  style="width:518px;"/>
    </li>
    
    <li>
    	<label><b>&nbsp;&nbsp;</b>联系方式</label>
    	<input name="" type="text" class="dfinput" id="userPhone"  style="width:518px;"/>
    </li>
    
    </li>
    <li><label>&nbsp;</label><input type="button" id="bt" class="btn" value="修改"/></li>
    </ul>
    
    </div> 
    
    
  	<div id="tab2" class="tabson">
    	<div id="newtitle" style="text-align: center;"></div>
   		<div id="newcontent"></div>
    	<div id="nauthor" style="float: right;"></div>
    </div>  
       
	</div> 
 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    </div>

</body>
<script type="text/javascript">

	$(document).ready(function(){
		loadUpdate();
	})
	function loadUpdate(){
		
		var userID = $("#id").val();
// 		alert(userID)
		$.post("${pageContent.request.contentPath}/data/manager/findUserById",
				{userId : userID,page : 0,size : 10},
				function(data){
					$("#userPhone").val(data.jsonobejct.userPhone);
		})
		
	}
	
	$("#bt").click(function(){
			
		swal({
			title: "确定修改?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#1ab394",
            cancelButtonText: "取消",
            confirmButtonText: "确定 ",
            closeOnConfirm: false,
		},function(isConfirm){
			if(isConfirm){
				
				var userPassword = $("#userPassword").val();
				var userPhone = $("#userPhone").val();
				var userID = $("#id").val();
				var reuserPassword = $("#reuserPassword").val();
				
				if(userPassword != reuserPassword){
					swal({
						confirmButtonColor: "#FF0000",
						title: "两次密码输入不一致", 
						confirmButtonText: "确认",
						type: "error"
					});
	     			return false;
				}
				
				swal({
                    title: "请等待……",
                    type: "warning",
                    showConfirmButton: false,
                    showCancelButton: true
                });
				var userID = $("#id").val();
				var requests = {
						userPassword : userPassword,
						userPhone : userPhone,
						userId : userID
				}
				$.post("/data/manager/updateUser",requests,function(date){
					if(date.tipStatus == 1){
						swal({
        	                confirmButtonColor: "#1ab394",
        	                title: "修改成功!",
        	                confirmButtonText: "确认",
        	                type: "success"
        	             },function(inputValue){
        	            	 window.location.href="userManager.html?ids="+new Date();
        	            }, 2000);
					} else{
						swal({
        	                confirmButtonColor: "#1ab394",
        	                title: "数据异常!",
        	                confirmButtonText: "确认",
        	                type: "error"
        	             });
					}
				})
			}
        });
	});
</script>
       <script type="text/javascript">
	$(document).ready(function(){
		$.post("/data/manager/getUser",{},function(data){
  		  if(data.userName != null){
	    		  $("#showuser").html(data.roleName+":"+data.userName);
  		  } else{
  			  window.top.location = "/manager/login.html";
  		  }
  	  })
	})
</script>      
</html>