[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp修改工单</strong>
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
												<button type="button" data-returnUrl="/workOrderController/findWorkOrder.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" class="smart-form" returnUrl="/workOrderController/findWorkOrder.do" action="/workOrderController/updateWorkOrderById.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="hidden" name="id" value="${w.id}">
													<input type="text" name="name" value="${w.name}"  placeholder="名称">
													 </label>
												</label>
											</section>
											<select id="moreSelect" disabled="true" data-wechatId="${w.wechatId}" data-openId="${w.openId}" name="wechatId"/>
										</div>
										<div class="row">
											<section class="col col-6">
												<select id="dictionaryData" data-css="true" data-code='softServiceType' data-placeholder="维修类型" data-value="${w.type}"   name="type"/>
											</section>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-phone"></i>
													<input type="tel" name="mobile" placeholder="手机" data-mask="999-9999-9999" value="${w.mobile}" >
												</label>
											</section>
										</div>
										<section>
											<label class="input"> <i class="icon-append fa  fa-map-marker fa-fw"></i>
													<input type="text" name="address" value="${w.address}"  placeholder="详细地址">
											</label>
										</section>
									 	<section>
											<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
												<textarea rows="3" name="content" placeholder="内容" class="custom-scroll">${w.content}</textarea>
											</label>
										</section>
										 <section>
											<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
												<textarea rows="3" name="remark" placeholder="备注" class="custom-scroll">${w.remark}</textarea>
											</label>
										</section>
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
<script src="${resources_static}/js/wechat/select_fan.js"></script>
<script src="${resources_static}/style/index/js/areaSelect.js"></script> 
<script src="${resources_static}/js/wechat/workorder/add.js"></script>