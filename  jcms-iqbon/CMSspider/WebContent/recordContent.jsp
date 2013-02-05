<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.iqbon.spider.web.RecordController" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.String" %>
<%@ page import="com.iqbon.spider.domain.Record" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%
	RecordController recordController = new RecordController();
	String linkUrl = recordController.getStringParameter(request, "linkUrl", null);
	String sourceUrl = recordController.getStringParameter(request, "sourceUrl", null);
	String type = recordController.getStringParameter(request, "type", "view");
	if(linkUrl==null||sourceUrl==null){
	  out.println("输入URL为空");
	  return;
	}
	
	Record record = recordController.getCrawlContentBySourceAndUrl(linkUrl, sourceUrl);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
<title>采集记录内容</title>
</head>
<body>
		<ul class="nav nav-tabs">
			<li class="<%=type.equals("view")?"active":""%>"><a href="recordContent.jsp?linkUrl=<%=linkUrl%>&sourceUrl=<%=sourceUrl%>&type=view">视图</a></li>
			<li class="<%=type.equals("code")?"active":""%>"><a href="recordContent.jsp?linkUrl=<%=linkUrl%>&sourceUrl=<%=sourceUrl%>&type=code">代码</a></li>
		</ul>
<h4><%=record.getTitle() %></h4>
<%if(type.equals("view")){ %>
<p><%=record.getContent() %></p>
<%} %>
<%if(type.equals("code")){ %>
<pre><%=StringEscapeUtils.escapeHtml4(record.getContent()) %></pre>
<%} %>
</body>
</html>