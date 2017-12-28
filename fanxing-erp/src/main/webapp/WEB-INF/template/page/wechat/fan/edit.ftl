[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp修改粉丝</strong>
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
												<button type="button" id="submit" class="btn btn-sm btn-success btn-next" data-last="保存">
													保存<i class="fa fa-arrow-right"></i>
												</button>
												<button type="button" data-returnUrl="/fanController/findFan.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" class="smart-form" returnUrl="/fanController/findFan.do" action="/fanController/updateFanById.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
				                 		<div class="row">
										  <input type="hidden" name="id"  value="${f.id}">
											<section class="col col-6">
											     公众号：${f.wechatName}
											</section>
											<section class="col col-6">
											     所属分组：${f.groupName}
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
											     公众号Id：${f.wechatId}
											</section>
											<section class="col col-6">
											     粉丝号：${f.openId}
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
											    粉丝注册地址：${f.province}省${f.city}市
											</section>
											<section class="col col-6">
											     性别：[#if f.sex==1 ]男[#elseif f.sex==2 ]女[#else]未知[/#if]
											</section>
										</div>
									
										<div class="row">
											<select id="selectArea"     data-areaCode="${f.areaCodes}"/>
											<section class="col col-3">
											     <label class="input"> <i class="icon-append fa  fa-map-marker fa-fw"></i>
													<input type="text" name="address"  placeholder="详细地址" value="${f.address}">
													 </label>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
											     <label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="text" name="nickName"  placeholder="昵称" value="${f.nickName}">
													 </label>
												</label>
											</section>
											 <section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-phone"></i>
													<input type="tel" name="mobile" placeholder="手机" value="${f.mobile}" data-mask="999-9999-9999">
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
<script src="${resources_static}/style/index/js/areaSelect.js"></script>
<script src="${resources_static}/js/wechat/fan/edit.js"></script> 