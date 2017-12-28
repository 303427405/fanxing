<link rel="stylesheet" type="text/css" href="${resources_static}/style/index/js/jquery/zTree/zTreeStyle/zTreeStyle.css">
<style>
<!-- 自定义样式，与系统冲突 
.smart-form .button{float:right;padding: 0 25px;} -->
.smart-form .button{float:left;padding: 0px; }
</style>
<script src="${resources_static}/style/index/js/jquery/zTree/jquery.ztree.all.min.js"></script>
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-list"></i></span>
						<strong>&nbsp&nbsp角色详情</strong>
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
												<button type="button" data-returnUrl="/roleController/findRole.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 返回
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form class="smart-form" id="viewForm" data-returnUrl="/roleController/findRole.do" >
				              <div class="step-pane active" id="step1">
				                 <fieldset>
				                 		<div class="row">
				                 		<input type="hidden" id="roleId" name="id" value="${r.id}">
											<section class="col col-6">
											    角色名称：${r.roleName}
											</section>
											<section class="col col-6">
											     角色描述：${r.description}
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												创建人：${r.createUser}
											</section>
											<section class="col col-6">
													创建时间：[#if r.createDate?? ]${r.createDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												修改人：${r.updateUser}
											</section>
											<section class="col col-6">
													修改时间：[#if r.updateDate?? ]${r.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]
											</section>
										</div>
								</fieldset>
				              </div>
				              <div class="step-pane" id="step2">
				                <fieldset>
									<div class="row">
											<section>
												  <ul id="tree" class="ztree"></ul>
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
<script src="${resources_static}/js/role/view.js"></script>