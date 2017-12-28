<header>
	<span class="widget-icon"> <i class="fa fa-table"></i></span>
		<small class='text-info'> &nbsp&nbsp 显示${startShow}-${endShow}条 共${total}条</small>
	<div class="btn-group" style="margin-left: 10px; margin-bottom: 10px">
		<button class="btn dropdown-toggle btn-xs btn-info"
			data-toggle="dropdown">
			每页显示 <i class="fa fa-caret-down"></i>
		</button>
		<ul class="dropdown-menu pull-center page-num" style="min-width: 50px">
			[#list page.pageEnum as p]
			<li [#if p.size==page.pageSize ] class="label-info" [/#if]><a href="javascript:void(0);"> [#if p.size==page.pageSize ] <i class="fa fa-check"></i> [/#if]  ${p.size}</a></li>
			[/#list]
		</ul>
	</div>
	<div class="widget-toolbar smart-form">
		<ul class="pagination pagination-xs">
			<li [#if isFirst] class="disabled"  [/#if]><a href="javascript:void(0);">首页</a></li>
			<li [#if isFirst] class="disabled"  [/#if]><a href="javascript:void(0);">上一页</a></li>
			[#list pageSplit as p]
			  <li [#if p==pageNum ] class="active" [/#if]><a href="javascript:void(0);">${p}</a></li>
			[/#list]
			[#if hasNext]<li ><a href="javascript:void(0);" alt="下一组">...</a></li>[/#if]
			<li [#if isLast] class="disabled"  [/#if]><a href="javascript:void(0);">下一页</a></li>
			<li [#if isLast] class="disabled"  [/#if]><a href="javascript:void(0);"  data-pagetotal=${pageTotal}>尾页</a></li>
		</ul>
		<div class="widget-toolbar smart-form">
			<label class="input" style="margin-top: -2px"> <i
				class="icon-append fa fa-hand-o-right" style="cursor: pointer" id="pageSkip" data-pagetotal=${pageTotal}></i>
				<input type="text" placeholder="至第几页" style="width: 100px" onpaste="return false;" id="pageSkipNum" data-pagetotal=${pageTotal}>
				<b class="tooltip tooltip-top-right"> <i
					class="fa fa-info txt-color-teal"></i> 输入数字到指定页
			</b>
			</label>
			<input id="tableSort" type="hidden" value="${page.sort}">
		</div>
	</div>
</header>
<script type="text/javascript">
$("#listTable .sort").each(function(){
	var flg='${page.sort}';
	if(flg.indexOf("DESC")>0){
		$(this).append('<a href="javascript:void(0);" class="btn btn-xs btn-default pull-center" id="table_sort"><i class="fa fa-sort-desc"></i></a>');
	}else if(flg.indexOf("ASC")>0){
		$(this).append('<a href="javascript:void(0);" class="btn btn-xs btn-default pull-center" id="table_sort"><i class="fa fa-sort-asc"></i></a>');
	}else{
		$(this).append('<a href="javascript:void(0);" class="btn btn-xs btn-default pull-center" id="table_sort"><i class="fa fa-sort"></i></a>');
	}
});
</script>
