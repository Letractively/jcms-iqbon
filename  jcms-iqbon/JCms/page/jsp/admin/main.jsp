<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet"/>
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet"/>
<title>个人菜单设定</title>
</head>
<body>
<div id="content">
<div id="welcome" class="content">
  <div align="center">
    <p>&nbsp;</p>
    <h1>欢迎使用JCMS</h1>
    <p>&nbsp;</p>
    </div>
     <a  class="btn" href="javaScript:showAddTopic();" title="添加主栏目">添加主栏目</a>
     <br/><hr/>
     <ul class="nav nav-tabs">
     	<li class="active"> <h3>主栏目</h3></li>
     </ul>
     <c:forEach items="${topicList }" var="topTopic">
    <a  class="btn" href="/admin/topic/topicPage.do?pageNum=1&type=0&topicid=${topTopic.topicId }" title="打开${topTopic.topicName }">${topTopic.topicName }</a>
   </c:forEach>
</div>
</div>
<div class="modal" id="addTopic" style="display: none">
 	<div class="modal-header">
 		<h3>新建主栏目</h3>
 	</div>
 	<form id="addTopicForm" action="addTopTopic.do" method="post" onsubmit="return checkAddTopic();">
	 	<div class="modal-body">
	 		名称:<input onfocus="$('#topicNameError').html('');" name="topicName" id="addTopicName"><span id="topicNameError" class="help-inline" style="color: red;"></span>
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
function showAddTopic(){
	$('#addTopic').modal('show');
}

function checkAddTopic(){
	if($('#addTopicName').val()==''){
		$('#topicNameError').html('不能为空');
		return false;
	}
	$('addTopicForm').submit();
}
</script>
</html>