[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp修改公众号</strong>
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
												<button type="button" data-returnUrl="/tencentUserController/findTencentUser.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" enctype="multipart/form-data" class="smart-form" returnUrl="/tencentUserController/findTencentUser.do" action="/tencentUserController/updateTencentUserById.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
										 <fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
												     <input type="hidden" name="id"  value="${t.id}">
													<input type="text" name="name"  placeholder="公众号" value="${t.name}">
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="text" name="weChatId"  placeholder="公众号Id" value="${t.weChatId}">
													 </label>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input">
													 <input  type="text"   name="appId" placeholder="appId" value="${t.appId}">
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> 
													<input type="text" name="appSecret"  placeholder="appSecret"value="${t.appSecret}">
												</label>
											</section>
										</div>
											<section >
												<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
													<textarea rows="3" name="remark" placeholder="备注" class="custom-scroll">${t.remark}</textarea>
												</label>
											</section>
								</fieldset>
				              </div>
				              <div class="step-pane" id="step2">
				                <fieldset>
									<div class="row">
											<section>
												<label class="input">
													 <input  type="text"   name="title" value="${t.title}" placeholder="标题">
													 </label>
											</section>
											<section>
												<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
													<textarea rows="3" name="content" placeholder="内容" class="custom-scroll">${t.content}</textarea>
												</label>
											</section>
											<section>
												<div class="input input-file">
													 <span class="button"><input id="file"  type="file" name="file"  onchange="readURL(this);">浏览</span><input type="text"  value="${t.url}" placeholder="请选择图片" readonly="">
														<input type="hidden" id="x" name="x" />  
												        <input type="hidden" id="y" name="y" />  
												        <input type="hidden" id="w" name="w" />  
												        <input type="hidden" id="h" name="h" />  
														<img alt="" src="${upload_static}${t.url}" id="displayImg" width="300" height="200"/> 
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