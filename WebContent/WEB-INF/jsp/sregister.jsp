<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>商家注册页面</title>
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

$(function(){
	$.ajax({
		url : "${pageContext.request.contextPath}/seller/gettype",
		success:function(res){
				//res=eval("("+res+")");
				var  str="<option value='-1'>please select</option>";
				for (var i = 0; i < res.length; i++) {
					str=str+"<option value='"+res[i].addrid+"'>"+res[i].addr+"</option>";
				}
				$("#addr").html(str);
		}
	});
});


var xmlHttp=null;
function check(name){
	//第一步：创建异步通信对象
	if(xmlHttp==null){
		xmlHttp=new XMLHttpRequest();
	}
	//第二步：设定回调函数处理请求结果 
	xmlHttp.onreadystatechange=function(){
		if (xmlHttp.readyState==4 && xmlHttp.status==200){
			var res=xmlHttp.responseText;
			//document.getElementById("ts").innerHTML=res;    //显示账号注册
			if(res.indexOf("have")!=-1){
				document.getElementById("sregister").disabled="disabled";
				document.getElementById("ts").innerHTML="账号已被占用";
			}else {
				document.getElementById("sregister").disabled="";
				document.getElementById("ts").innerHTML="账号可用";
			}
			
			//alert(xmlHttp.responseText);
		}
	}
	//第三步：发送异步请求
	//规定请求的类型、URL 以及是否异步处理请求。
	xmlHttp.open("get","${pageContext.request.contextPath}/seller/check/"+name);
	//将请求发送到服务器。
	xmlHttp.send(null);
}
function isPass(str){
	if(str==null||str.length<6||str.length>12)
		return false;
	var count_n=0;
	var count_l=0;
	for(var i=0;i<str.length;i++){
		var ch=str.charAt(i);
		if(ch>='0'&&ch<='9'){
			count_n++;
		}else if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')){
			count_l++;
		}else{
			return false;
		}
	}
	return count_n>0&&count_l>0;
}
function confrim(frm){
	var pass=frm.pass.value;
	var cfpass=frm.cfpass.value;
	var tel=frm.tel.value;
	if(tel.length!=11){
		//alert("fd ");
		document.getElementById("ts").innerHTML="电话号码格式不正确：11位数字";
		return false;
	}
	if(!isPass(pass)){
		document.getElementById("ts").innerHTML="密码格式不正确.提示：数字加字母长度为【6-12】";
		return false;
	}
	if(pass!=cfpass){
		document.getElementById("ts").innerHTML="两次输入密码不一致";
		return false;
	}
	return true;
}
</script>
</head>
<body id="account-login" data-com="pagecommon" data-page-id="100040"
	data-iswebview='false'>
	<header class="navbar">
		<span class="nav-header h1">商家注册</span>
	</header>
	<div id="login">
		<dl class="list">
			<dd class="nav">
				<ul class="taba taban samfix" data-com="tab">
					<li class="active"><a class="react" id="ts">帐号注册</a>
				</ul>
			</dd>
		</dl>
		<form onsubmit="return confrim(this)" id="normal-login-form" action="${pageContext.request.contextPath}/seller/sregister" autocomplete="off" 
			method="post" style="display: block">
			<input type="hidden" id="op" name="op" value="sregister">
			<dl class="list list-in">
				<dd class="visibale">
					<dl>
						<dd class="dd-padding kv-line-r">
							<input id="name" class="input-weak J-input" type="text"
								placeholder="姓名" name="name" onkeyup="check(this.value)"  required />
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
						<dd class="dd-padding kv-line-r">
							<input id="pass" class="input-weak J-input" type="password"
								placeholder="密码" name="pass" required />
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
						<dd class="dd-padding kv-line-r">
							<input id="cfpass" class="input-weak J-input" type="password"
								placeholder="确认密码" name="cfpass" required />
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
						<dd class="dd-padding kv-line-r">
							<input id="tel" class="input-weak J-input" type="number"
								placeholder="电话" name="tel" required />
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
						<dd class="dd-padding kv-line-r">
							<select id="addr" class="input-weak J-input" 
								 name="addr" required ></select>
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
					</dl>
				</dd>
			</dl>
			<div class="btn-wrapper">
				<button type="submit" disabled="disabled" id="sregister"
					class="btn btn-normal btn-block disabled mj_login login-btn">注册</button>
			</div>
		</form>
	</div>
	<ul class="subline" style="text-align: center;">
        <li><a href="${pageContext.request.contextPath}/seller/slogin">立即登录</a>
    </ul>
</body>
</html>