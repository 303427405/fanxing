<header id="header">
			<div id="logo-group">
				<span id="logo"> <img src="${resources_static}/style/index/img/demo/logo.png" alt="SmartAdmin"> </span>

				<!-- Note: The activity badge color changes when clicked and resets the number to 0
				Suggestion: You may want to set a flag when this happens to tick off all checked messages / notifications -->
				<span id="activity" class="activity-dropdown"> <i class="fa fa-user"></i> <b class="badge"> 0 </b> </span>

				<!-- AJAX-DROPDOWN : control this dropdown height, look and feel from the LESS variable file -->
				<div class="ajax-dropdown">
					<div class="btn-group btn-group-justified" data-toggle="buttons">
						<label class="btn btn-default">
							<input type="radio" name="activity" id="/ajax/notify/mail.html">
							消息 (0) </label>
						<label class="btn btn-default">
							<input type="radio" name="activity" id="/ajax/notify/notifications.html">
							通知 (0) </label>
						<label class="btn btn-default">
							<input type="radio" name="activity" id="/ajax/notify/tasks.html">
							消息 (0) </label>
					</div>

					<!-- notification content -->
					<div class="ajax-notifications custom-scroll">
						<div class="alert alert-transparent">
							<h4>单击tab页，显示不同类型的消息</h4>
							这个空白页的信息不仅可以帮助保护您的隐私,同时也可以自动显示第一条消息。
						</div>
						<i class="fa fa-lock fa-4x fa-border"></i>
					</div>
					<!-- end notification content -->
					<!-- footer: refresh area -->
					<span> 最后一次更新: 12/12/2013 9:43AM
						<button type="button" data-loading-text="<i class='fa fa-refresh fa-spin'></i> 加载中..." class="btn btn-xs btn-default pull-right">
							<i class="fa fa-refresh"></i>
						</button> </span>
					<!-- end footer -->

				</div>
				<!-- END AJAX-DROPDOWN -->
			</div>
			<!-- pulled right: nav area -->
			<div class="pull-right">
				<!-- collapse menu button -->
				<div id="hide-menu" class="btn-header pull-right">
					<span> <a href="javascript:void(0);" title="隐藏菜单"><i class="fa fa-reorder"></i></a> </span>
				</div>
				<!-- end collapse menu -->
				<div id="logout" class="btn-header transparent pull-right">
					<span> <a href="${base}/j_spring_security_logout;" title="退出系统" data-logout-msg="确定要退出系统吗？"><i class="fa fa-sign-out"></i></a> </span>
				</div>
			</div>
			<!-- end pulled right: nav area -->
		</header>