var $cancel = $("#cancel");
runAllForms();

/**
 * 组件初始化
 */
$('#widget-grid').jarvisWidgets({
	toggleButton : false,
	deleteButton : false,
	editButton : false,
	fullscreenButton : false,
	colorButton : false
});

if($("#addForm").doesExist()){
	var wizard = $('.wizard').wizard({
		formId:'addForm',
		finishedBtnText:"保存"
	});
	/**
	 * 表单提交
	 */
	wizard.on('finished', function (e, data) {
		$('#submit').attr('disabled','disabled');
		$("#addForm").submit();
	});
}

if($("#viewForm").doesExist()){
	var viewWizard = $('.wizard').wizard({
		formId:'viewForm',
		finishedBtnText:"返回"
	});
	/**
	 * 表单返回
	 */
	viewWizard.on('finished', function (e, data) {
		var returnUrl=$("#viewForm").data('returnurl');
		loadURL(returnUrl, $container);
		return false;
	});
}









/**
 * 表单取消
 */
$cancel.click(function() {
	var returnUrl=$(this).data('returnurl');
	loadURL(returnUrl, $container);
	return false;
});
$(function(){
	setPanelHeight();
});
$(window).resize(function(){
	setPanelHeight();
});

/**
 * 设置表格面板高度
 */
function setPanelHeight(){
	var setHeight =windowSize[3]- $('#header').height()- $('#ribbon').height()-(windowSize[3]*0.15);
	if(setHeight<400)
		setHeight=300;
	$('#formContent').css('height', setHeight + 'px');
	$('#formContent').css('overflow-y','auto');
}


