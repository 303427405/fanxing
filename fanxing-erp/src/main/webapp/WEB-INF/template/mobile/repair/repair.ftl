<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width"/>
<meta name="viewport" content="initial-scale=1.0,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" media="screen" href="${resources_static}/style/repair/css/css.css">
<script src="${resources_static}/style/mobile/js/jquery/jquery-1.8.3.min.js"></script>
<script src="${resources_static}/style/mobile/js/jquery/plugin/jquery-validate/jquery.validate.min.js"></script> 
<script src="${resources_static}/style/mobile/js/jquery/plugin/jquery-validate/additional-methods.js"></script>
<script src="${resources_static}/style/mobile/js/jquery/plugin/jquery-validate/messages_cn.js"></script> 
<title>业务单填写</title>

<script type="text/javascript">
	var base_shop_path='${base}';
</script>
<script src="${resources_static}/js/common/common.js"></script>
<script src="${resources_static}/js/repair/repair.js"></script>
</head>
<body>
	<div id="main-text">
	<form id="addForm" action="${base}/workOrderController/addWorkOrderNoAuth.do" method="post">
    	<table width="100%" border="0" >
        	<tr class="big">
            	<td width="20" class="left-name">姓名：</td>
                <td width="80">
                <input type="text" class="kuang" name="name">
                <input type="text" class="kuang" name="wechatId" value="${wechatId}">
                <input type="text" class="kuang" name="openId"   value="${openId}">
                </td>
            </tr>
            <tr class="big">
            	<td width="20" class="left-name">手机：</td>
                <td width="80"><input type="text" class="kuang" name="mobile"></td>
            </tr>
             <tr class="big">
            	<td width="20" class="left-name">维修类型：</td>
                <td width="80">
                	<select id="type" class="kuang" name="type">
                	    
                    </select>
                </td>
            </tr>
            <tr class="big">
            	<td width="20" class="left-name">地址：</td>
                <td width="80"><div><input type="text" class="kuang" name="address"></div></td>
            </tr>
        </table>
            <div class="big">
            	<div class="left-name">原因：</td>
                <div class="left-name-box"><textarea name="content" row="10" cols="10" class="ku"></textarea></td>
            </div>
        <div class="one"><input value="登记" id="submit" type="submit" class="two"></div>
       </form>
    </div>
</body>
</html>