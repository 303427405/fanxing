$(function(){
	var $resetPwd=$('#resetPwdSysOneBtn');
	$resetPwd.on('resetPwdSysOne_customEvent',function(){
		smartMsgBox("确认要重置所选用户的密码吗？","[下次吧][重置]",resetPwdCallBack,$(this).attr('id'));
		return false;
		
	})
	function resetPwdCallBack(ButtonPressed,elementId){
		if (ButtonPressed == "重置"&&elementId=="resetPwdSysOneBtn") {
			var $checkedIds = $("#listTable input[name='ids']:enabled:checked").serialize().replaceAll("&ids=",",").replaceAll("ids=","");
			var data={id:$checkedIds};
			var path=$('#'+elementId).attr('href');
			var flg=ajaxSubmit_custom(path,data,true);
			if(flg){
				loadURL($searchForm.attr('action'), $container,$searchForm.serializeJson());
			}
		}
	}
})

