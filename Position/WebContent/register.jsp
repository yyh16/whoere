<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>register</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
<!--header S-->
<div class="header">
    <div class="banner">
        <a class="logo" href="register"></a>
        <div class="logo-title">欢迎注册</div>
        <div class="have-account">
            已有账号
            <a href="login.jsp">请登录</a>
        </div>
    </div>
</div>
<!--header E-->

<!--container S-->
<div class="container">
    <div class="main">
        <div class="reg-form f1">
            <form id="register-form" action="register" method="post" onsubmit="return validate()">
                <div id="form-regName" class="form-item" style="z-index:13;">
                    <label>用 户 名</label>
                    <input id="regName" class="field" area-required="true" name="name" autocomplete="off"
                           maxlength="20" placeholder="" type="text" onBlur="validateRegName()" />
                </div>
                <div class="input-tip"><span id="regNameMsg"></span></div>
                <div id="form-pwd"class="form-item" style="z-index:12">
                    <label>设置密码</label>
                    <input id="pwd" class="field" name="pwd" maxlength="20" type="password"  onBlur="validatePwd()" />
                </div>
                <div class="input-tip"><span id="pwdMsg"></span></div>
                <div id="form-pwdRepeat" class="form-item">
                    <label>确认密码</label>
                    <input id="pwdRepeat" class="field" name="repwd" maxlength="20" type="password" onBlur="validateConf()" />
                </div>
                <div class="input-tip"><span id="pwdRepeatMsg"></span></div>
                <div class="item-phone-wrap">
                    <div id="form-phone" class="form-item form-item-phone" style="z-index:12;">
                        <label>中国 +86</label>
                        <input id="phone" class="field" name="phone" maxlength="11" type="text" autocomplete="off" onBlur="validatePhone()" />
                    </div>
                    <div class="input-tip"><span id="phoneMsg"></span></div>
                    <div class="orEmail">
                        或
                        <a href="#" style="color:#38f">邮箱验证</a>
                    </div>
                </div>
                <div class="item-email-wrap">
                    <div id="form-email" class="form-item">
                        <label>邮箱验证</label>
                        <input id="email" class="field" name="email" type="text" autocomplete="off" onBlur="validateEmail()" />
                    </div>
                    <div class="input-tip"><span id="emailMsg"></span></div>
                    <div class="orPhone">
                        或
                        <a href="#" style="color:#38f">手机验证</a>
                    </div>
                </div>
                <div id="form-authcode"class="form-item">
                    <label>验证码</label>
                    <input id="authcode" class="field form-authcode" name="authcode" maxlength="6" type="text" autocomplete="off" onBlur="validateAuthCode()" />
                    <img id="imgAuthCode" class="img-code" src="imgcode" />
                </div>
                <div class="input-tip"><span id="authcodeMsg"></span></div>
                <div>
                    <button class="btn-register" type="submit">立即注册</button>
                </div>

            </form>
        </div>
        <div class="reg-other">
            <img src="img/flag.jpg" alt="Welcome">
        </div>
    </div>
</div>
<!--container E-->
<!--footer S-->
<div class="footer"></div>
<!--footer E-->
<script type="text/javascript" src="js/password.js"></script>

</body>

</html>
