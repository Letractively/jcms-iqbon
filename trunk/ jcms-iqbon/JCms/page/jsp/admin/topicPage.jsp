<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    	<li><a href="/admin/doc/showAddModifyDoc.do?topicid=${topicid}&pageNum=${pageNum }&type=${type }">发布文章</a></li>
    	<li><a href="#" onclick="$('#addBlankDoc').modal('show');">发布空文章</a></li>
    </ul>
    <button class="btn" onclick="deletePushRecords();" title="删除推送记录">删除</button>
    <button class="btn" onclick="fullDeletePushRecords();" title="完全删除推送记录">完全删除</button>
    <button class="btn" onclick="copyPushRecords()" title="复制文章或空文章">复制</button>
    <button class="btn" onclick="cutPushRecords()" title="剪切文章或空文章">剪切</button>
    <button class="btn" onclick="pastePushRecords()" title="粘贴文章或空文章">粘贴</button>
    <button class="btn btn-success" onclick="republicPushRecords();" title="刷新文章">刷新</button>
    </div>
</c:if>
<c:if test="${type==1 }">
	<div class="btn-group">
		<button class="btn" onclick="window.location.href='/admin/model/addModifyModelPage.do?topicid=${topicid}&pageNum=${pageNum }&type=${type }&modelType=0'" title="新建模板">新建</button>
		<button class="btn" onclick="deleteModels();" title="删除模板">删除</button>
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
 			<th> 地址 </th>
 			<th>最后修改</th>
 			<th>发布时间</th>
 			<th> 权重</th>
 			<th> 用户 </th>
 		</tr>
 	</thead>
 	<tbody id="listFrom">
 	<!-- 先显示文章列表 -->
 	<c:forEach var="topic" items="${subTopicList}" varStatus="topicStatus">
 		<tr>
 			<td><input type="checkbox" name="topicSelect" value="${topic.topicId }" id="topic_${topicStatus.index }"/></td>
 			<td><a href="/admin/topic/topicPage.do?pageNum=${pageNum }&type=${type }&topicid=${topic.topicId }"><img src="/images/folderopen.gif"/>&nbsp;&nbsp;${topic.topicName}</a></td>
 			<td></td>
 			<td>${topic.lastModify}</td>
 			<td></td>
 			<td></td>
 			<td><a href="#">${topic.modifyUser}</a></td>
 		</tr>
 	</c:forEach>
 	<!-- 文章列表 -->
 	<c:if test="${type==0 }">
	 	<c:forEach var="pushRecord" items="${pushRecordList}" varStatus="pushRecordStatus">
	 		<tr>
	 			<td><input type="checkbox" name="pushRecordSelect" value="${pushRecord.indexid }" id="topic_${pushRecordStatus.index }"/></td>
	 			<td>
	 			<c:if test="${pushRecord.type!=1 }">
	 				<a target="blank" href="${pushRecord.url }">
	 					<img src="/images/blank_file.gif"/>&nbsp;&nbsp;${pushRecord.title}
	 				</a>&nbsp;&nbsp;<a onclick="modifyPushRecord('${pushRecord.indexid}')" href="#" title="修改属性" class="btn-small">
	 					<img src="/images/edit.gif"/>
	 				</a>
	 				</a>&nbsp;&nbsp;<a onclick="modifyPushRecord('${pushRecord.indexid}')" href="#" title="修改属性" class="btn-small">
	 					<img src="/images/refresh.png"/>
	 				</a>
	 			</c:if>
	 			<c:if test="${pushRecord.type==1 }">
	 				<a target="blank" href="${pushRecord.url }" >
	 					<img src="/images/file.gif"/>&nbsp;&nbsp;
	 				</a>
	 				<a href="/admin/doc/showAddModifyDoc.do?topicid=${topicid }&pageNum=${pageNum}&type=${type}&docid=${pushRecord.docid}">
	 					${pushRecord.title}
	 				</a>
	 				</a>&nbsp;&nbsp;<a onclick="modifyPushRecord('${pushRecord.indexid}')" href="#" title="修改属性" class="btn-small">
	 					<img src="/images/edit.gif"/>
	 				</a>
	 				<a onclick="repushRecord('${pushRecord.indexid}')" href="#" title="刷新页面" class="btn-small">
	 					<img src="/images/refresh.png"/>
	 				</a>
	 			</c:if>	
	 			</td>
	 			<td>${pushRecord.url }</td>
	 			<td>${pushRecord.lastModify}</td>
	 			<td>${pushRecord.addDate}</td>
	 			<td><a onclick="showChangeLspri('${pushRecord.indexid}')" id="pushRecord_lspri_${pushRecord.indexid }" href="#">${pushRecord.lspri }</a></td>
	 			<td><a href="#">${pushRecord.modifyUser}</a></td>
	 		</tr>
	 	</c:forEach>
 	</c:if>
 	<!-- 模板列表 -->
 	<c:if test="${type==1 }">
 		<c:forEach var="model" items="${modelList}" varStatus="modelStatus">
	 		<tr>
	 			<td><input type="checkbox" name="modelSelect" value="${model.modelName }" id="model_${modelStatus.index }"/></td>
	 			<td><a href="/admin/model/addModifyModelPage.do?modelName=${model.modelName }&topicid=${topicid}&pageNum=${pageNum }&type=${type }&modelType=0"><img src="/images/model.gif"/>&nbsp;&nbsp;${model.title}</a>&nbsp;&nbsp;</td>
	 			<td>${model.url }</td>
	 			<td>${model.lastModify}</td>
	 			<td>${model.addTime}</td>
	 			<td></td>
	 			<td><a href="#">${model.modifyUser}</a></td>
	 		</tr>
	 	</c:forEach>
 	</c:if>
 	</tbody>
 </table>
 </form>
	<a title="第一页" href="/admin/topic/topicPage.do?pageSize=${pageSize }&pageNum=1&type=${type }&topicid=${topicid}"><i class="icon-step-backward"></i></a>
	<a title="上一页" href="/admin/topic/topicPage.do?pageSize=${pageSize }&pageNum=${prePageNum }&type=${type }&topicid=${topicid}"><i class="icon-chevron-left"></i></a> 
	第<input id="goPageNum" onkeypress="if (event.keyCode==13){goPage();}" type="text" class="input-mini"  value="${pageNum }"/>页<button type="submit" onclick="goPage();" class="btn">Go</button><a href="#" id="goPage" style="display: none;"><span id="goPageContent">goPage</span></a>
	<a title="下一页" href="/admin/topic/topicPage.do?pageSize=${pageSize }&pageNum=${nextPageNum }&type=${type }&topicid=${topicid}"><i class="icon-chevron-right"></i></a>
	<a title="最后页" href="/admin/topic/topicPage.do?pageSize=${pageSize }&pageNum=${totalPageNum }&type=${type }&topicid=${topicid}"><i class="icon-step-forward"></i></a> 
	共${totalPageNum }页
	每页<input id="goPageSize" onkeypress="if (event.keyCode==13){goPage();}" type="text" class="input-mini"  value="${pageSize }"/>条

<div class="modal" id="addTopic" style="display: none">
 	<div class="modal-header">
 		<h3>新建子栏目</h3>
 	</div>
 	<form id="addTopicForm" action="addSubTopic.do" method="post" onsubmit="return checkAddSubTopic();">
	 	<div class="modal-body">
	 		名称:<input onfocus="$('#topicNameError').html('');" name="topicName" id="addTopicName"><span id="topicNameError" class="help-inline" style="color: red;"></span>
	 		<div>栏目导航:<input name="topicNav" id="addTopicNav"/></div>
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
	 		<div>栏目导航:<input name="topicNav" id="modifyTopicNav"> 同步到子栏目<input type="checkbox" name="toSubTopic"/></div>
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
 
  <div class="modal" id="republicProgress" style="display: none">
	<div class="modal-header">
 		<h3>刷新文章</h3>
 	</div>
	<div class="modal-body">
	<div class="progress progress-striped active">
    	<div class="bar" id="refreshProgress" style="width:0%;">刷新中...</div>
    </div>
    <hr/>
    <div id="refreshProgressContent"></div>
    <div class="modal-footer">
	 		<button type="button" class="btn" onclick="stopRepublicPushRecords();">结束</button>
	</div>
    </div>
 </div>
</body>
<script src="/js/jquery.js"></script>
<script  src="/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="/js/bootstrap-dropdown.js"></script>
<script src="/js/bootstrap-button.js"></script>
<script type="text/javascript">
	function goPage(){
		var goPageNum = $('#goPageNum').val();
		var goPageSize = $('#goPageSize').val();
		if(!$.isNumeric(goPageNum )){
			alert("只能输入数字");
			return;
		} 
		var goUrl = "/admin/topic/topicPage.do?type=${type }&topicid=${topicid}&pageNum="+goPageNum+"&pageSize="+goPageSize;
		$('#goPage').attr('href',goUrl);
		$('#goPageContent').click();
		
	}

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
			$('#modifyTopicNav').val(json.topicNav);
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
	
	function repushRecord(indexid){
		$.post("republicPushRecord.do",{
			'indexid':indexid},
			function(result){
				if(result.result=='success'){
					alert("刷新成功");
				}else{
					alert(result.message);
				}
				
			});
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
	
	function fullDeletePushRecords(){
		if(confirm('确认完全删除所选文章')){
			if($('input[name="pushRecordSelect"]:checked').length>0){
				$('#dolot').attr('action','/admin/topic/deletePushRecordWithDocs.do');
				$('#dolot').submit();
			}else{
				alert('请选择一篇文章');
			}
		}
	}
	
	function deleteModels(){
		if(confirm('确认删除所选模板')){
			if($('input[name="modelSelect"]:checked').length>0){
				$('#dolot').attr('action','/admin/topic/deleteModels.do');
				$('#dolot').submit();
			}else{
				alert('请选择一个模板');
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
	
	//批量刷新推送记录
	function republicPushRecords(){
		if($('input[name="pushRecordSelect"]:checked').length>0){
			$('#republicProgress').modal('show');
			var postLen = $('input[name="pushRecordSelect"]:checked').length;
			var content = "";
			for(var i=0;i<postLen;i++){
				var postid =$($('input[name="pushRecordSelect"]:checked')[i]).val();
				$.ajax({
	                url : 'republicPushRecord.do',
	                data:{'indexid':postid},
	                cache : false,
	                async : false,
	                type : "POST",
	                dataType : 'json',
	                success : function (result){
	                	if(result.result=='success'){
	                		content+=postid+"刷新成功<br/>";
	                		$('#refreshProgressContent').html(content);
						}else{
							content+=postid+"刷新失败<br/>";
	                		$('#refreshProgressContent').html(content);
						}
						$('#refreshProgress').css('width',(i+1)/postLen*100+'%');
						if(postLen>(i+1)){
							$('#refreshProgress').html('刷新中('+(i+1)+'/'+postLen+')');
						}else{
							$('#refreshProgress').html('完成');
						}
	                }
	            });
			}
		}else{
			alert('请选择一篇文章');
		}
	}
	
	function stopRepublicPushRecords(){
		$('#republicProgress').modal('hide');
		$('#refreshProgress').css('width','0%');
		$('#refreshProgress').html('');
		$('#refreshProgressContent').html('');
	}
	
</script>
</html>