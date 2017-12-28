[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp添加素材</strong>
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
												<button type="button" data-returnUrl="/materialController/findMaterial.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" class="smart-form" enctype="multipart/form-data" returnUrl="/materialController/findMaterial.do" action="/materialController/addMaterial.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="text" name="name"  placeholder="名称">
													 </label>
												</label>
											</section>
											<section class="col col-6">
												<select id="dictionaryData" data-css="true" data-code='materialType' data-placeholder="素材类型"   name="type"/>
											</section>
										</div>
										<section  id="contents">
												<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
													<textarea rows="3" name="content" placeholder="内容" class="custom-scroll"></textarea>
												</label>
										</section>
											<section  id="photo">
												<div class="input input-file">
													 <span class="button"><input id="file"  type="file" name="file"  onchange="readURL(this);">浏览</span><input type="text" placeholder="请选择图片" readonly="">
													 <img alt="" src="" id="displayImg" width="500" height="250"/> 
												</div>
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
<script src="${resources_static}/style/index/js/areaSelect.js"></script>
<script src="${resources_static}/js/wechat/material/add.js"></script>
