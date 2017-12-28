<!DOCTYPE html>
<html>
<head>
<title>SmartAdmin</title>
<script type="text/javascript">
	var base_path='${base}';
	var resources_static='${resources_static}';
</script>
[#include "page/common/base.ftl"]
</head>
<body class="">
		<!-- header -->
		[#include "page/main/header.ftl"]
		<!-- menu -->
		[#include "page/main/menu.ftl"]
		<!-- main panel -->
		<div id="main" role="main">
			<div id="ribbon">
				<span class="ribbon-button-alignment"> 
				<!--<span id="refresh" class="btn btn-ribbon" data-title="refresh"  rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true">
				    <i class="fa fa-refresh"></i></span> -->
				</span>
				<ol class="breadcrumb">
					<li>首页</li>
				</ol>
			</div>
			<div id="content">
			</div>
		</div>
		<!-- shortcut-->	
		[#include "page/main/shortcut.ftl"]
		[#include "page/main/changePwd.ftl"]
	    <script src="${resources_static}/style/index/js/app.js"></script> 
	    <script src="${resources_static}/style/index/js/demo.js"></script>
	    
		<script>
			$(document).ready(function() {
				pageSetUp();
			});
		</script>
</body>
</html>