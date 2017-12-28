var setting = {
	check : {enable :true},
	view: {showLine: true,selectedMulti: false,nameIsHTML: true,showIcon:false},
	data: {
		   simpleData: {enable:true,idKey: "id",pIdKey: "parentId",rootPId: null}
	},
	async: {enable: true,url: base_path+"/permissionsController/getPermissionsForAuth.do",
		    otherParam: { "roleId": $('#roleId').val(),"disabled":true}
    }
};	
$(function(){
	$.fn.zTree.init($("#tree"), setting);
});

