// 后台校验密码
jQuery.validator.addMethod("checkPwdSelf", function(value, element,param) {
	var url=param['url'];
	if(url!=null&&url!=''&&url!=undefined){
		var flg=ajaxSubmit_custom(url,{password:value},false);
		if(flg==true)
		   return true;
	}
	return false;
});

// 中文字两个字节
jQuery.validator.addMethod("byteRangeLength",
		function(value, element, param) {
			var length = value.length;
			for (var i = 0; i < value.length; i++) {
				if (value.charCodeAt(i) > 127) {
					length++;
				}
			}
			return this.optional(element)
					|| (length >= param[0] && length <= param[1]);
		});

// 邮政编码验证
jQuery.validator.addMethod("isZipCode", function(value, element) {
	var tel = /^[0-9]{6}$/;
	return this.optional(element) || (tel.test(value));
});

// 字母数字
jQuery.validator.addMethod("alnum", function(value, element) {
	return this.optional(element) || /^[a-zA-Z0-9_]+$/.test(value);
});
// 字符验证 只能包括中文字、英文字母、数字和下划线
jQuery.validator.addMethod("stringCheck", function(value, element) {
	return this.optional(element) || /^[u0391-uFFE5w]+$/.test(value);
});
// 身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function(value, element) {
	return this.optional(element) || isIdCardNo(value);
});
// 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+d{8})$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
});
// 电话号码验证
jQuery.validator.addMethod("isTel", function(value, element) {
	var tel = /^d{3,4}-?d{7,9}$/; // 电话号码格式010-12345678
	return this.optional(element) || (tel.test(value));
});
// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isPhone", function(value, element) {
	var length = value.length;
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+d{8})$/;
	var tel = /^d{3,4}-?d{7,9}$/;
	return this.optional(element) || (tel.test(value) || mobile.test(value));
});