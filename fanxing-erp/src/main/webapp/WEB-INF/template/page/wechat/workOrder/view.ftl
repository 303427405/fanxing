<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			   <header>
					<span class="widget-icon"> <i class="fa fa-list"></i></span>
						<strong>&nbsp&nbsp工单详情</strong>
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
												<button type="button" data-returnUrl="/workOrderController/findWorkOrder.do" class="btn btn-sm btn-info" id="cancel">
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
											     名称：${w.name}
											</section>
											<section class="col col-6">
											    状态：[#if 0==w.status ]
												       已受理
												      [#elseif 1==w.status]
												         正在处理
												      [#elseif 2==w.status]
												        已完成
												      [#elseif 3==w.status]
												         已取消
												      [#else]
												         未知
												   [/#if]
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
											  所属公众号：${w.wechatName}
											</section>
											<section class="col col-6">
											所属客户：${w.fanNickName}
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
											  手机号码：${w.mobile}
											</section>
											<section class="col col-6">
											工单类型：[#if 0==w.type ]
												       主机
												      [#elseif 1==w.type]
												         显示器
												      [#elseif 2==w.type]
												        摄像头
												      [#elseif 3==w.type]
												         路由器
												      [#elseif 4==w.type]
												         操作系统
												      [#elseif 5==w.type]
												         办公软件
												      [#elseif 6==w.type]
												         网络
												        [#elseif 7==w.type]
												         其它
												      [#else]
												         未知
												   [/#if]
											</section>
										</div>
										<div class="row">
											<section class="col col-12">
												详细地址：${w.address}
											</section>	
										</div>
										<div class="row">
											<section class="col col-12">
												内容：${w.content}
											</section>	
										</div>
										<div class="row">
											<section class="col col-12">
												备注：${w.remark}
											</section>	
										</div>
										<div class="row">
											<section class="col col-6">
												创建人：${w.createUser}
											</section>
											<section class="col col-6">
													创建时间：[#if w.createDate?? ]${w.createDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												修改人：${t.updateUser}
											</section>
											<section class="col col-6">
													修改时间：[#if w.updateDate?? ]${w.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]
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
