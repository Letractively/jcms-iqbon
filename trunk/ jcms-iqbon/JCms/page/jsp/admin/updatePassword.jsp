<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet"/>
<title>修改用户密码</title>
</head>
<body>
<form action="/admin/user/modifyUserPassword.do" method="post">
<div  style="bpadding-top: 30px;padding-left: 20px;margin-top: 6px;"> 
	<label>新密码</label>
	<input id="password" name="password" type="password">
	<label>确认新密码</label>
	<input id="confirmPassword" type="password">
	<label>旧密码</label>
	<input name="oldPassword" type="password">
	<div class="form-actions">
		<input name="userName" value="${userName }" type="hidden">
		<button class="btn btn-primary" type="submit">修改</button>
	</div>
</div>
</form>
</body>
</html>