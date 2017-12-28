[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp关注回复</strong>
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
												<button type="button" data-returnUrl="/tencentUserController/findTencentUser.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" class="smart-form" returnUrl="/tencentUserController/findTencentUser.do" action="/tencentUserController/addReplyMaterial.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
				                       <section>
				                       			<input type="hidden" value="${t.wechatId}" id="wechatId" name="wechatId">
				                       			<input type="hidden" id="materialIds" name="materialIds">
												<select id="dictionaryData" data-css="true" data-code='materialType' data-value="${t.type}" data-placeholder="素材类型"/>
										</section>
										<div class="row">
											<section class="col col-5">
												<select id="leftData"  multiple="multiple" style="min-height:200px;"   />
											</section>
											<section class="col col-1">
												<button type="button" id="leftAll" class="btn btn-sm btn-primary"><i class="fa fa-angle-double-left"></i>
												</button>
												</br></br>
												<button type="button" id="left" class="btn btn-sm btn-primary"><i class="fa fa-angle-left"></i>
												</button>
												</br></br>
												<button type="button" id="right" class="btn btn-sm btn-success"><i class="fa fa-angle-right"></i>
												</button>
												</br></br>
												<button type="button" id="rightAll" class="btn btn-sm btn-success"><i class="fa fa-angle-double-right"></i>
												</button>
											</section>
											<section class="col col-5">
												<select id="rightData"  multiple="multiple" style="min-height:200px;"/>
											</section>
											<section class="col col-1">
												<button type="button" id="upStart" class="btn btn-sm btn-primary"><i class="fa fa-angle-double-up"></i>
												</button>
												</br></br>
												<button type="button" id="up" class="btn btn-sm btn-primary"><i class="fa fa-angle-up"></i>
												</button>
												</br></br>
												<button type="button" id="down" class="btn btn-sm btn-success"><i class="fa fa-angle-down"></i>
												</button>
												</br></br>
												<button type="button" id="downEnd" class="btn btn-sm btn-success"><i class="fa fa-angle-double-down"></i>
												</button>
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
<script src="${resources_static}/js/wechat/user/add_reply_material.js"></script>  