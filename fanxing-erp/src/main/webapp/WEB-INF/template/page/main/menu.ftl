<aside id="left-panel">
			<!-- User info -->
			<div class="login-info">
				<span>
					<a href="javascript:void(0);" id="show-shortcut">
					<!-- ${SPRING_SECURITY_CONTEXT.authentication.principal.imgPath}	<img src="${resources_static}/style/index/img/avatars/sunny.png" alt="me" class="online" />  -->
					[#if SPRING_SECURITY_CONTEXT.authentication.principal.imgPath!=null]
					   <img src="${upload_static}${SPRING_SECURITY_CONTEXT.authentication.principal.imgPath}" alt="me" style="height:30px;" class="online" />
					  [#else] 
					   <img src="${resources_static}/style/index/img/avatars/users_32.png" alt="me" class="online" />
					[/#if]
						<span>
							${SPRING_SECURITY_CONTEXT.authentication.principal.username}
						</span>
						<i class="fa fa-angle-down"></i>
					</a> 
				</span>
			</div>
			<!-- end user info -->
			<!-- 菜单-->
			<nav>
				 <ul>
				  <li class="active">
						<a href="/loginController/system_right.do" data-id="-1"><i class="iconfont icon-home" ></i> <span class="menu-item-parent">首页</span></a>
				  </li>
				  <!-- 支持四级菜单 -->
				  [#list permissionsList as p]
				    [#if p.webUrl!=null&&p.leaf==1]
				       <li><a href="${p.webUrl}" data-id="${p.id}"><i class="iconfont ${p.icon} icon-1-3x"></i><span class="menu-item-parent">${p.name}</span></a></li>
					   [#elseif p.webUrl==null&&p.leaf==1]
					     <li><a href="#" data-id="${p.id}" ><i class="iconfont ${p.icon} icon-1-3x"></i><span class="menu-item-parent">${p.name}</span></a>
						      <ul>
							       [#list permissionsList as pp]
							              [#if pp.webUrl!=null&&pp.leaf==2&&pp.parentId==p.id]
							                 <li><a data-id="${pp.id}" href="${pp.webUrl}" ><i class="iconfont ${pp.icon} icon-1-3x"></i>&nbsp${pp.name}</a></li>
							                 [#elseif pp.webUrl==null&&pp.leaf==2&&pp.parentId==p.id]
							                  <li><a href="#" data-id="${pp.id}" ><i class="iconfont ${pp.icon} icon-1-3x"></i>&nbsp${pp.name}</a>
								                   <ul>
								                       [#list permissionsList as ppp]
								                         [#if ppp.webUrl!=null&&ppp.leaf==3&&ppp.parentId==pp.id]
								                           <li><a data-id="${ppp.id}" href="${ppp.webUrl}"><i class="iconfont ${ppp.icon} icon-1-3x"></i>&nbsp${ppp.name}</a></li>
								                             [#elseif ppp.webUrl==null&&ppp.leaf==3&&ppp.parentId==pp.id]
								                                <li><a  data-id="${ppp.id}" href="#"><i class="iconfont ${ppp.icon} icon-1-3x"></i>&nbsp${ppp.name}</a>
								                                  <ul>
								                                     [#list permissionsList as pppp]
								                                       [#if pppp.webUrl!=null&&pppp.leaf==4&&pppp.parentId==ppp.id]
								                                         <li><a data-id="${pppp.id}" href="${pppp.webUrl}"><i class="iconfont ${pppp.icon} icon-1-3x"></i>&nbsp${pppp.name}</a></li>
								                                       [/#if]
								                                     [/#list]
								                                  </ul>
								                                </li>
								                         [/#if]
								                       [/#list]
								                   </ul>
							                  </li>
							              [/#if]
							       [/#list]
						      </ul>
					     </li>  
				    [/#if]
				  [/#list]
		       </ul>
			</nav>
			<span class="minifyme"> <i class="fa fa-arrow-circle-left hit"></i> </span>
		</aside>