<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet"/>
<title>用户信息</title>
</head>
<body>
<form action="/admin/user/modifyUserInfo.do" method="post">
	<div  style="bpadding-top: 30px;padding-left: 20px;margin-top: 6px;"> 
		<div>
			<label>昵称</label>
			<input name="nickname" value="${user.nickName }" type="text">
			<label>Email</label>
			<input name="email" value="${user.email }" type="text">
			<label>联系电话</label>
			<input name="telephone" value="${user.telephone }" type="text">
			<label>移动电话</label>
			<input name="mobile" value="${user.mobile }" type="text">
			<div class="form-actions">
			<input value="${user.userName }" type="hidden" name="userName">
	            <button class="btn btn-primary" type="submit">修改</button>
	            <button class="btn" type="reset">取消</button>
          </div>	
		</div>
	</div>
	</form>
</body>
</html>