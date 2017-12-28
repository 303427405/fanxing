<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript">
	var num = 6;
	function redirect() {
		num--;
		document.getElementById("num").innerHTML = num;
		if (num < 0) {
			document.getElementById("num").innerHTML = 0;
			location.href = "login.jsp";
		}
	}
	setInterval("redirect()", 1000);
</script>
<body class="">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="row">
				<div class="col-sm-12">
					<div class="text-center error-box">
						<h1 class="error-text tada animated">
							<i class="fa fa-times-circle text-danger error-icon-shadow"></i>
							408
						</h1>
						<br />
						<p class="lead semi-bold">
							<strong>会话超时</strong><br> <br> <small> <span
								id="num"></span>秒后自动跳转到登录页面<a href="login.jsp">[跳转]</a>
							</small>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>