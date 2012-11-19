<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet"/>
<title>添加定时任务</title>
</head>
<body>
<div class="span4"><h2>添加定时刷新任务</h2></div>
<br>
<form action="/quartz/jobAdd.do" class="well">
	<label>名称</label>
	<input name="jobName" type="text" class="span3" placeholder="仅包含数字、字母和下划线">
	<label>刷新栏目</label>
	<input name="topicIds" type="text" class="span2" placeholder="输入栏目ID，用,分隔，空白为刷新全部栏目">
	<label>备注</label>
	<textarea class="span8" name="description" rows="20" cols="10"></textarea>
	<div class="form-inline">
	<label>小时</label>
	<input type="text" name="hourPattern">
	<label>分钟</label>
	<input type="text" name="minutePattern">
	<span class="help-inline">允许值 0-59 ，允许的特殊字符 , - * /</span>
	</div>
	<center><input type="submit" value="添加" class="span4"></center>
</form>
</body>
</html>