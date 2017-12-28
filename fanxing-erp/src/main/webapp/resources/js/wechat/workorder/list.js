var $handleBtn=$(".group_handleSysBtn");
var $completeBtn=$(".group_completeSysBtn");
var $canceledBtn=$(".group_canceledSysBtn");

//正在处理
$handleBtn.each(function(){
	//$(this).attr("id",$(this).attr('class').substring(0,$(this).attr('class').indexOf('Btn'))+"_"+i+"_Btn");
	$(this).on('group_handle_customEvent', function handle() {
		smartMsgBox("确认要改变所选数据状态吗？","[再想想][处理]",callBack,$(this).attr('id'));
	});
})

//已完成
$completeBtn.each(function(i){
	//$(this).attr("id",$(this).attr('class').substring(0,$(this).attr('class').indexOf('Btn'))+"_"+i+"_Btn");
	$(this).on('group_complete_customEvent', function handle() {
		smartMsgBox("确认要改变所选数据状态吗？","[再想想][完成]",callBack,$(this).attr('id'));
	});
})


//已取消
$canceledBtn.each(function(i){
	//$(this).attr("id",$(this).attr('class').substring(0,$(this).attr('class').indexOf('Btn'))+"_"+i+"_Btn");
	//console.log($(this).attr('id').indexOf("canceledSys_"));
	$(this).click(function handle() {
		smartMsgBox("确认要改变所选数据状态吗？","[再想想][取消]",callBack,$(this).attr('id'));
	});
})





/**
 * 消息框架回调函数
 * @param ButtonPressed
 */
function callBack(ButtonPressed,elementId) {
	var $checkedIds = $("#listTable input[name='ids']:enabled:checked").serialize().replaceAll("&ids=",",").replaceAll("ids=","");
	var flg=false;
	var path="/workOrderController/updateWorkOrderById.do";
	var data=null;
	if (ButtonPressed == "处理"&&elementId.indexOf("handleSys")>5) {
		data={id:$checkedIds,status:1};
	}else if (ButtonPressed == "完成"&&elementId.indexOf("completeSys")>5) {
		data={id:$checkedIds,status:2};
	}else if (ButtonPressed == "取消"&&elementId.indexOf("canceledSys_")>5) {
		data={id:$checkedIds,status:3};
	}
	if(data!=null){
		flg=ajaxSubmit_custom(path,data,true);
	}
	if(flg){
		loadURL($searchForm.attr('action'), $container,$searchForm.serializeJson());
	}
}