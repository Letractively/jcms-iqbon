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
<div class="span4"><h3>添加定时刷新任务</h3></div>
<br>
<form action="/quartz/jobAdd.do" class="well">
	<label>名称</label>
	<input name="jobName" type="text" class="span3" placeholder="仅包含数字、字母和下划线">
	<label>备注</label>
	<textarea class="span4" name="description" rows="20" cols="10"></textarea>
	<div class="form-inline">
	<label>小时</label>
	<select  name="hourPattern" class="span1">
		<option value="*">*</option>
		<option value="0">0</option>
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
		<option value="6">6</option>
		<option value="7">7</option>
		<option value="8">8</option>
		<option value="9">9</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
		<option value="13">13</option>
		<option value="14">14</option>
		<option value="15">15</option>
		<option value="16">16</option>
		<option value="17">17</option>
		<option value="18">18</option>
		<option value="19">19</option>
		<option value="20">20</option>
		<option value="21">21</option>
		<option value="22">22</option>
		<option value="23">23</option>
	</select>
	<label>分钟</label>
	<select name="minutePattern" class="span1">
		<c:forEach items="${refreshList }" var="code">
		<option value="${code.value }">${code.key }</option>
		</c:forEach>
	</select>
	<!-- 允许值 0-59 ，允许的特殊字符 , - * / -->
	<span class="help-inline">每隔多少分钟执行一次</span>
	</div>
	<br/>
	<input type="submit" value="添加" class="span2">
	<br/>
</form>
</body>
</html>