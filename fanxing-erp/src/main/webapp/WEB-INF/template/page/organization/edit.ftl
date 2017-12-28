<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp修改组织</strong>
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
												<button type="button" data-returnUrl="/organizationController/findOrganization.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" class="smart-form" returnUrl="/organizationController/findOrganization.do" action="/organizationController/updateOrganizationById.do">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
				                 		<div class="row">
											<section >
												<label class="input"> 
												<input type="hidden" name="id"  value="${o.id}">
														组织编码:${o.code}
													 </label>
												</label>
											</section>
										</div>
										<div class="row">
											<section >
												<label class="input"> <i class="icon-append fa fa-user"></i>
														所属区域:${o.areaName}
													 </label>
												</label>
											</section>
										</div>
										<div class="row">
											<section >
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="text" name="name" placeholder="组织名称" value="${o.name}">
													 </label>
												</label>
											</section>
										</div>
										<div class="row">
										 <section >
												<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
													<textarea rows="3" name="remark" placeholder="备注" class="custom-scroll">${o.remark}</textarea>
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
<script src="${resources_static}/js/organization/edit.js"></script>
