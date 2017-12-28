var flg=true;
var setting = {
	//check : {enable : true,chkStyle: "radio",radioType: "all"},
	view: {selectedMulti: false,nameIsHTML: true,dblClickExpand:false},
	data :  {
				simpleData : {enable : true,idKey : "id",pIdKey : "parentId",rootPId : 1}
	        },
	callback: {beforeExpand: beforeExpand,onClick: zTreeOnClick},
	async : {enable : true,
		     url : base_path+ "/areaController/findArea.do",
		     autoParam: ["id=parentId"],
		     otherParam: { "nameOrCode":function(){if(flg){return $('#treeCode').val();}return null;}
	                     }
	       }
};
function zTreeOnClick(event,treeId,treeNode) {
	beforeExpand(treeId, treeNode);
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	treeObj.expandNode(treeNode, true, false, true);
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
	var setHeight =windowSize[3]- $('#header').height()- $('#ribbon').height()-100;
	if(setHeight<400)
		setHeight=300;
	$('#treePanel').css('min-height', setHeight + 'px');
	$('#tree').css('height', setHeight + 'px');
	
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