<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
</head>
<body>
<div class="row">
	<div class="span2"><img height="35" width="120"src="/images/Logo2.png"></div>
	<div class="span10">
	<span>
		<ul class="nav nav-pills">
		<li>
	          <a href="#"><i class="icon-user"></i> ${user.userName }</a>
		</li>
	    <li class="active">
	    <a href="#">首页</a>
	    </li>
	    <li id="userInfo" class="dropdown">
	    <a class="dropdown-toggle" href="#userInfo" data-toggle="dropdown">我的信息</a>
		    <ul class="dropdown-menu">
		    	<li><a href="/admin/user/userInfo.do?userName=${user.userName }">修改信息</a></li>
	    		<li><a href="#" onclick="$('#addBlankDoc').modal('show');">更新密码</a></li>
	    		<li><a href="#">我的栏目</a></li>
	    	</ul>
	   	</li>
	    <li><a href="#">系统设置</a></li>
    	</ul>
	</span>
    
	</div>
</div>
</body>
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.min.js" ></script>
<script src="/js/bootstrap-dropdown.js" ></script>
</html>