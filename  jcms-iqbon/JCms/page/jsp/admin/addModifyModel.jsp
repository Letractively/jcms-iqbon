<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="/css/bootstrap.min.css" rel="stylesheet">
		<style type="text/css">
			div {
				margin-bottom: 9px;
			}
		</style>
		<title>模板编辑</title>
	</head>
	<body>
	<form action="" id="addModifyForm" method="post">
	<div>
	<div style="bpadding-top: 10px;padding-left: 20px;margin-top: 6px;width: 78%;float: left;border-width:0 1px 0 0;border-color: #AAAAAA;border-style: none solid none none;">
		<div>
			<c:if test="${topicid!=''&&topicid!=null}">
    			<a href='/admin/topic/topicPage.do?pageNum=${pageNum }&type=1&topicid=${topicid}' title="返回目录">返回目录</a>
    		</c:if>
    		<c:if test="${topicid==''||topicid==null}">
    			<a href='/admin/model/showDocModelList.do' title="返回目录">返回目录</a>
    		</c:if>
    	</div>
		<div class="form-inline ">
			<label>模板名称</label>
			<input value="${model.modelName }" <c:if test="${model!=''&&model!=null }"> readonly="true" </c:if> name="modelName" id="modelName" class="span4" type="text" placeholder="只能包含数字、字母和下划线">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>后缀</label>
			<select name="suffix" class="span1" id="suffix">
				<c:forEach items="${suffixList}" var="suffix">
					<option <c:if test="${model.extname==suffix.value }">selected="selected"</c:if> value="${suffix.value }">${suffix.key }</option>
				</c:forEach>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>刷新频率</label>
			<select name="refresh" class="span1" id="refresh">
				<c:forEach items="${refreshList }" var="refresh">
				<option <c:if test="${model.rate==refresh.value }">selected="selected"</c:if> value="${refresh.value }">${refresh.key }</option>
				</c:forEach>
			</select>分钟&nbsp;&nbsp;&nbsp;&nbsp;
				<label>过期天数</label>
			<select name="timeout" class="span1" id="timeout">
				<c:forEach items="${timeoutList }" var="timeout">
				<option <c:if test="${model.timeout==timeout.value }">selected="selected"</c:if> value="${timeout.value }">${timeout.key }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-inline ">
			<label>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题</label>
			<input value="${model.title }" name="title" class="span4" type="text" id="title"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<label>关键字</label>
			<input value="${model.keyword }" name="keyword" id="keyword" class="span4" type="text" placeholder="用于SEO优化">
		</div>
		<div class="form-inline "><textarea name="content" style="width: 750px;height: 450px">${model.content }</textarea></div>
		<input type="hidden" value="${topicid }" name="topicid">
		<input type="hidden" value="${pageNum }" name="pageNum">
		<input type="hidden" value="${type }" name="type">
		<input type="hidden" value="${modelType }" name="modelType"/>
		<input type="hidden" value="${model.url }" name="url"/>
		<input type="hidden" value='<c:if test="${model.status!='' }">${model.status }</c:if><c:if test="${model.status==''||model.status==null }">0</c:if>' id="status" name="status"/>
		</div>
		
		<div style="padding-top: 10px;padding-right: 20px;margin-top: 6px;width: 17% ;float: right;">
			<c:if test="${topicid!=''&&topicid!=null }">
			<div>当前状态：<c:if test="${model.status==0 }">未发布</c:if><c:if test="${model.status==1 }">发布</c:if></div>
			</c:if>
			<div class="form-inline ">
					<button class="btn-small" type="button">预览</button>&nbsp;&nbsp;
					<button class="btn-small" type="button" onclick="save()">保存</button>&nbsp;&nbsp;
					<c:if test="${topicid!=''&&topicid!=null }">
					<button class="btn-small btn-primary" type="button" onclick="setPublish();save();">发布</button>
					</c:if>
			</div>
			<hr/>
			<button onclick="deleteModel();" class="btn-small btn-danger" type="button">放到回收站</button>
			<hr/>
			
			<c:if test="${model!='' }">
			操作日志：
				<c:forEach items="${modelLogList}" var="log" varStatus="logStatus">
					<c:if test="${logStatus.index<=6 }">
					<div><a href="#">${log.time},${log.userName}${log.content }</a></div>
					</c:if>
				</c:forEach>
				<c:if test="${fn:length(modelLogList)>6 }">
						<button class="btn-small" type="button">查看全部日志</button>&nbsp;&nbsp;
				</c:if>
			</c:if>
		</div>
		</div>
		</form>
	</body>
	<script src="/js/jquery.js"></script>
	<script type="text/javascript">
		function save(){
			if(checkForm()){
				<c:if test="${model!='' }">
					$('#addModifyForm').attr('action','/admin/model/modifyModel.do');
				</c:if>
				<c:if test="${model==''||model==null }">
				$('#addModifyForm').attr('action','/admin/model/addModel.do');
				</c:if>
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
		
		function deleteModel(){
			$('#addModifyForm').attr('action','/admin/model/deleteModel.do');
			$('#addModifyForm').submit();
		}
		
		function setPublish(){
			$('#status').val('1');
		}
	</script>
</html>