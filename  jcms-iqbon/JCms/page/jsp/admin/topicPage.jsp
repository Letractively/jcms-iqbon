<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  type="text/css" rel="stylesheet" href="/css/main.css"/>
<link  type="text/css" rel="stylesheet" href="/css/colorbox.css"/>
<title>JCMS栏目页</title>
</head>
<body>
<div id="tableMenu">
<span><input type="button" value="发布文章" title="发布文章"/></span>
<span><input type="button" value="发布空文章" title="发布空文章"/></span>
<span><input type="button" value="复制" title="复制文章或空文章"/></span>
<span><input type="button" value="剪切" title="剪切文章或空文章"/></span>
<span><input type="button" value="粘贴" title="粘贴文章或空文章"/></span>
<span> | </span>
<span><input type="button" value="修改栏目" title="修改当前栏目"/></span>
<span><input type="button" value="删除栏目" title="删除当前栏目"/></span>
<span><input type="button" value="添加子栏目" title="添加子栏目"/></span>
<span> | </span>
<span><input type="button" value="查看模板" title="查看当前栏目的模板"/></span>
</div>
<div id="tableTop">
	<ul>
	<li><input type="checkbox" /></li>
	<li style="width:35%"> 名称 </li>
	<li style="width:20%">最后修改</li>
	<li style="width:20%">发布时间</li>
	<li style="width:10%"> 权重 </li>
	<li style="width:10%"> 用户 </li>
	</ul>
</div>
<div id="tableContent">
<c:forEach var="topic" items="${subTopicList}">
<ul>
	<li><input type="checkbox" /></li>
	<li style="width:35%"> <a href="/admin/topic/topicPage.do?pageNum=1&type=1&topicid=${topic.topicId }"><img src="/images/folderopen.gif"/>&nbsp;&nbsp;${topic.topicName}</a> </li>
	<li style="width:20%">${topic.lastModify}</li>
	<li style="width:20%"></li>
	<li style="width:10%">  </li>
	<li style="width:10%">${topic.modifyUser} </li>
	</ul>
</c:forEach>
</div>
<div id="tableFooter">
	<span><input type="checkbox" /></span>
	<span  style="width:95%"> 第<input type="text"  size="3" value="1"/>页 <input type="button" value="Go"/> 共1页  </span>
</div>
</body>
<script src="/js/jquery.js"></script>
<script src="/js/jquery.colorbox-min.js"></script>
</html>