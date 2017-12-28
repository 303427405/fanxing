[#assign auth=JspTaglibs["/WEB-INF/tld/auth.tld"] /]
<!--微信消息-->
<section id="widget-grid" class="">
	<div class="row">
		<article>
		
			<div class="jarviswidget ${formColor}" id="wid-id-1"    data-widget-fullscreenbutton="false"   >
			    <div>
			       <div class="widget-body no-padding">
			          <form id="searchForm" class="smart-form" action="/inboxController/findInbox.do" novalidate="novalidate" method="post">
							<fieldset>
								<div class="row">
									<input type="text" data-icon="iconfont icon-msg" name="content" value="${i.content}"  placeholder="消息内容">
									<select id="moreSelect" data-col="2" data-wechatId="${i.wechatId}" data-openId="${i.openId}" name="wechatId"/>
								</div>
							 </fieldset>
							</form>
						</div>	
			    </div>
			</div>
			
	        <div class="alert ${toolColor}" id="toolForm" style="margin-top:-30px;margin-bottom:5px">
	        		<a  href=""  id="replyOneBtn" ><i class="iconfont icon-edit"></i>  回复</a>
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
									<th>粉丝昵称</th>
									<th>所在分组</th>
									<th>内容 </th>
									<th class="sort" data-sort="createTime">接收日期</th>
								</tr>
							</thead>
							<tbody>
							  [#list page.rows as o]
								<tr>
									<td><label class="checkbox">
										<input type="checkbox" name="ids" value="${o.id}" data-wechatId="${o.wechatId}" data-openId="${o.openId}">
										<i></i> </label></td>
									<td>${o.wechatName}</td>
									<td>${o.fanNickName}</td>
									<td>${o.groupName}</td>
									<td title="${o.content}">${abb(o.content,20,"...")}</td>
									<td>[#if o.createTime?? ]${o.createTime?string("yyyy-MM-dd HH:mm:ss")}[/#if]</td>
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
	[#include "page/wechat/inbox/replyMsg.ftl"]
</section>
<script src="${resources_static}/style/index/js/list.js"></script>

<script src="${resources_static}/style/index/js/bootstrap/markdown/markdown.min.js"></script>
<script src="${resources_static}/style/index/js/bootstrap/markdown/to-markdown.min.js"></script>
<script src="${resources_static}/style/index/js/bootstrap/markdown/bootstrap-markdown.min.js"></script>
<script src="${resources_static}/style/index/js/bootstrap/markdown/summernote.js"></script>
<script src="${resources_static}/js/wechat/select_fan.js"></script>
<script src="${resources_static}/js/wechat/inbox/list.js"></script>