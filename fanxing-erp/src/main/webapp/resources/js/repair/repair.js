

$(function() {
	$("#addForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2,
				maxlength : 20,
			},
			mobile : {
				required : true,
				maxlength : 11
			},
			type : {
				required : true
			},
			address : {
				required : true,
				minlength : 2,
				maxlength : 200,
			},
			content : {
				required : true,
				minlength : 2,
				maxlength : 200,
			}

		},
		messages : {
			type : {
				required : '请选择维修类型！'
			}
		}
	});
	
	initOption();
	function initOption(){
		var data=getOptionData("/dictionaryController/getItemByParentCodeNoAuth.do", {code:"softServiceType"},true);
		if(data!=null&&data.length>0){
			var html = '<option  value="">请选择维修类型</option>';
			$.each(data, function(i, n){
				html += '<option  value="' + n.code + '">' + n.name + '</option>';
			});
			$("#type").html(html);
		}
	}
	function getOptionData(url, data,flg) {
		var obj=null;
		var param=$.extend(data);
		$.ajax({
			type : "POST",
			url : base_shop_path + url,
			cache : false,
			data : data,
			async : false,
			success : function(data) {
				 obj=eval(data);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				 obj=null;
			}
		});
		if(flg){
			localStorage.setItem(param.code, JSON.stringify(obj));
		}
		return obj;
	}
});
