<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/jquery.validity.css" rel="stylesheet">
<link href="css/spider.css" rel="stylesheet">
<title>添加修改数据源</title>
</head>
<body>
	<span><a href="http://regexpal.com/" target="_blank">正则表达式校验</a></span>
	<h4>添加数据源</h4>
	<form class="form-horizontal" id="sourceForm">
		<div class="control-group">
			<label class="control-label">地址</label>
			<div class="controls">
			<input onblur="$('#urlHelp').hide();" onfocus="$('#urlHelp').show();" id="url" type="text" name="url"/>
			<span id="urlHelp" class="help-inline blueText hide">输入采集数据源地址</span></div>
		</div>
		<div class="control-group">
			<label class="control-label">内容匹配规则</label>
			<div class="controls">
			<input onblur="$('#contentMatcherHelp').hide();" onfocus="$('#contentMatcherHelp').show();" title="输入内容匹配的正则表达式" id="contentMatcher" type="text" name="contentMatcher"/>
			<span id="contentMatcherHelp" class="help-inline blueText hide">输入内容匹配的正则表达式</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述</label>
			<div class="controls">
			<input type="text" name="description"/>
			</div>
		</div>
		
		<hr size="4"/>
		<div class="control-group">
			<div class="controls">
			<input onclick="addMatcher();" type="button" value="添加链接匹配规则"/>&nbsp;&nbsp;
			<span class="help-inline">点击增加文章链接匹配的正则表达式</span>
			</div>
		</div>
		<div  class="control-group" id="matchers">
			<div id="matcher_0">
			<label class="control-label">链接父节点</label>
			<input title="链接列表的父节点" type="text" value="" id="matcherParent_0"/>
			链接匹配规则
			<input title="链接匹配的正则表达式" type="text" value="" id="matcherRule_0"/>
			描述
			<input type="text" value="" id="matcherDesc_0"/>
			<input type="button" value="-" onclick="cancelMatchers(0)" title="取消"/>
		</div>
		</div>
		
		<hr size="4"/>
		<div class="control-group">
			<div class="controls">
			<input onclick="addReplace();" type="button" value="添加替换规则"/>&nbsp;&nbsp;
			<span class="help-inline">点击增加文章内容替换的正则表达式</span>
			</div>
		</div>
		<div  class="control-group" id="replaces">
		</div>
		<hr/>
		 <div class="control-group">
		 	<div class="controls">
				<input class="btn" type="button" value="测试抓取效果"/>&nbsp;&nbsp;
				<input class="btn" type="submit" value="提交"/>
				<input onclick="window.location.href='sourceList.jsp'" class="btn" type="button" value="返回"/>
			</div>
		</div>
		<input type="hidden" value="" name="replaces">
		<input type="hidden" value="" name="matchers">
	</form>
	
	<input type="hidden" id="replaceNum" value="0">
	<input type="hidden" id="matcherNum" value="1">
	<div id="replaceContent" style="display: none;">
		<div id="replace_num">
		<label class="control-label">替换</label>
			<input title="输入需要替换的内容" type="text" value="" id="replaceMatcher_num"/>为
			<input title="替换为" type="text" value="" id="replaceto_num"/>
		<input type="button" value="-" onclick="cancelReplace(num)" title="取消"/>
		</div>
	</div>
	<div id="matchersContent" style="display: none;">
		<div id="matcher_num">
		<label class="control-label">链接父节点</label>
			<input title="链接列表的父节点" type="text" value="" id="macherParent_num"/>
			链接匹配规则
			<input title="链接匹配的正则表达式" type="text" value="" id="matcherRule_num"/>
			描述
			<input type="text" value="" id="matcherDesc_num"/>
			<input type="button" value="-" onclick="cancelMatchers(num)" title="取消"/>
		</div>
	</div>
</body>
<script src="js/jquery.js"></script>
<script src="js/validity.js"></script>
<script type="text/javascript">
//增加替换规则
	function addReplace(){
		var replaceNum = $("#replaceNum").val();
		
		var content = $("#replaceContent").html();
		content = content.replace(/num/g,replaceNum+"");
		$("#replaces").append(content);
		replaceNum++;
		$("#replaceNum").val(replaceNum);
	}
	//删除替换规则
	function cancelReplace(num){
		$("#replace_"+num).remove();
		var replaceNum = $("#replaceNum").val();
		replaceNum--;
		$("#replaceNum").val(replaceNum);
	}
	
	//增加匹配规则
	function addMatcher(){
		var matcherNum = $("#matcherNum").val();
		
		var content  = $("#matchersContent").html();
		content = content.replace(/num/g,matcherNum+"");
		$("#matchers").append(content);
		matcherNum++;
		$("#matcherNum").val(matcherNum);
	}
	//删除匹配规则
	function cancelMatchers(num){
		var matcherNum = $("#matcherNum").val();
		matcherNum--;
		if(matcherNum==0){
			alert("至少需要一个匹配规则");
			return;
		}
		$("#matcher_"+num).remove();
		
		$("#matcherNum").val(matcherNum);
	}
	//验证
	 $(function() {  
		 $("#sourceForm").validity(function() { 
			 $("#url").require("url不能为空").match("url","必须是正确的URL格式");
			 $("#contentMatcher").require("内容匹配规则不能为空");
			 var matcherNum = $("#matcherNum").val();
			 for(var i=0;i<matcherNum;i++){
				 $("#matcherParent_"+i).require("链接匹配的父节点不能为空");
			 }
			 var replaceNum = $("#replaceNum").val();
			 for(var i=0;i<replaceNum;i++){
				 $("#replaceMatcher_"+i).require("替换内容不能为空");
			 }
		 });
		 }); 
</script>
</html>