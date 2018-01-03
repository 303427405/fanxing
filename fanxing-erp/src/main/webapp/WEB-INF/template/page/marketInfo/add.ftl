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
					<span class="widget-icon"> <i class="fa fa-plus-circle"></i></span>
						<strong>&nbsp&nbsp新增市场</strong>
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
													<span class="badge">2</span>设置区域<span class="chevron"></span>
												</li>
											</ul>
											<div class="actions">
												<button type="button" class="btn btn-sm btn-primary btn-prev">
													<i class="fa fa-arrow-left"></i>上一步
												</button>
												<button type="button" id="submit" class="btn btn-sm btn-success btn-next" data-last="保存">
													下一步<i class="fa fa-arrow-right"></i>
												</button>
												<button type="button" data-returnUrl="/marketInfoController/findMarketInfo.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" class="smart-form" returnUrl="/marketInfoController/findMarketInfo.do" action="/marketInfoController/addMarketInfo.do">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
                                    <div class="row">
                                        <section>
                                            <label class="input"> <i class="icon-append fa fa-user"></i>
                                                <input type="text" name="name" placeholder="市场名称">
                                                 </label>
                                            </label>
                                        </section>
                                    </div>


                                    <div class="row">
                                     <section >
                                            <label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
                                                <textarea rows="3" name="remark" placeholder="备注" class="custom-scroll"></textarea>
                                            </label>
                                        </section>
                                    </div>
								</fieldset>
				              </div>
				              <div class="step-pane" id="step2">
				                <fieldset>
											<section >
											    <label class="input">  <i class="icon-append iconfont icon-orgb"></i>
												  <input type="text" readonly="readonly" name="areaCodeName" id="areaCodeName" placeholder="所属区域">
												  <input type="hidden" name="areaCode" id="areaCode" placeholder="所属区域">
												</label>
											</section>

                                    <div class="row">
                                        <section>
                                            <label class="input">
                                                <input type="text" name="address"  placeholder="详细地址">
                                            </label>
                                        </section>

                                    </div>

									<div class="row">
										 <section class="col col-4" >
												<label class="input"><i class="icon-append fa fa-map-marker"></i>
													<input type="text" placeholder="输入名称或编码"  id="treeCode">
												</label>
											</section>
											<section class="col col-2" >
												<label class="input">
													<button id="refreshByTree" class="btn btn-info btn-sm">刷新区域</button>
												</label>
											</section>
									</div>
									<div class="row">
											<section>
												<ul id="tree" class="ztree"  ></ul>
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
<script src="${resources_static}/js/organization/add.js"></script>
