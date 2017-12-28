//$('#ribbon').append(
  //      '<div class="demo"><span id="demo-setting"><i class="fa fa-cog txt-color-blueDark"></i></span> <form><legend class="no-padding margin-bottom-10">布局设置</legend><section><label><input name="subscription" id="smart-fixed-nav" type="checkbox" class="checkbox style-0"><span>固定头部</span></label><label><input type="checkbox" name="terms" id="smart-fixed-ribbon" class="checkbox style-0"><span>固定内容</span></label><label><input type="checkbox" name="terms" id="smart-fixed-navigation" class="checkbox style-0"><span>固定菜单</span></label><label><input type="checkbox" name="terms" id="smart-fixed-container" class="checkbox style-0"><span><b>内部容器</b></span></label><label style="display:none;"><input type="checkbox" name="terms" id="smart-rtl" class="checkbox style-0"><span>Right to left <b>(rtl)</b></span></label> <span id="smart-bgimages"></span></section><section><h6 class="margin-top-10 semi-bold margin-bottom-5"></h6><a href="javascript:void(0);" class="btn btn-xs btn-block btn-primary" id="lockScreen-smart-widget"><i class="fa fa-lock"></i>锁屏</a></section> <h6 class="margin-top-10 semi-bold margin-bottom-5"><legend class="no-padding margin-bottom-10">系统皮肤</legend></h6><section id="smart-styles"><a href="javascript:void(0);" id="smart-style-0" data-skinlogo="/fanxing-erp/resources/style/index/img/logo.png" class="btn btn-block btn-xs txt-color-white margin-right-5" style="background-color:#4E463F;"><i class="fa fa-check fa-fw" id="skin-checked"></i>默认</a><a href="javascript:void(0);" id="smart-style-1" data-skinlogo="/product_erp/resources/style/index/img/logo-white.png" class="btn btn-block btn-xs txt-color-white" style="background:#3A4558;">优雅黑</a><a href="javascript:void(0);" id="smart-style-2" data-skinlogo="/product_erp/resources/style/index/img/logo-blue.png" class="btn btn-xs btn-block txt-color-darken margin-top-5" style="background:#fff;">高度亮</a><a href="javascript:void(0);" id="smart-style-3" data-skinlogo="/product_erp/resources/style/index/img/logo-pale.png" class="btn btn-xs btn-block txt-color-white margin-top-5" style="background:#f78c40">欢乐橙</a></section></form> </div>'
//);

/**
 * 设置按扭
 */
$('#ribbon').append('<div class="demo"><span id="demo-setting">&nbsp<i class="fa fa-cog  txt-color-blueDark"></i></span> <form><section><h6 class="margin-top-10 semi-bold margin-bottom-5"></h6><a href="javascript:void(0);" class="btn btn-xs btn-block btn-primary" id="changePwd-smart-widget"><i class="fa fa-key "></i> 修改密码</a></section><section><h6 class="margin-top-10 semi-bold margin-bottom-5"></h6><a href="javascript:void(0);" class="btn btn-xs btn-block btn-primary" id="clearCache-smart-widget"><i class="fa fa-flash "></i> 清理缓存</a></section><section><h6 class="margin-top-10 semi-bold margin-bottom-5"></h6><a href="javascript:void(0);" class="btn btn-xs btn-block btn-primary" id="lockScreen-smart-widget"><i class="fa fa-lock"></i> 锁屏</a></section> <h6 class="margin-top-10 semi-bold margin-bottom-5"><legend class="no-padding margin-bottom-10">系统皮肤</legend></h6><section id="smart-styles"><a href="javascript:void(0);" id="smart-style-0" data-skinlogo='+resources_static+'/style/index/img/demo/logo.png class="btn btn-block btn-xs txt-color-white margin-right-5" style="background-color:#4E463F;"><i class="fa fa-check fa-fw" id="skin-checked"></i>默认</a><a href="javascript:void(0);" id="smart-style-1" data-skinlogo='+resources_static+'/style/index/img/demo/logo-white.png class="btn btn-block btn-xs txt-color-white" style="background:#3A4558;">优雅黑</a><a href="javascript:void(0);" id="smart-style-2" data-skinlogo='+resources_static+'/style/index/img/demo/logo-blue.png class="btn btn-xs btn-block txt-color-darken margin-top-5" style="background:#fff;">高度亮</a><a href="javascript:void(0);" id="smart-style-3" data-skinlogo='+resources_static+'/style/index/img/demo/logo-pale.png class="btn btn-xs btn-block txt-color-white margin-top-5" style="background:#f78c40">欢乐橙</a></section></form> </div>');

// 桌面log
var smartbgimage ="<h6 class='margin-top-10 semi-bold'>背景色</h6><img src="+resources_static+"/style/index/img/pattern/graphy-xs.png' data-htmlbg-url="+resources_static+"/style/index/img/pattern/graphy.png' width='22' height='22' class='margin-right-5 bordered cursor-pointer'><img src="+resources_static+"/style/index/img/pattern/tileable_wood_texture-xs.png' width='22' height='22' data-htmlbg-url="+resources_static+"/style/index/img/pattern/tileable_wood_texture.png' class='margin-right-5 bordered cursor-pointer'><img src="+resources_static+"/style/index/img/pattern/sneaker_mesh_fabric-xs.png' width='22' height='22' data-htmlbg-url="+resources_static+"/style/index/img/pattern/sneaker_mesh_fabric.png' class='margin-right-5 bordered cursor-pointer'><img src="+resources_static+"/style/index/img/pattern/nistri-xs.png' data-htmlbg-url="+resources_static+"/style/index/img/pattern/nistri.png' width='22' height='22' class='margin-right-5 bordered cursor-pointer'><img src="+resources_static+"/style/index/img/pattern/paper-xs.png' data-htmlbg-url="+resources_static+"/style/index/img/pattern/paper.png' width='22' height='22' class='bordered cursor-pointer'>";
$("#smart-bgimages").fadeOut();


$('#demo-setting').click(function () {
     $('#ribbon .demo').toggleClass('activate');
});



/**
 * 修改密码表单提交
 */
$('#submitBtn').on('click',function(){
	var $valid=$("#changePwd-form").valid();
	 if ($valid) {
		 $('#submitBtn').attr('disabled','disabled');
		 $("#changePwd-form").submit();
	 }
});

/**
 * 自定义修改密码成功后回调函数
 */
$("#changePwd-form").on('returnLogin',function(){
	msgBox("4秒后将转跳到登录页面！ ",$time);
	setTimeout(function(){
		  window.location = base_path+"/j_spring_security_logout;";
	},4000);
});

/**
 * 修改密码div(回车事件)
 */
$("#changePwd").keydown(function(event){
    if(event.keyCode==13){
    	$('#submitBtn').attr('disabled','disabled');
    	$('#submitBtn').click();
    }
 });

/**
 * 修改密码
 */

$("#changePwd-smart-widget").on("click", function(){
	$('#changePwd').modal({
		keyboard: true
	});
});
/**
 * 修改密码表单
 */
var $changePwdForm = $("#changePwd-form").validate({
	rules : {
		passwordSelf : {
			required : true,
			minlength : 4,
			maxlength : 20,
			alnum:true,
			checkPwdSelf:{
				url:'/operatorController/checkSelfPasswordById.do'
			}
		},
		passwordCheck : {
			required : true,
			alnum:true,
			minlength : 4,
			maxlength : 20
		},
		password : {
			required : true,
			alnum:true,
			minlength : 4,
			maxlength : 20,
			equalTo : '#passwordCheck'
		}
	}
});


/**
 * 清理缓存
 */
$("#clearCache-smart-widget").on("click", function () {
	$.SmartMessageBox({
		title : "<i class='fa fa-flash' style='color:green'></i>清理缓存",
		content : "确定要清理缓存数据，重置页面个性化设置吗？",
		buttons : '[不了][重置]'
	}, function(ButtonPressed) {
		if (ButtonPressed == "重置" && localStorage) {
			localStorage.clear();
			location.reload();
		}

	});
	e.preventDefault();
});
/**
 * 锁屏
 */
$("#lockScreen-smart-widget").on("click", function () {
    window.location = base_path+"/loginController/lockScreen.do";
    return false;
});

if(localStorage){
	if(localStorage.getItem('skinId')!=undefined){
		 var skinId=localStorage.getItem('skinId');
		 changeSkin($("#smart-styles").find('a[id='+skinId+']'));
	}
}

/**
 * 皮肤更换
 */
$("#smart-styles > a").on("click",function() {
    var $this = $(this);
    changeSkin($this);
    localStorage.setItem('skinId',$this.attr("id"));
});

/**
 * 皮肤更换函数
 * @param a
 */
function changeSkin(a){
    var $logo = $("#logo img");
    $.root_.removeClassPrefix('smart-style').addClass(a.attr("id"));
    $logo.attr('src', a.data("skinlogo"));
    $("#smart-styles > a #skin-checked").remove();
    a.prepend("<i class='fa fa-check fa-fw' id='skin-checked'></i>");
}

/**
 * 固定头部
 */
$('input[type="checkbox"]#smart-fixed-nav').click(function() {
    if ($(this).is(':checked')) {
        $.root_.addClass("fixed-header");
        nav_page_height();
    } else {
        $('input[type="checkbox"]#smart-fixed-ribbon').prop('checked', false);
        $('input[type="checkbox"]#smart-fixed-navigation').prop('checked', false);
        $.root_.removeClass("fixed-header");
        $.root_.removeClass("fixed-navigation");
        $.root_.removeClass("fixed-ribbon");
    }
});

/**
 * 固定导航带
 */
$('input[type="checkbox"]#smart-fixed-ribbon').click(function() {
    if ($(this).is(':checked')) {
        $('input[type="checkbox"]#smart-fixed-nav').prop('checked', true);
        $.root_.addClass("fixed-header");
        $.root_.addClass("fixed-ribbon");
        $('input[type="checkbox"]#smart-fixed-container').prop('checked', false);
        $.root_.removeClass("container");
    } else {
        $('input[type="checkbox"]#smart-fixed-navigation').prop('checked', false);
        $.root_.removeClass("fixed-ribbon");
        $.root_.removeClass("fixed-navigation");
    }
});


/**
 * 固定菜单
 */
$('input[type="checkbox"]#smart-fixed-navigation').click(function() {
    if ($(this).is(':checked')) {
        $('input[type="checkbox"]#smart-fixed-nav').prop('checked', true);
        $('input[type="checkbox"]#smart-fixed-ribbon').prop('checked', true);
        //apply
        $.root_.addClass("fixed-header");
        $.root_.addClass("fixed-ribbon");
        $.root_.addClass("fixed-navigation");
        $('input[type="checkbox"]#smart-fixed-container').prop('checked', false);
        $.root_.removeClass("container");
    } else {
        $.root_.removeClass("fixed-navigation");
    }
});

/**
 * RTL SUPPORT
 */
$('input[type="checkbox"]#smart-rtl').click(function() {
	if ($(this).is(':checked')) {
		$.root_.addClass("smart-rtl");
	} else {
		$.root_.removeClass("smart-rtl");
	}
});

/**
 * 容器显示
 */
$('input[type="checkbox"]#smart-fixed-container').click(function() {
	if ($(this).is(':checked')) {
		$.root_.addClass("container");
		$('input[type="checkbox"]#smart-fixed-ribbon').prop('checked', false);
		$.root_.removeClass("fixed-ribbon");
		$('input[type="checkbox"]#smart-fixed-navigation').prop('checked', false);
		$.root_.removeClass("fixed-navigation");
		if (smartbgimage) {
			$("#smart-bgimages").append(smartbgimage).fadeIn(1000);
			$("#smart-bgimages img").bind("click",function() {
				var $this = $(this);
				var $html = $('html');
				bgurl = ($this.data("htmlbg-url"));
				$html.css("background-image", "url(" + bgurl + ")");
			});
			smartbgimage = null;
		} else {
			$("#smart-bgimages").fadeIn(1000);
		}
	} else {
		$.root_.removeClass("container");
		$("#smart-bgimages").fadeOut();
	}
});

$(window).resize(function(){
	  getPageSize();
});
getPageSize();
/**
 * 获取页面的高度、宽度
 * @returns
 */
function getPageSize() {
    var xScroll, yScroll;
    if (window.innerHeight && window.scrollMaxY) {
        xScroll = window.innerWidth + window.scrollMaxX;
        yScroll = window.innerHeight + window.scrollMaxY;
    } else {
        if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac    
            xScroll = document.body.scrollWidth;
            yScroll = document.body.scrollHeight;
        } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari    
            xScroll = document.body.offsetWidth;
            yScroll = document.body.offsetHeight;
        }
    }
    var windowWidth, windowHeight;
    if (self.innerHeight) { // all except Explorer    
        if (document.documentElement.clientWidth) {
            windowWidth = document.documentElement.clientWidth;
        } else {
            windowWidth = self.innerWidth;
        }
        windowHeight = self.innerHeight;
    } else {
        if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode    
            windowWidth = document.documentElement.clientWidth;
            windowHeight = document.documentElement.clientHeight;
        } else {
            if (document.body) { // other Explorers    
                windowWidth = document.body.clientWidth;
                windowHeight = document.body.clientHeight;
            }
        }
    }       
    // for small pages with total height less then height of the viewport    
    if (yScroll < windowHeight) {
        pageHeight = windowHeight;
    } else {
        pageHeight = yScroll;
    }    
    // for small pages with total width less then width of the viewport    
    if (xScroll < windowWidth) {
        pageWidth = xScroll;
    } else {
        pageWidth = windowWidth;
    }
    windowSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight);
    return windowSize;
}
 
// 滚动条
document.body.scrollTop;
$(document).scrollTop();