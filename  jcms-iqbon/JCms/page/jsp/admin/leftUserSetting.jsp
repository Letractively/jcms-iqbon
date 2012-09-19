<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet"/>
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet"/>
<title>个人菜单设定</title>
</head>
<body>
<div class="well" style="background-color:#FFFFFF;border:0px;padding-left:1px;">
	 <ul class="nav nav-list" style="padding-left:1px;padding-right:1px;">
	 	<li><a href="/admin/user/userInfo.do?userName=${userName }" target="mainFrame"><i class="icon-edit"></i>修改信息</a></li>
	    <li><a href="/admin/user/showModifyUserPassword.do?userName=${userName }" onclick="$('#addBlankDoc').modal('show');" target="mainFrame"><i class="icon-lock"></i>更新密码</a></li>
	    <li><a href="#" target="mainFrame"><i class="icon-th-list"></i>我的常用栏目</a></li>
	 </ul>
</div>
</body>
</html>