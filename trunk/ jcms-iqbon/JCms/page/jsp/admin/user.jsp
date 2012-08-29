<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
</head>
<body>
	<div  style="bpadding-top: 10px;padding-left: 20px;margin-top: 6px;width: 78%;float: left;border-width:0 1px 0 0;border-color: #AAAAAA;border-style: none solid none none;"> 
		<div class="form-vertical">
			<label>昵称</label>
			<input value="${user.nickName }">
			<label>Email</label>
			<input value="${user.email }">
			<label>联系电话</label>
			<input value="${user.telephone }">
			<label>移动电话</label>
			<input value="${user.mobile }">
			<div class="form-actions">
	            <button class="btn btn-primary" type="submit">修改</button>
	            <button class="btn" type="reset">取消</button>
          </div>	
		</div>
	</div>
</body>
</html>