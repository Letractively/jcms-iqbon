<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="/css/jquery.validator.css" rel="stylesheet">
<title>FTP设置页</title>
</head>
<body>
	<form class="form-horizontal" action="updateFtpSetting.do">
	<label class="control-label">是否开启FTP推送</label>
		<div class="control-group">
			<div class="controls">
				否<input type="radio" name="enable" value="0"
				<c:if test="${!ftp.enable }"> checked="checked"</c:if> id="ftpdisable">
				是<input type="radio" name="enable" value="1"
				<c:if test="${ftp.enable }"> checked="checked"</c:if> id="ftpenable">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ftpip" >IP</label>
			<div class="controls">
				<input data-rule="required;ip" data-rule-ip="[/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/,'请检查IP格式']" type="text" value="${ftp.ftpIp }" name="ftpip" id="ftpip" placeholder="输入IP或者域名">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ftpport">端口</label>
			<div class="controls">
				<input type="text"  value="${ftp.ftpPort }" name="ftpport" id="ftpport" placeholder="21">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="username">用户名</label>
			<div class="controls">
				<input type="text"  value="${ftp.username }" name="username" id="username" placeholder="用户名">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="password">密码</label>
			<div class="controls">
				<input type="text"  value="${ftp.password }" name="password" id="password" placeholder="密码">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ftpPath">发布路径</label>
			<div class="controls">
				<input type="text"  value="${ftp.ftpPath }" name="ftpPath" id="ftpPath" placeholder="需要发布的路径">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ftpRefresh">刷新频率</label>
			<div class="controls">
				<select id="ftpRefresh" name="ftpRefresh">
					<option value="0"
					 <c:if test="${ftp.ftpPushRate ==0}"> selected="selected"</c:if>>每分钟</option>
					<option value="1"
					 <c:if test="${ftp.ftpPushRate ==1}"> selected="selected" </c:if>>每小时</option>
					<option value="2"
					 <c:if test="${ftp.ftpPushRate ==2}"> selected="selected" </c:if>>每天</option>
				</select>
			</div>
		</div>
		<div class="control-group">
		<label class="control-label" > </label>
		<div class="controls">
		<button type="submit" class="btn">保存</button>
		<button type="button" class="btn">立刻上传</button>
		</div></div>
	</form>
</body>
<script type="text/javascript">
	function pushFTP(){
		
	}
</script>
<script src="/js/jquery.js"></script>
<script src="/js/validator/jquery.validator.js"></script>
<script src="/js/validator/zh_CN.js"></script>
</html>