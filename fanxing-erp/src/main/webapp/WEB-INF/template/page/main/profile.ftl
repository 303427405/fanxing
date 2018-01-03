<div class="row">
	<div class="col-sm-12">
		<div class="well well-sm">
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-6">
					<div class="well well-light well-sm no-margin no-padding">
						<div class="row">
							<div class="col-sm-12">
								<div id="myCarousel" class="carousel fade profile-carousel">
									<div class="air air-bottom-right padding-10">
									</div>
									<ol class="carousel-indicators">
										<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
										<li data-target="#myCarousel" data-slide-to="1" class=""></li>
									</ol>
									<div class="carousel-inner">
										<div class="item active">
											<img src="${resources_static}/style/index/img/demo/s1.jpg"
												alt="">
										</div>
										<div class="item">
											<img src="${resources_static}/style/index/img/demo/m3.jpg"
												alt="">
										</div>
									</div>
								</div>
							</div>

							<div class="col-sm-12">
								<div class="row">
									<div class="col-sm-3 profile-pic">
										[#if
										SPRING_SECURITY_CONTEXT.authentication.principal.imgPath!=null]
										<img
											src="${upload_static}${SPRING_SECURITY_CONTEXT.authentication.principal.imgPath}"
											alt="me" style="height: 128px;" /> [#else] <img
											src="${resources_static}/style/index/img/avatars/users_32.png"
											alt="me" /> [/#if]
									</div>
									<div class="col-sm-6">
										<h1>
											${o.loginName}</span> <br> <small>${o.realName}</small>
										</h1>
										<ul class="list-unstyled">
											<li>
												<p class="text-muted">
													<i class="fa fa-mobile"></i>&nbsp;&nbsp;<span
														class="txt-color-darken">${o.mobile}</span>
												</p>
											</li>
											<li>
												<p class="text-muted">
													<i class="fa fa-phone"></i>&nbsp;&nbsp;<span
														class="txt-color-darken">${o.phone}</span>
												</p>
											</li>
											<li>
												<p class="text-muted">
													<i class="fa fa-envelope"></i>&nbsp;&nbsp;${o.email}
												</p>
											</li>
										</ul>
										<br> <a href="javascript:void(0);"
											class="btn btn-default btn-xs" id="profile"><i
											class="fa fa-envelope-o"></i> 完善信息</a>
											<br>
											<br>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-6">
					<span class="timeline-seperator text-center"> <span>我的权限</span></span>
					<br>
					   [#list list as r]
                       qwqw
								          <div class="user" title="${r.description}">
								          	${r.roleName}
								          </div>
						[/#list]
				</div>
				
			</div>
		</div>
	</div>
</div>
<script src="${resources_static}/js/operator/profile.js"></script>
