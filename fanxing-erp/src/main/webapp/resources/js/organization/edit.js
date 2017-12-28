var $registerForm = $("#addForm").validate({
	rules : {
		name : {required : true,minlength : 2,maxlength : 8},
		areaCodeName:{required : true}
	 },
	 messages:{
		 areaCodeName:{required:'请为该组织勾选所属区域！'}
	}
});