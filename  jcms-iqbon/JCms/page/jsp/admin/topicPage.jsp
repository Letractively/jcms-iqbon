<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
<title>JCMS栏目页</title>
</head>
<body>
<div class="btn-toolbar">
    <div class="btn-group">
    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
    	发布
    <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
    	<li><a href="#">发布文章</a></li>
    	<li><a href="@">发布空文章</a></li>
    </ul>
    <button class="btn" title="复制文章或空文章">复制</button>
    <button class="btn" title="剪切文章或空文章">剪切</button>
    <button class="btn" title="粘贴文章或空文章">粘贴</button>
    </div>
    <div class="btn-group">
    <button class="btn" title="修改栏目">修改栏目</button>
    <button class="btn" title="删除栏目">删除栏目</button>
    <button class="btn" onclick="showAddSubTopic('${topicid}')" title="添加子栏目">添加子栏目</button>
    </div>
    <div class="btn-group" data-toggle="buttons-radio">
    <button class="btn btn-primary <c:out value="${type==0?'active':'' }"></c:out>" title="查看当前栏目的文章">查看文章</button>
    <button class="btn btn-primary <c:out value="${type==1?'active':'' }"></c:out>" title="查看当前栏目的模板">查看模板</button>
    </div>
 </div>
 <table class="table table-bordered">
 	<thead style="background-color: silver;">
 		<tr>
 			<th><input type="checkbox" /></th>
 			<th> 名称 </th>
 			<th>最后修改</th>
 			<th>发布时间</th>
 			<th> 权重</th>
 			<th> 用户 </th>
 		</tr>
 	</thead>
 	<tbody>
 	<c:forEach var="topic" items="${subTopicList}">
 		<tr>
 			<td><input type="checkbox" /></td>
 			<td><a href="/admin/topic/topicPage.do?pageNum=1&type=1&topicid=${topic.topicId }"><img src="/images/folderopen.gif"/>&nbsp;&nbsp;${topic.topicName}</a></td>
 			<td>${topic.lastModify}</td>
 			<td></td>
 			<td></td>
 			<td>${topic.modifyUser}</td>
 		</tr>
 	</c:forEach>
 	</tbody>
 </table>
<form class="well form-inline">
	<a><i class="icon-step-backward"></i></a><a><i class="icon-chevron-left"></i></a> 
	第<input type="text" class="input-mini"  value="${pageNum }"/>页<button type="submit" class="btn">Go</button>
	<a><i class="icon-chevron-right"></i></a><a><i class="icon-step-forward"></i></a> 
	共${totalPageNum }页
</form>

<div class="modal" id="addTopic" style="display: none">
 	<div class="modal-header">
 		<h3>新建栏目</h3>
 	</div>
 	<form id="addTopicForm" action="addSubTopic.do" method="post" onsubmit="return checkAddSubTopic();">
	 	<div class="modal-body">
	 		名称:<input onfocus="$('#topicNameError').html('');" name="topicName" id="addTopicName"><span id="topicNameError" class="help-inline" style="color: red;"></span>
	 		<input type="hidden" name="parentTopicid" id="addParentTopicid" value="">
	 		<input type="hidden" name="pageNum" value="${pageNum }">
	 		<input type="hidden" name="type"  value="${type }">
	 	</div>
	 	<div class="modal-footer">
	 		<button type="button" class="btn" onclick="$('#addTopic').modal('hide');">关闭</button>
	 		<button type="submit" class="btn btn-primary" >提交</button>
	 	</div>
 	</form>
 </div>
</body>
<script src="/js/jquery.js"></script>
<script  src="/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="/js/bootstrap-dropdown.js"></script>
<script src="/js/bootstrap-button.js"></script>
<script type="text/javascript">
	function showAddSubTopic(parentTopicid){
		$('#addParentTopicid').val(parentTopicid);
		$('#addTopic').modal('show');
	}
</script>
</html>