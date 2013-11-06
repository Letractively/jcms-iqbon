<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="/ckeditor/_samples/sample.css" />
<style type="text/css">
			div {
				margin-bottom: 9px;
			}
</style>
<title>文章新建修改页</title>
</head>
<body>
<form id="docForm" action="" method="post">
<div style="bpadding-top: 10px;padding-left: 20px;margin-top: 6px;width: 78%;float: left;border-width:0 1px 0 0;border-color: #AAAAAA;border-style: none solid none none;">
	<div>
    	<a href='/admin/topic/topicPage.do?pageNum=${pageNum }&type=0&topicid=${topicid}' title="返回目录">返回目录</a>
    </div>
	<div>
		<label>标题:</label><input value="${doc.title }" type="text" class="span9" name="title">
	</div>
	<div>
		<label>摘要:</label><textarea rows="" cols="" class="span9" name="digest">${doc.digest }</textarea>
	</div>
	<div>
		<label>正文</label>
		<textarea rows="" cols="30" class="span10"  style="height: 350px" name="content" id="content">${doc.content }</textarea>
	</div>
	<div class="form-inline ">
		<label>关键字:</label><input value="${doc.keyword }" type="text" name="keyword">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<div class="form-inline ">
	<label>编辑:</label><input type="text" name="reporter" <c:if test="${doc eq null }"> value="${user.nickName }"</c:if><c:if test="${!(doc eq null) }"> value="${doc.reporter }"</c:if>>
	<input type="hidden" name="topicid" value="${topicid }">
	<input type="hidden" value="${pageNum }" name="pageNum">
	<input type="hidden" value="${type }" name="type">
	<input type="hidden" value="${doc.docid }" name="docid">
	<input type="hidden" value="${doc.url }" name="url">
	<input type="hidden" value="0" name="status" id="status">
	</div>
</div>
<div style="padding-top: 10px;padding-right: 20px;margin-top: 6px;width: 17% ;float: right;">
	<div>当前状态：<c:if test="${doc.status==0 }">未发布</c:if><c:if test="${doc.status==1 }">发布</c:if></div>
	<div class="form-inline ">
		<label>文章模板:</label>
		<select name="modelName" class="span2">
			<c:forEach items="${docModelList }" var="model">
				<option <c:if test="${doc.modelName==model.modelName }">selected="selected"</c:if>  value="${model.modelName }">${model.title }</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-inline ">
		<button class="btn-small" type="button">预览</button>&nbsp;&nbsp;
		<button class="btn-small" type="button" onclick="save()">保存</button>&nbsp;&nbsp;
	</div>
	<div class="form-inline ">
		<button class="btn-small btn-primary" type="button" onclick="setPublish();save();">发布</button>&nbsp;&nbsp;
		<button class="btn-small btn-danger" type="button" onclick="deleteDoc()">删除文章</button>&nbsp;&nbsp;
	</div>
</div>
</form>
</body>

<script src="/js/jquery.js"></script>
<script src="/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	function save(){
		<c:if test="${doc==null||doc=='' }">
			$('#docForm').attr('action','/admin/doc/addDoc.do');
		</c:if>
		<c:if test="${doc!=null&&doc!='' }">
		$('#docForm').attr('action','/admin/doc/modifyDoc.do');
		</c:if>
		$('#docForm').submit();
	}
	
	function deleteDoc(){
		if(confirm("删除该篇文章和相关推送记录？")){
			$('#docForm').attr('action','/admin/doc/deleteDoc.do');
			$('#docForm').submit();
		}
	}
	
	function setPublish(){
		$('#status').val('1');
	}
	CKEDITOR.replace('content'); 
</script>
</html>