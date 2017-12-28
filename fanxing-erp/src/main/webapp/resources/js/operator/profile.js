// $('#myCarousel').carousel({ interval : 3000, cycle : true });

$("#profile").click(function(){
	 loadURL("/operatorController/profileEdit_view.do", $container);
})

var $registerForm = $("#addForm").validate({
	rules : {
		realName : {
			maxlength : 20
		},
		email:{
			email : true
		}
	}
});
