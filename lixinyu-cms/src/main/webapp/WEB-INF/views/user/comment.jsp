<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cms后台登录</title>
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/cms.css" rel="stylesheet">
</head>
<form>
 <input type="hidden" name="pageNum" value="1" value="${pageInfo.pageNum}">
</form>
<body>
<table class="table" bgcolor="white">
  <thead>
    <tr>
     <th scope="col"><input type="checkbox" id="chkALL" name="chkALL"></th>
      <th scope="col">#</th>
      <th scope="col">文章标题</th>
      <th scope="col">评论内容</th>
      <th scope="col">评论时间</th>
      <th scope="col">操作</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${list}" var="el">
    <tr>
    	<td><input type="checkbox" name="chk_list" value="${el.id}"></td>
    	<td>${el.id}</td>
    	<td>
    	<c:if test="${USER_SESSION_ID!=null && USER_SESSION_ID.headimg!=null }">
				<img src="${USER_SESSION_ID.headimg }" style="border-radius: 15px" width="30" height="30" alt="">
				${el.title}
		</c:if>
    	</td>
    	<td>${el.content}</td>
    	<td>${el.created}</td>
    	 <td>
	      	<c:if test="${item.status==2 || item.status==-1 }">
	      		<button type="button" class="btn btn-primary" onclick="edit('${el.id}')">编辑</button>
	      	</c:if>
	      		<button type="button" class="btn btn-primary" onclick="view('${el.id}')">查看</button>
	      </td>
    </tr>
    </c:forEach>
  </tbody>
</table>
<div class="col-10">
<input type="button" value="批量删除" class="btn btn-danger" onclick="delAlert()">
		<jsp:include page="../common/page.jsp"></jsp:include>
		<div class="alert alert-danger" role="alert" style="display: none"></div>
</div>	

<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/checkbox.js?v1.00"></script>
<script type="text/javascript">
function gotoPage(pageNo){
	$("[name=pageNum]").val(pageNo);
	reload($("form").serialize());
}
function delAlert(){
	var ids = getCheckboxIds();
	if(ids==""){
		$(".alert").html("请选择要删除的评论");
		$(".alert").show();
		return;
	}
	$.post(
		"/user/deleteComment",
		{ids:ids},
		function(rs){
			if(rs){
				alert("删除成功");
				reload();
			}else{
				alert("删除失败");
				reload();
			}
		}
	)
}
function edit(id){
	openPage("/article/add?id="+id);
}
function view(id){
	window.open("/article/"+id+".html");
}
</script>
</body>
</html>