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
						<strong>&nbsp&nbsp新增商户</strong>
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
                                                <li data-target="#step3">
                                                    <span class="badge">3</span>商户信息<span class="chevron"></span>
                                                </li>
                                                <li data-target="#step4">
                                                    <span class="badge">4</span>上传文件<span class="chevron"></span>
                                                </li>
											</ul>
											<div class="actions">
												<button type="button" class="btn btn-sm btn-primary btn-prev">
													<i class="fa fa-arrow-left"></i>上一步
												</button>
												<button type="button" id="submit" class="btn btn-sm btn-success btn-next" data-last="保存">
													下一步<i class="fa fa-arrow-right"></i>
												</button>
												<button type="button" data-returnUrl="/shopController/findShop.do" class="btn btn-sm btn-info" id="cancel">
													<i class="fa fa-location-arrow"></i> 取消
												</button>
											</div>
						 </div>
						 <div class="step-content">
				             <form id="addForm" class="smart-form" enctype="multipart/form-data"  returnUrl="/shopController/findShop.do" action="/shopController/addShop.do">
				              <div class="step-pane active" id="step1">
				                 <fieldset>
                                    <div class="row">
                                        <section >
                                            <label class="input"> <i class="icon-append fa fa-user"></i>
                                                <input type="text" name="name" placeholder="商户名称">
                                                 </label>
                                            </label>
                                        </section>
                                    </div>

                                     <div class="row">
                                         <section >
                                             <label class="input"> <i class="icon-append fa fa-user"></i>
                                                 <input type="text" name="username" placeholder="用户名（登陆使用）">
                                             </label>
                                             </label>
                                         </section>
                                     </div>


                                     <div class="row">
                                         <section >
                                             <label class="input"> <i class="icon-append fa fa-user"></i>
                                                 <input type="password" name="password" placeholder="用户密码">
                                             </label>
                                             </label>
                                         </section>
                                     </div>


                                     <div class="row">
                                         <section >
                                             <label class="input"> <i class="icon-append fa fa-user"></i>
                                                 <input type="text" name="nick" placeholder="商户昵称">
                                             </label>
                                             </label>
                                         </section>
                                     </div>


                                     <div class="row">
                                         <section >
                                             <label class="input"> <i class="icon-append fa fa-user"></i>
                                                 <input type="text" name="contact" placeholder="联系人">
                                             </label>
                                             </label>
                                         </section>
                                     </div>


                                     <div class="row">
                                         <section >
                                             <label class="input"> <i class="icon-append fa fa-user"></i>
                                                 <input type="text" name="contactPhone" placeholder="联系电话">
                                             </label>
                                             </label>
                                         </section>
                                     </div>


                                     <div class="row">
                                         <section >
                                             <label class="input"> <i class="icon-append fa fa-user"></i>
                                                 <input type="text" name="idNumber" placeholder="身份证号">
                                             </label>
                                             </label>
                                         </section>
                                     </div>

								</fieldset>
				              </div>
				              <div class="step-pane" id="step2">
				                <fieldset>
                                    <div class="row">
                                        <section >
                                            <label class="input">  <i class="icon-append iconfont icon-orgb"></i>
                                                <input type="text" readonly="readonly" name="areaCodeName" id="areaCodeName" placeholder="所属区域">
                                                <input type="hidden" name="areaCode" id="areaCode" placeholder="所属区域">
                                            </label>
                                        </section>
                                    </div>

                                    <div class="row">
                                        <section >
                                            <div class="form-group">
                                                <select class="form-control" id="marketCode" name="marketCode"></select>

                                        </section>
                                    </div>

                                    <div class="row">
                                        <section>
                                            <label class="input">  <i class="icon-append iconfont icon-orgb"></i>
                                                <input type="text" name="address" id="address" placeholder="详细地址">
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



                                 <div class="step-pane" id="step3">
                                     <fieldset>
                                         <div class="row">
                                             <section >
                                                 <label class="input"> <i class="icon-append fa fa-user"></i>
                                                     <select class="form-control" id="createSource" name="shopTypeEnum">
                                                         <option value="SUPPLIER">供应商</option>
                                                         <option value="PURCHASERS">采购商</option>
                                                     </select>
                                                 </label>
                                             </section>
                                         </div>

                                         <div class="row">
                                             <section >
                                                 <label class="input"> <i class="icon-append fa fa-user"></i>
                                                     <input type="text" name="startTime" placeholder="开始营业时间">
                                                 </label>
                                             </section>
                                         </div>


                                         <div class="row">
                                             <section >
                                                 <label class="input"> <i class="icon-append fa fa-user"></i>
                                                     <input type="text" name="endTime" placeholder="结束营业时间">
                                                 </label>
                                             </section>
                                         </div>


                                     </fieldset>
                                 </div>

                                 <div class="step-pane" id="step4">
                                     <fieldset>
                                         <div class="row">

                                             <section >
                                                 <div class="input input-file">
                                                     <span class="button"><input id="idCardFaceFile"  type="file" name="file" >浏览</span><input type="text" id="idCardFaceName" placeholder="请选择身份证正面">
                                                 </div>
                                                 <input type="hidden" id="idCardFace" name="idCardFace">
                                             </section>
                                         </div>


                                         <div class="row">
                                             <section >
                                                 <label class="input input-file"">
                                                     <span class="button"><input id="idCardBackFile"  type="file" name="file" >浏览</span><input type="text" id="idCardBackName" placeholder="请选择身份证背面">
                                                 </label>
                                                 <input type="hidden" name="idCardBack" id = "idCardBack" >
                                             </section>
                                         </div>


                                         <div class="row">
                                             <section >
                                                 <label class="input input-file"">
                                                     <span class="button"><input id="bigLogoFile"  type="file" name="file" >浏览</span><input type="text" id="bigLogoName" placeholder="请选择商户大图">
                                                 </label>
                                                 <input type="hidden" name="bigLogo" placeholder="商户大图" id="bigLogo">
                                             </section>
                                         </div>

                                         <div class="row">
                                             <section >
                                                 <label class="input input-file"">
                                                     <span class="button"><input id="minLogoFile"  type="file" name="file" >浏览</span><input type="text" id="minLogoName" placeholder="请选择商户小图">
                                                 </label>
                                                 <input type="hidden" name="minLogo" placeholder="商户小图" id="minLogo">
                                             </section>
                                         </div>


                                     <div class="row">
                                         <section >
                                             <label class="input input-file"">
                                                 <span class="button"><input id="businessLicenseFile"  type="file" name="file" >浏览</span><input type="text" id="businessLicenseName" placeholder="请选择商户营业执照">
                                             </label>
                                             <input type="hidden" name="businessLicense" placeholder="营业执照" id="businessLicense">
                                         </section>
                                     </div>


                                     <div class="row">
                                         <section >
                                             <label class="input input-file"">
                                                 <span class="button"><input id="foodLicenseFile"  type="file" name="file" >浏览</span><input type="text" id="foodLicenseName" placeholder="请选择商户食品许可证">
                                             </label>
                                             <input type="hidden" name="foodLicense" placeholder="食品许可证" id="foodLicense">
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
<script src="${resources_static}/js/shop/add.js"></script>

