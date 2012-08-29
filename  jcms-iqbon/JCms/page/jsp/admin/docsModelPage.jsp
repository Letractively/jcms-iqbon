<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<title>文章模板列表</title>
</head>
<body>
<div class="btn-toolbar">
	<div class="btn-group">
		<button type="button" title="新建文章模板"
		 onclick="window.location.href='/admin/model/addModifyModelPage.do?pageNum=1&type=1&modelType=1'" class="btn">新建文章模板</button>
	</div>
</div>
<form action="">
<table class="table">
	<thead style="background-color: silver;">
		<tr>
			<th> 名称 </th>
			<th> 最后修改时间 </th>
			<th> 用户 </th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${modelList }" var="model">
		<tr>
			<td><a href="/admin/model/addModifyModelPage.do?modelName=${model.modelName }&pageNum=0&type=0&modelType=1">${model.modelName }</a></td>
			<td>${model.lastModify }</td>
			<td><a href="#">${model.modifyUser }</a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</form>
</body>
<script src="/js/jquery.js"></script>
</html>