$dictionaryData = $("#dictionaryData");
$left = $("#left");
$leftAll = $("#leftAll");
$leftData = $("#leftData");
$right = $("#right");
$rightAll = $("#rightAll");
$rightData= $("#rightData");
$up=$("#up");
$upStart=$("#upStart");
$down=$("#down");
$downEnd=$("#downEnd");
$materialIds=$("#materialIds");
$wechatId=$("#wechatId");

var leftUrl="/materialController/getMaterialByTypeAndOrgCode.do";

var rightUrl="/materialController/getMaterialByWechatId.do";


$dictionaryData.change(function() {
	initOption_left_select();
	$rightData.html('');
	buildMaterialIds();
});
initOption_left_select();
initOption_right_select();
buildMaterialIds();

//全部移到左边
$leftAll.click( function() {
	$('#rightData option').appendTo('#leftData');
	buildMaterialIds();
});

$left.click(function() {
	$('#rightData option:selected').appendTo('#leftData');
	buildMaterialIds();
});

//全部移到右边
$rightAll.click( function() {
	var leftLength=$leftData.find('option').length;
	var rightLength=$rightData.find('option').length;
	var type=$dictionaryData.val();
	if(type==0||type==1){
		if(rightLength>=1||leftLength>1){
			msgBox("关注回复文本或图片时，只能选择1条素材!",$time);
			return
		}
	}else{
		if(rightLength>10||leftLength>10){
			msgBox("关注回复图文时，最多只能选中10条素材!",$time);
			return ;
		}
	}
	//获取全部的选项,删除并追加给对方
	$('#leftData option').appendTo('#rightData');
	buildMaterialIds();
	
});

$right.click(function() {
	var rightLength=$rightData.find('option').length;
	if(rightLength.length>10){
		msgBox("关注回复图文时，最多只能选中10条素材!",$time);
	}else{
		var type=$dictionaryData.val();
		if(type==0||type==1){
			if(rightLength>=1||$('#leftData option:selected').length>1){
				msgBox("关注回复文本或图片时，只能选择1条素材!",$time);
				return
			}
		}
		//获取选中的选项，删除并追加给对方
		$('#leftData option:selected').appendTo('#rightData');
		buildMaterialIds();
	}
});
//双击选项
$leftData.dblclick( function() { //绑定双击事件
	console.log('dd');
	var rightLength=$rightData.find('option').length;
	if(rightLength.length>10){
		msgBox("关注回复图文时，最多只能选中10条素材!",$time);
	}else{
		var type=$dictionaryData.val();
		if(type==0||type==1){
			if(rightLength>=1||$('#leftData option:selected').length>1){
				msgBox("回复文本或图片时，只能选择1条素材!",$time);
				return
			}
		}
		//获取全部的选项,删除并追加给对方
		$("option:selected", this).appendTo('#rightData'); //追加给对方
		buildMaterialIds();
	}
});
//双击选项
$rightData.dblclick( function() { //绑定双击事件
	//获取全部的选项,删除并追加给对方
	$("option:selected", this).appendTo('#leftData'); //追加给对方
	buildMaterialIds();
	
});

//置顶
$upStart.click(function(){
	if ($("#rightData option:selected").length <= 0) {
		msgBox("请选择需要置顶的素材!",$time);
        return;
    }
	var theObjOptions=$("#rightData option");
	var selectObj=document.getElementById("rightData");
	var oOption=null;
    for(var i=0;i<theObjOptions.length;i++) {
        if( theObjOptions[i].selected && oOption) {
            selectObj.insertBefore(theObjOptions[i],oOption);
        }
        else if(!oOption && !theObjOptions[i].selected) {
            oOption=theObjOptions[i];
        }
    }
    buildMaterialIds();
});
//置尾
$downEnd.click(function(){
		if ($("#rightData option:selected").length <= 0) {
            msgBox("请选择需要置尾的素材!",$time);
            return;
        }
	    var theObjOptions=$("#rightData option");
	    var selectObj=document.getElementById("rightData");
	    var oOption=null;
	    for(var i=theObjOptions.length-1;i>-1;i--) {
	        if( theObjOptions[i].selected ) {
	            if(oOption) {
	                oOption=selectObj.insertBefore(theObjOptions[i],oOption);
	            }
	            else oOption=selectObj.appendChild(theObjOptions[i]);
	        }
	    }
	    buildMaterialIds();
	    
});
//上移
$up.click(function(){
	if ($("#rightData option:selected").length <= 0) {
        msgBox("请选择需要上移的素材!",$time);
        return;
    }
	var selectLen=$("#rightData option").length;
	var options=$("#rightData option");
	for(var i=1;i<selectLen;i++){
		if($("#rightData option").get(i).selected && !$("#rightData option").get(i-1).selected ) {
            swapOptionProperties(options[i],options[i-1]);
        }
	}
	buildMaterialIds();
});
//下移
$down.click(function(){
	if ($("#rightData option:selected").length <= 0) {
		msgBox("请选择需要下移的素材!",$time);
        return;
    }
	var selectLen=$("#rightData option").length;
	var options=$("#rightData option");
	for(var i=selectLen-2;i>-1;i--){
		if($("#rightData option").get(i).selected && !$("#rightData option").get(i+1).selected ) {
            swapOptionProperties(options[i],options[i+1]);
        }
	}
	buildMaterialIds();
});

function initOption_left_select(){
	var	data=eval(getOptionData(leftUrl,{type:$dictionaryData.val(),wechatId:$wechatId.val()}));
	var html = '';
	$.each(data, function(i, n){
		html += '<option style="color:#404040;" data-level="1" value="' + n.id + '">' + n.name +'[' +n.id+']</option>';
	});
	$leftData.html(html);
}

function initOption_right_select(){
	var	data=eval(getOptionData(rightUrl,{wechatId:$wechatId.val()}));
	var html = '';
	$.each(data, function(i, n){
		html += '<option style="color:#404040;" data-level="1" value="' + n.id + '">' + n.name +'[' +n.id+']</option>';
	});
	$rightData.html(html);
}

/**
 * option移动方法
 * @param option1
 * @param option2
 */
function swapOptionProperties(option1,option2){
    var tempStr=option1.value;
    option1.value=option2.value;
    option2.value=tempStr;
    tempStr=option1.text;
    option1.text=option2.text;
    option2.text=tempStr;
    tempStr=option1.selected;
    option1.selected=option2.selected;
    option2.selected=tempStr;
    
}

function buildMaterialIds() {
	var options = $("#rightData option");
	var materialId = "";
	for ( var i = 0; i < options.length; i++) {
		materialId += options[i].value + ",";
	}
	$materialIds.val(materialId);
}
function getOptionData(url, data) {
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
	return obj;
}