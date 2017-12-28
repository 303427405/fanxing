$.extend($.validator.messages, {
    required: "必填字段",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 (ISO).",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
    minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
    rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
    range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: $.validator.format("请输入一个最大为 {0} 的值"),
    min: $.validator.format("请输入一个最小为 {0} 的值"),
    isZipCode:"请正确填写您的邮政编码",
    byteRangeLength:$.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"),
    stringCheck:"只能包括中文字、英文字母、数字和下划线",
    isIdCardNo: "请正确输入您的身份证号码",
    isMobile: "请正确填写您的手机号码",
    isTel:"请正确填写您的电话号码",
    isPhone:"请正确填写您的联系电话",
    alnum:"只能包括英文字母、数字、下划线",
    checkPwdSelf:'原密码不正确',
    checkLoginName:'该用户名已经存在，请重新输入！'
});