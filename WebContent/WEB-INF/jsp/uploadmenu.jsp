<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单维护页面</title>
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
var xmlHttp=null;
function check1(name){
	//第一步：创建异步通信对象
	if(xmlHttp==null){
		xmlHttp=new XMLHttpRequest();
	}
	//第二步：设定回调函数处理请求结果 
	xmlHttp.onreadystatechange=function(){
		if (xmlHttp.readyState==4 && xmlHttp.status==200){
			var res=xmlHttp.responseText;
			if(res.indexOf("have")!=-1){
				//有，按钮不可用
				document.getElementById("menu").disabled="disabled";
				document.getElementById("ts").innerHTML="菜名已被使用";
			}else{
				//没有，按钮可用
				document.getElementById("menu").disabled="";
				document.getElementById("ts").innerHTML="菜名可用";
			}
			//document.getElementById("ts").innerHTML=xmlHttp.responseText;
		}
	}
	//第三步：发送异步请求
	xmlHttp.open("get","${pageContext.request.contextPath}/sellmenu/check/"+name);
	xmlHttp.send(null);


}
</script>
</head>
<body id="account-login" data-com="pagecommon" data-page-id="100040"
	data-iswebview='false'>
	<header class="navbar">
		<span class="nav-header h1">菜单</span>
	</header>
	<div id="login">
		<dl class="list">
			<dd class="nav">
				<ul class="taba taban samfix" data-com="tab">
					<li class="active"><a class="react" id="ts">菜单维护</a>
				</ul>
			</dd>
		</dl>
		<form id="normal-login-form" action="menu" autocomplete="off" 
			method="post" enctype="multipart/form-data" style="display: block" >
			<input type="hidden" id="op" name="op" value="menu">
			<dl class="list list-in">
				<dd class="visibale">
					<dl>
						<dd class="dd-padding kv-line-r">
							<input id="name"
								class="input-weak J-login-name J-input J-account" type="text"
								placeholder="请输入菜名" name="name" onkeyup="check1(this.value)" value="" required>
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
						<dd class="dd-padding kv-line-r">
							<input id="price" class="input-weak J-input" type="text"
								placeholder="请输入价格" name="price" required />
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
						<dd class="dd-padding kv-line-r">
							<input id="pic" class="input-weak J-input" type="file"
								placeholder="图片" name="pic" required />
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
						<dd class="dd-padding kv-line-r">
							<input id="remark" class="input-weak J-input" type="text"
								placeholder="请输入备注" name="remark" required />
							<div class="to-del J-to-del" style="display: none"></div>
						</dd>
					</dl>
				</dd>
			</dl>
			<div class="btn-wrapper">
				<button type="submit" disabled="disabled" id="menu" onclick="upload()"
					class="btn btn-normal btn-block disabled mj_login login-btn">增加</button>
			</div>
		</form>
	</div>
	<ul class="subline" style="text-align: center;">
        <li><a href="login.html">买家登录</a>
    </ul>
</body>
<script type="text/javascript">
//转到uploadmenu页面
function upload(){
	$.ajax(
			{
				url:"${pageContext.request.contextPath}/seller/slogin",
				success:function(result){
					if(result=="mtc_null"){
							alert("请先登录");
					}else{
						$("#normal-login-form").submit();
					}
				}
			}
	);
}
</script>
</html>