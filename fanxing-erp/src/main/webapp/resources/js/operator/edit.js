var $registerForm = $("#addForm").validate({
	rules : {
		loginName : {
			required : true,
			minlength : 2,
			maxlength : 20,
			alnum:true
		},
		roleIds:{
			required : true
		},
		selectOrg:{
			required : true
		}
	},
	 messages:{
		 roleIds:{required:'请为该用户进行授权勾选！'},
		 selectOrg:{required:'请为该用户选择所属区域！'}
	}
});
/**
 * 图片裁减
 * @param input
 */
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
	        };
	     }
    }  
} 
