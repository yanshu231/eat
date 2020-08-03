<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<meta name="applicable-device" content="mobile">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name='apple-touch-fullscreen' content='yes'>
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<!-- 重要不能删 -->
<link href="${pageContext.request.contextPath}/static/css/mtc.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<style>
.nav {
	text-align: center;
}
.taba li.active {
	font-family: PingFangSC-Medium;
	font-size: 0.25rem;
	color: #222222;
}
.taba li a.react {
	font-size: 0.25rem;
	color: #222222;
}
.navbar {
	height: 1.01rem;
	display: -webkit-box;
	display: -ms-flexbox;
	position: relative;
	background-image: linear-gradient(135deg, #FFD000 0%, #FFBD00 100%);
	border: none;
}
.navbar .h1.nav-header {
	font-family: PingFangSC-Regular;
	font-size: 0.32rem;
	color: #222222;
}
@media ( max-device-height : 480px) {
	.msg-doc {
		bottom: auto;
		top: 20%;
	}
	.msg-doc .msg-bd {
		padding: .2rem;
	}
}
</style>
<script type="text/javascript">
function check(frm) {
	var memid=document.getElementById("memid").value;
	var mempass=frm.mempass.value;
	if (memid=="" || mempass=="") {
		document.getElementById("display").innerHTML="用户或密码不能为空";
		return false;
	}
}
</script>
</head>
<body id="account-login" data-com="pagecommon" data-page-id="100040"
	data-iswebview='false'>
	<header class="navbar">
		<span class="nav-header h1">登录</span>
	</header>
	<div id="login">
		<dl class="list">
			<dd class="nav">
				<ul class="taba taban samfix" data-com="tab">
					<li class="active"><a class="react" id="ts">帐号登录</a>
				</ul>
			</dd>
		</dl>
		<form id="normal-login-form" action="${pageContext.request.contextPath}/member/login" autocomplete="off"
			onsubmit="return check(this)" method="post" style="display: block">
			<input type="hidden" id="op" name="op" value="login">
			<abbr id="display">${requestScope.error}</abbr>
			<dl class="list list-in">
				<dd class="visibale">
					<dl>
						<dd class="dd-padding kv-line-r">
							<input id="memid"
								class="input-weak J-login-name J-input J-account" type="text"
								placeholder="账户名/Email" name="memid" value="" required>
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
						<dd class="dd-padding kv-line-r">
							<input id="mempass" class="input-weak J-input" type="password"
								placeholder="请输入您的密码" name="mempass" required />
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
					</dl>
				</dd>
			</dl>
			<div class="btn-wrapper">
				<button type="submit"  value="login"
					class="btn btn-normal btn-block disabled mj_login login-btn">登录</button>
			</div>
		</form>
	</div>
	<ul class="subline" style="text-align: center;">
        <li><a href="${pageContext.request.contextPath}/member/register">立即注册</a>
    </ul>
</body>
</html>