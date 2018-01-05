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
						<strong>&nbsp&nbsp新增快捷方式</strong>
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
                                    <button type="button" data-returnUrl="/shortcutController/findShortcut.do" class="btn btn-sm btn-info" id="cancel">
                                        <i class="fa fa-location-arrow"></i> 取消
                                    </button>
                                </div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" class="smart-form" returnUrl="/shortcutController/findShortcut.do" action="/shortcutController/addShortcut.do" method="post">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input type="text" name="name" id="shortcutName" placeholder="快捷方式名称">
													 </label>
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa fa-user"></i>
													<input  readonly="readonly"  id="spinner" name="sequence" placeholder="排序" type="text">
													 </label>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<button id="refreshByTree" class="btn btn-info btn-sm">刷新模块树</button>
											</section><!-- style='border: 0px;color: white;background-color: white;' -->
											 <input type="text" style='border: 0px;color: white;background-color: white;'  readonly="readonly"  id="permissionsId" name="permissionsId">
										</div>
										<div class="row">
											<section class="col col-6">
												<ul id="tree" class="ztree" ></ul>
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
<script src="${resources_static}/js/shortcut/add.js"></script>
