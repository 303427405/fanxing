[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp新增商品</strong>
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
													<span class="badge">2</span>图片上传<span class="chevron"></span>
												</li>
											</ul>
											<div class="actions">
												<button type="button" class="btn btn-sm btn-primary btn-prev">
													<i class="fa fa-arrow-left"></i>上一步
												</button>
												<button type="button" id="submit" class="btn btn-sm btn-success btn-next" data-last="保存">
													下一步<i class="fa fa-arrow-right"></i>
												</button>
												<button type="button" data-returnUrl="/product/findProduct.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" enctype="multipart/form-data" class="smart-form" returnUrl="/product/findProduct.do" action="/product/addProduct.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="text" name="name" placeholder="名称">
												</label>
											</section>
											<section class="col col-6">
												<select id="dictionaryData" data-css="true" data-code='materialType' data-placeholder="商品规格"   name="unit"/>
											</section>
										</div>
										<div class="row">
											<select id="moreSelect" data-col="6"  name="wechatId"/>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
														  <input type="text" name="salePrice" placeholder="价格">
												</label>
											</section>
										</div>
										 <section>
												<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
													<textarea rows="3" name="content" placeholder="内容" class="custom-scroll"></textarea>
												</label>
										</section>
										 <section>
												<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
													<textarea rows="3" name="remark" placeholder="备注" class="custom-scroll"></textarea>
												</label>
										</section>
								</fieldset>
				              </div>
				              <div class="step-pane" id="step2">
				                <fieldset>
									<div class="row">
											<section class="col col-6">
												<div class="input input-file">
													 <span class="button"><input id="file"  type="file" name="file"  onchange="readURL(this);">浏览</span><input type="text" placeholder="请选择图片" readonly="">
														<input type="hidden" id="x" name="x" />  
												        <input type="hidden" id="y" name="y" />  
												        <input type="hidden" id="w" name="w" />  
												        <input type="hidden" id="h" name="h" />  
														<img alt="" src="" id="displayImg" width="120" height="120"/> 
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
<script src="${resources_static}/js/product/add.js"></script>
<script src="${resources_static}/js/wechat/select_fan.js"></script>
<script src="${resources_static}/style/index/js/areaSelect.js"></script> 