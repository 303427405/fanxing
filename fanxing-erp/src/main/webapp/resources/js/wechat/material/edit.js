var $registerForm = $("#addForm").validate({
	rules : {
		name : {
			required : true,
			minlength : 2,
			maxlength : 10,
		},
		type : {
			required : true
		},
		img:{
			required : true,
		},
		content:{
			required : true,
			maxlength : 200,
		}
	},
	 messages:{
		 type:{required:'请选择素材类型！'},
		 file:{required:'请选择图片！'}
	}
});


if($("#dictionaryData").val()=='0'){
	$("#contents").show();
	$("#photo").hide();
}else if($("#dictionaryData").val()=='1'){
	$("#contents").hide();
	$("#photo").show();
}else if($("#dictionaryData").val()=='2'){
	$("#contents").show();
	$("#photo").show();
}else{
	$("#contents").hide();
	$("#photo").hide();
}


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


