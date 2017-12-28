<div class="modal fade" id="changePwd" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<img src="${resources_static}/style/index/img/demo/logo.png" width="150"
						alt="SmartAdmin" />
				</h4>
			</div>
			<div class="modal-body no-padding">
				<form id="changePwd-form" class="smart-form" customfn="returnLogin" action="/operatorController/updatePasswordById.do">
					<fieldset>
						<section>
							<div class="row">
								<label class="label col col-2">原密码</label>
								<div class="col col-10">
									<label class="input"> <i class="icon-append fa fa-lock"></i>
										<input type="password" placeholder="请输入原密码" name="passwordSelf" />
									</label>
								</div>
							</div>
						</section>
						<section>
							<div class="row">
								<label class="label col col-2">新密码</label>
								<div class="col col-10">
									<label class="input"> <i class="icon-append fa fa-lock"></i>
										<input type="password" id="passwordCheck" placeholder="请输入新密码" name="passwordCheck" />
									</label>
								</div>
							</div>
						</section>
						<section>
							<div class="row">
								<label class="label col col-2">确认密码</label>
								<div class="col col-10">
									<label class="input"> <i class="icon-append fa fa-lock"></i>
										<input type="password" id="password" placeholder="请输入确认密码" name="password" />
									</label>
								</div>
							</div>
						</section>
					</fieldset>
					<footer>
						<button type="button" id="submitBtn"  class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							取消</button>
					</footer>
				</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>