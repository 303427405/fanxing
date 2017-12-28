[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp新增公众号</strong>
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
													<span class="badge">2</span>简介<span class="chevron"></span>
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
				             <form id="addForm"  enctype="multipart/form-data" class="smart-form" returnUrl="/tencentUserController/findTencentUser.do" action="/tencentUserController/addTencentUser.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="text" name="name"  placeholder="公众号">
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="text" name="weChatId"  placeholder="公众号Id">
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input">
													 <input  type="text"   name="appId" placeholder="appId">
												</label>
											</section>
											<section class="col col-6">
												<label class="input">
													<input type="text" name="appSecret"  placeholder="appSecret">
												</label>
											</section>
										</div>
											 <section>
												<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
													<textarea rows="3" name="remark" placeholder="备注" class="custom-scroll"></textarea>
												</label>
											</section>
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
											<section>
												<label class="input">
													 <input  type="text"   name="title" placeholder="标题">
													 </label>
											</section>
											<section>
												<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
													<textarea rows="3" name="content" placeholder="内容" class="custom-scroll"></textarea>
												</label>
											</section>
											<section>
												<div class="input input-file">
													 <span class="button"><input id="file"  type="file" name="file"  onchange="readURL(this);">浏览</span><input type="text" placeholder="请选择图片" readonly="">
														<input type="hidden" id="x" name="x" />  
												        <input type="hidden" id="y" name="y" />  
												        <input type="hidden" id="w" name="w" />  
												        <input type="hidden" id="h" name="h" />  
														<img alt="" src="" id="displayImg" width="300" height="200"/> 
												</div>
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
<script src="${resources_static}/js/wechat/user/add.js"></script>
<script src="${resources_static}/style/index/js/areaSelect.js"></script> 