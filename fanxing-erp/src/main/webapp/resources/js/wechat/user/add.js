
var $registerForm = $("#addForm").validate({
	rules : {
		name : {
			required : true,
			minlength : 2,
			maxlength : 10,
		},
		weChatId : {
			required : true,
			minlength : 2,
			maxlength : 20,
		},
		appId:{
			required : true,
			minlength : 2,
			maxlength : 20,
		},
		appSecret:{
			required : true,
			minlength : 2,
			maxlength : 40,
		},
		remark:{
			minlength : 2,
			maxlength : 40,
		},
		title:{
			minlength : 2,
			maxlength : 20,
		},
		content:{
			minlength : 2,
			maxlength : 200,
		},
		selectOrg:{
			required : true
		}
	},
	 messages:{
		 selectOrg:{required:'请为该公众号选择所属区域！'}
	}
});


function readURL(input) { 
    if (input.files && input.files[0]) {
    	 var tmpFileValue = input.value;
	    //校验图片格式
	     if(!/^.*?\.(gif|png|jpg|jpeg|bmp)$/.test(tmpFileValue.toLowerCase())){
	    	input.value=null;
	    	msgBox("亲，只能上传jpg、jpeg、png、bmp或gif格式的图片！",$time);
	        return false;
	     }else{
	      	 input.parentNode.nextSibling.value = input.value;
	    	 var reader = new FileReader();  
	         reader.readAsDataURL(input.files[0]); 
	         reader.onload = function (e) {  
	        	 $('#displayImg').removeAttr('src');  
		         $('#displayImg').attr('src', e.target.result);
	         }
	     }
	     return true;
    }  
} 


