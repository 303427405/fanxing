var flg=true;
var setting = {
	check : {enable : true,chkStyle: "radio",radioType: "all"},
	view  : {showTitle:true,showLine : false,dblClickExpand: false,selectedMulti : false,nameIsHTML : true,showIcon : false},
	data :  {key: {title: "fullName"},simpleData : {enable : true,idKey : "id",pIdKey : "parentId",rootPId : 1}},
	callback: {beforeExpand: beforeExpand,onCheck : zTreeOnCheck,onClick: zTreeOnClick},
	async : {enable : true,
		     url : base_path+ "/areaController/findArea.do",
		     autoParam: ["id=parentId"],
		     otherParam: { "nameOrCode":function(){if(flg){return $('#treeCode').val();}return null;}
             }
	 }
};


function zTreeOnCheck(event,treeId,treeNode) {
	if(treeNode.checked==true){
		$('#areaCodeName').val(treeNode.fullName);
		$('#areaCode').val(treeNode.code);
        $('#areaCodeName').change();
    }else{
		$('#areaCodeName').val('');
		$('#areaCode').val('');
	}
}

function zTreeOnClick(event,treeId,treeNode) {
	beforeExpand(treeId, treeNode);
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	treeObj.expandNode(treeNode, true, false, true);
	treeObj.checkNode(treeNode, true, false);
	$('#areaCodeName').val(treeNode.fullName);
	$('#areaCode').val(treeNode.code);
    $('#areaCodeName').change();
    return true;
}

function beforeExpand(treeId, treeNode) {
	flg=false;
	if (!treeNode.isAjaxing) {
		ajaxGetNodes(treeNode, "refresh");
		return true;
	}
}
function ajaxGetNodes(treeNode, reloadType) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if (reloadType == "refresh") {
		zTree.updateNode(treeNode);
	}
	zTree.reAsyncChildNodes(treeNode, reloadType, true);
}

$(function() {
	var treeObj= $.fn.zTree.init($("#tree"), setting);
	$('#refreshByTree').click(function(){
		$('#areaCodeName').val('');
		$('#areaCode').val('');
		$('#treeCode').val('');
		treeObj.reAsyncChildNodes(null, "refresh");
		return false;
	});
	
	/**
	 * 输入名称或是编码
	 */
	$("#treeCode").keyup(function(event) {
		if($.trim($(this).val()).length>=2){
			setTimeout(function() {
				flg=true;
				var treeNode = treeObj.getNodeByParam("id", 1, null);
				if (!treeNode.isAjaxing) {
					ajaxGetNodes(treeNode, "refresh");
				}
				treeObj.expandNode(treeNode, true, false, true);
			}, 1000);
		}
	});
	
});
var $registerForm = $("#addForm").validate({
	rules : {
		name : {required : true,minlength : 2,maxlength : 8},
		areaCodeName:{required : true}
	 },
	 messages:{
		 areaCodeName:{required:'请为该组织勾选所属区域！'}
	}
});