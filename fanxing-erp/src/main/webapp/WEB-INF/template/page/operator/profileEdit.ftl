[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp个人信息完善</strong>
				</header>
			    <div id="formContent"> 
				    <div class="widget-body fuelux">
				        <div class="widget-body fuelux">
				          <div class="wizard">
											<ul class="steps">
												<li data-target="#step1" class="active">
													<span class="badge badge-info">1</span>基本信息<span class="chevron"></span>
												</li>
											</ul>
											<div class="actions">
												<button type="button" class="btn btn-sm btn-primary btn-prev">
													<i class="fa fa-arrow-left"></i>上一步
												</button>
												<button type="button" id="submit" class="btn btn-sm btn-success btn-next" data-last="保存">
													保存<i class="fa fa-arrow-right"></i>
												</button>
												<button type="button" data-returnUrl="/loginController/profile.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" class="smart-form" returnUrl="/loginController/profile.do" action="/operatorController/updateProfileById.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-star"></i>
												<input type="hidden" name="id"  value="${o.id}">
													<input type="text" name="realName" placeholder="昵称" value="${o.realName}">
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-envelope-o"></i>
													<input type="email" name="email" placeholder="邮箱" value="${o.email}">
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-phone"></i>
													<input type="tel" name="mobile" placeholder="手机" value="${o.mobile}" data-mask="999-9999-9999">
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-envelope-o"></i>
													<input type="tel" name="phone" placeholder="电话" value="${o.phone}" data-mask="9999-99999999">
												</label>
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
<script src="${resources_static}/js/operator/profile.js"></script>