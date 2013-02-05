<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.iqbon.spider.web.RecordController" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.String" %>
<%
	RecordController recordController = new RecordController();
	String sourceUrl = recordController.getStringParameter(request, "sourceUrl", null);
	if(sourceUrl==null){
	  out.println("输入URL为空");
	  return;
	}
	List<String> linkList  = recordController.getCrawlRecordBySourceId(sourceUrl);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
<title>采集记录列表</title>
</head>
<body>
<%
	if(linkList==null||linkList.size()==0){
	  out.println("没有匹配到任何链接");
	}
%>
<div class="well" style="background-color:#FFFFFF;padding-top: 10px;border-width: 1px;border-color: #D5D5D5;">
	<table class="table">
 		<tbody id="listFrom">
 			<%for(String link :linkList){ %>
 			<tr>
 				<td><a href="<%=link %>"  target="_blank"><%=link %></a>&nbsp;&nbsp;&nbsp;&nbsp;
 				<a href="recordContent.jsp?linkUrl=<%=link%>&sourceUrl=<%=sourceUrl%>" target="recordContent">查看抓取内容</a></td>
 			</tr>
 			<%} %>
 		</tbody>
	</table>
</div>
</body>
</html>