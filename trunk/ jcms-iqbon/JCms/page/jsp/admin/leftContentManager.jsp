<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>JCMS</title>
<link href="/css/bootstrap.min.css" rel="stylesheet"/>
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet"/>
</head>
<body>
<div class="well" style="background-color:#FFFFFF;border:0px;padding-left:1px;">
        <ul class="nav nav-list" style="padding-left:1px;padding-right:1px;">
          <li>
          <span id="topicMenu"> 
          <a href="javaScript:showTopicList();">
          <i class="icon-book"></i>
          	内容管理
          </a>
          </span> 
          </li>
          <li><a href="javaScript:showSysMenu();"><i class="icon-pencil"></i>系统设置</a>
          	<div id="sysMenu" style="padding-left:10px;display: none;">
				<div><a href="/admin/code/codePage.do" target="mainFrame">参数管理</a></div>
				<div><a href="/admin/model/showDocModelList.do" target="mainFrame">文章模板</a></div>
				<div><a href="/admin/ftp/ftpSetting.do" target="mainFrame">FTP设置</a></div>		
			</div>
          </li>
        </ul>

</div>
<script language="javascript" src="/js/bootstrap.min.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
</body>
<script language="javascript">
//栏目列表是否显示
var topTopicListShow = false;
var sysMenuShow = false;
var loadingContent = "<p id='loader'><img src='/images/ajax-loader.gif'/></p>";
//显示栏目列表
function showTopicList(){
	if(!topTopicListShow){
		$("#topicMenu").after(loadingContent);
		$.getJSON("/admin/topic/topTopicList.do", function(json){
		var content  = "";
				if(json.topicList.length>0){
					content +="<div id='topicList'>";
					for(var i = 0; i<json.topicList.length;i++){
						content += '<p id="tree'+json.topicList[i].topicId+'"><span><a href="javaScript:void(0);"><img src="/images/closedFloder.gif"><img src="/images/folderopen.gif"></a></span>&nbsp;<a href="/admin/topic/topicPage.do?pageNum=1&type=0&topicid='+json.topicList[i].topicId +'&" target="mainFrame">'+json.topicList[i].topicName + '</a></p>';
					}
					content +="</div>";
				}
				$("#loader").remove();
				$("#topicMenu").after(content);
			}); 
			topTopicListShow = true;
		}else{
			$("#topicList").hide();
			topTopicListShow = false;
		}
}
//显示系统菜单
function showSysMenu(){
	if(sysMenuShow){
		$('#sysMenu').hide();
	}else{
		$('#sysMenu').show();
	}
	
}

</script> 
</html>