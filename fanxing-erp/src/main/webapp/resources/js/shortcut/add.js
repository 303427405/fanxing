var setting = {
	check : {enable : true,chkStyle: "radio",radioType: "all"},
	view  : {showLine : false,dblClickExpand: false,selectedMulti : false,nameIsHTML : true,showIcon : false},
	data :  {
				simpleData : {enable : true,idKey : "id",pIdKey : "parentId",rootPId : null}
	        },
	callback : {onCheck : zTreeOnCheck,onClick: zTreeOnClick},
	async : {enable : true,url : base_path+ "/shortcutController/getOperatorPermissionsForShortcut.do"}
};


function zTreeOnClick(event,treeId,treeNode) {
	if(treeNode.chkDisabled==true){
		msgBox('该模块不能被创建快捷方式！',$time);
	}else{
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		treeObj.checkNode(treeNode, true, false);
		var name=treeNode.name.replaceAll('&nbsp','');
		$('#shortcutName').val(name.substring(name.indexOf('/i>')+3,name.length));	
		$('#permissionsId').val(treeNode.id);
	}
}
function zTreeOnCheck(event,treeId,treeNode) {
	if(treeNode.checked==true){
		var name=treeNode.name.replaceAll('&nbsp','');
		$('#shortcutName').val(name.substring(name.indexOf('/i>')+3,name.length));	
		$('#permissionsId').val(treeNode.id);
	}else{
		$('#permissionsId').val('');
		$('#shortcutName').val('');
	}
}
var $registerForm = $("#addForm").validate({
	rules : {
		name : {required : true,minlength : 2,maxlength : 6},
		permissionsId:{required : true}
	 },
	 messages:{
		 permissionsId:{required:'请为该快捷方式进行模块勾选！'}
	}
	 
});
$(function() {
	$.fn.zTree.init($("#tree"), setting);
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	$("#spinner").spinner({
	    min: 0,
	    max: 50,
	    numberFormat: "n"
	});
	$('#refreshByTree').click(function(){
		treeObj.reAsyncChildNodes(null, "refresh");
		$('#permissionsId').val('');
		$('#shortcutName').val('');
		return false;
	});
});
