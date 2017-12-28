// 后台校验用户名是否重复
jQuery.validator.addMethod("checkLoginName", function(value, element,param) {
	var url=param['url'];
	if(url!=null&&url!=''&&url!=undefined){
		var flg=ajaxSubmit_custom(url,{loginName:value},false);
		if(flg==true)
		   return true;
	}
	return false;
});

var $registerForm = $("#addForm").validate({
	rules : {
		loginName : {
			required : true,
			minlength : 2,
			maxlength : 20,
			alnum:true,
			checkLoginName:{
				url:'/operatorController/checkLoginNameRepeat.do'
			},
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
//	            var api = $('#displayImg').Jcrop({  
//	                setSelect: [ 20, 20, 120, 120 ],  
//	                aspectRatio: 1,  
//	                maxSize:[ 120, 120],
//	                minSize:[50, 50],
//	                keySupport:false,
//	                //allowResize:false,
//	               // allowSelect:false,
//	                onSelect: updateCoords  
//	                },function(){
//	                    jcrop_api = this;
//	                    jcrop_api.animateTo([100, 100, 400, 300]);
//	                  });  
	        };
	     }
    }  
} 
/**
 * 图片裁减
 * @param c
 */
function updateCoords(c){     
    $('#x').val(c.x);  
    $('#y').val(c.y);  
    $('#w').val(c.w);  
    $('#h').val(c.h);  
};



//校验图片格式及大小 Add Date 2012-6-14 LIUYI  readURL(this);
function validateImage(obj) {
    var file = obj;
    var tmpFileValue = file.value;
    //校验图片格式
    if(/^.*?\.(gif|png|jpg|jpeg|bmp)$/.test(tmpFileValue.toLowerCase())){
        return true;
    } else {
        alert("只能上传jpg、jpeg、png、bmp或gif格式的图片！");
        return false;
    }
    //校验图片大小,这段代码需调整浏览器安全级别(调到底级)和添加可信站点(将服务器站点添加到可信站点中)
    //var imgSize = 1024 * 100; //最大100K
    //var img = new Image();
    if(file.value != ""){
    //    img.onreadystatechange = function(){
    //        if(img.readyState == "complete"){
    //            if(img.fileSize <=0 || img.fileSize > imgSize){
    //                alert("当前文件大小" + img.fileSize / 1024 + "KB, 超出最大限制 " + imgSize / 1024 + "KB");
    //                return false;
    //            }else{
    //                alert("OK");
    //                return true;
    //            }
    //        }
    //    }
    //    img.src = file.value;
        //return true;
    }else{
        alert("请选择上传的文件!");
        return false;
    }
}
