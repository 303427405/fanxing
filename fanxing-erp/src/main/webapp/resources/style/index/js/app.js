//$.throttle_delay = 350;
$.menu_speed = 235;
$.navbar_height = 49; 
$.root_ = $('body');
$.left_panel = $('#left-panel');
$.shortcut_dropdown = $('#shortcut');
$.bread_crumb = $('#ribbon ol.breadcrumb');
$.device = null;
$.navAsAjax = true; 
$time=4000;
$container = $('#content');
//存储页面的高度、宽度
var windowSize;

//$.enableJarvisWidgets = true;
//$.enableMobileWidgets = false;

var ismobile = (/iphone|ipad|ipod|android|blackberry|mini|windows\sce|palm/i.test(navigator.userAgent.toLowerCase()));
if (!ismobile) {
	$.root_.addClass("desktop-detected");
	$.device = "desktop";
} else {
	$.root_.addClass("mobile-detected");
	$.device = "mobile";
	// $('.selectpicker').selectpicker('mobile');
}



/**
 * 调整页面高度
 */
function nav_page_height() {
	var setHeight = $('#main').height();
	//menuHeight = $.left_panel.height();
	
	var windowHeight = $(window).height() - $.navbar_height;
	//set height

	if (setHeight > windowHeight) {// if content height exceedes actual window height and menuHeight
		$.left_panel.css('min-height', setHeight + 'px');
		$.root_.css('min-height', setHeight + $.navbar_height + 'px');

	} else {
		$.left_panel.css('min-height', windowHeight + 'px');
		$.root_.css('min-height', windowHeight + 'px');
	}
}

/**
 * 校验是否是手机
 */
function check_if_mobile_width() {
	if ($(window).width() < 979) {
		$.root_.addClass('mobile-view-activated');
	} else if ($.root_.hasClass('mobile-view-activated')) {
		$.root_.removeClass('mobile-view-activated');
	}
}

$(document).ready(function() {
	if ($("[rel=tooltip]").length) {
		$("[rel=tooltip]").tooltip();
	}
	nav_page_height();
	/**
	 * 渲染菜单
	 */
	if (!null) {
		$('nav ul').jarvismenu({
			accordion : true,
			speed : $.menu_speed,
			closedSign : '<em class="fa fa-plus-square-o"></em>',
			openedSign : '<em class="fa fa-minus-square-o"></em>'
		});
	} else {
		alert("菜单数据渲染失败！");
	}
	/**
	 * 隐藏菜单
	 */
	$('.minifyme').click(function(e) {
		$('body').toggleClass("minified");
		$(this).effect("highlight", {}, 500);
		e.preventDefault();
	});

	/**
	 * 隐藏菜单
	 */
	$('#hide-menu >:first-child > a').click(function(e) {
		$('body').toggleClass("hidden-menu");
		e.preventDefault();
	});

	/**
	 * 面板（消息，通知，任务）控制
	 */
	$('#activity').click(function(e) {
		var $this = $(this);
		if ($this.find('.badge').hasClass('bg-color-red')) {
			$this.find('.badge').removeClassPrefix('bg-color-');
			$this.find('.badge').text("0");
		}
		if (!$this.next('.ajax-dropdown').is(':visible')) {
			$this.next('.ajax-dropdown').fadeIn(150);
			$this.addClass('active');
		} else {
			$this.next('.ajax-dropdown').fadeOut(150);
			$this.removeClass('active');
		}
		//var mytest = $this.next('.ajax-dropdown').find('.btn-group > .active > input').attr('id');
		e.preventDefault();
	});
   /**
    * 加载面板（消息，通知，任务）
    */
	$('input[name="activity"]').change(function() {
		var $this = $(this);
		url = $this.attr('id');
		container = $('.ajax-notifications');
		loadURL(url, container);
	});
	
	/**
	 * 面板（消息，通知，任务）刷新
	 */
	$('button[data-loading-text]').on('click', function() {
		var btn = $(this);
		btn.text('加载中...');
		setTimeout(function() {
			btn.text('刷新');
		}, 3000);
	});
	/**
	 * 如果鼠标单击该区域以外的区域，消息面板隐藏
	 */
	$(document).mouseup(function(e) {
		if (!$('.ajax-dropdown').is(e.target)// if the target of the click isn't the container...
		&& $('.ajax-dropdown').has(e.target).length === 0) {
			$('.ajax-dropdown').fadeOut(150);
			$('.ajax-dropdown').prev().removeClass("active");
		}
	});
	
	notification_check();
	/**
	 * 导航条刷新
	 */
	/*$('#refresh').click(function(e) {
		$.SmartMessageBox({
			title : "<i class='fa fa-refresh' style='color:green'></i> Clear Local Storage",
			content : "Would you like to RESET all your saved widgets and clear LocalStorage?",
			buttons : '[No][Yes]'
		}, function(ButtonPressed) {
			if (ButtonPressed == "Yes" && localStorage) {
				localStorage.clear();
				location.reload();
			}

		});
		e.preventDefault();
	});*/

	/**
	 * 退出系统
	 */
	$('#logout a').click(function(e) {
		var $this = $(this);
		$.loginURL = $this.attr('href');
		$.logoutMSG = $this.data('logout-msg');
		$.SmartMessageBox({
			title : "<i class='fa fa-sign-out txt-color-orangeDark'></i> 退出 <span class='txt-color-orangeDark'><strong>" + $('#show-shortcut').text() + "</strong></span> ?",
			content : $.logoutMSG,
			buttons : '[再等等吧][退出]'
		}, function(ButtonPressed) {
			if (ButtonPressed == "退出") {
				$.root_.addClass('animated fadeOutUp');
				setTimeout(logout, 1000);
			}
		});
		e.preventDefault();
	});

	/**
	 * 显示快捷方式
	 */
	$('#show-shortcut').click(function(e) {
		if ($.shortcut_dropdown.is(":visible")) {
			shortcut_buttons_hide();
		} else {
			shortcut_buttons_show();
		}
		e.preventDefault();
	});
	
	
	/**
	 * 首页
	 */
	$("#ribbon ol").delegate('li[id=firstPage]','click',function(){
		var url="/loginController/system_right.do";
		//更新导航栏
		$('nav li').removeClass('active');
		$('nav li > a').each(function() {
			if($(this).data('id')==-1){
				$(this).closest('li').addClass('active');
			}
		});
		changeUrlAddress(url);
		loadURL(url, container);
	})
	/**
	 * 快捷方式事件
	 */
	$.shortcut_dropdown.find('a').click(function(e) {
		e.preventDefault();
		container = $('#content');
		var url=$(this).attr('href');
		var id=$(this).data('id');
		if(url==undefined||url==""){
			url="/loginController/system_right.do";
			id=-1;
		}
		$(this).addClass('selected');
		$(this).closest('li').siblings('li').find('a').removeClass('selected');
		
		//更新导航栏
		$('nav li').removeClass('active');
		$('nav li > a').each(function() {
			if($(this).data('id')==id){
				$(this).closest('li').addClass('active');
			}
		});
		changeUrlAddress(url);
		loadURL(url, container);
		setTimeout(shortcut_buttons_hide, 300);
	});

	/**
	 * 如果鼠标单击该区域以外的区域，快捷区域隐藏
	 */
	$(document).mouseup(function(e) {
		if (!$.shortcut_dropdown.is(e.target)// if the target of the click isn't the container...
		&& $.shortcut_dropdown.has(e.target).length === 0) {
			shortcut_buttons_hide();
		}
	});

	
	$('#main').resize(function() {
		nav_page_height();
		check_if_mobile_width();
	});

	$('nav').resize(function() {
		nav_page_height();
	});
	
	

});

/**
 * 退出系统
 */
function logout() {
	window.location = $.loginURL;
}

/**
 * 快捷方式隐藏
 */
function shortcut_buttons_hide() {
	$.shortcut_dropdown.animate({
		height : "hide"
	}, 300, "easeOutCirc");
	$.root_.removeClass('shortcut-on');

}

/**
 * 消息通知数量检查
 */
function notification_check() {
	$this = $('#activity > .badge');
	if (parseInt($this.text()) > 0) {
		$this.addClass("bg-color-red bounceIn animated");
	}
}

/**
 * 快捷方式显示
 */
function shortcut_buttons_show() {
	$.shortcut_dropdown.animate({
		height : "show"
	}, 200, "easeOutCirc");
	$.root_.addClass('shortcut-on');
}




/**
 * 面板全屏功能
 */
function launchFullscreen(element) {
	if (!$.root_.hasClass("full-screen")) {
		$.root_.addClass("full-screen");
		if (element.requestFullscreen) {
			element.requestFullscreen();
		} else if (element.mozRequestFullScreen) {
			element.mozRequestFullScreen();
		} else if (element.webkitRequestFullscreen) {
			element.webkitRequestFullscreen();
		} else if (element.msRequestFullscreen) {
			element.msRequestFullscreen();
		}
	} else {
		$.root_.removeClass("full-screen");
		if (document.exitFullscreen) {
			document.exitFullscreen();
		} else if (document.mozCancelFullScreen) {
			document.mozCancelFullScreen();
		} else if (document.webkitExitFullscreen) {
			document.webkitExitFullscreen();
		}
	}
}

/**
 * 表单,提示框初始化
 */
function runAllForms() {
	$("[rel=tooltip]").tooltip();
	//提示框
	$("[rel=popover]").popover();
	$("[rel=popover-hover]").popover({
		trigger : "hover"
	});
	if ($.fn.slider) {
		$('.slider').slider();
	}
	if ($.fn.select2) {
		$('.select2').each(function() {
			var $this = $(this);
			var width =$this.attr('width')!=undefined?$this.attr('width'):'100%';
			//var _showSearchInput = $this.attr('data-select-search') === 'true';
			$this.select2({
				showSearchInput :false,
				allowClear : true,
				width : width
			});
		});
	}
	if ($.fn.mask) {
		$('[data-mask]').each(function() {
			var $this = $(this);
			var mask = $this.attr('data-mask') || 'error...', mask_placeholder = $this.attr('data-mask-placeholder') || 'X';
			$this.mask(mask, {
				placeholder : mask_placeholder
			});
		});
	}
	if ($.fn.autocomplete) {
		$('[data-autocomplete]').each(function() {
			var $this = $(this);
			var availableTags = $this.data('autocomplete') || ["The", "Quick", "Brown", "Fox", "Jumps", "Over", "Three", "Lazy", "Dogs"];
			$this.autocomplete({
				source : availableTags
			});
		});
	}
	if ($.fn.datepicker) {
		$('.datepicker').each(function() {
			var $this = $(this);
			var dataDateFormat = $this.attr('data-dateformat') || 'yy-mm-dd';
			$this.datepicker({
				regional:"zh-CN",
				dateFormat : dataDateFormat,
				prevText : '<i class="fa fa-chevron-left"></i>',
				nextText : '<i class="fa fa-chevron-right"></i>',
				showButtonPanel: true,
				changeMonth: true
			});
		});
	}
	$('button[data-loading-text]').on('click', function() {
		var btn = $(this);
		btn.button('加载中...');
		setTimeout(function() {
			btn.button('刷新');
		}, 3000);
	});
}

/**
 * 改变url地址
 * @param href
 */
function changeUrlAddress(href){
   if ($.root_.hasClass('mobile-view-activated')) {
	    $.root_.removeClass('hidden-menu');
	    window.setTimeout(function() {
			if (window.location.search) {
				window.location.href =
					window.location.href.replace(window.location.search, '')
						.replace(window.location.hash, '') + '#' + href;
			} else {
				window.location.hash = href;
			}
	    }, 150);
    } else {
		if (window.location.search) {
			window.location.href =
				window.location.href.replace(window.location.search, '')
					.replace(window.location.hash, '') + '#' + href;
		} else {
			window.location.hash = href;
		}
    }
}

if($.navAsAjax)
{
    if ($('nav').length) {
	    checkURL();
    };
    $(document).on('click', 'nav a[href!="#"]', function(e) {
	    e.preventDefault();
	    var $this = $(e.currentTarget);
		if (!$this.parent().hasClass("active") && !$this.attr('target')) {
			changeUrlAddress($this.attr('href'));
	    }
    });
    $(document).on('click', 'nav a[target="_blank"]', function(e) {
	    e.preventDefault();
	    var $this = $(e.currentTarget);
	    window.open($this.attr('href'));
    });
    $(document).on('click', 'nav a[target="_top"]', function(e) {
	    e.preventDefault();
	    var $this = $(e.currentTarget);
	    window.location = ($this.attr('href'));
    });
    $(document).on('click', 'nav a[href="#"]', function(e) {
	    e.preventDefault();
    });
    // 菜单选择
    $(window).on('hashchange', function() {
	    checkURL();
    });
}

/**
 * 组装url
 */
function checkURL() {
	//get the url by removing the hash substring(0,url.lastIndexOf('_'))
	var url = location.hash.replace(/^#/, '');
	container = $('#content');
	if (url) {
		$('nav li.active').removeClass("active");
		$('nav li:has(a[href="' + url + '"])').addClass("active");
		var title = ($('nav a[href="' + url + '"]').attr('title'));
		document.title = (title || document.title);
		loadURL(url + location.search, container);
	} else {
		var $this = $('nav > ul > li:first-child > a[href!="#"]');
		window.location.hash = $this.attr('href');
	}

}

  

/**
 * 加载页面
 */
function loadURL(url, container,data) {
	data=$.extend(data,{ajaxType:'ajax'});
	$.ajax({
		type : "POST",
		url : base_path+url,
		dataType : 'html',
		cache : false, 
		data:data,
		//processData:false,
		complete: function(XMLHttpRequest, textStatus) { 
		},
		beforeSend : function() {
			container.html('<h1><i class="fa fa-cog fa-spin"></i> 加载中...</h1>');
			if (container[0] == $("#content")[0]) {
				drawBreadCrumb();
				$("html").animate({
					scrollTop : 0
				}, "fast");
			};
		},
		success : function(data) {
			container.css({ opacity : '0.0' }).html(data).delay(30).animate({ opacity : '1.0' }, 300);
			nav_page_height();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			  container.css({ opacity : '0.0' }).html(xhr.responseText).delay(30).animate({ opacity : '1.0' }, 300);
			//container.html('<h4 style="margin-top:10px; display:block; text-align:left"><i class="fa fa-warning txt-color-orangeDark"></i> Error 404! Page not found.</h4>');
		},
		async : false
	});
}


/**
 * 更新导航栏
 */
function drawBreadCrumb() {
	var nav_elems = $('nav li.active > a'), count = nav_elems.length;
	$.bread_crumb.empty();
	$.bread_crumb.append($("<li id='firstPage' style='color:#E4E4E4;cursor: pointer'>首页</li>"));
	if(nav_elems.length!=1&&!nav_elems.text()!="首页"){
		if(nav_elems.length==0){
			$.bread_crumb.append("<li>个人信息</li>")
		}
		nav_elems.each(function() {
			$.bread_crumb.append($("<li></li>").html($.trim($(this).clone().children(".badge").remove().end().text())));
			// 用户完成后更新标题
			if (!--count) document.title = $.bread_crumb.find("li:last-child").text();
		});
	}

}
/**
 * 页面初始化
 */
function pageSetUp() {
	if ($.device === "desktop"){
		
	} else {
	}
	nav_page_height();
	runAllForms();
}


/**
 * 表单验证消息显示样式
 */
$.validator.setDefaults({
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		var returnUrl=form.getAttribute('returnUrl');
		if(returnUrl==null||returnUrl==undefined||returnUrl=="")
			returnUrl="/loginController/system_right.do";
		if(form.enctype=="multipart/form-data"){
			var fileId=$("#"+form.getAttribute('id')).find("input[type=file]").attr('id');
			ajaxFileSubmit(form.getAttribute('action'),$("#"+form.getAttribute('id')).serializeJson(),fileId,returnUrl);
			form.reset();
		}else{
			if(ajaxSubmit_custom(form.getAttribute('action'),$("#"+form.getAttribute('id')).serializeJson(),true)==true){
				if(form.getAttribute('customfn')!=undefined){//自定义事件
					$("#"+form.getAttribute('id')).trigger(form.getAttribute('customfn'));
				}else{
					loadURL(returnUrl, $container);
				}
		    	form.reset();
		    }else{
		    	$('#submit').removeAttr('disabled');
		    }
		}
	}
});


/**
 * ajax 文件上传+表单提交
 */
function ajaxFileSubmit(url,data,fileId,returnUrl){
	$.ajaxFileUpload({
		url:base_path+url,
		async : false,
		secureuri:false,                       //是否启用安全提交,默认为false 
		fileElementId:fileId,           //文件选择框的id属性
		dataType:'json',
		data:data,
		success:function(data, status){    
			var obj=eval(data);
			msgBox(obj.msg,$time);
			loadURL(returnUrl, $container);
		},
		error:function(data, status, e){
			msgBox(data.responseText,undefined);
		}
	});
}

/**
 * ajax表单提交
 * @param url
 * @param data
 * @returns {Boolean}
 */
function ajaxSubmit_custom(url,data,showBox) {
	var flg=false;
	$.ajax({
		type : "POST",
		async : false,
		url : base_path+url,
		dataType : 'json',
		cache : false, 
		data:data,
		success : function(data) {
			var obj=eval(data);
			if(showBox==undefined||showBox==true)
			   msgBox(obj.msg,$time);
			if(obj.success==true)
			   flg=true;
		},
		error : function(xhr, ajaxOptions, thrownError) {
			msgBox(xhr.responseText,undefined);
			flg=false;
		}
	});
	return flg;
}
/**
 * 消息框
 * @param msg
 * @param timeout
 */
function msgBox(msg,timeout){
	$.smallBox({
		title : "温馨提示",
		content :msg,
		color : "#5384AF",
		icon : "fa fa-bell",
		timeout : timeout,
		sound:false
	});
}

/*
 * CUSTOM MENU PLUGIN
 */

$.fn.extend({
	//pass the options variable to the function
	jarvismenu : function(options) {
		var defaults = {
			accordion : 'true',
			speed : 200,
			closedSign : '[+]',
			openedSign : '[-]'
		};
		// Extend our default options with those provided.
		var opts = $.extend(defaults, options);
		//Assign current element to variable, in this case is UL element
		var $this = $(this);

		//add a mark [+] to a multilevel menu
		$this.find("li").each(function() {
			if ($(this).find("ul").size() != 0) {
				//add the multilevel sign next to the link
				$(this).find("a:first").append("<b class='collapse-sign'>" + opts.closedSign + "</b>");

				//avoid jumping to the top of the page when the href is an #
				if ($(this).find("a:first").attr('href') == "#") {
					$(this).find("a:first").click(function() {
						return false;
					});
				}
			}
		});

		//open active level
		$this.find("li.active").each(function() {
			$(this).parents("ul").slideDown(opts.speed);
			$(this).parents("ul").parent("li").find("b:first").html(opts.openedSign);
			$(this).parents("ul").parent("li").addClass("open");
		});

		$this.find("li a").click(function() {

			if ($(this).parent().find("ul").size() != 0) {

				if (opts.accordion) {
					//Do nothing when the list is open
					if (!$(this).parent().find("ul").is(':visible')) {
						parents = $(this).parent().parents("ul");
						visible = $this.find("ul:visible");
						visible.each(function(visibleIndex) {
							var close = true;
							parents.each(function(parentIndex) {
								if (parents[parentIndex] == visible[visibleIndex]) {
									close = false;
									return false;
								}
							});
							if (close) {
								if ($(this).parent().find("ul") != visible[visibleIndex]) {
									$(visible[visibleIndex]).slideUp(opts.speed, function() {
										$(this).parent("li").find("b:first").html(opts.closedSign);
										$(this).parent("li").removeClass("open");
									});

								}
							}
						});
					}
				}// end if
				if ($(this).parent().find("ul:first").is(":visible") && !$(this).parent().find("ul:first").hasClass("active")) {
					$(this).parent().find("ul:first").slideUp(opts.speed, function() {
						$(this).parent("li").removeClass("open");
						$(this).parent("li").find("b:first").delay(opts.speed).html(opts.closedSign);
					});

				} else {
					$(this).parent().find("ul:first").slideDown(opts.speed, function() {
						/*$(this).effect("highlight", {color : '#616161'}, 500); - disabled due to CPU clocking on phones*/
						$(this).parent("li").addClass("open");
						$(this).parent("li").find("b:first").delay(opts.speed).html(opts.openedSign);
					});
				} // end else
			} // end if
		});
	} // end function
});




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


String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
};

String.prototype.endWith=function(endStr){
	  var d=this.length-endStr.length;
	  return (d>=0&&this.lastIndexOf(endStr)==d)
};
String.prototype.endWith=function(startStr){
	  var d=startStr.length;
	  return (d>=0&&this.indexOf(startStr)==0)
};
//光标放在最后 $("#文本框ID").textFocus();
//光标放在第二个字符后面 $("#文本框ID").textFocus(2);
(function($){
    $.fn.textFocus=function(v){
        var range,len,v=v===undefined?0:parseInt(v);
        this.each(function(){
            if($.browser.msie){
                range=this.createTextRange();
                v===0?range.collapse(false):range.move("character",v);
                range.select();
            }else{
                len=this.value.length;
                v===0?this.setSelectionRange(len,len):this.setSelectionRange(v,v);
            }
            this.focus();
        });
        return this;
    }
})(jQuery);


