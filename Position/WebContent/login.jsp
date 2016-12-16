<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎登陆</title>
    <link rel="stylesheet" type="text/css" href="css/login.css" />
</head>

<% Integer error=(Integer)session.getAttribute("error"); 
	if(error==null){
		error=-2;
	}
%>
<body>
<img id="background" src="img/back2.jpg"/>
<div id="header" >
    <img src="img/logo.png" />
</div>
<div id="backlogin"></div>
<div id="loginerror">
    <%
        if(error==-1){
            out.println( "密码错误，请从新输入");
        }
        if(error==0){
        	out.println( "账号不存在");
        }
    %>
</div>
<div id="container">
    <div class="login">
        <table border="0">
            <tbody>
            <form action="login" method="post">
                <tr>
                    <td>用户名:</td>
                    <td colspan="2"><input name="id" class="input" required/></td>
                </tr>
                <tr>
                    <td>密 码:</td>
                    <td colspan="2"><input type="password" name="password" class="input"  required/></td>
                </tr>
                <tr>
                    <td></td>
                    <td ><input type="checkbox" value="1"  class="checkbox"/>记住密码</td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td ><input type="submit" value="登 录" class="button"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="text-align:center;"><a href="###" class="link">忘记密码</a></td>
                    <td><a href="register.jsp" class="link">注册</a></td>
                </tr>
            </form>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

