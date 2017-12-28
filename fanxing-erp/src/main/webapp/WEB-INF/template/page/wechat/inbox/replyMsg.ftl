<div class="modal fade" id="replyMsg" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					回复
				</h4>
			</div>
			<div class="modal-body no-padding">
			    <div class="well padding-bottom-10">
					<textarea rows="10" class="form-scroll" readonly="readonly" id="replyText" style="resize:none;width: 100%"></textarea>
				</div>	
					<form method="post" class="well padding-bottom-10" onsubmit="return false;">
						<textarea rows="2" onkeyup="charlength(value)" id="msgContent" class="form-control" style="resize:none" placeholder="请输入文本"></textarea>
						<div class="margin-top-10">
							<button type="submit" id="sendMsg" class="btn btn-sm btn-primary pull-right">
								回复
							</button>
								<label class="form-lbl">已输入：</label><span id="counter">0</span>个字（注意：回复内容不能超过140字)
							<a href="javascript:void(0);" class="btn btn-link profile-link-btn" rel="tooltip" data-placement="bottom" title="添加语音"><i class="fa fa-microphone"></i></a>
							<a href="javascript:void(0);" class="btn btn-link profile-link-btn" rel="tooltip" data-placement="bottom" title="添加照片"><i class="fa fa-camera"></i></a>
							<a href="javascript:void(0);" class="btn btn-link profile-link-btn" rel="tooltip" data-placement="bottom" title="添加附件"><i class="fa fa-file"></i></a>
						</div>
					</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>