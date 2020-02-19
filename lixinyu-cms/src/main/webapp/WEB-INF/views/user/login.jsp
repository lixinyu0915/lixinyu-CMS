<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.8.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/cms.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/index.css" rel="stylesheet">
</head>
<body style="background:url('http://www.wallpaperup.com/uploads/wallpapers/2012/10/21/20181/cad2441dd3252cf53f12154412286ba0.jpg');">
<nav class="nav justify-content-start" style="background-color: #222;">
		<a class="nav-link navbar-brand" href="#">
			<img src="https://v4.bootcss.com/docs/4.3/assets/brand/bootstrap-solid.svg" width="30" height="30" alt="">
		</a>
		<a class="nav-link" href="/">网站首页</a> 
	</nav>
	<form>

<div id="login" align="center">

        <h3 class="text-center text-white pt-5">用户登录界面</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-10">
                    <div id="login-box" class="col-md-12" style="opacity: 0.8">
                        <div class="alert alert-danger" role="alert" style="display: none"></div>
                        <h3 class="text-center text-info">Login</h3>
                        <span style="color:red;">${error}</span>
                            <div class="form-group col-mt-10">
                                <label for="username" class="text-info" ><br></label><br>
                                <input type="text" name="username" id="username" class="form-control" placeholder="请输入用户名">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info"><br></label><br>
                                <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码">
                            </div>
                            <div class="form-group form-check">
						    	<input type="checkbox" class="form-check-input" id="isMima" name="isMima" value="1">
						    	<label class="form-check-label" for="exampleCheck1">记住用户名</label>
						 	 </div>
                            <div class="form-group"> 
                                <!-- <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br> -->
                                <input type="button" value="登录" class="btn btn-info btn-md" onclick="login()">
                                <label for="exampleInputPassword1">没有帐号，去<a href="/user/register">注册</a></label>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.1.12.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function login(){
		//空判断
		var username = $("#username").val();
		var password = $("#password").val();
		if(username==null || password==""){
			$(".alert").html("请输入用户名和密码");
			$(".alert").show();
			return;
		}
		$(".alert").hide();
		//后台验证
		var formData = $("form").serialize();
		$.post("/user/login",formData,function(res){
			if(res.result){
				//验证通过跳转到后台首页
				location.href="/";
			}else{
				//否则提示错误信息
				$(".alert").html(res.message);
				$(".alert").show();
			}
		})
	}
</script>
</body>
</html>