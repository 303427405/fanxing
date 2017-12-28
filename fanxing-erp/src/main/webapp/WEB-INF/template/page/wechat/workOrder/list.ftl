[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<!-- 工单-->
<section id="widget-grid" class="">
	<div class="row">
		<article>
		
			<div class="jarviswidget ${formColor}" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			    <div>
			       <div class="widget-body no-padding">
			          <form id="searchForm" class="smart-form" action="/workOrderController/findWorkOrder.do" novalidate="novalidate" method="post">
							<fieldset>
								<div class="row">
									<input type="text" data-icon="fa fa-user" name="name" value="${w.name}"  placeholder="名称">
									<input type="text" data-icon="fa fa-user" name="content" value="${w.content}"  placeholder="内容">
							  		<select id="moreSelect" data-col="2" data-wechatId="${w.wechatId}" data-openId="${w.openId}" name="wechatId"/>
							  		<select id="dictionaryData"  data-code='workOrderStatus' data-value="${w.status}"  name="status"/>
									<input type="text" data-icon="fa fa-calendar"  name="updateTimeStart" id="startdate" value="${w.updateTimeStart}"  onfocus="WdatePicker({readOnly:true})" placeholder="修改开始时期">
								    <input type="text" data-icon="fa fa-calendar" name="updateTimeEnd"  value="${w.updateTimeEnd}" onfocus="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'startdate\')}'})" placeholder="修改结束时期">
								</div>
							 </fieldset>
							</form>
						</div>	
			    </div>
			</div>
			
	        <div class="alert ${toolColor}" id="toolForm" style="margin-top:-30px;margin-bottom:5px">
	    		  [@auth.auth ifAnyAuth="function.workorder.add"]
					 <a  href="/workOrderController/add_view.do"   id="addSysBtn"><i class="iconfont icon-tianjia"></i>  添加工单</a>
			      [/@auth.auth]
			      [@auth.auth ifAnyAuth="function.workorder.update"]
					<a  href="/workOrderController/edit_view.do"  id="editSysOneBtn" ><i class="iconfont icon-edit"></i>  编辑工单</a>
				  [/@auth.auth]
				    <a  href="/workOrderController/view.do" id="viewSysOneBtn"><i class="iconfont icon-zhuangtailaiyuan"></i> 详情</a>
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
									<th>工单名称</th>
									<th>所属公众号</th>
									<th>所属客户</th>
									<th>联系方式</th>
									<th>工单类型</th>
									<th>内容</th>
									<th>详细地址</th>
									<th >状态 </th>
									<th>修改人</th>
									<th class="sort" data-sort="ww.updateDate">修改日期</th>
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
									<td>${o.wechatName}</td>
									<td title="${o.fanNickName}">${abb(o.fanNickName,10,"...")}</td>
									<td>${o.mobile}</td>
									<td>[#if 0==o.type ]
												      <span class="label label-success text-center pull-center"> 主机
												      [#elseif 1==o.type]
												         <span class="label label-success text-center pull-center">显示器
												      [#elseif 2==o.type]
												        <span class="label label-success text-center pull-center">摄像头</span>
												      [#elseif 3==o.type]
												         <span class="label label-success text-center pull-center">路由器</span>
												      [#elseif 4==o.type]
												         <span class="label label-success text-center pull-center">操作系统</span>
												      [#elseif 5==o.type]
												         <span class="label label-success text-center pull-center">办公软件</span>
												      [#elseif 6==o.type]
												        <span class="label label-success text-center pull-center">网络</span>
												        [#elseif 7==o.type]
												         <span class="label label-success text-center pull-center">其它</span>
												      [#else]
												        <span class="label label-warning text-center pull-center">未知</span>
												   [/#if]
									</td>
									<td title="${o.content}">${abb(o.content,20,"...")}</td>
									<td title="${o.address}">${abb(o.address,20,"...")}</td>
									<td>[#if 0==o.status ]
									       <span class="label label-success text-center pull-center">已受理</span>
									      [#elseif 1==o.status]
									         <span class="label label-success text-center pull-center">正在处理</span>
									      [#elseif 2==o.status]
									         <span class="label label-success text-center pull-center">已完成</span>
									      [#elseif 3==o.status]
									         <span class="label label-warning text-center pull-center">已取消</span>
									      [#else]
									         <span class="label label-warning text-center pull-center">未知</span>
									    [/#if]
									</td>
									<td>${o.updateUser}</td>
									<td>[#if o.updateDate?? ]${o.updateDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]</td>
									<td>
										<div class="btnGroupTool pull-center">
												<button><i class="fa fa-cog"></i>&nbsp&nbsp</button>
												<button class="dropdown-toggle" data-toggle="dropdown"/>
												<ul class="dropdown-menu">
													[@auth.auth ifAnyAuth="function.workorder.audit"]
													   [#if 0==o.status ]
														<li><a class='group_handleSysBtn' ><i class="iconfont icon-chulizhong"></i> 处理</a></li>
													   [/#if]
													   [#if 1==o.status ]
														<li><a class='group_completeSysBtn' ><i class="iconfont icon-wancheng"></i> 完成</a></li>
													   [/#if]
													   [#if 3!=o.status ]
													   		<li><a class='group_canceledSysBtn' ><i class="iconfont icon-quxiao"></i> 取消</a></li>
													    [/#if]
													[/@auth.auth]
													
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
<script src="${resources_static}/js/wechat/select_fan.js"></script>
<script src="${resources_static}/js/wechat/workorder/list.js"></script>