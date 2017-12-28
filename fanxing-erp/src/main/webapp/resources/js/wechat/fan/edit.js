var $registerForm = $("#addForm").validate({
	rules : {
		nickName : {
			maxlength : 10,
		},
		address : {
			maxlength : 50,
		}
	}
});
