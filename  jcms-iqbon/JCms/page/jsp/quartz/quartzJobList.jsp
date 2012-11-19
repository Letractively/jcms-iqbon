<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="300; url="/>
<link href="/css/bootstrap.min.css" rel="stylesheet"/>
<title>定时任务</title>
</head>
<body>
<div class="span4"><h2>定时刷新任务</h2></div>
<br>
<div class="span12"><a href="/quartz/showJobAdd.do">添加定时刷新任务</a></div>
<table class="table">
	<thead style="background-color: silver;">
		<tr>
			<th> 名称 </th>
			<th> 描述 </th>
			<th>当前状态</th>
			<th>下次开始时间</th>
			<th>上次开始时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="quartz" items="${quartzList }">
			<tr>
				<td>${quartz.jobName }</td>
				<td>${quartz.description }</td>
				<td>${quartz.status }</td>
				<td>${quartz.nextTime }</td>
				<td>${quartz.prevTime }</td>
				<td><a href="/quartz/jobDelete.do?jobName=${quartz.jobName }">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>