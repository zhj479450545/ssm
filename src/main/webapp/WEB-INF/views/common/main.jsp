<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/plugins/bootstrap/bootstrap/css/bootstrap.css"  />
	<link type="text/css" rel="stylesheet"  href="<%=request.getContextPath() %>/css/main.css" />
	<!-- <script type="text/javascript" src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script type="text/javascript" src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script> -->
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-2.2.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/page.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/main.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/bootstrap/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/pagination/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/layer/layer.js"></script>
</head>
<body>
<div class="top-page row">
	<div class="col-md-2">你好，${currentUser.username }</div>
	<div class="col-md-2 pull-right">
		<a class="pull-right" href="#">退出</a>
	</div>
</div>
<div class="left-menu">
	<ul class="nav nav-pills nav-stacked">
		<li class="active"><a href="#">首页</a></li>
		<li><a href="#">SVN</a></li>
		<li><a href="#">iOS</a></li>
		<li><a href="#">VB.Net</a></li>
		<li><a href="#">Java</a></li>
		<li><a href="#">PHP</a></li>
		<li><a href="#">C++</a></li>
		<li><a href="#">Python</a></li>
		<li><a href="#">Android</a></li>
	</ul>
</div>
</body>
<script type="text/javascript">
	$(function(){
		var footerHTML = '<div class="foot-page">&copy;2016-2026 zhoujian</div>';
		$('body').append(footerHTML);
	});
</script>
</html>