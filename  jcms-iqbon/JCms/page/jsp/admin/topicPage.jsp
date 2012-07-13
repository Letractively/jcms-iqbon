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
<c:if test="${topic.parentTopic!=''&&topic.parentTopic!=null}">
	<div class="btn-group">
    <button onclick="window.location.href='/admin/topic/topicPage.do?pageNum=1&type=${type}&topicid=${topic.parentTopic}'" class="btn btn-primary" title="返回上一层">上一层</button>
    </div>
</c:if>
<c:if test="${type==0 }">
    <div class="btn-group">
    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
    	发布
    <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
    	<li><a href="#">发布文章</a></li>
    	<li><a href="#" onclick="$('#addBlankDoc').modal('show');">发布空文章</a></li>
    </ul>
    <button class="btn" onclick="deletePushRecords();" title="删除推送记录">删除</button>
    <button class="btn" onclick="copyPushRecords()" title="复制文章或空文章">复制</button>
    <button class="btn" onclick="cutPushRecords()" title="剪切文章或空文章">剪切</button>
    <button class="btn" onclick="pastePushRecords()" title="粘贴文章或空文章">粘贴</button>
    </div>
</c:if>
    <div class="btn-group">
    <button class="btn" onclick="showModifyTopic('${topicid}')" title="修改栏目">修改栏目</button>
    <button class="btn" onclick="deleteTopics();" title="删除栏目">删除栏目</button>
    <button class="btn" onclick="showAddSubTopic('${topicid}')" title="添加子栏目">添加子栏目</button>
    </div>
    <div class="btn-group" data-toggle="buttons-radio">
    <button onclick="window.location.href='/admin/topic/topicPage.do?pageNum=1&type=0&topicid=${topicid}'"
    class="btn btn-primary <c:out value="${type==0?'active':'' }"></c:out>" title="查看当前栏目的文章">查看文章</button>
    <button onclick="window.location.href='/admin/topic/topicPage.do?pageNum=1&type=1&topicid=${topicid}'"
     class="btn btn-primary <c:out value="${type==1?'active':'' }"></c:out>" title="查看当前栏目的模板">查看模板</button>
    </div>
 </div>
 <form method="post" id="dolot">
 <input type="hidden" name="pageNum" value="${pageNum }"/>
 <input type="hidden" name="type"  value="${type }"/>
 <input type="hidden" name="topicid" value="${topicid}"/>
 <table class="table table-bordered table-condensed">
 	<thead style="background-color: silver;">
 		<tr>
 			<th><input id="selectItem" type="checkbox" onclick="selectAll();"/></th>
 			<th> 名称 </th>
 			<th>最后修改</th>
 			<th>发布时间</th>
 			<th> 权重</th>
 			<th> 用户 </th>
 		</tr>
 	</thead>
 	<tbody id="listFrom">
 	<c:forEach var="topic" items="${subTopicList}" varStatus="topicStatus">
 		<tr>
 			<td><input type="checkbox" name="topicSelect" value="${topic.topicId }" id="topic_${topicStatus.index }"/></td>
 			<td><a href="/admin/topic/topicPage.do?pageNum=${pageNum }&type=${type }&topicid=${topic.topicId }"><img src="/images/folderopen.gif"/>&nbsp;&nbsp;${topic.topicName}</a></td>
 			<td>${topic.lastModify}</td>
 			<td></td>
 			<td></td>
 			<td><a href="#">${topic.modifyUser}</a></td>
 		</tr>
 	</c:forEach>
 	<c:forEach var="pushRecord" items="${pushRecordList}" varStatus="pushRecordStatus">
 		<tr>
 			<td><input type="checkbox" name="pushRecordSelect" value="${pushRecord.indexid }" id="topic_${pushRecordStatus.index }"/></td>
 			<td><a target="blank" href="${pushRecord.url }"><img src="/images/blank_file.gif"/>&nbsp;&nbsp;${pushRecord.title}</a>&nbsp;&nbsp;<a onclick="modifyPushRecord('${pushRecord.indexid}')" href="#" title="修改属性" class="btn-small"><img src="/images/edit.gif"/></a></td>
 			<td>${pushRecord.lastModify}</td>
 			<td>${pushRecord.addDate}</td>
 			<td><a onclick="showChangeLspri('${pushRecord.indexid}')" id="pushRecord_lspri_${pushRecord.indexid }" href="#">${pushRecord.lspri }</a></td>
 			<td><a href="#">${pushRecord.modifyUser}</a></td>
 		</tr>
 	</c:forEach>
 	</tbody>
 </table>
 </form>
<form class="well form-inline">
	<a href="/admin/topic/topicPage.do?pageNum=1&type=${type }&topicid=${topicid}"><i class="icon-step-backward"></i></a><a><i class="icon-chevron-left"></i></a> 
	第<input type="text" class="input-mini"  value="${pageNum }"/>页<button type="submit" class="btn">Go</button>
	<a><i class="icon-chevron-right"></i></a><a href="/admin/topic/topicPage.do?pageNum=${totalPageNum }&type=${type }&topicid=${topicid}"><i class="icon-step-forward"></i></a> 
	共${totalPageNum }页
</form>

<div class="modal" id="addTopic" style="display: none">
 	<div class="modal-header">
 		<h3>新建子栏目</h3>
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
 
 <div class="modal" id="modifyTopic" style="display: none">
 	<div class="modal-header">
 		<h3>修改栏目</h3>
 	</div>
 	<form id="modifyTopicForm" action="modifyTopic.do" method="post" onsubmit="return checkModifyTopic();">
	 	<div class="modal-body">
	 		名称:<input onfocus="$('#modifyTopicNameError').html('');" name="topicName" id="modifyTopicName"><span id="modifyTopicNameError" class="help-inline" style="color: red;"></span>
	 		<input type="hidden" name="topicid" id="modifyTopicid" value="${topicid}">
	 		<input type="hidden" name="pageNum" value="${pageNum }">
	 		<input type="hidden" name="type"  value="${type }">
	 	</div>
	 	<div class="modal-footer">
	 		<button type="button" class="btn" onclick="$('#modifyTopic').modal('hide');">关闭</button>
	 		<button type="submit" class="btn btn-primary" >提交</button>
	 	</div>
 	</form>
 </div>
  <div class="modal" id=addBlankDoc style="display: none">
 	<div class="modal-header">
 		<h3>添加空文章</h3>
 	</div>
 	<form id="addBlankDocForm" action="addBlankDoc.do" method="post" onsubmit="return checkAddBlankDoc();">
	 	<div class="modal-body">
	 		标&nbsp;&nbsp;题：<input onfocus="$('#addBlankTitleError').html('');" name="title" id="addBlankTitle"><span id="addBlankTitleError" class="help-inline" style="color: red;">*</span><br/>
	 		副标题：<input name="subTitle" id="addBlankTSubitle"><br/>
	 		图&nbsp;&nbsp;片：<input name="img"><br/>
	 		U&nbsp;R&nbsp;L：<input onfocus="$('#addBlankUrlError').html('');" name="url" id="addBlankUrl"><span id="addBlankUrlError" class="help-inline" style="color: red;">*</span><br/>
	 		权&nbsp;&nbsp;重：<input name="lspri" value="60" id="addBlankLspri"/><span id="addBlankLspriError" class="help-inline" style="color: red;">*</span><br/>
	 		<input type="hidden" name="topicid" value="${topicid}">
	 		<input type="hidden" name="pageNum" value="${pageNum }">
	 		<input type="hidden" name="type"  value="${type }">
	 	</div>
	 	<div class="modal-footer">
	 		<button type="button" class="btn" onclick="$('#addBlankDoc').modal('hide');">关闭</button>
	 		<button type="submit" class="btn btn-primary" >提交</button>
	 	</div>
 	</form>
 </div>
 
 <div class="modal" id=updateLspri style="display: none">
 	<div class="modal-header">
 		<h3>修改权重</h3>
 	</div>
 	<form id="updateLspriForm" action="updateLspri.do" method="post" onsubmit="return changeLspri();">
	 	<div class="modal-body">
	 		权&nbsp;&nbsp;重:<input name="lspri" value="60" id="updateLspriValue"/><span id="updateLspriError" class="help-inline" style="color: red;">*</span><br/>
	 		<input type="hidden" name="topicid" value="${topicid}">
	 		<input type="hidden" name="pageNum" value="${pageNum }">
	 		<input type="hidden" name="type"  value="${type }">
	 		<input type="hidden" name="indexid" value="" id="updateLspriIndexid"/>
	 	</div>
	 	<div class="modal-footer">
	 		<button type="button" class="btn" onclick="$('#updateLspri').modal('hide');">关闭</button>
	 		<button type="submit" class="btn btn-primary" >提交</button>
	 	</div>
 	</form>
 </div>
 
 <div class="modal" id="modifyPushRecord" style="display: none">
 	<div class="modal-header">
 		<h3>修改属性</h3>
 	</div>
 	<form id="modifyPushRecordForm" action="modifyPushRecord.do" method="post" onsubmit="return checkModifyPushRecord();">
	 	<div class="modal-body">
	 		标&nbsp;&nbsp;题：<input onfocus="$('#modifyPushRecordTitleError').html('');" name="title" id="modifyPushRecordTitle"/><span id="modifyPushRecordTitleError" class="help-inline" style="color: red;">*</span><br/>
	 		副标题：<input value="" name="subTitle" id="modifyPushRecordSubTitle"/><br/>
	 		图&nbsp;&nbsp;片：<input name="img" id="modifyPushRecordImg"/><br/>
	 		U&nbsp;R&nbsp;L：<input onfocus="$('#modifyPushRecordUrlError').html('');" name="url" id="modifyPushRecordUrl"><span id="modifyPushRecordUrlError" class="help-inline" style="color: red;">*</span><br/>
	 		权&nbsp;&nbsp;重：<input name="lspri" value="60" id="modifyPushRecordLspri"/><span id="modifyPushRecordLspriError" class="help-inline" style="color: red;">*</span><br/>
	 		<input type="hidden" name="topicid" value="${topicid}">
	 		<input type="hidden" name="pageNum" value="${pageNum }">
	 		<input type="hidden" name="type"  value="${type }">
	 		<input type="hidden" name="indexid" id="modifyRushRecordIndexid" value=""/>
	 	</div>
	 	<div class="modal-footer">
	 		<button type="button" class="btn" onclick="$('#modifyPushRecord').modal('hide');">关闭</button>
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
	
	function checkAddSubTopic(){
		if($('#addTopicName').val()==''){
			$('#topicNameError').html('不能为空');
			return false;
		}
		$('addTopicForm').submit();
	}
	
	function showModifyTopic(topicid){
		$('#modifyTopic').modal('show');
		$.getJSON("/admin/topic/getTopicInfo.do?topicid="+topicid, function(json){
			$('#modifyTopicName').val(json.topicName);
				}); 
	}
	
	function checkModifyTopic(){
		if($('#modifyTopicName').val()==''){
			$('#modifyTopicNameError').html('不能为空');
			return false;
		}
		$('modifyTopic').submit();
	}
	
	function selectAll(){
		var selectList =$('#listFrom :checkbox');
		for(var i=0;i<selectList.length;i++){
			selectList[i].checked = $('#selectItem').attr('checked');
		}
	}
	
	function deleteTopics(){
		if(confirm('确认删除所选栏目')){
			if($('input[name="topicSelect"]:checked').length>0){
				$('#dolot').attr('action','/admin/topic/deleteSubTopic.do');
				$('#dolot').submit();
			}else{
				alert('请选择一个栏目');
			}
		}
	}
	
	function checkAddBlankDoc(){
		if($('#addBlankTitle').val()==''){
			$('#addBlankTitleError').html('标题不能为空');
			return false;
		}
		if($('#addBlankUrl').val()==''){
			$('#addBlankUrlError').html('URL不能为空');
			return false;
		}
		if($('#addBlankLspri').val()==''){
			$('#addBlankLspriError').html('权重不能为空');
			return false;
		}
		if($('#addBlankLspri').val()<0||$('#addBlankLspri').val()>100){
			$('#addBlankLspriError').html('权重必须为0-100');
			return false;
		}
	}
	
	function showChangeLspri(indexid){
		$('#updateLspriIndexid').val(indexid);
		$('#updateLspri').modal('show');
	}
	
	function changeLspri(){
		if($('#updateLspriValue').val()==''){
			$('#updateLspriError').html('权重不能为空');
			return false;
		}
		if($('#updateLspriValue').val()<0||$('#updateLspriValue').val()>100){
			$('#updateLspriError').html('权重必须为0-100');
			return false;
		}
	}
	
	function modifyPushRecord(indexid){
		$.post("getPushRecord.do",{
			'indexid':indexid},
			function(result){
				$('#modifyPushRecordTitle').val(result.title);
				$('#modifyPushRecordSubTitle').val(result.subTitle);
				$('#modifyPushRecordLspri').val(result.lspri);
				$('#modifyPushRecordImg').val(result.img);
				$('#modifyPushRecordUrl').val(result.url);
				$('#modifyRushRecordIndexid').val(result.indexid);
				
			});
		$('#modifyPushRecord').modal('show');
	}
	
	function checkModifyPushRecord(){
		if($('#modifyPushRecordTitle').val()==''){
			$('#modifyPushRecordTitleError').html('标题不能为空');
			return false;
		}
		if($('#modifyPushRecordUrl').val()==''){
			$('#modifyPushRecordUrlError').html('URL不能为空');
			return false;
		}
		if($('#modifyPushRecordLspri').val()==''){
			$('#modifyPushRecordLspriError').html('权重不能为空');
			return false;
		}
		if($('#modifyPushRecordLspri').val()<0||$('#addBlankLspri').val()>100){
			$('#modifyPushRecordLspriError').html('权重必须为0-100');
			return false;
		}
	}
	
	function deletePushRecords(){
		if(confirm('确认删除所选推送文章')){
			if($('input[name="pushRecordSelect"]:checked').length>0){
				$('#dolot').attr('action','/admin/topic/deletePushRecord.do');
				$('#dolot').submit();
			}else{
				alert('请选择一篇文章');
			}
		}
	}
	
	function copyPushRecords(){
		if(confirm('确认复制所选推送文章')){
			if($('input[name="pushRecordSelect"]:checked').length>0){
				$('#dolot').attr('action','/admin/topic/copyPushRecord.do');
				$('#dolot').submit();
			}else{
				alert('请选择一篇文章');
			}
		}
	}
	
	function cutPushRecords(){
		if(confirm('确认剪切所选推送文章')){
			if($('input[name="pushRecordSelect"]:checked').length>0){
				$('#dolot').attr('action','/admin/topic/cutPushRecord.do');
				$('#dolot').submit();
			}else{
				alert('请选择一篇文章');
			}
		}
	}
	
	function pastePushRecords(){
				$('#dolot').attr('action','/admin/topic/pastePushRecord.do');
				$('#dolot').submit();
	}
	
</script>
</html>