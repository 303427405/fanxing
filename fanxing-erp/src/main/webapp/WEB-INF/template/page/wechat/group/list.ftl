[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<!--分组-->
<section id="widget-grid" class="">
	<div class="row">
		<article>
		
			<div class="jarviswidget ${formColor}" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			    <div>
			       <div class="widget-body no-padding">
			          <form id="searchForm" class="smart-form" action="/wechatGroupController/findWechatGroup.do" novalidate="novalidate" method="post">
							<fieldset>
								<div class="row">
									<select id="moreSelect" data-col="2" data-wechatId="${g.wechatId}" data-showFan="true" name="wechatId"/>
									<input type="text" data-icon="fa fa-user" name="name" value="${g.name}"  placeholder="分组名称">
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
				   		  <table id="listTable">
							<thead >
								<tr>
									<th ><label class="checkbox"><input type="checkbox" id="selectAll"><i></i> </label></th>
									<th>公众号</th>
									<th>分组名称</th>
									<th>粉丝数量</th>
								</tr>
							</thead>
							<tbody>
							  [#list page.rows as o]
								<tr>
									<td><label class="checkbox">
										<input type="checkbox" name="ids" value="${o.id}">
										<i></i> </label></td>
									<td>${o.wechatId}</td>
									<td>${o.groupName}</td>
									<td>${o.countFan}</td>
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
<script src="${resources_static}/js/wechat/select_fan.js"></script> 