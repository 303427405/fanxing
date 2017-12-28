runAllForms();

var $listTable=$('#listTable');
var $selectAll=$("#listTable #selectAll");
var $ids = $("#listTable input[name='ids']");

var $addSysBtn = $('#toolForm #addSysBtn');
var $editSysOneBtn = $("#toolForm #editSysOneBtn");
var $delSysAllBtn = $("#toolForm #delSysAllBtn");
var $statusSysOneBtn = $("#toolForm #statusSysOneBtn");
var $refreshBtn = $("#toolForm #refreshBtn");
var $viewSysOneBtn = $("#toolForm #viewSysOneBtn");



var $searchForm = $("#searchForm");
var $searchFormInput=$('#searchForm input');
var $toolForm=$("#toolForm");

var $container = $('#content');
var $widgetGrid =$('#widget-grid');

var $btnGroupTool=$('.btnGroupTool');


var $pagination =$('.pagination li[class!=disabled] a');
var $pageSkip = $('#pageSkip');
var $sort=$("#tableSort");

/**
 * 组件初始化
 */
$('#widget-grid').jarvisWidgets({
	toggleClass : 'fa fa-minus | fa fa-plus',
	deleteButton:false,
	editButton:false,
	fullscreenClass : 'fa fa-resize-full | fa fa-resize-small'
});
$(function(){
	setPanelHeight();
});
$(window).resize(function(){
	setPanelHeight();
});

/**
 * 下拉框重新激活
 */
$('.selectpicker').selectpicker('render');

/**
 * searchform input style[1,2]
 */
var style_searchForm_input=1;
/**
 * 添加样式
 */
$toolForm.find('a').addClass('btn btn-info btn-xs');
$toolForm.find('a[id$=OneBtn]').addClass('disabled');
$toolForm.find('a[id$=AllBtn]').addClass('disabled');
$listTable.addClass('table table-bordered table-striped table-hover table-condensed smart-form has-tickbox');
$widgetGrid.find('article').addClass('col-xs-12 col-sm-12 col-md-12 col-lg-12');
$btnGroupTool.addClass('btn-group');
$btnGroupTool.css('width','60px');
$btnGroupTool.find('button').addClass('btn btn-success');
$btnGroupTool.find('button[class^=dropdown-toggle]').append('<i class="fa fa-angle-down"></i>');
$btnGroupTool.find('ul').addClass('pull-right').css('background-color','#d6dde7');
$searchForm.closest('div[class^=jarviswidget]').prepend('<header><span class="widget-icon"> <i class="fa fa-search"></i></span><h2>高级搜索</h2></header>')
$searchFormInput.each(function(){
	if($(this).attr('type')!='hidden'){
		if(style_searchForm_input==1){
			$(this).wrap("<section class='col col-2'></section>").wrap("<label class='input'></label>");
			if($(this).data('icon')!=undefined){
				$(this).before("<i class='icon-prepend "+$(this).data('icon')+"'></i>");
			}
		}else if(style_searchForm_input==2){
			$(this).wrap("<section class='col col-2'></section>").wrap("<div class='form-group'></div>").wrap("<div class='input-group'></div>");
			$(this).addClass('form-control');
			if($(this).data('icon')!=undefined){
				$(this).before("<span class='input-group-addon' style='color: #BDBDBD;background-color: white'><i class='"+$(this).data('icon')+"'></i></span>");
			}
		}
	}
});
$searchForm.find('div[class=row]').append('	<section class="col col-2"><button id="searchBtn" class="btn btn-xs btn-primary">&nbsp <i class="iconfont icon-search"></i>  &nbsp</button></section>');


$btnGroupTool.find('a').each(function(i){
	if($(this).attr('class')!=null)
	  $(this).attr("id",$(this).attr('class').substring(0,$(this).attr('class').indexOf('Btn'))+"_"+i+"_Btn");
})

/**
 * 每行下拉框工具条按钮事件
 */
$btnGroupTool.delegate('a[id$=Btn]','click',function(){
	$("#listTable input[name='ids']:enabled").prop("checked", false).change();
	$(this).closest('tr').find("input[name='ids']").click();
	var id=$(this).attr('id');
	if(id.indexOf("editSys")>5){
		var $checkedIds = $("#listTable input[name='ids']:enabled:checked").serialize().replaceAll("&ids=",",").replaceAll("ids=","");
		var data={id:$checkedIds};
		loadURL($(this).attr('href'), $container,data);
	}else if(id.indexOf("statusSys")>5){
		var enabled=$("#listTable input[name='ids']:enabled:checked").data('enabled');
		var btnText=enabled==1?"禁用":"启用";
		smartMsgBox("确认要"+btnText+"所选数据的状态吗？","[下次吧]["+btnText+"]",smartMsgBoxCallBack,$(this).attr('id'));
	}else if(id.indexOf("delSys")>5){
		smartMsgBox("确认要删除所选数据吗？","[再想想][删除]",smartMsgBoxCallBack,$(this).attr('id'));
	}else if(id.indexOf("viewSys")>5){
		var $checkedIds = $("#listTable input[name='ids']:enabled:checked").serialize().replaceAll("&ids=",",").replaceAll("ids=","");
		var data={id:$checkedIds};
		loadURL($(this).attr('href'), $container,data);
	}else{
		var id=$(this).attr('class').replaceAll('SysBtn','')+"_customEvent";
		$(this).trigger(id);
	}
	return false;
});







/**
 * 工具条按钮事件
 */
$toolForm.delegate('a[id$=Btn]','click',function(){
	if($(this).attr('id')=="addSysBtn"){//添加
		eventHandle($(this),"addSys","SysBtn");
	}else if($(this).attr('id')=="editSysOneBtn"){//修改
		eventHandle($(this),"editSys","SysOneBtn");
	}else if($(this).attr('id')=="statusSysOneBtn"){//状态
		eventHandle($(this),"statusSys","SysOneBtn");
	}else if($(this).attr('id')=="delSysAllBtn"){ //删除  viewSysOneBtn
		eventHandle($(this),"delAllSys","SysAllBtn");
	}else if($(this).attr('id')=="viewSysOneBtn"){ //查看
		eventHandle($(this),"viewSys","SysOneBtn");
	}else{//其他
		var id=$(this).attr('id').replaceAll('Btn','')+"_customEvent";
		$(this).trigger(id);
	}
	return false;
});


/**
 * 工具条触发事件函数
 * @param a
 * @param eventStr
 * @param replaceStr
 */
function eventHandle(a,eventStr,replaceStr){
	if(a.data('fn')==true||a.data('fn')==undefined){
		a.trigger(eventStr);
	}else{
		var id=a.attr('id').replaceAll(replaceStr,'')+"_customEvent";
		a.trigger(id);
	}
}

/**
 *  添加函数
 */
$addSysBtn.on('addSys',function delAll(){
	 loadURL($(this).attr('href'), $container);
	 return false;
});
/**
 *  修改函数
 */
$editSysOneBtn.on('editSys',function delAll(){
	var $checkedIds = $("#listTable input[name='ids']:enabled:checked").serialize().replaceAll("&ids=",",").replaceAll("ids=","");
	var data={id:$checkedIds};
	loadURL($(this).attr('href'), $container,data);
	return false;
});
/**
 * 查看
 * @returns {Boolean}
 */
$viewSysOneBtn.on('viewSys',function delAll(){
	var $checkedIds = $("#listTable input[name='ids']:enabled:checked").serialize().replaceAll("&ids=",",").replaceAll("ids=","");
	var data={id:$checkedIds};
	loadURL($(this).attr('href'), $container,data);
	return false;
});

/**
 * 状态函数
 */
$statusSysOneBtn.on('statusSys',function delAll(){
	var enabled=$("#listTable input[name='ids']:enabled:checked").data('enabled');
	var btnText=enabled==1?"禁用":"启用";
	smartMsgBox("确认要"+btnText+"所选数据的状态吗？","[下次吧]["+btnText+"]",smartMsgBoxCallBack,$(this).attr('id'));
	return false;
});
/**
 * 批量删除函数
 */
$delSysAllBtn.on('delAllSys',function delAll(){
	smartMsgBox("确认要删除所选数据吗？","[再想想][删除]",smartMsgBoxCallBack,$(this).attr('id'));
	return false;
});


/**
 * 消息框
 * @param content
 * @param buttons
 */
function smartMsgBox(content,buttons,smartMsgBoxCallBack,elementId){
	$.SmartMessageBox({
		title : "<i class='fa fa-warning  txt-color-orangeDark'></i> <span class='txt-color-orangeDark'><strong>警告</strong></span>",
		content : content,
		elementId:elementId,
		buttons : buttons
	}, smartMsgBoxCallBack);
}
/**
 * 消息框架回调函数
 * @param ButtonPressed
 */
function smartMsgBoxCallBack(ButtonPressed,elementId) {
	var $checkedIds = $("#listTable input[name='ids']:enabled:checked").serialize().replaceAll("&ids=",",").replaceAll("ids=","");
	var flg=false;
	if (ButtonPressed == "删除"&&(elementId=="delSysAllBtn"||elementId.indexOf("delSys")>5)) {
		var path=$('#'+elementId).attr('href');
		var data={ids:$checkedIds};
		flg=ajaxSubmit_custom(path,data,true);
	}else if ((ButtonPressed == "禁用"||ButtonPressed == "启用")&&(elementId=="statusSysOneBtn"||elementId.indexOf("statusSys")>5)) {
		var enabled=$("#listTable input[name='ids']:enabled:checked").data('enabled');
		var flgParam=enabled==1?false:true;
		var data={id:$checkedIds,flg:flgParam};
		var path=$('#'+elementId).attr('href');
		 flg=ajaxSubmit_custom(path,data,true);
	}
	if(flg){
		loadURL($searchForm.attr('action'), $container,$searchForm.serializeJson());
	}
}
	

/**
 * 排序
 */
$listTable.delegate('#table_sort','click',function(){
	if($sort.val()!=null&&$sort.val()!=""){
		if($sort.val().indexOf("DESC")>0){
			 $sort.val($sort.val().replaceAll("DESC","ASC"));
			 $(this).find('i').removeClass("fa-sort-desc").addClass("fa-sort-asc");
		}else{
			 $sort.val($sort.val().replaceAll("ASC","DESC")); 
			 $(this).find('i').removeClass("fa-sort-asc").addClass("fa-sort-desc");
		}
	}else{
		 $sort.val($(this).parent().data("sort")+" DESC");
		 $(this).find('i').removeClass("fa-sort").addClass("fa-sort-desc");
	}
	pageSkip(1);
});



/**
 * 跳到第几页
 */
$pageSkip.bind('click',function(){
	var pageNum=$("#pageSkipNum").val();
	if (!/^\d*[1-9]\d*$/.test(pageNum)) {
		pageNum=1;
	}
	var pageTotal=$(this).data("pagetotal");
	if(pageNum>pageTotal)
		pageNum=pageTotal;
	if(pageNum<1)
		pageNum=1;
	pageSkip(pageNum);
});

/**
 * 跳到第几页(回车事件)
 */
$("#pageSkipNum").keypress(function(event) {
	var key = event.keyCode ? event.keyCode : event.which;
	if ((key == 13 && $(this).val().length > 0)) {
		var pageNum=$(this).val();
		if (!/^\d*[1-9]\d*$/.test(pageNum)) {
			pageNum=1;
		}
		var pageTotal=$(this).data("pagetotal");
		if(pageNum>pageTotal)
			pageNum=pageTotal;
		pageSkip(pageNum);
		return true;
	} 
});

/**
 * 页面跳转方法
 */
function pageSkip(pageNum){
	var pageSize=parseInt($('.page-num li[class=label-info]').text());
	var json=[{name:'pageNum',value:pageNum},{name:'pageSize',value:pageSize},{name:'sort',value:$sort.val()}];
	loadURL($searchForm.attr('action'), $container,$searchForm.serializeJson(json));
}
/**
 * 页面跳转
 */
$pagination.bind('click',function(){
	var flg=$(this).text();
	var pageNum=1;
	if(flg=="..."){
		pageNum=parseInt($(this).closest('li').prev('li').text())+1;//下一组
	}else if(flg=="首页"){
		pageNum=1;
	}else if(flg=="上一页"){
		pageNum=parseInt($(this).closest('ul').find('li[class=active]').text())-1;
	}else if(flg=="下一页"){
		pageNum=parseInt($(this).closest('ul').find('li[class=active]').text())+1;
	}else if(flg=="尾页"){
		pageNum=parseInt($(this).data("pagetotal"));
	}else{
		pageNum=parseInt($(this).text());
	}
	
	pageSkip(pageNum);
});




/**
 * 每页显示几条
 */
$('.page-num li').bind('click',function(){
	$(this).find('a').prepend("<i class='fa fa-check'></i>");
	$(this).siblings('li').find('i').remove();
	$(this).addClass('label-info');
	$(this).siblings('li').removeClass('label-info');
	var pageSize=parseInt($(this).find('a').text());
	var json=[{name:'pageSize',value:pageSize},{name:'sort',value:$sort.val()}];
	loadURL($searchForm.attr('action'), $container,$searchForm.serializeJson(json));
});

/**
 * 选择页数
 */
$('.pagination li[class!=disabled]').bind('click',function(){
	$(this).addClass('active');
	$(this).siblings('li').removeClass('active');
});

/**
 * 全选
 */
$selectAll.bind('click',function(){
	var $this = $(this);
	var $enabledIds = $("#listTable input[name='ids']:enabled");
	if ($this.prop("checked")) {
		$enabledIds.prop("checked", true).change();
		if ($enabledIds.filter(":checked").size() > 0) {
			if($enabledIds.filter(":checked").size()==1){
				$('#toolForm').find('a[id$=OneBtn]').removeClass("disabled");
				
			}else{
				$('#toolForm').find('a[id$=OneBtn]').addClass("disabled");
			}
			$('#toolForm').find('a[id$=AllBtn]').removeClass("disabled");
		}else{
			$('#toolForm').find('a[id$=OneBtn]').addClass("disabled");
			$('#toolForm').find('a[id$=AllBtn]').addClass("disabled");
		}
	}else{
		$enabledIds.prop("checked", false).change();
		$('#toolForm').find('a[id$=OneBtn]').addClass("disabled");
		$('#toolForm').find('a[id$=AllBtn]').addClass("disabled");
	}
});

//选择
$ids.click( function() {
	var $this = $(this);
	if ($this.prop("checked")) {
		$('#toolForm').find('a[id$=AllBtn]').removeClass("disabled");
	} else {
		if($("#listTable input[name='ids']").filter(":checked").size()==0){
			$('#toolForm').find('a[id$=AllBtn]').addClass("disabled");
		}
	}
	if($("#listTable input[name='ids']").filter(":checked").size() == 1){
			$('#toolForm').find('a[id$=OneBtn]').removeClass("disabled");
	}else{
			$('#toolForm').find('a[id$=OneBtn]').addClass("disabled");
	}
	if($("#listTable input[name='ids']:not(:checked)").size() == 0){
		$selectAll.prop("checked", true).change();
	}else{
		$selectAll.prop("checked", false).change();
	}
});


/**
 * 搜索
 */
$searchForm.delegate('#searchBtn','click',function(){
	loadURL($searchForm.attr('action'), $container,$searchForm.serializeJson());
	return false;
});
// 刷新
$refreshBtn.click( function() {
	loadURL($searchForm.attr('action'), $container);
	return false;
});

/**
 * 设置表格面板高度
 */
function setPanelHeight(){
	var setHeight =windowSize[3]- $('#header').height()- $('#ribbon').height()-$searchForm.height()-$toolForm.height()-(windowSize[3]*0.2);
	if(setHeight<400)
		setHeight=300;
	$('.custom-scroll').css('height', setHeight + 'px');
	$('.custom-scroll').css('overflow-y','auto');	
	$('.ajax-notifications').css('height','');
	$('.ajax-notifications').css('overflow-y','');

}

//缩写
function abbreviate(str, width, ellipsis){
	if(str!=null){
		if (width != null) {
			var strLength = 0;
			for (var strWidth = 0; strLength < str.length; strLength++) {
				strWidth += str[strLength].replace(/[^\x00-\xff]/gi,'xx').length;
				if (strWidth >= width) {
					break;
				}
			}
			if (strLength+1 < str.length) {
				if (ellipsis != null) {
					return str.substring(0, strLength + 1) + ellipsis;
				} else {
					return str.substring(0, strLength + 1);
				}
			} else {
				return str;
			}
		} else {
			if (ellipsis != null) {
				return str + ellipsis;
			} else {
				return str;
			}
		}
	}
	else{
		return "";
	}
}