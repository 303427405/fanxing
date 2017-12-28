[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<!-- 在线用户-->
<section id="widget-grid" class="">
	<div class="row">
		<article>
		
			<div class="jarviswidget ${formColor}" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			    <div>
			       <div class="widget-body no-padding">
			          <form id="searchForm" class="smart-form" action="/loginController/findOnLineUser.do" novalidate="novalidate" method="post">
							<fieldset>
								<div class="row">
									<select id="selectArea" data-org="true" data-initlevel="1"   data-orgCode="${o.orgCode}" data-areaCode="${o.areaCodes}"/>
									<input type="text" data-icon="fa fa-user" name="loginName" value="${o.loginName}"  placeholder="用户名">
								</div>
							 </fieldset>
							</form>
						</div>	
			    </div>
			</div>
			
	        <div class="alert ${toolColor}" id="toolForm" style="margin-top:-30px;margin-bottom:5px">
					<a  href="javascript:void(0);" id="refreshBtn"><i class="iconfont icon-shuaxin"></i>  刷新</a>
			</div>
		
			<div class="jarviswidget ${tableColor}" id="wid-id-2" data-widget-togglebutton="false" >
				<div>
				    <div class="custom-scroll table-responsive">
				   		  <table id="listTable">
							<thead >
								<tr>
									<th style="width: auto">用户名</th>
									<th>所属组织</th>
									<th >状态 </th>
									<th >有效期 </th>
									<th >最后登录日期</th>
									<th >登录IP</th>
								</tr>
							</thead>
							<tbody>
							  [#list page as o]
								<tr>
									<td>${o.username}</td>
									<td>${o.orgName}</td>
									<td>[#if o.enabled==true ]
									       <span class="label label-success text-center pull-center">启用</span>
									      [#else]
									      <span class="label label-warning text-center pull-center">禁用</span>
									    [/#if]
									</td>
									<td>[#if o.validDate==null ] <span class="label label-success text-center pull-center">无限制</span>[#else]${o.validDate}[/#if]</td>
									<td>${o.lastLoginDate?string("yyyy-MM-dd HH:mm:ss")}</td>
									<td>${o.ip}</td>
								</tr>
								[/#list]
							</tbody>
						</table>
						<br/><br/><br/><br/>
				   </div>
				</div>
			</div>
			
		</article>
	</div>
</section>
<script src="${resources_static}/style/index/js/list.js"></script>
<script src="${resources_static}/style/index/js/areaSelect.js"></script>