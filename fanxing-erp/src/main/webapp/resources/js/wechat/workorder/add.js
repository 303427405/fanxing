
var $addForm = $("#addForm").validate({
	rules : {
		name : {
			required : true,
			minlength : 2,
			maxlength : 10
		},
		content:{
			minlength : 2,
			maxlength : 400
		},
		remark:{
			minlength : 2,
			maxlength : 40
		},
		wechatId:{
			required : true
		},
		openId:{
			required : true
		},
		type:{
			required : true
		},
		mobile:{
			required : true
		},
		address:{
			minlength : 2,
			maxlength : 200
		}
	},
	 messages:{
		 wechatId:{required:'请选择公众号！'},
		 openId:{required:'请选择工单所对应的客户！'}
	}
});





