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
	<form action="" id="addModifyForm" method="post">
	<div>
	<div style="bpadding-top: 10px;padding-left: 20px;margin-top: 6px;width: 78%;float: left;border-width:0 1px 0 0;border-color: #AAAAAA;border-style: none solid none none;">
		<div class="form-inline ">
			<label>模板名称</label>
			<input name="modelName" id="modelName" class="span4" type="text" placeholder="只能包含数字、字母和下划线">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>后缀</label>
			<select name="suffix" class="span1" id="suffix">
				<c:forEach items="${suffixList}" var="suffix">
					<option value="${suffix.value }">${suffix.key }</option>
				</c:forEach>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>刷新频率</label>
			<select name="refresh" class="span1" id="refresh">
				<c:forEach items="${refreshList }" var="refresh">
				<option value="${refresh.value }">${refresh.key }</option>
				</c:forEach>
			</select>分钟&nbsp;&nbsp;&nbsp;&nbsp;
				<label>过期天数</label>
			<select name="timeout" class="span1" id="timeout">
				<c:forEach items="${timeoutList }" var="timeout">
				<option value="${timeout.value }">${timeout.key }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-inline ">
			<label>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题</label>
			<input name="title" class="span4" type="text" id="title"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>关键字</label>
			<input name="keyword" id="keyword" class="span4" type="text" placeholder="用于SEO优化">
		</div>
		<div class="form-inline "><textarea name="content" style="width: 780px;height: 450px"></textarea></div>
		<input type="hidden" value="${topicid }" name="topicid">
		<input type="hidden" value="${pageNum }" name="pageNum">
		<input type="hidden" value="${type }" name="type">
		<input type="hidden" value="${modelType }" name="modelType"/>
		<input type="hidden" value="" id="status" name="status"/>
		</div>
		<div style="padding-top: 10px;padding-right: 20px;margin-top: 6px;width: 17% ;float: right;">
			<div class="form-inline ">
					<button class="btn-small" type="button">预览</button>&nbsp;&nbsp;
					<button class="btn-small" type="button" onclick="save()">保存</button>&nbsp;&nbsp;
					<button class="btn-small btn-primary" type="submit">发布</button>
			</div>
			<hr/>
		</div>
		</div>
		</form>
	</body>
	<script src="/js/jquery.js"></script>
	<script type="text/javascript">
		function save(){
			if(checkForm()){
				$('#addModifyForm').attr('action','/admin/model/addModel.do');
				$('#status').val('0');
				$('#addModifyForm').submit();
			}
		}
		function checkForm(){
			if($('#modelName').val()==''){
				alert('模板名称不能为空');
				$('#modelName').focus();
				return false;
			}
			if($('#title').val()==''){
				alert('标题不能为空');
				$('#title').focus();
				return false;
			}
			return true;
		}
	</script>
</html>