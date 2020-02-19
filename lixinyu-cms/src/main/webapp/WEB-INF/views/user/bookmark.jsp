<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/cms.css" rel="stylesheet">
</head>
<body>
<form>
 <input type="hidden" name="pageNum" value="1" value="${pageInfo.pageNum}">
</form>
<table class="table" bgcolor="white">
  	<tr>
  		<td colspan="2">我的收藏夹</td>
  	</tr>
  	
		<c:forEach items="${list }" var="list">
		<tr>
			<td>
				<a href="" onclick="gotoArticle(${list.url})" >${list.text }</a>
				${list.created }
			</td>
			<td><input type="button" value="删除" class="btn btn-danger" onclick="bookmarkDel(${list.id})"></td>
			</tr>
		</c:forEach>
  	
</table>
<div class="col-10">
		<jsp:include page="../common/page.jsp"></jsp:include>
		<div class="alert alert-danger" role="alert" style="display: none"></div>
</div>
<body>

</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/checkbox.js?v1.00"></script>
<script type="text/javascript">
function gotoPage(pageNo){
	$("[name=pageNum]").val(pageNo);
	reload($("form").serialize());
}
function gotoArticle(url) {
	window.open(url);
}
function bookmarkDel(id) {
	alert(id);
	$.post("bookmarkDel",{id:id},function(flag){
		if(flag){
			alert("删除成功");
		}else{
			alert("删除失败")
		}
	},"json")
	
}
</script>
</html>