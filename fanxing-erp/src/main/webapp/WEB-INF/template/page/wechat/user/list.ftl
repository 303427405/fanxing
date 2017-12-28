[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<!-- 用户-->
<section id="widget-grid" class="">
	<div class="row">
		<article>
		
			<div class="jarviswidget ${formColor}" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			    <div>
			       <div class="widget-body no-padding">
			          <form id="searchForm" class="smart-form" action="/tencentUserController/findTencentUser.do" novalidate="novalidate" method="post">
							<fieldset>
								<div class="row">
									<input type="text" data-icon="fa fa-user" name="weChatId" value="${t.weChatId}"  placeholder="微信号">
							  		<select id="dictionaryData"  data-code='systemStatus' data-value="${t.status}"  name="status"/>
									[@auth.auth ifAnyAuth="function.tencentUser.orgSelect"]
										<select id="selectArea" data-org="true" data-initlevel="1"   data-orgCode="${t.orgCode}" data-areaCode="${t.areaCodes}"/>
									[/@auth.auth]
									<input type="text" data-icon="fa fa-calendar"  name="updateTimeStart" id="startdate" value="${t.updateTimeStart}"  onfocus="WdatePicker({readOnly:true})" placeholder="修改开始时期">
								    <input type="text" data-icon="fa fa-calendar" name="updateTimeEnd"  value="${t.updateTimeEnd}" onfocus="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'startdate\')}'})" placeholder="修改结束时期">
								</div>
							 </fieldset>
							</form>
						</div>	
			    </div>
			</div>
			
	        <div class="alert ${toolColor}" id="toolForm" style="margin-top:-30px;margin-bottom:5px">
	    		  [@auth.auth ifAnyAuth="function.tencentUser.add"]
					 <a  href="/tencentUserController/add_view.do"   id="addSysBtn"><i class="iconfont icon-tianjia"></i>  添加</a>
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
									<th>公众号</th>
									<th>公众号Id</th>
									<th>appId</th>
									<th>appSecret</th>
									<th>所属组织</th>
									<th >状态 </th>
									<th>修改人</th>
									<th class="sort" data-sort="so.updateDate">修改日期</th>
									<th id="handle">操作</th>
								</tr>
							</thead>
							<tbody>
							  [#list page.rows as o]
								<tr>
									<td><label class="checkbox">
										<input type="checkbox" name="ids" value="${o.id}" data-enabled=${o.status}>
										<i></i> </label></td>
									<td>${o.name}</td>
									<td>${abb(o.weChatId,10,"...")}</td>
									<td>${abb(o.appId,10,"...")}</td>
									<td>${abb(o.appSecret,10,"...")}</td>
									<td>${o.orgName}</td>
									<td>[#if o.status==1 ]
									       <span class="label label-success text-center pull-center">启用</span>
									      [#else]
									      <span class="label label-warning text-center pull-center">&nbsp&nbsp禁用&nbsp&nbsp</span>
									    [/#if]
									</td>
									<td>${o.updateUser}</td>
									<td>[#if o.updateDate?? ]${o.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]</td>
									<td>
										<div class="btnGroupTool pull-center">
												<button><i class="fa fa-cog"></i>&nbsp&nbsp</button>
												<button class="dropdown-toggle" data-toggle="dropdown"/>
												<ul class="dropdown-menu">
													[@auth.auth ifAnyAuth="function.tencentUser.update"]
														<li><a href="/tencentUserController/edit_view.do" class='group_editSysBtn' ><i class="fa fa-edit"></i>编辑</a></li>
													[/@auth.auth]
													[@auth.auth ifAnyAuth="function.tencentUser.enabled"]
														<li><a  href="/tencentUserController/changeStatus.do" class="group_statusSysBtn"><i class="iconfont icon-zhuangtailaiyuan"></i>启/禁用</a></li>
													[/@auth.auth]
													[@auth.auth ifAnyAuth="function.tencentUser.replyMaterial"]
													<li><a  href="/tencentUserController/reply_view.do" class="group_editSysBtn"><i class="iconfont icon-zhuangtailaiyuan"></i>关注回复</a></li>
													[/@auth.auth]
													<li><a  href="/tencentUserController/view.do" class='group_viewSysBtn' ><i class="fa fa-external-link"></i>查看</a></li>
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