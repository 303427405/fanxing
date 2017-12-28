[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<!-- 快捷方式-->
<section id="widget-grid" class="">
	<div class="row">
		<article>
		
			<div class="jarviswidget ${formColor}" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			    <div>
			       <div class="widget-body no-padding">
			        <form id="searchForm" class="smart-form" action="/shortcutController/findShortcut.do" novalidate="novalidate" method="post">
							<fieldset>
								<div class="row">
									<input type="text" data-icon="fa fa-user" name="name" value="${s.name}"  placeholder="快捷名称">
								</div>
							 </fieldset>
							</form>
						</div>	
			    </div>
			</div>
			
	        <div class="alert ${toolColor}" id="toolForm" style="margin-top:-30px;margin-bottom:5px">
					[@auth.auth ifAnyAuth="function.shortcut.add"]
					<a  href="/shortcutController/add_view.do"   id="addSysBtn"><i class="iconfont icon-tianjia"></i>  添加</a>
					[/@auth.auth]
					[@auth.auth ifAnyAuth="function.shortcut.delete"]
					<a  href="/shortcutController/delShortcut.do"  id="delSysAllBtn" rel="tooltip" data-placement="bottom" data-original-title="<i class='fa fa-info'></i>支持批量操作" data-html="true"  ><i class="iconfont icon-delete"></i> 删除</a>
					[/@auth.auth]
					<a  href="javascript:void(0);" id="refreshBtn"><i class="iconfont icon-shuaxin"></i>  刷新</a>
			</div>
		
			<div class="jarviswidget ${tableColor}" id="wid-id-2" data-widget-togglebutton="false" >
				[@paging pageSize = page.pageSize total =page.total pageNum =page.pageNum]
						[#include "page/include/paging.ftl"]	
				[/@paging]
				<div>
				    <div class="custom-scroll table-responsive">
				   		  <table id="listTable" >
								<thead >
									<tr>
										<th><label class="checkbox"><input type="checkbox" id="selectAll"><i></i> </label></th>
										<th>快捷名称</th>
										<th>排序</th>
										<th>修改人</th>
										<th class="sort" data-sort="ss.updateDate">修改日期</th>
										<th id="handle">操作</th>
									</tr>
								</thead>
								<tbody>
								  [#list page.rows as o]
									<tr>
										<td><label class="checkbox">
											<input type="checkbox" name="ids" value="${o.id}">
											<i></i> </label></td>
										<td>${o.name}</td>
										<td>${o.sequence}</td>
										<td>${o.updateUser}</td>
										<td>[#if o.updateDate?? ]${o.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]</td>
										<td>
											<div class="btnGroupTool">
												  [@auth.auth ifAnyAuth="function.shortcut.delete" ]
														<button><i class="fa fa-cog"></i>&nbsp&nbsp</button>
														<button class="dropdown-toggle" data-toggle="dropdown"/>
														<ul class="dropdown-menu">
															<li><a href="/shortcutController/delShortcut.do" class="group_delSysBtn" ><i class="iconfont icon-delete"></i> 删除</a></li>
														</ul>
												  [/@auth.auth]
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