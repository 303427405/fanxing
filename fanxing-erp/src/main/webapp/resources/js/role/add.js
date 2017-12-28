var setting = {
	check : {enable :true},
	view: {showLine: true,selectedMulti: false,nameIsHTML: true,showIcon:false},
	data: {
		   simpleData: {enable:true,idKey: "id",pIdKey: "parentId",rootPId: null}
	},
	callback:{onCheck:zTreeOnCheck},
	async: {enable: true,url: base_path+"/permissionsController/getPermissionsForAuth.do",
		    otherParam: { "roleId": $('#roleId').val()}
    }
};	

function zTreeOnCheck(){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var nodes = treeObj.getCheckedNodes(true);
	var pId = "";
	for ( var i = 0; i < nodes.length; i++) {
		pId += nodes[i].id + ",";
	}
	$('#permissionIds').val(pId);
}
var $registerForm = $("#addForm").validate({
	rules : {
		roleName : {required : true,minlength : 2,maxlength : 20},
		description : {required : true,minlength : 2,maxlength : 200},
		permissionIds : {required : true},
	},
	messages:{
		permissionIds:{required:'请为该角色进行授权勾选！'}
	}
});

$(function(){
	$.fn.zTree.init($("#tree"), setting);
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	$('#selectAllByTree').click(function(){
		treeObj.checkAllNodes(true);
		zTreeOnCheck();
		return false;
	});
	$('#SelectNoneByTree').click(function(){
		treeObj.checkAllNodes(false);
		zTreeOnCheck();
		return false;
	});
	$('#refreshByTree').click(function(){
		treeObj.reAsyncChildNodes(null, "refresh");
		$('#permissionIds').val('');
		return false;
	});
});

