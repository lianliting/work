<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0051)http://demo1.mycodes.net/denglu/HTML5_yonghudenglu/ -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>寝室管理系统</title>
<meta name="description"
	content="particles.js is a lightweight JavaScript library for creating particles.">
<meta name="author" content="Vincent Garreau">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" media="screen"
	href="../resources/admin/login/css/style.css">
<link rel="stylesheet" type="text/css"
	href="../resources/admin/login/css/reset.css">
<style type="text/css">
.radio {
	position: absolute;
	margin-top: 3px;
}
</style>
<body>

	<div id="particles-js">
		<div class="login" style="display: block;">
			<div class="login-top">寝室管理系统</div>
			<div class="login-center clearfix">
				<div class="login-center-img">
					<img src="../resources/admin/login/images/name.png">
				</div>
				<div class="login-center-input">
					<input type="text" name="adminNumber" id="adminNumber" value=""
						placeholder="请输入您的工号" 
						>
					<div class="login-center-input-text">工号</div>
				</div>
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img">
					<img src="../resources/admin/login/images/password.png">
				</div>
				<div class="login-center-input">
					<input type="password" name="password" id="password" value=""
						placeholder="请输入您的密码"
						>
					<div class="login-center-input-text">密码</div>
				</div>
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img">
					<img src="../resources/admin/login/images/cpacha.png">
				</div>
				<div class="login-center-input">
					<input style="width: 50%;" type="text" name="cpacha" id="cpacha"
						value="" placeholder="请输入验证码"
						onfocus="this.placeholder=&#39;&#39;"
						onblur="this.placeholder=&#39;请输入验证码&#39;">
					<div class="login-center-input-text">验证码</div><div class="login-center-input-text">验证码</div>
					<img id="cpacha-img" title="点击切换验证码" style="cursor: pointer;"
						src="get_cpacha?vl=4&w=150&h=40&type=loginCpacha" width="175px"
						height="32px" onclick="changeCpacha()">
				</div>
			</div>
			<div class="login-button">登录</div>
		</div>
		<div class="sk-rotating-plane"></div>
		<canvas class="particles-js-canvas-el" width="1147" height="952"
			style="width: 100%; height: 100%;"></canvas>
	</div>

	<!-- scripts -->
	<script src="../resources/admin/login/js/particles.min.js"></script>
	<script src="../resources/admin/login/js/app.js"></script>
	<script src="../resources/admin/login/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript">
	function hasClass(elem, cls) {
	  cls = cls || '';
	  if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
	  return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
	}
	 
	function addClass(ele, cls) {
	  if (!hasClass(ele, cls)) {
	    ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
	  }
	}
	 
	function removeClass(ele, cls) {
	  if (hasClass(ele, cls)) {
	    var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
	    while (newClass.indexOf(' ' + cls + ' ') >= 0) {
	      newClass = newClass.replace(' ' + cls + ' ', ' ');
	    }
	    ele.className = newClass.replace(/^\s+|\s+$/g, '');
	  }
	}
	
	function changeCpacha(){
		$("#cpacha-img").attr("src",'get_cpacha?vl=4&w=150&h=40&type=loginCpacha&t=' + new Date().getTime());
	}
		document.querySelector(".login-button").onclick = function(){
				var adminNumber = $("#adminNumber").val();
				var password = $("#password").val();
				var cpacha = $("#cpacha").val();
				
				if(adminNumber == '' || adminNumber == 'undefined'){
					alert("请填写工号！");
					return;
				}
				if(password == '' || password == 'undefined'){
					alert("请填写密码！");
					return;
				}
				if(cpacha == '' || cpacha == 'undefined'){
					alert("请填写验证码！");
					return;
				}
				addClass(document.querySelector(".login"), "active")
				addClass(document.querySelector(".sk-rotating-plane"), "active")
				document.querySelector(".login").style.display = "none"
				$.ajax({
					url:'login',
					data:{adminNumber:adminNumber,password:password,cpacha:cpacha},
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.type == 'success'){
							window.parent.location = 'index';
						}else{
							removeClass(document.querySelector(".login"), "active");
							removeClass(document.querySelector(".sk-rotating-plane"), "active");
							document.querySelector(".login").style.display = "block";
							alert(data.msg);
							changeCpacha();
						}
					}
				});
				
		}
</script>
</body>
</html>