/****
 * 字典项 
 * id=dictionaryData 
 * data-code:数据库存的code 
 * data-value:后台传的值 
 * data-cache（启用缓存：true?false 默认启用）
 * data-css（添加默认样式：true?false 默认添加）
 * 区域 
 * id=selectArea 
 * data-initlevel：从第几级开始('1':'国家',	'2':'省份','3':'市区','4':'区县','5':'街道') 默认从第二级开始; 
 * data-initParentId：第几级的父id; 
 * data-orgcode：后台传的区域code
 * 区域提交后台的参数【areaIds，areaCodes】
 * data-org:是否带组织（true:false）
 * data-orgCode:后台传的组织code
 * 组织提交后台的参数【orgCode】
 * 
 * 
 */

var $dictionary = $('#dictionaryData');
var $area= $('#selectArea');
var optionUrl="/dictionaryController/getItemByParentCode.do";
var areaUrl="/areaController/findArea.do";
var orgUrl="/organizationController/getOrganizationByAreaCode.do";
//下拉框样式
$('select').addClass('form-control');
var levelStr={'1':'国家',	'2':'省份','3':'市区','4':'区县','5':'街道'};
if($dictionary.length > 0){
	//字典项下拉框样式
   if($dictionary.data('css')==null||$dictionary.data('css')==undefined||$dictionary.data('css')==false){
	 $dictionary.wrap("<section class='col col-2'></section>").wrap("<div class='form-group'></div>").wrap("<div class='input-group'></div>");
	$dictionary.before("<span class='input-group-addon' style='color: #BDBDBD;background-color: white'><i class='fa fa-bookmark fa-fw'></i></span>");
   }else{
	 $dictionary.wrap("<div class='form-group'></div>").wrap("<div class='input-group'></div>");
		$dictionary.after("<span class='input-group-addon' style='color: #BDBDBD;background-color: white'><i class='fa fa-bookmark fa-fw'></i></span>");
   }
	$dictionary.addClass('dictionary');
	initOption_dictionary();
	$('section').delegate('select[class*=dictionary]','change',function(){
		selectStyle($(this));
		return false;
	});
}

if($area.length > 0){
	if($area.data('initlevel')!=null){
		var id=$area.attr('id');
		$area.attr('id',$area.data('initlevel')+"_"+id);
	}else{
		var id=$area.attr('id');
		$area.attr('id',"2_"+id);
	}
	var size=2;
	var flg=$area.data('org');//如果带组织就加大宽度
	if(flg==true){
		size=4;
	}
	//区域下拉框样式
	$area.wrap("<section id="+$area.attr('id').substring(0,1)+"_divArea class='col col-"+size+"'></section>").wrap("<div class='form-group'></div>").wrap("<div class='input-group'></div>");
	$area.before("<span class='input-group-addon' style='color: #BDBDBD;background-color: white'><i class='fa  fa-map-marker fa-fw'></i></span>");
	$area.addClass('area');
	initOption_area();
	//区域
	$('section').delegate('select[class*=area]','change',function(){
		selectStyle($(this));
		setAreaValue();
		$(this).closest('section').nextAll('section[id$=_divArea]').remove();
		var level=$(this).find('option:selected').data('level')+1;
		selectClickEvent($(this),level);
		var flg=$(this).data('org');
		if(flg){
			init_org($(this).val(),"");
		}
		return false;
	});
	//组织
	$('section').delegate('select[id=select_org]','change',function(){
		selectStyle($(this));
		$('#orgCode').val($(this).val());
		return false;
	});
}





/***
 * 设置下拉框要提交的值
 */
function setAreaValue(){
	var areaCode="";
	var areaId="";
	$('section select[class*=area]').each(function(){
		var code=$(this).val();
		var id=$(this).find('option:selected').data('id');
		if(code!=""){
			areaCode+=code+",";
		}
		if(id!=""){
			areaId+=id+",";
		}
	})
	if(areaId.length>0){
		$('#areaIds').val(areaId.substring(0,areaId.length-1));
	}else{
		$('#areaIds').val('');
	}
	if(areaCode.length>0){
		$('#areaCodes').val(areaCode.substring(0,areaCode.length-1));
	}else{
		$('#areaCodes').val('');
	}
}

/**
 * 区域下拉框单击事件函数
 * @param area
 * @param level
 */
function selectClickEvent(area,level){
	var parentId=area.find('option:selected').data('id');
	if(parentId!=""){
		var data=getOptionData(areaUrl, {parentId:parentId},false);
		if(data!=null&&data.length>0){
			var html = '<option style="color:#404040;" value="" data-id="" data-level="'+data[0].level+'">请选择'+levelStr[level]+'...</option>';
			$.each(data, function(i, n){
				html += '<option style="color:#404040;" value="' + n.code + '" data-id="'+n.id+'" data-level="'+n.level+'">' + n.name + '</option>';
			});
			area.closest('section').after("<select id='"+level+"_selectArea' data-org="+area.data('org')+" class='area form-control'>"+html+"</select>");
			selectStyle($('#'+level+'_selectArea'));
			$('#'+level+'_selectArea').wrap("<section  id='"+level+"_divArea' class='col col-2'></section>").wrap("<div class='form-group'></div>").wrap("<div class='input-group'></div>");
			$('#'+level+'_selectArea').before("<span class='input-group-addon' style='color: #BDBDBD;background-color: white'><i class='fa  fa-map-marker fa-fw'></i></span>");
		}
	}
}




/**
 * 区域初始化
 */
function initOption_area(){
	var codeArr=new Array();
	if(($area.data('areacode')+"").indexOf(',')>0){
		 codeArr=($area.data('areacode')+"").split(',');
	}else{
		codeArr.push($area.data('areacode'));
	}
	var level=parseInt($area.attr('id').substring(0,1));//初始化层级
	var flg=$area.data('org');
	if(flg){
		$area.after('<input type="hidden" id="orgCode" value="'+$area.data('orgcode')+'" name="orgCode">');
		$area.prev().before("<span class='input-group-addon' style='color: #BDBDBD;background-color: white'><i class='iconfont icon-orgb fa-fw'></i></span><select id='select_org' name='selectOrg' class='form-control'></select>");
		init_org(codeArr[codeArr.length-1],$area.data('orgcode'));
	}
	var parentId=null;
	var initParentId=$area.data('initparentid');
	if(level==1){
		parentId=null;
	}else if(level==2){
		parentId=1;
	}else{
		parentId=parseInt(initParentId);
	}
	$area.after('<input type="hidden" id="areaIds" name="areaIds" >')
	$area.after('<input type="hidden" id="areaCodes" name="areaCodes" value="'+codeArr+'">')
	var data=getOptionData(areaUrl, {parentId:parentId},false);
	var html = '<option style="color:#404040;" value="" data-id="" data-level="'+level+'">请选择'+levelStr[level]+'...</option>';
	$.each(data, function(i, n){
		html += '<option style="color:#404040;" value="' + n.code + '" data-id="'+n.id+'" data-level="'+n.level+'">' + n.name + '</option>';
	});
	$area.html(html);
	$area.find('[value=' + codeArr[0] + ']').prop('selected', true);
	selectStyle($area);
	if(codeArr.length>1){
		for(var i=0;i<codeArr.length;i++){
			selectClickEvent($('#'+(level+i)+'_selectArea'),(level+1+i));
			$('#'+(level+1+i)+'_selectArea').find('[value=' + codeArr[i+1] + ']').prop('selected', true);
			selectStyle($('#'+(level+1+i)+'_selectArea'));
		}
	}
	
}

function init_org(areaCode,orgCode){
	var data=getOptionData(orgUrl, {areaCode:areaCode},false);
	var html = '<option style="color:#404040;" value="">请选择组织...</option>';
	$.each(data, function(i, n){
		html += '<option style="color:#404040;" value="' + n.code + '" data-id="'+n.id+'">' + n.name + '</option>';
	});
	$('#select_org').html(html);
	$('#select_org').find('[value=' + orgCode + ']').prop('selected', true);
	selectStyle($("#select_org"));
}

/**
 * 初始化字典
 */
function initOption_dictionary(){
	var code=$dictionary.data('code');
	var data=localStorage.getItem(code);
	if(data==null||data==undefined||data=='null'){
		var cache=$dictionary.data('cache');
		var flg=(cache==undefined||cache==true)?true:false;
		data=getOptionData(optionUrl, {code:code},flg);
	}else{
		data=eval(data);
	}
	var placeholder=$dictionary.data('placeholder')!=undefined?$dictionary.data('placeholder'):'状态';
	var html = '<option style="color:#404040;" value="">请选择'+placeholder+'...</option>';
	$.each(data, function(i, n){
		html += '<option style="color:#404040;" value="' + n.code + '">' + n.name + '</option>';
	});
	$dictionary.html(html);
	$dictionary.find('[value=' + $dictionary.data('value') + ']').prop('selected', true);
	selectStyle($('section').find('select[class*=dictionary]'));
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
