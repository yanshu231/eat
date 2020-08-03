<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单页面</title>
<meta name="applicable-device" content="mobile">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name='apple-touch-fullscreen' content='yes'>
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<!-- 重要不能删 -->
<link href="${pageContext.request.contextPath}/static/css/mtc.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
</head>
<body id="index" data-com="pagecommon" data-page-id="100001"
	data-iswebview='false'>

	<style>
.navbar {
	height: 1.01rem;
	display: -webkit-box;
	display: -ms-flexbox;
	position: relative;
	background-image: linear-gradient(135deg, #FFD000 0%, #FFBD00 100%);
	border: none;
}

.navbar .nav-city {
	display: inline-block;
	vertical-align: middle;
	text-align: center;
	font-family: PingFangSC-Medium;
	font-size: 0.28rem;
	color: #222222;
	margin: 0.31rem 0 0.31rem 0.37rem;
}

.navbar .box-search {
	-webkit-box-flex: 1;
	-ms-flex: 1;
	line-height: .64rem;
	-webkit-box-sizing: border-box;
	position: relative;
	margin-top: .2rem;
	opacity: 0.9;
	background: #FFFFFF;
	border-radius: 0.18rem;
	width: 4.85rem;
	height: 0.64rem;
	color: #999999;
}

.navbar .box-search span {
	font-size: .26rem;
	box-sizing: border-box;
	width: 100%;
	color: #999999;
	padding-left: 0rem;
	display: contents;
}

.nav-city img.icn_down {
	width: 0.16rem;
	height: 0.09rem;
	padding-right: 0.315rem;
	margin-left: -0.1rem;
	margin-top: -0.07rem;
}

.nav-wrap-right img.icn_mine {
	width: 0.48rem;
	height: 0.48rem;
}

a.react img.icn_search {
	width: 0.28rem;
	height: 0.28rem;
	padding: 0.15rem 0.13rem 0.16rem 0.27rem;
}

a.react .nav-btn img.icn_home {
	width: 0.48rem;
	height: 0.48rem;
}

a.back img.icn_back {
	width: 0.2rem;
	height: 0.36rem;
}

.navbar .h1.nav-header {
	font-family: PingFangSC-Regular;
	font-size: 0.32rem;
	color: #222222;
}

.nav-btn img.icn_search_2 {
	width: 0.48rem;
	height: 0.48rem;
	padding-right: 0.2rem;
}

.nav-btn img.icn_edit {
	width: 0.48rem;
	height: 0.48rem;
}

.nav-btn img.icn_menu {
	width: 0.48rem;
	height: 0.48rem;
}

img.icn_drop_home, img.icn_drop_mine, img.icn_drop_search {
	width: 0.48rem;
	height: 0.48rem;
}

.navbar .nav-dropdown {
	position: absolute;
	right: .04rem;
	top: 1rem;
	width: 2rem;
	opacity: 1;
	-webkit-transition: height .1s;
	background: #FFFFFF;
	color: #222222;
	box-shadow: 0 0.12rem 0.6rem 0 rgba(196, 196, 196, 0.60);
	border-radius: 0.1rem;
	overflow: visible;
	z-index: 100;
	display: none;
}

.nav-dropdown li {
	height: .83rem;
	line-height: .83rem;
	border-bottom: none;
	text-align: center;
}

.nav-dropdown.active {
	display: block !important;
	height: 2.5rem;
	width: 2rem;
}

.navbar .nav-dropdown:before {
	content: "";
	background-image:
		url('https://p0.meituan.net/travelcube/dc0f73a6ecc53ad44da9d5bc10ceff96421.png');
	background-size: contain;
	display: block;
	width: 0.38rem;
	height: 0.12rem;
	position: absolute;
	top: -0.12rem;
	right: 0.2rem;
	box-shadow: 0 0 0 0 rgba(196, 196, 196, 0.60);
	border-radius: 0.05rem;
}
</style>

	<header class="navbar">
		<div class="nav-wrap-left">
			<a href="#" class="react"> <span class="nav-city">南京<space></space>
					<img
					src="https://p0.meituan.net/travelcube/45c79a92755b54adc9dc1c4682b123b3201.png"
					class="icn_down" />
			</span>
			</a>
		</div>
		<div class="box-search J-box-search">
			<input id="keyword" type="text" style="width: 100%;"
				placeholder="请输入菜名" value="" />
		</div>
		<div class="nav-wrap-right">
			<a class="react" href="javascript:void(0)" onclick="getMenu()"> <span
				class="nav-btn"> <img
					src="https://p0.meituan.net/travelcube/641521461179df7cfb88738dd1ea11ec1031.png"
					class="icn_mine" />
			</span>
			</a>
		</div>
	</header>

	<div id="guessLike" class="guess-like">
		<dl class="list">

			<div style="font-size: 20px; color: #ffc100; padding-top: 2px;">&nbsp&nbsp猜你喜欢</div>
			<hr />
			<mtc id="caidan">
			
			 </mtc>
			<dd class="db">
				
			</dd>

		</dl>
	</div>
	<div align="center">
		<br> <br> <br> <br> <a href="${pageContext.request.contextPath}/member/login" style=""><button
				style="width: 20%;" type="submit" id="login"
				class="btn btn-normal btn-block disabled mj_login login-btn">登录</button></a>
		<a href="${pageContext.request.contextPath}/member/register"><button style="width: 20%;" type="submit"
				id="login"
				class="btn btn-normal btn-block disabled mj_login login-btn">注册</button></a>
		<button style="width: 30%;" type="submit" id="login"
			onclick="disCart()"
			class="btn btn-normal btn-block disabled mj_login login-btn">购物车</button>
	</div>

</body>
<script type="text/javascript">
	function getMenu() {
		var name = $("#keyword").val();	
		if(name==""){
			name="abc";
		}
		//alert(name);
		$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/getmenu/sel/" + name,
					success : function(result) {
						var menus = eval(result);//eval()是程序语言中的函数，功能是获取返回值
						var dis = "";
						for (var i = 0; i < menus.length; i++) {
							dis = dis
									+ "<dd> <a href='javascript:void(0)' class='react'>";
							dis = dis + "<div class='dealcard'>";
							dis = dis
									+ "<div class='dealcard-img imgbox' style='background: rgba(0, 0, 0, 0) none repeat scroll 0% 0%;'>";
							dis = dis
									+ "<img width='30px' height='80px' src='${pageContext.request.contextPath}/images/"
									+ menus[i].pic
									+ "' style='width:100%;'></div>";
							dis = dis + "<div class='dealcard-block-right'>";
							dis = dis
									+ "<div class='dealcard-brand single-line'>"
									+ menus[i].name + "</div>";
							dis = dis
									+ "<div class='dealcard-brand single-line'>"
									+ menus[i].remark + "</div>";
							dis = dis + "<div class='price'>";
							dis = dis
									+ "<span class='strong'>"
									+ menus[i].price
									+ "</span><span class='strong-color'>元</span>";
							dis = dis
									+ "<span class='line-right'> <input type='image' src='img/add.png' border='0' width=20px height=20px' onclick='addCart("
									+ menus[i].id + ")'> </span>";
							dis = dis + "</div></div></div></a></dd>";
						}
						//alert(dis);
						$("#caidan").html(dis);//在<mtc id="caidan"></caidan>
					}
				});
	}
	function addCart(menuid) {
		$.ajax({
			url : "${pageContext.request.contextPath}/member/islogin",
			success : function(result) {
				//alert(result=="htc_null");
				if (result == "htc_null") {
					alert("你还没登录，请先登录");
				} else {
					$.ajax({
						url : "${pageContext.request.contextPath}/cart/insert/" + menuid,
						success : function(res) {
							alert(res);
						}
					});
				}
			}
		});
	}
	//点击购物车按钮页面跳转
	function disCart() {
		//判断是否登录
		//没有登录提示，
		//已登录转到discart.html
		$.ajax({
			url : "${pageContext.request.contextPath}/member/islogin",
			success : function(result) {
				//alert(result=="htc_null");
				if (result == "htc_null") {
					alert("你还没登录，请先登录");
				} else {
					window.location.href = '${pageContext.request.contextPath}/cart';
				}
			}
		});
	}
	getMenu();
</script>
</html>