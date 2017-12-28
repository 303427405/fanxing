var $replyBtn = $('#toolForm #replyOneBtn');
var $sendMsg=$("#sendMsg");
var charLength=140;
var $msgContent=$("#msgContent");
$sendMsg.click(function(){
	if($msgContent.val().length<=charLength&&$msgContent.val()!=""){
		var checkbox= $("#listTable input[name='ids']:enabled:checked");
		var content= $msgContent.val();
		var param = {
				wechatId : checkbox.data('wechatid'),
				openId : checkbox.data('openid'),
				content :content
			};
		var data = getData("/inboxController/sendText.do", param);
		var obj=eval(data);
		if(obj.success==true){
			var time= (new Date()).Format("yyyy-MM-dd hh:mm")
			var msg="<div style='text-align: center;'>"+time+"</div><div style='text-align: right;color: green'>"+ content + "</div>";
			$('#replyText').code($('#replyText').code()+msg);
			$msgContent.val('');
		}
	}
	return false;
})

$msgContent.focusout(function() {
	if($(this).length>charLength){
		$(this).val($(this).val().substring(0,charLength))	
	}
});
$replyBtn.on('replyOne_customEvent',function reply() {
			$('#replyMsg').modal({
				keyboard : true,
				backdrop : false
			});
			$("#replyText").summernote({toolbar: false});
			buildData();
			//setInterval("buildData()",1000);//1000为1秒钟
			return false;
});
var intervalProcess =null;
$('#replyMsg').on('show.bs.modal', function () {
	intervalProcess=setInterval("buildData()",3000);//1000为1秒钟
});
$('#replyMsg').on('hide.bs.modal', function () {
	clearInterval(intervalProcess);
});
	

function buildData(){
	var checkbox= $("#listTable input[name='ids']:enabled:checked");
	var param = {
		wechatId : checkbox.data('wechatid'),
		openId : checkbox.data('openid')
	};
	var data = getData("/inboxController/getMsgByFanAndWechatId.do", param);
	var msg = '';
	var flg='';
	$.each(data,function(i,item){
		if(flg!=''){
			if(flg.substring(0,flg.length-3)!=i.substring(0,i.length-3)){
				msg +="<div style='text-align: center;'>"+i.substring(0,i.length-3)+"</div>";
			}
		}else{
			msg +="<div style='text-align: center;'>"+i.substring(0,i.length-3)+"</div>";
		}
		for(var j in item){
			var obj=item[j];
			if(obj.flg==0){
				msg+="<div style='text-align: left;color: blue'>"+obj.content + "</div>"
			}else if(obj.flg==1){
				msg+="<div style='text-align: right;color: green'>"+obj.content + "</div>"
			}
		}
		flg=i;
	})
	$('#replyText').code(msg);
}

//计算文字长度
function charlength(value){
	var a=value.length;
	if(a>charLength){
		a=charLength;
		$msgContent.val($msgContent.val().substring(0,charLength))	
	}
	document.getElementById("counter").innerText=a;
}
function getData(url, data) {
	var obj = null;
	$.ajax({
		type : "POST",
		async : false,
		url : base_path + url,
		dataType : 'json',
		cache : false,
		data : data,
		success : function(data) {
			obj = eval(data);
		},
		error : function(xhr, ajaxOptions, thrownError) {
		}
	});
	return obj;
}
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
