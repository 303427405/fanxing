/**
 * jquery serializeJson 改造
 */
(function($){  
    $.fn.serializeJson=function(data){  
        var serializeObj={};  
        var array=this.serializeArray();  
        //var str=this.serialize();  
        $(array).each(function(){  
            if(serializeObj[this.name]){  
                if($.isArray(serializeObj[this.name])){ 
                	if(this.value!=null&&this.value!="")
                		serializeObj[this.name].push(this.value==""?null:this.value);  
                }else{ 
                	if(this.value!=null&&this.value!="")
                		serializeObj[this.name]=[serializeObj[this.name],this.value==""?null:this.value];  
                }  
            }else{ 
            	if(this.value!=null&&this.value!="")
                   serializeObj[this.name]=this.value;   
            }  
        });
        if(data!=undefined){
        	//jQuery.extend(serializeObj, data);
        	for(var p in data){//遍历json数组时，这么写p为索引，0,1
        		serializeObj[data[p].name]=data[p].value; 
        	}
        }
        return serializeObj;  
    };  
    /**
     * 元素存在或不存在  例：$('#myDiv').doesExist();
     */
    $.fn.doesExist = function() {
    	return jQuery(this).length > 0;
    };
})(jQuery);


/**
 * 表单验证消息显示样式
 */
$.validator.setDefaults({
	submitHandler : function(form) {
		var returnUrl=form.getAttribute('returnUrl');
		$('#submit').attr('disabled','disabled');
		if(ajaxSubmit_custom(form.getAttribute('action'),$("#"+form.getAttribute('id')).serializeJson(),true)==true){
	    	form.reset();
	    	if(returnUrl!=null||returnUrl!=undefined){
	    		window.location.href =base_shop_path+returnUrl;
	    	}
	    	
	    }else{
	    	$('#submit').removeAttr('disabled');
	    }
	}
});


function ajaxSubmit_custom(url,data,showBox) {
	var urls='';
	if(url.StartWith('http://')){
		urls=url;
	}else{
		urls=base_shop_path+url;
	}
	var flg=false;
	$.ajax({
		type : "POST",
		async : false,
		url : urls,
		dataType : 'json',
		cache : false, 
		data:data,
		success : function(data) {
			var obj=eval(data);
			if(showBox==undefined||showBox==true)
			   alert(obj.msg);
			if(obj.success==true)
			   flg=true;
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert('操作失败，请稍后再试！');
			flg=false;
		}
	});
	return flg;
}

String.prototype.StartWith=function(s){
	  if(s==null||s==""||this.length==0||s.length>this.length)
	   return false;
	  if(this.substr(0,s.length)==s)
	     return true;
	  else
	     return false;
	  return true;
}