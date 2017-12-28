[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<!-- 用户-->
<section id="widget-grid" class="">
	<div class="row">
		<article>
		
			<div class="jarviswidget ${formColor}" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			    <div>
			       <div class="widget-body no-padding">
			          <form id="searchForm" class="smart-form" action="/operatorController/findOperator.do" novalidate="novalidate" method="post">
							<fieldset>
								<div class="row">
									<input type="text" data-icon="fa fa-user" name="loginName" value="${o.loginName}"  placeholder="用户名">
							  		<select id="dictionaryData"  data-code='systemStatus' data-value="${o.enabled}"  name="enabled"/>
									[@auth.auth ifAnyAuth="function.operator.orgSelect"]
										<select id="selectArea" data-org="true" data-initlevel="1"   data-orgCode="${o.orgCode}" data-areaCode="${o.areaCodes}"/>
									[/@auth.auth]
									<input type="text" data-icon="fa fa-calendar"  name="updateTimeStart" id="startdate" value="${o.updateTimeStart}"  onfocus="WdatePicker({readOnly:true})" placeholder="修改开始时期">
								    <input type="text" data-icon="fa fa-calendar" name="updateTimeEnd"  value="${o.updateTimeEnd}" onfocus="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'startdate\')}'})" placeholder="修改结束时期">
								</div>
							 </fieldset>
							</form>
						</div>	
			    </div>
			</div>
			
	        <div class="alert ${toolColor}" id="toolForm" style="margin-top:-30px;margin-bottom:5px">
	    		  [@auth.auth ifAnyAuth="function.operator.add"]
					 <a  href="/operatorController/add_view.do"   id="addSysBtn"><i class="iconfont icon-tianjia"></i>  添加</a>
			      [/@auth.auth]
			      [@auth.auth ifAnyAuth="function.operator.resetPassword"]
					<a  href="/operatorController/resetPassword.do" id="resetPwdSysOneBtn"><i class="iconfont icon-password"></i> 重置密码</a>
				  [/@auth.auth]
					<a  href="javascript:void(0);" id="refreshBtn"><i class="iconfont icon-shuaxin"></i>  刷新</a>
			</div>
		
			<div class="jarviswidget ${tableColor}" id="wid-id-2" data-widget-togglebutton="false" >
				[@paging pageSize = page.pageSize total =page.total pageNum =page.pageNum]
						[#include "page/include/paging.ftl"]	
				[/@paging]
				<div>
				    <div class="custom-scroll table-responsive">
				   		  <table id="listTable">
							<thead >
								<tr>
									<th ><label class="checkbox"><input type="checkbox" id="selectAll"><i></i> </label></th>
									<th>用户名</th>
									<th>昵称</th>
									<th>所属组织</th>
									<th >状态 </th>
									<th >有效期 </th>
									<th>修改人</th>
									<th class="sort" data-sort="so.updateDate">修改日期</th>
									<th id="handle">操作</th>
								</tr>
							</thead>
							<tbody>
							  [#list page.rows as o]
								<tr>
									<td><label class="checkbox">
										<input type="checkbox" name="ids" value="${o.id}" data-enabled=${o.enabled}>
										<i></i> </label></td>
									<td>${o.loginName}</td>
									<td>${o.realName}</td>
									<td>${o.orgName}</td>
									<td>[#if o.enabled==1 ]
									       	<span class="label label-success text-center pull-center">启用</span>
									      [#elseif o.status==0]
									      	<span class="label label-warning text-center pull-center">禁用</span>
									      [#else]
									      	<span class="label label-warning text-center pull-center">未知</span>
									    [/#if]
									</td>
									<td>[#if o.validDate==null ] <span class="label label-success text-center pull-center">无限制</span>[#else]${o.validDate}[/#if]</td>
									
									<td>${o.updateUser}</td>
									<td>[#if o.updateDate?? ]${o.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]</td>
									<td>
										<div class="btnGroupTool">
												<button><i class="fa fa-cog"></i>&nbsp&nbsp</button>
												<button class="dropdown-toggle" data-toggle="dropdown"/>
												<ul class="dropdown-menu">
													[@auth.auth ifAnyAuth="function.operator.update"]
														<li><a href="/operatorController/edit_view.do" class='group_editSysBtn' ><i class="fa fa-edit"></i> 编辑</a></li>
													[/@auth.auth]
													[@auth.auth ifAnyAuth="function.operator.enabled"]
														<li><a  href="/operatorController/changeEnabled.do" class="group_statusSysBtn"><i class="iconfont icon-zhuangtailaiyuan"></i> 启/禁用</a></li>
													[/@auth.auth]
													<li><a class='group_viewSysBtn' href="/operatorController/view.do" ><i class="fa fa-external-link"></i> 查看</a></li>
												</ul>
										 </div>
									</td>
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
<script src="${resources_static}/js/operator/list.js"></script>