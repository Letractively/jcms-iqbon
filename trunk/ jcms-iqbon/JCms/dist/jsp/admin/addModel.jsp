<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="/css/bootstrap.min.css" rel="stylesheet">
		<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
		<style type="text/css">
			div {
				margin-bottom: 9px;
			}
		</style>
		<title>增加模板</title>
	</head>
	<body>
	<form action="">
	<div>
	<div style="bpadding-top: 10px;padding-left: 20px;margin-top: 6px;width: 78%;float: left;border-width:0 1px 0 0;border-color: #AAAAAA;border-style: none solid none none;">
		<div class="form-inline ">
			<label>模板名称</label>
			<input class="span4" type="text" placeholder="只能包含数字、字母和下划线">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>后缀</label>
			<select class="span1" >
				<c:forEach items="${suffixList }" var="suffix">
					<option value="${suffix.value }">${suffix.key }</option>
				</c:forEach>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>刷新频率</label>
			<select class="span1">
				<c:forEach items="${refreshList }" var="refresh">
				<option value="${refresh.value }">${refresh.key }</option>
				</c:forEach>
			</select>分钟&nbsp;&nbsp;&nbsp;&nbsp;
				<label>过期天数</label>
			<select class="span1">
				<c:forEach items="${timeoutList }" var="timeout">
				<option value="${timeout.value }">${timeout.key }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-inline ">
			<label>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题</label>
			<input class="span4" type="text" />
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>关键字</label>
			<input class="span4" type="text" placeholder="用于SEO优化">
		</div>
		<div class="form-inline "><textarea style="width: 780px;height: 450px"></textarea></div>
		<input type="hidden" value="${topicid }" name="topicid">
		<input type="hidden" value="${pageNum }" name="pageNum">
		<input type="hidden" value="${type }" name="type">
		</div>
		<div style="padding-top: 10px;padding-right: 20px;margin-top: 6px;width: 17% ;float: right;">
			<div class="form-inline ">
					<button class="btn-small" type="submit">预览</button>&nbsp;&nbsp;
					<button class="btn-small" type="submit">保存</button>&nbsp;&nbsp;
					<button class="btn-small btn-primary" type="submit">发布</button>
			</div>
		</div>
		</div>
		</form>
	</body>
</html>