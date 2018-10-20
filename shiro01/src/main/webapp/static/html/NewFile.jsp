<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();//项目名称
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ path +"/";
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=basePath %>static/css/mycss.css" type="text/css">
   
    <title>登录页面</title>
</head>

<style>
   
</style>

<body style="background-color: #1c77ac;background-image: url(../images/light.png);background-repeat: no-repeat;background-position: center top; overflow: hidden">  

    <div class="loginbox">
        <ul>
            <li><span>用户名</span>&nbsp;<input type="text" class="loginuser" id="name" name="loginId" value=""></li>
            <li><span>密&nbsp;&nbsp;&nbsp;&nbsp;码</span>&nbsp;<input type="text" class="loginpwd" id="password" name="password" value=""></li>
            <li><button class="loginbtn" onclick="login()">登录</button>
                <button class="logincancel">取消</button>
            </li>
            <li><a href="#" class="register">注册</a><a href="#" class="forgetpwd">忘记密码</a></li>
        </ul>

    </div>


</body>

<script  src="../js/jquery.js"></script>
<script  src="../js/myjs.js"></script>

</html>