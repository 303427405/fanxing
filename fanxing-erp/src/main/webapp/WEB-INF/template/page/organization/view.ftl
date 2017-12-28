<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-list"></i></span>
						<strong>&nbsp&nbsp查看组织</strong>
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
												<button type="button" data-returnUrl="/organizationController/findOrganization.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 返回
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form class="smart-form">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
				                 		<div class="row">
											<section class="col col-6">
											     组织编码：${o.code}
											</section>
											<section class="col col-6">
														所属区域：${o.areaName}
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												组织名称：${o.name}
											</section>
											<section class="col col-6">
													备注：${o.remark}
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
													修改时间：[#if o.updateDate?? ]${o.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]]
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
