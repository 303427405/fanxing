[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<!--粉丝-->
<section id="widget-grid" class="">
	<div class="row">
		<article>
		
			<div class="jarviswidget ${formColor}" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			    <div>
			       <div class="widget-body no-padding">
			          <form id="searchForm" class="smart-form" action="/fanController/findFan.do" novalidate="novalidate" method="post">
							<fieldset>
								<div class="row">
									<select id="moreSelect" data-col="2" data-wechatId="${f.wechatId}" data-showFan="true" name="wechatId"/>
									<input type="text" data-icon="fa fa-user" name="nickName" value="${f.nickName}"  placeholder="粉丝昵称">
									<select id="selectArea"     data-areaCode="${f.areaCodes}"/>
								</div>
							 </fieldset>
							</form>
						</div>	
			    </div>
			</div>
			
	        <div class="alert ${toolColor}" id="toolForm" style="margin-top:-30px;margin-bottom:5px">
	          	  [@auth.auth ifAnyAuth="function.fan.update"]
					 <a  href="/fanController/edit_view.do"   id="editSysOneBtn"><i class="iconfont icon-edit"></i>  编辑</a>
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
									<th>所在分组</th>
									<th>粉丝昵称</th>
									<th>性别</th>
									<th>省</th>
									<th>市</th>
									<th>手机</th>
									<th>详细地址</th>
									<th class="sort" data-sort="wf.createDate">关注日期</th>
								</tr>
							</thead>
							<tbody>
							  [#list page.rows as o]
								<tr>
									<td><label class="checkbox">
										<input type="checkbox" name="ids" value="${o.id}">
										<i></i> </label></td>
									<td>${o.wechatName}</td>
									<td>${o.groupName}</td>
									<td>${o.nickName}</td>
									<td>[#if o.sex==1 ]男[#elseif o.sex==2 ]女[#else]未知[/#if]</td>
									<td>${o.province}</td>
									<td>${o.city}</td>
									<td>${o.mobile}</td>
									<td title="${o.addressFull}">${abb(o.addressFull,20,"...")}</td>
									<td>[#if o.createDate?? ]${o.createDate?string("yyyy-MM-dd HH:mm:ss")}[/#if]</td>
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