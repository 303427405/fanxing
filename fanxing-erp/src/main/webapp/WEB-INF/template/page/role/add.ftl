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
						<strong>&nbsp&nbsp新增角色</strong>
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
													<span class="badge">2</span>授权<span class="chevron"></span>
												</li>
											</ul>
											<div class="actions">
												<button type="button" class="btn btn-sm btn-primary btn-prev">
													<i class="fa fa-arrow-left"></i>上一步
												</button>
												<button type="button" id="submit" class="btn btn-sm btn-success btn-next" data-last="保存">
													下一步<i class="fa fa-arrow-right"></i>
												</button>
												<button type="button" data-returnUrl="/roleController/findRole.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" class="smart-form" returnUrl="/roleController/findRole.do" action="/roleController/addRole.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
										<div class="row">
											<section >
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="text" name="roleName" placeholder="角色名称">
													 </label>
												</label>
											</section>
										</div>
										<div class="row">
										 <section >
												<label class="textarea textarea-resizable"><i class="icon-append fa fa-comment"></i>
													<textarea rows="3" name="description" placeholder="角色描述" class="custom-scroll"></textarea>
												</label>
											</section>
										</div>
								</fieldset>
				              </div>
				              <div class="step-pane" id="step2">
				                <fieldset>
			                		<div class="row">
											<section>
												<button id="selectAllByTree" class="btn btn-info btn-sm">全选</button>
												<button id="SelectNoneByTree" class="btn btn-info btn-sm">取消全选</button>
												<button id="refreshByTree" class="btn btn-info btn-sm">刷新模块树</button>
												<input type="text"  readonly="readonly"  style='border: 0px;color: white;background-color: white;' id="permissionIds" name="permissionIds">
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
<script src="${resources_static}/js/role/add.js"></script>
