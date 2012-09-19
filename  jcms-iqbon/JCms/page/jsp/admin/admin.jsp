<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>JCMS后台管理系统</title>
</head>
<frameset rows="40,*,35" frameborder="0" border="0" framespacing="0">
<frame src="/admin/user/userSessionInfo.do" scrolling="no"/>
<frameset cols="170,*">
<frame src="/admin/common/leftContentManager.do" scrolling="auto" name="left"/>
<frame src="/admin/main.do" scrolling="auto" name="mainFrame" />
</frameset>
<frame src="/jsp/admin/footer.html" scrolling="no"  />
</frameset>
</html>