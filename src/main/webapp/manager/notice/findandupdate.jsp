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
<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : '../kindeditor/plugins/code/prettify.css',
				uploadJson : '../kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '../kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				},
			afterBlur:function(){this.sync();} 
			});
			prettyPrint();
		});
	</script>
  
<script type="text/javascript">
</script>
</head>

<body>
<input type="hidden" id="id" value='<%= request.getParameter("newId") %>'/>
	<div class="place">
    <span class="sp">位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="tab.html">通知管理</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">修改通知</a></li> 
    <li><a href="#tab2">预览通知</a></li> 
  	</ul>
    </div> 
    
  	<div id="tab1" class="tabson">
    
    <ul class="forminfo">
    
    <ul class="forminfo">
    <li>
    	<label>拟定人<b>*</b></label>
    	<input name="" type="text" class="dfinput" id="author"  style="width:518px;"/>
    </li>
   
    
    </li>
    <li><label>通知内容<b>*</b></label>
    

    <textarea id="content7" name="content1" style="width:100%;height:400px;visibility:hidden;"></textarea>
    
    </li>
    <li><label>&nbsp;</label><input type="button" id="bt" class="btn" value="修改"/></li>
    </ul>
    </ul>
    
    </div> 
    
    
  	<div id="tab2" class="tabson">
    	<div id="newtitle" style="text-align: center;"></div>
   		<div id="noticecontent"></div>
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
		loadFind();
	})
	
	function loadFind(){
		
		var noticeID = $("#id").val();
		$.post("${pageContent.request.contentPath}/data/manager/findNotice",
				{noticeId:noticeID},
				function(data){
						$("#newtitle").html("<p>"+data.jsonobejct.noticeContent+"</p>");
						$("#nauthor").html("拟定人:"+data.jsonobejct.noticeUser+"&nbsp;&nbsp;时间:"+data.jsonobejct.createTime);
		})
		
	}
	function loadUpdate(){
		
		var noticeID = $("#id").val();
		$.post("${pageContent.request.contentPath}/data/manager/findNotice",
				{noticeId:noticeID},
				function(data){
					$("#author").val(data.jsonobejct.noticeUser);
					KindEditor.html("#content7",data.jsonobejct.noticeContent);
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
				
				swal({
                    title: "请等待……",
                    type: "warning",
                    showConfirmButton: false,
                    showCancelButton: true,
                    cancelButtonText:"取消"
                });
				
				var author = $("#author").val();
				var content = $("#content7").val();
				var newID = $("#id").val();
				
				var requests = {
						noticeUser : author,
						noticeContent : content,
						noticeId : newID
				}
				$.post("/data/manager/updateNotice",requests,function(date){
					if(date.tipStatus == 1){
						swal({
        	                confirmButtonColor: "#1ab394",
        	                title: "修改成功!",
        	                confirmButtonText: "确认",
        	                type: "success"
        	             },function(inputValue){
        	            	 loadFind();
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
             
</html>