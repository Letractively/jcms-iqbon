<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.iqbon.spider.web.SourceController" %>
<%@ page import="com.iqbon.spider.domain.Source" %>
<%@ page import="java.util.List" %>
<%
	   
	SourceController controller = new SourceController();
	String keyword = controller.getStringParameter(request, "keywrod", null);
	int pageNum = controller.getIntParameter(request, "pageNum", 0);
	int totalNum  = controller.getSourceNum(keyword);
	
	List<Source> sources = controller.getAllSource(keyword,pageNum,totalNum);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
<title>自动抓取</title>
</head>
<body>
<div style="padding-top: 10px;padding-left: 10px;">
		<input type="text" >
		<input type="button" value="查找" class="btn">
		<input class="btn" onclick="window.location.href='addModifySource.jsp'" data-toggle="modal" type="button" value="添加采集数据源">
</div>
<div class="well" style="background-color:#FFFFFF;padding-top: 10px;border-width: 1px;border-color: #D5D5D5;">
	<table class="table">
		<thead style="background-color: silver;">
	 		<tr>
	 			<th><input id="selectItem" type="checkbox" onclick="selectAll();"/></th>
	 			<th> url </th>
	 			<th> 描述 </th>
	 			<th> 操作</th>
	 		</tr>
 		</thead>
 		<tbody id="listFrom">
 			<%for(Source source :sources){ %>
 			<tr>
 				<td><input type="checkbox" name="sourceSelect"></td>
 				<td><a href="<%=source.getUrl() %>" target="_blank"><%=source.getUrl() %></a></td>
 				<td><%=source.getDescription() %></td>
 				<td><a href="recordList.jsp?sourceUrl=<%=source.getUrl()%>" target="list">查看匹配列表</a></td>
 			</tr>
 			<%} %>
 		</tbody>
	</table>
</div>
</body>
</html>