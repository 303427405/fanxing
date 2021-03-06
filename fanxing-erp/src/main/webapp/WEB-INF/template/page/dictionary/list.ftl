[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<!--字典-->
<section id="widget-grid" class="">
	<div class="row">
		<article>
		
		<div class="jarviswidget ${formColor}" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			<div>
		       <div class="widget-body no-padding">
		        	<form id="searchForm" class="smart-form" action="/dictionaryController/findDictionary.do" novalidate="novalidate" method="post">
						<fieldset>
							<div class="row">
								<input type="hidden" id="parentId" name="parentId" value="${d.parentId}">
								<input type="text" data-icon="fa fa-user" name="name" value="${d.name}"  placeholder="名称或编码">
								<select id="dictionaryData" data-code='systemStatus' data-value="${d.enabled}"  name="enabled"/>
								<input type="text" data-icon="fa fa-calendar" name="updateTimeStart" id="startdate" value="${d.updateTimeStart}"  onfocus="WdatePicker({readOnly:true})" placeholder="修改开始时期">
							    <input type="text" data-icon="fa fa-calendar" name="updateTimeEnd"  value="${d.updateTimeEnd}" onfocus="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'startdate\')}'})" placeholder="修改结束时期">
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
				[@paging pageSize = page.pageSize total =page.total pageNum =page.pageNum]
						[#include "page/include/paging.ftl"]	
				[/@paging]
				<div>
				    <div class="custom-scroll table-responsive">
				   		  <table id="listTable" >
								<thead >
									<tr>
										<th><label class="checkbox"><input type="checkbox" id="selectAll"><i></i></label></th>
										<th>名称</th>
										<th>编码</th>
										<th>层级</th>
										<th>状态</th>
										<th>排序</th>
										<th>修改人</th>
										<th class="sort" data-sort="sd.updateDate">修改日期</th>
										<th id="handle">操作</th>
									</tr>
								</thead>
								<tbody>
								  [#list page.rows as o]
									<tr>
										<td><label class="checkbox">
											<input type="checkbox" name="ids" value="${o.id}" data-enabled=${o.enabled}><i></i></label>
										</td>
										<td class="dictionaryName" data-leaf="${o.leaf}" data-id="${o.id}">${o.name}</td>
										<td>${o.code}</td>
										<td><span class="label label-default text-center pull-center">[#if o.leaf==1 ]一[#else]二[/#if]级</span></td>
										<td>[#if o.enabled==1 ]
										       <span class="label label-success text-center pull-center">启用</span>
										      [#else]
										      <span class="label label-warning text-center pull-center">&nbsp&nbsp禁用&nbsp&nbsp</span>
										    [/#if]
										</td>
										<td>${o.sequence}</td>
										<td>${o.updateUser}</td>
										<td>[#if o.updateDate?? ]${o.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]</td>
										<td>
											<div class="btnGroupTool">
												[@auth.auth ifAnyAuth="function.dictionary.enabled"]
													<button><i class="fa fa-cog"></i>&nbsp&nbsp</button>
													<button class="dropdown-toggle" data-toggle="dropdown"/>
													<ul class="dropdown-menu">
														<li><a href="/dictionaryController/changeEnabled.do" class="group_statusSysBtn"><i class="iconfont icon-zhuangtailaiyuan"></i> 启/禁用</a></li>
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
<script src="${resources_static}/style/index/js/areaSelect.js"></script>
<script src="${resources_static}/js/dictionary/list.js"></script>