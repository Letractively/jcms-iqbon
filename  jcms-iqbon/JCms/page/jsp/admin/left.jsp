<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>JCMS</title>
<link  type="text/css" rel="stylesheet" href="/css/main.css"/>
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
</head>
<body>
<div id="leftMenu">
<ul>
<li>
<div id="topicMenu" onclick="javaScript:showTopicList(this);">栏目</div>

</li>
</ul>
</div>
</body>
<script language="javascript">
//栏目列表是否显示
var topTopicListShow = false;
var loadingContent = "<span id='loader'><img src='/images/ajax-loader.gif'/></span>";
//显示栏目列表
function showTopicList(obj){
	if(!topTopicListShow){
		$("#"+obj.id).after(loadingContent);
		$.getJSON("/admin/topic/topTopicList.do", function(json){
		var content  = "";
				if(json.topicList.length>0){
				content = "<ul id='topicList'>";
					for(var i = 0; i<json.topicList.length;i++){
						content += '<li><span onclick="javaScript:void(0);"><img src="/images/closedFloder.gif"><img src="/images/folderopen.gif"></span><a href="/admin/topic/topicPage.do?pageNum=1&type=1&topicid='+json.topicList[i].topicId +'&" target="mainFrame">'+json.topicList[i].topicName + '</a></li>';
					}
				content += "</ul>";
				}
				$("#loader").remove();
				$("#"+obj.id).after(content);
			}); 
			topTopicListShow = true;
		}else{
			$("#topicList").hide();
			topTopicListShow = false;
		}
}

</script>
</html>