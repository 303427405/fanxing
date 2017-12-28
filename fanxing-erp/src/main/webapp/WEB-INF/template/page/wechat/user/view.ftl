<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-list"></i></span>
						<strong>&nbsp&nbsp公众号详情</strong>
				</header>
			    <div id="formContent"> 
				    <div class="widget-body fuelux">
				        <div class="widget-body fuelux">
				          <div class="wizard">
											<ul class="steps">
												<li data-target="#step1" class="active">
													<span class="badge badge-info">1</span>基本信息<span class="chevron"></span>
												</li>
												<li data-target="#step2">
													<span class="badge">2</span>简介<span class="chevron"></span>
												</li>
											</ul>
											<div class="actions">
												<button type="button" class="btn btn-sm btn-primary btn-prev">
													<i class="fa fa-arrow-left"></i>上一步
												</button>
												<button type="button" id="submit" class="btn btn-sm btn-success btn-next" data-last="返回">
													下一步<i class="fa fa-arrow-right"></i>
												</button>
												<button type="button" data-returnUrl="/tencentUserController/findTencentUser.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 返回
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form class="smart-form" id="viewForm" data-returnUrl="/tencentUserController/findTencentUser.do">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
				                 		<div class="row">
											<section class="col col-6">
											     所属组织：${t.orgName}
											</section>
											<section class="col col-6">
											     公众号：${t.name}
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
											   公众号Id：${t.weChatId}
											</section>
											<section class="col col-6">
												appId：${t.appId}
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												appSecret：${t.appSecret}
											</section>
											<section class="col col-6">
												状态：[#if t.status==1 ]启用[#else] 禁用[/#if]
											</section>
										</div>
										<div class="row">
											<section class="col col-12">
												备注：${t.remark}
											</section>	
										</div>
										<div class="row">
											<section class="col col-6">
												创建人：${t.createUser}
											</section>
											<section class="col col-6">
													创建时间：[#if t.createDate?? ]${t.createDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												修改人：${t.updateUser}
											</section>
											<section class="col col-6">
													修改时间：[#if t.updateDate?? ]${t.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]
											</section>
										</div>
								</fieldset>
				              </div>
				              <div class="step-pane" id="step2">
				                <fieldset>
									<div class="row">
											<section>
											标题：${t.title}
											</section>
											<section>
											   内容：${t.content}
											</section>
											<section>
												<div class="input input-file">
														<img alt="" src="${upload_static}${t.url}" id="displayImg" width="300" height="200"/> 
												</div>
										    </section>
									</div>
								 </fieldset>	
							  </div>
						 </form>
				      </div>
				    </div>
			    </div>
			</div>
		</article>
	</div>
</section>
<script src="${resources_static}/style/index/js/form.js"></script>
