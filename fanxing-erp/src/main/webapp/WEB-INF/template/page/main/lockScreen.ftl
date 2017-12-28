<!DOCTYPE html>
<html>
<head>
<title>锁屏</title> 
[#include "page/common/base.ftl"]
<link rel="stylesheet" type="text/css" media="screen" href="${resources_static}/style/index/css/lockscreen.css">
</head>
<body>
	<div id="main" role="main">
		<form class="lockscreen animated flipInY" method="post"
			action="${base}/loginController/unLockScreen.do">
			<div class="logo">
				<h1 class="semi-bold">
					<img src="${resources_static}/style/index/img/demo/logo-o.png" alt="" />海宝
				</h1>
			</div>
			<div>
				<!-- <img src="${resources_static}/style/index/img/avatars/sunny-big.png"
					alt="" width="120" height="120" /> ${SPRING_SECURITY_CONTEXT.authentication.principal.imgPath} -->
					[#if SPRING_SECURITY_CONTEXT.authentication.principal.imgPath!=null]
				     <img src="${upload_static}${SPRING_SECURITY_CONTEXT.authentication.principal.imgPath}" width="120" height="120" />
				       [#else] 
					   <img src="${resources_static}/style/index/img/avatars/user_128.ico" width="120" height="120" />
					[/#if]	
				<div>
					<h1>
						<i
							class="fa fa-user fa-3x text-muted air air-top-right hidden-mobile"></i>${SPRING_SECURITY_CONTEXT.authentication.principal.username}<small><i
							class="fa fa-lock text-muted"></i> &nbsp;已锁屏</small>
					</h1>
					<p class="text-muted txt-color-redLight">
							${msg}
					</p>
					<br/>
					<div class="input-group">
						<input class="form-control" type="password" name="password" placeholder="输入密码">
						<div class="input-group-btn">
							<button class="btn btn-primary" type="submit" title="解锁">
								<i class="fa fa-key"></i>
							</button>
						</div>
					</div>
					<p class="no-margin margin-top-5">
						<a href="${base}/login.jsp">其他账号登录？</a>
					</p>
				</div>

			</div>
			<p class="font-xs margin-top-5"></p>
		</form>

	</div>
</body>
</html>