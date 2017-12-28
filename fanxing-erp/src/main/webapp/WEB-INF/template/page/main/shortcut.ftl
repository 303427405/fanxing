<style>
.selected:before{display:block;position:absolute;content:"\f00c";color:#fff;right:4px;font-family:FontAwesome;z-index:2}
.selected:after {
  width: 0;
  height: 0;
  border-top: 35px solid #0091d9;
  border-left: 35px solid rgba(0,0,0,0);
  position: absolute;
  display: block;
  right: 0;
  content: ".";
  top: 0;
  z-index: 1;
}
</style>
<div id="shortcut">
	<ul>
	 [#list shortcutList as s]
	    <li>
			<a href="${s.webUrl}" data-id="${s.permissionsId}" class="jarvismetro-tile big-cubes bg-color-${s.bgColor}"> 
			   <span class="iconbox"> <i class="iconfont ${s.icon} icon-4x"></i> 
			     <span>${s.name}</span>
			   </span>
			 </a>
	    </li>
	 [/#list]
		<li>
			<a href="/loginController/profile.do" class="jarvismetro-tile big-cubes  bg-color-greenLight"> <span class="iconbox"> <i class="iconfont icon-user icon-4x"></i> <span>个人信息</span> </span> </a>
		</li>
	</ul>
</div>