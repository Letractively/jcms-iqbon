<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="/css/jquery.treeview.css" rel="stylesheet">
</head>
<body>
<div style="padding-top: 10px;">
		<input class="btn" data-toggle="modal" onclick="$('#addGroup').modal('show');" type="button" value="添加参数组">
</div>
<div class="well" style="background-color:#FFFFFF;padding-top: 10px;border-width: 1px;border-color: #D5D5D5;">
        <table class="table">
	        <tbody>
	        <c:forEach  var="group" items="${groupList}">
	        	 <tr onmouseover="$('#group_${group.groupName}').show();" onmouseout="$('#group_${group.groupName}').hide();">
		        	 <td id="${group.groupName }_${group.key }_${group.parentKey}">
		        	 	<a href="javaScript:showSubCode('${group.groupName}','${group.key }','${group.parentKey}');"><i class="icon-list-alt"></i>${group.value }</a>
		        	 </td>
		        	 <td><span style="display: none;" id="group_${group.groupName }">
		        	 <a href="/admin/code/deleteCodeGroup.do?groupName=${group.groupName  }">删除</a>&nbsp;&nbsp;
		        	 <a href="#">修改</a>&nbsp;&nbsp;
		        	 <a href="javaScript:showAddCode('${group.groupName}','${group.groupName}')">添加参数</a>&nbsp;&nbsp;
		        	 </span></td>
	        	 </tr>
	        </c:forEach>
         </tbody>
        </table>
 </div>
 <div class="modal" id="addGroup" style="display: none">
 	<div class="modal-header">
 		<h3>添加参数组</h3>
 	</div>
 	<form id="addCodeForm" action="addCode.do" method="post" onsubmit="return checkAddGroup();">
	 	<div class="modal-body">
	 		组名:<input onfocus="$('#descriptionError').html('');" name="description" id="description"><span id="descriptionError" class="help-inline" style="color: red;"></span>
	 	</div>
	 	<div class="modal-footer">
	 		<button type="button" class="btn" onclick="$('#addGroup').modal('hide');">关闭</button>
	 		<button type="submit" class="btn btn-primary" >提交</button>
	 	</div>
 	</form>
 </div>
 <div class="modal" id="addCode" style="display: none">
 	<div class="modal-header">
 		<h3>添加参数</h3>
 	</div>
	 	<div id="addCodeContent" class="modal-body">
	 		key&nbsp;&nbsp;:<input onfocus="$('#groupNameError').html('');" name="codeKey" id="codeKey"/><span id="codeKeyError" class="help-inline" style="color: red;"></span><br>
	 		value:<input onfocus="$('#descriptionError').html('');" name="codeValue" id="codeValue"><span id="codeValueError" class="help-inline" style="color: red;"></span>
	 		<input type="hidden" value="" name="groupName" id="addCodeGroupName" >
	 		<input type="hidden" value="" name="parentKey" id="addCodeParentKey" >
	 	</div>
	 	<div class="modal-footer">
	 		<button type="button" class="btn" onclick="$('#addCode').modal('hide');">关闭</button>
	 		<button type="button" onclick="checkAddCode();" class="btn btn-primary" >提交</button>
	 	</div>
 </div>
 <div class="modal" id="showSubCode" style="display: none">
 	<div class="modal-header">
 		<h3>参数列表</h3>
 	</div>
	 	<div id="showSubCodeContent" class="modal-body">
	 		<img  src="/images/ajax-loader.gif">
	 	</div>
	 	<div class="modal-footer">
	 		<button type="button" class="btn" onclick="$('#showSubCode').modal('hide');">关闭</button>
	 	</div>
 </div>
</body>
<script  src="/js/jquery.js" type="text/javascript"></script>
<script  src="/js/jquery.treeview.js" type="text/javascript"></script>
<script  src="/js/bootstrap.min.js" type="text/javascript"></script>
<script  src="/js/bootstrap-modal.js" type="text/javascript"></script>
<script type="text/javascript">
var loadingContent = '<img  src="/images/ajax-loader.gif">';
function checkAddGroup(){
	if($('#description').val()==''){
		$('#descriptionError').html('不能为空');
		return false;
	}
	$('addCodeForm').submit();
}

function checkAddCode(){
	if($('#codeKey').val()==''){
		$('#codeKeyError').html('不能为空');
		return false;
	}
	if($('#codeValue').val()==''){
		$('#codeValueError').html('不能为空');
		return false;
	}
	var codeKey = $('#codeKey').val();
	var codeValue = $('#codeValue').val();
	var addCodeGroupName = $('#addCodeGroupName').val();
	var addCodeParentKey = $('#addCodeParentKey').val();
	$('#addCodeContent').empty();
	$('#addCodeContent').append(loadingContent);
	$.post("addSubCode.do",{'codeKey':codeKey,
							'codeValue':codeValue,
							'groupName':addCodeParentKey,
							'parentKey':addCodeParentKey},function(result){
								if(result.success){
									$('#addCode').modal('hide');
									alert('添加成功');
								}else{
									alert('添加失败');
								}
							});
}

function showAddCode(groupName,parentKey){
	$('#addCodeGroupName').val(groupName);
	$('#addCodeParentKey').val(parentKey);
	$('#addCode').modal('show');
	
}

function showSubCode(groupName,key,parentKey){
	$('#showSubCode').modal('show');
	$.post("getSubCode.do",{
		'groupName':groupName,
		'parentKey':key},function(result){
			var content = '<ul id="'+groupName+'_'+key+'_'+parentKey+'" class="filetree treeview">';
			var codeList = result;
			if(codeList.length==0){
				content= "没有数据";
			}
			for(var i=0;i<codeList.length;i++){
				content += '<li id="'+codeList[i].groupName+'_'+codeList[i].key+'_'+codeList[i].parentKey+'">'
				+'<span class="file">'
				+'<a href="javaScript:showSecSubCode(\''+codeList[i].groupName+'\',\''+codeList[i].key+'\',\''+codeList[i].parentKey+'\');">'
				+'key='+codeList[i].key+'&nbsp;&nbsp;value='+codeList[i].value
				+'</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="showAddCode(\''+codeList[i].groupName+'\',\''+codeList[i].key+'\');$(\'#showSubCode\').modal(\'hide\');">添加</a></span></li>';
			}
			content +='</ul>'
			$('#showSubCodeContent').empty();
			$('#showSubCodeContent').append(content);
			if(codeList.length>0){
				$('#'+groupName+'_'+key+'_'+parentKey).Treeview();
			}
		});
}

function showSecSubCode(groupName,key,parentKey){
	$.post("getSubCode.do",{
		'groupName':groupName,
		'parentKey':key},function(result){
			var content = '<ul>';
			var codeList = result;
			if(codeList.length==0){
				return;
			}
			for(var i=0;i<codeList.length;i++){
				content += '<li id="'+codeList[i].groupName+'_'+codeList[i].key+'_'+codeList[i].parentKey
				+'" onclick="showSubCode(\''+codeList[i].groupName+'\',\''+codeList[i].key+'\',\''+codeList[i].parentKey+'\');">'
				+'<span>key='+codeList[i].key+'&nbsp;&nbsp;value='+codeList[i].value
				+'</span></li>';
			}
			content +='</ul>'
			$('#'+codeList[i].groupName+'_'+codeList[i].key+'_'+codeList[i].parentKey).empty();
			$('#'+codeList[i].groupName+'_'+codeList[i].key+'_'+codeList[i].parentKey).append(content);
		});
}

</script>
</html>