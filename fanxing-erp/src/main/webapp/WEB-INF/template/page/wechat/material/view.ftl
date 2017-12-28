<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-list"></i></span>
						<strong>&nbsp&nbsp素材详情</strong>
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
												<button type="button" data-returnUrl="/materialController/findMaterial.do" class="btn btn-sm btn-info" id="cancel">
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
											   名称：${m.name}
											</section>
											<section class="col col-6">
												类型：[#if 0==m.type ]
												          		文本
												      [#elseif 1==m.type]
												         		图片
												      [#elseif 2==m.type]
												         		图文
												      [#else]
												           		未知
												    [/#if]
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												图片：[#if m.photoUrl?? ]<a href="javascript:void(0);" data-url="${upload_static}${m.photoUrl}" class="viewImg btn btn-success btn-sm">查看 </a>[/#if]
											</section>
											<section class="col col-6">
												所属组织：${m.orgName}
											</section>
										</div>
										<div class="row">
											<section class="col col-12">
												内容：${m.content}
											</section>	
										</div>
										<div class="row">
											<section class="col col-6">
												创建人：${m.createUser}
											</section>
											<section class="col col-6">
													创建时间：[#if m.createDate?? ]${m.createDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												修改人：${m.updateUser}
											</section>
											<section class="col col-6">
													修改时间：[#if m.updateDate?? ]${m.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]
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
