[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp新增用户</strong>
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
													<span class="badge">2</span>头像上传<span class="chevron"></span>
												</li>
												<li data-target="#step3">
													<span class="badge">3</span>授权<span class="chevron"></span>
												</li>
											</ul>
											<div class="actions">
												<button type="button" class="btn btn-sm btn-primary btn-prev">
													<i class="fa fa-arrow-left"></i>上一步
												</button>
												<button type="button" id="submit" class="btn btn-sm btn-success btn-next" data-last="保存">
													下一步<i class="fa fa-arrow-right"></i>
												</button>
												<button type="button" data-returnUrl="/operatorController/findOperator.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" enctype="multipart/form-data" class="smart-form" returnUrl="/operatorController/findOperator.do" action="/operatorController/addOperator.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="text" name="loginName" placeholder="用户名">
													 </label>
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-star"></i>
													<input type="text" name="realName" placeholder="昵称">
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-envelope-o"></i>
													<input type="email" name="email" placeholder="邮箱">
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-phone"></i>
													<input type="tel" name="mobile" placeholder="手机" data-mask="999-9999-9999">
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-envelope-o"></i>
													<input type="tel" name="phone" placeholder="电话" data-mask="9999-99999999">
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-calendar"></i>
													<input type="text" data-icon="fa fa-calendar"  name="validDate"   onfocus="WdatePicker({readOnly:true,minDate:'%y-%M-{%d+7}',dateFmt:'yyyy-MM-dd'})" placeholder="账号有效期">
												</label>
											</section>
										</div>
										[@auth.auth ifAnyAuth="function.operator.orgSelect"]
										<div class="row">
											<select id="selectArea" data-org="true"  data-initlevel="1" data-orgCode="${orgCode}" data-areaCode="${areaCodes}"/>
										</div>
										 [/@auth.auth]
								</fieldset>
				              </div>
				              <div class="step-pane" id="step2">
				                <fieldset>
									<div class="row">
											<section class="col col-6">
												<div class="input input-file">
													 <span class="button"><input id="file"  type="file" name="file"  onchange="readURL(this);">浏览</span><input type="text" placeholder="请选择头像" readonly="">
														<input type="hidden" id="x" name="x" />  
												        <input type="hidden" id="y" name="y" />  
												        <input type="hidden" id="w" name="w" />  
												        <input type="hidden" id="h" name="h" />  
														<img alt="" src="" id="displayImg" width="120" height="120"/> 
												</div>
										    </section>
									</div>
								 </fieldset>	
							  </div>
							  <div class="step-pane" id="step3">
							     <fieldset>
							       <div class="row">
							          <section class="col col-6">
							            <label class="control-label">角色：</label>
							           </section>
							       </div>
							       <div class="row">
							         [#list list as r]
								          <section class="col col-2">
								          	<label class="checkbox state-success"><input type="checkbox" value=${r.id} name="roleIds" [#if r.checked==1 ] checked [/#if]><i></i>${r.roleName}</label>
								          </section>
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
<script src="${resources_static}/js/operator/add.js"></script>
<script src="${resources_static}/style/index/js/areaSelect.js"></script> 