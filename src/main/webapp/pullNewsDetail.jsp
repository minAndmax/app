<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>全球军事详情</title>
<link href="../source/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link href="../source/css/style.css" rel="stylesheet" type="text/css" media="all" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Kaisersosa Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href='https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../source/css/flexslider.css" type="text/css" media="screen" />
<script src="../source/js/jquery.min.js"></script>
<link rel="shortcut icon" href="../source/images/favicon.ico" type="image/x-icon"> 
</head>
<body>
<!-- header -->
	<div class="container">
		<div class="header">
			<div class="logo">
				<a href="index.html"><img src="../source/images/logo.png" class="img-responsive" alt="" /></a>
			</div>
			<div class="header-left">
				<div class="head-nav">
						<span class="menu"> </span>
					<ul class="cl-effect-1">
					    <li class="active"><a href="index.html">首页</a></li>
						<li><a href="blog.html">军营风采</a></li>
						<li><a href="pullNewsList.html">全球军事</a></li>
							<div class="clearfix"></div>
					</ul>
				</div>
				<!-- script-for-nav -->
						<script>
							$( "span.menu" ).click(function() {
							  $( ".head-nav ul" ).slideToggle(300, function() {
								// Animation complete.
							  });
							});
						</script>
					<!-- script-for-nav -->
<!-- 				<div class="search2"> -->
<!-- 					<form> -->
<!-- 						<input type="text" value="Search here.." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search here..';}"> -->
<!-- 						<input type="submit" value=""> -->
<!-- 					</form> -->
<!-- 				</div> -->
					<div class="clearfix"> </div>
			</div>
				<div class="clearfix"> </div>
		</div>
	
<!-- header -->	
            <input type="hidden" id="hiddenNewsId" value="<%= request.getParameter("reptileId")%>">
            <div class="single-page-artical" id="pullNewDetail">
								
	        </div>

</div>	
	<!-- footer -->
		<div class="footer">
			<div class="container">
			<i class="line"> </i>
			<div class="footer-bottom">
				<div class="foot-left">
					<div class="foot-nav">
					<ul>
					    <li class="active"><a href="index.html">首页</a></li>
						<li><a href="blog.html">军营风采</a></li>
						<li><a href="pullNewsList.html">全球军事</a></li>
							<div class="clearfix"></div>
					</ul>
					</div>					
				</div>
				<div class="foot-right">
					<p>Copyright &copy; 2018.Company - MagicalJ And MinAndMax Personal studio </p>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
</body>
 <script type="text/javascript">
 

 $(document).ready(function(){
	 loadpullNews();
 })
 
 
  function loadpullNews(){//加载自定义新闻 
	 
		var newId = $("#hiddenNewsId").val();
    	$.post("${pageContent.request.contentPath}/data/findpullNewsById",{reptileId : newId},
    		function(date){
    		var html ="";
    		
				html += "<div class='artical-content'>";
				html += "<h3>"+date.jsonobejct.reptileTitle+"</h3>";
// 				html += "<img class='img-responsive' src='"+date.jsonobejct+"' title='banner1'>";
				html +="<p>"+date.jsonobejct.reptileContent+"</p>";
				html += "</div>";
				html += "<div class='artical-links'>";
				html += "<ul>";
				html += "<li><small> </small><span>"+date.jsonobejct.reptileTime+"</span></li>";
				html += "<li><small class='admin'> </small><span>新华网</span></li>"; 
// 				html += "<li><a href='#'><small class='link'> </small><span>链接</span></a></li>";
				html += "</ul>";
				html += " </div>";
    		$("#pullNewDetail").html(html);
    	});
    }
    	    	  
 </script>
</html>