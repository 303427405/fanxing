<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-list"></i></span>
						<strong>&nbsp&nbsp用户详情</strong>
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
													<span class="badge">2</span>头像信息<span class="chevron"></span>
												</li>
												<li data-target="#step3">
													<span class="badge">3</span>授权信息<span class="chevron"></span>
												</li>
											</ul>
											<div class="actions">
												<button type="button" class="btn btn-sm btn-primary btn-prev">
													<i class="fa fa-arrow-left"></i>上一步
												</button>
												<button type="button" id="submit" class="btn btn-sm btn-success btn-next" data-last="返回">
													下一步<i class="fa fa-arrow-right"></i>
												</button>
												<button type="button" data-returnUrl="/operatorController/findOperator.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 返回
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form class="smart-form" id="viewForm" data-returnUrl="/operatorController/findOperator.do" >
				              <div class="step-pane active" id="step1">
				                 <fieldset>
				                 		<div class="row">
											<section class="col col-6">
											     用户名：${o.loginName}
											</section>
											<section class="col col-6">
											     昵称：${o.realName}
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
											   邮箱：${o.email}
											</section>
											<section class="col col-6">
												手机：${o.mobile}
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												电话：${o.phone}
											</section>
											<section class="col col-6">
												状态：[#if o.enabled==1 ]启用[#else] 禁用[/#if]
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												账号有效期：[#if o.validDate?? ]${o.validDate}[#else]无限制[/#if]
											</section>	
											<section class="col col-6">
												所属组织：${o.orgName}
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												创建人：${o.createUser}
											</section>
											<section class="col col-6">
													创建时间：[#if o.createDate?? ]${o.createDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												修改人：${o.updateUser}
											</section>
											<section class="col col-6">
													修改时间：[#if o.updateDate?? ]${o.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]
											</section>
										</div>
								</fieldset>
				              </div>
				              <div class="step-pane" id="step2">
				                <fieldset>
									<div class="row">
											<section class="col col-6">
												<div class="input input-file">
														<img alt="" src="${upload_static}${o.imgPath}" id="displayImg" width="120" height="120"/> 
												</div>
										    </section>
									</div>
								 </fieldset>	
							  </div>
							  <div class="step-pane" id="step3">
							     <fieldset>
							       <div class="row">
							          <section class="col col-6">
							            <label class="control-label">拥有角色：</label>
							           </section>
							       </div>
							       <div class="row">
							         [#list list as r]
							         	[#if r.checked==1 ] 
								          <section class="col col-2">
								          	<label class="checkbox state-success">${r.roleName}</label>
								          </section>
								        [/#if]
							          [/#list]
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