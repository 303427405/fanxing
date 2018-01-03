<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title> 登录 </title>
		<meta name="description" content="">
		<meta name="author" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<link rel="stylesheet" type="text/css" media="screen" href="${resources_static}/style/index/css/bootstrap.min.css">
		
		<link rel="stylesheet" type="text/css" media="screen" href="${resources_static}/style/index/css/smartadmin-production.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${resources_static}/style/index/css/smartadmin-skins.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${resources_static}/style/index/css/demo.css">
		
		<link rel="stylesheet" href="${resources_static}/style/index/hzzfix/googlefonts.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${resources_static}/style/index/css/font-awesome.min.css">
		
		<link rel="shortcut icon" href="${resources_static}/style/index/img/favicon/favicon.ico" type="image/x-icon">
		<link rel="icon" href="${resources_static}/style/index/img/favicon/favicon.ico" type="image/x-icon">
	</head>
	<body id="login" class="animated fadeInDown">
		<header id="header">
			<div id="logo-group">
				<span id="logo"> <img src="${resources_static}/style/index/img/demo/logo.png" alt="SmartAdmin"> </span>
			</div>
		</header>
		<div id="main" role="main">
			<!-- MAIN CONTENT -->
			<div id="content" class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
						<h1 class="txt-color-red login-header-big">樊兴科技</h1>
						<div class="hero">
							<div class="pull-left login-desc-box-l">
								<h4 class="paragraph-header">欢迎使用樊兴供应链后台管理系统!</h4>
								<div class="login-app-icons">
									<a href="javascript:void(0);" class="btn btn-danger btn-sm">了解我们</a>
								</div>
							</div>
							<!-- <img src="${resources_static}/style/index/img/demo/iphoneview.png" class="pull-right display-image" alt="" style="width:210px"> -->
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
						<div class="well no-padding">
							<form action="${base}/j_spring_security_check" id="login-form" method="post" class="smart-form client-form">
								<header>
									系统登录
								</header>
								<fieldset>
									<section>
										<label class="label">E-mail</label>
										<label class="input"> <i class="icon-append fa fa-user"></i>
											<input type="text" name="j_username" value="${SPRING_SECURITY_LAST_USERNAME }">
											<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i>请输入用户名</b></label>
									</section>

									<section>
										<label class="label">Password</label>
										<label class="input"> <i class="icon-append fa fa-lock"></i>
											<input type="password" name="j_password">
											<b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> 请输入密码</b> </label>
									</section>

									<section>
										<label>${Session.SPRING_SECURITY_LAST_EXCEPTION.message}</label>
									</section>
								</fieldset>
								<footer>
									<button type="submit" class="btn btn-primary">
										登录
									</button>
								</footer>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>
		<script src="${resources_static}/style/index/js/plugin/pace/pace.min.js"></script>

	    <!-- jquery -->
		<script src="${resources_static}/style/index/js/jquery/2.0.2/jquery.min.js"></script>
		<script>if (!window.jQuery)document.write('<script src="${resources_static}/style/index/js/jquery/2.0.2/jquery-2.0.2.min.js"><\/script>');</script>
		<!-- jquery-ui -->
		<script src="${resources_static}/style/index/js/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script>if (!window.jQuery.ui) document.write('<script src="${resources_static}/style/index/js/jqueryui/1.10.3/jquery-ui-1.10.3.min.js"><\/script>');</script>
		<!-- bootstrap js -->
		<script src="${resources_static}/style/index/js/bootstrap/bootstrap.min.js"></script> 
		
		<!-- jquery validate --> 
		<script src="${resources_static}/style/index/js/plugin/jquery-validate/jquery.validate.min.js"></script> 
		<script src="${resources_static}/style/index/js/plugin/jquery-validate/additional-methods.js"></script>
		<script src="${resources_static}/style/index/js/plugin/jquery-validate/messages_cn.js"></script> 
		<!-- browser msie issue fix --> 
		<script src="${resources_static}/style/index/js/plugin/msie-fix/jquery.mb.browser.min.js"></script> 
		
		<!-- FastClick: For mobile devices  消除在触摸UI上的点击延迟--> 
		<script src="${resources_static}/style/index/js/plugin/fastclick/fastclick.js"></script>
		<!-- MAIN APP JS FILE -->
		 <script src="${resources_static}/style/index/js/app.js"></script> 
		<script type="text/javascript">
			runAllForms();
			$(function() {
				$("#login-form").validate({
					rules : {
						j_username : {
							required : true,
							minlength : 2,
							maxlength : 20,
						},
						j_password : {
							required : true,
							minlength : 4,
							maxlength : 20,
							alnum:true
						}
					}
				});
			});
		</script>

	</body>
</html>