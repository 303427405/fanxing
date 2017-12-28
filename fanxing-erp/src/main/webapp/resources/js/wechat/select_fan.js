var $moreSelect= $('#moreSelect');//两个下拉框联动
var wechatUrl="/tencentUserController/getTencentUserByOrgCode.do";
var fanUrl="/fanController/getFanByWechatId.do";
//下拉框样式
$('select').addClass('form-control');
if($moreSelect.length > 0){
	//两个下拉框联动样式
	var col=($moreSelect.data("col")==null||$moreSelect.data("col")==undefined)?"3":$moreSelect.data("col");
	$moreSelect.wrap("<section class='col col-"+col+"'></section>").wrap("<div class='form-group'></div>").wrap("<div class='input-group'></div>");
	$moreSelect.before("<span class='input-group-addon' style='color: #BDBDBD;background-color: white'><i class='fa fa-bookmark fa-fw'></i></span>");
	$moreSelect.addClass('moreLink');
	initOption_moreSelect();
	$('section').delegate('select[class*=moreLink]','change',function(){
		selectStyle($(this));
		if($moreSelect.data('showfan')==null||$moreSelect.data('showfan')==undefined){
			selectMoreClickEvent($(this));
		}
		return false;
	});
}


/**
 * 下拉框单击事件函数
 * @param area
 * @param level
 */
function selectMoreClickEvent(obj){
	var param=obj.val();
	var data=getOptionData(fanUrl, {wechatId:param},false);
	if(data!=null&&data.length>0){
		var html = '<option style="color:#404040;" value="">请选择客户...</option>';
		$.each(data, function(i, n){
			html += '<option style="color:#404040;" value="' + n.openId + '">' + n.nickName + '</option>';
		});
		obj.closest('section').after("<select id='fanSelect' class='moreLink form-control' name='openId'>"+html+"</select>");
		var col=($moreSelect.data("col")==null||$moreSelect.data("col")==undefined)?"3":$moreSelect.data("col");
		$('#fanSelect').wrap("<section  class='col col-"+col+"'></section>").wrap("<div class='form-group'></div>").wrap("<div class='input-group'></div>");
		$('#fanSelect').before("<span class='input-group-addon' style='color: #BDBDBD;background-color: white'><i class='fa fa-bookmark fa-fw'></i></span>");
		selectStyle($('#fanSelect'));
	}
}
/**
 * 初始化moreSelect 的option
 */
function initOption_moreSelect(){
	var	data=eval(getOptionData(wechatUrl,null,false));
	var html = '<option style="color:#404040;" value="" data-level="1">请选择公众号...</option>';
	$.each(data, function(i, n){
		html += '<option style="color:#404040;" data-level="1" value="' + n.weChatId + '">' + n.name +'[' +n.weChatId+']</option>';
	});
	$moreSelect.html(html);
	$moreSelect.find('[value=' + $moreSelect.data('wechatid') + ']').prop('selected', true);
	selectStyle($moreSelect);
	var openId=$moreSelect.data('openid');
	if(openId!=undefined&&openId!=""){
		if($moreSelect.data('showfan')==null||$moreSelect.data('showfan')==undefined){
			selectMoreClickEvent($moreSelect);
			$('#fanSelect').find('[value=' + $moreSelect.data('openid') + ']').prop('selected', true);
			selectStyle($('#fanSelect'));
			if($moreSelect.attr('disabled')=='disabled')
				$('#fanSelect').attr('disabled',true);
		}
	}
}

function getOptionData(url, data,flg) {
	var obj=null
	var param=$.extend(data,{ajaxType:'ajax'});
	$.ajax({
		type : "POST",
		url : base_path + url,
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



/**
 * 设置下拉框样式
 * @param select
 */
function selectStyle(select) {
	if (select.val() == undefined || select.val() == "") {
		select.css('color', '#BDBDBD');
	} else {
		select.css('color', '#404040');
	}
}