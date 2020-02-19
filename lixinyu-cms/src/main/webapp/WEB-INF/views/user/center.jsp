<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/cms.css" rel="stylesheet">
<!-- <link href="/public/css/index.css" rel="stylesheet"> -->

<link rel="stylesheet" href="<%=request.getContextPath() %>/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=request.getContextPath() %>/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="<%=request.getContextPath() %>/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="<%=request.getContextPath() %>/kindeditor/plugins/code/prettify.js"></script>

<script>
	KindEditor.ready(function(K) {});
</script>
</head>
<!-- 头信息 -->
	<jsp:include page="../common/user/head.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row offset-1" style="margin-top: 15px;">
			  <div class="col-2">
			  	<!-- 左侧导航 -->
			    <jsp:include page="../common/user/left.jsp"></jsp:include>
			  </div>
			  <div class="col-8">
			    <div class="tab-content" id="v-pills-tabContent">
				  
			  	</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.1.12.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/cms.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.fr.js"></script>
</body>
</html>