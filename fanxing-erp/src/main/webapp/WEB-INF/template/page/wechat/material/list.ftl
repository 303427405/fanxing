[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<!-- 素材-->
<section id="widget-grid" class="">
	<div class="row">
		<article>
		
			<div class="jarviswidget ${formColor}" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			    <div>
			       <div class="widget-body no-padding">
			          <form id="searchForm" class="smart-form" action="/materialController/findMaterial.do" novalidate="novalidate" method="post">
							<fieldset>
								<div class="row">
									<input type="text" data-icon="fa fa-user" name="name" value="${m.name}"  placeholder="名称">
							  		<select id="dictionaryData"  data-code='materialType' data-placeholder="素材类型" data-value="${m.type}"  name="type"/>
								</div>
							 </fieldset>
							</form>
						</div>	
			    </div>
			</div>
			
	        <div class="alert ${toolColor}" id="toolForm" style="margin-top:-30px;margin-bottom:5px">
	    		  [@auth.auth ifAnyAuth="function.material.add"]
					 <a  href="/materialController/add_view.do"   id="addSysBtn"><i class="iconfont icon-tianjia"></i>  添加</a>
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
									<th>名称</th>
									<th >状态 </th>
									<th>类型</th>
									<th>内容</th>
									<th>图片地址</th>
									<th>图文地址 </th>
									<th>所属组织</th>
									<th>修改人</th>
									<th class="sort" data-sort="wm.updateDate">修改日期</th>
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
							        <td>[#if o.status==1 ]
									       <span class="label label-success text-center pull-center">启用</span>
									      [#elseif o.status==0]
									      <span class="label label-warning text-center pull-center">禁用</span>
									      [#else]
									      <span class="label label-warning text-center pull-center">未知</span>
									    [/#if]
									</td>
									<td>[#if 0==o.type ]
									       <span class="label label-success text-center pull-center">文本</span>
									      [#elseif 1==o.type]
									         <span class="label label-success text-center pull-center">图片</span>
									      [#elseif 2==o.type]
									         <span class="label label-success text-center pull-center">图文</span>
									      [#else]
									         <span class="label label-warning text-center pull-center">未知</span>
									    [/#if]
									</td>
									<td>${abb(o.content,10,"...")}</td>
									<td>[#if o.photoUrl?? ]<span class="viewImg label label-success text-center" data-url="${upload_static}${o.photoUrl}">查看</span>[/#if]</td>
									<td>${abb(o.newUrl,10,"...")}</td>
									<td>${o.orgName}</td>
									<td>${o.updateUser}</td>
									<td>[#if o.updateDate?? ]${o.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]</td>
									<td>
										<div class="btnGroupTool pull-center">
												<button><i class="fa fa-cog"></i>&nbsp&nbsp</button>
												<button class="dropdown-toggle" data-toggle="dropdown"/>
												<ul class="dropdown-menu">
													[@auth.auth ifAnyAuth="function.material.update"]
														<li><a href="/materialController/edit_view.do" class='group_editSysBtn' ><i class="fa fa-edit"></i> 编辑</a></li>
													[/@auth.auth]
													[@auth.auth ifAnyAuth="function.material.enabled"]
														<li><a  href="/materialController/changeStatus.do" class="group_statusSysBtn"><i class="iconfont icon-zhuangtailaiyuan"></i> 启/禁用</a></li>
													[/@auth.auth]
													<li><a  href="/materialController/view.do" class='group_viewSysBtn' ><i class="fa fa-external-link"></i> 查看</a></li>
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
<script src="${resources_static}/js/wechat/material/list.js"></script>
<script src="${resources_static}/style/index/js/plugin/superbox/superbox.min.js"></script>