$(function(){
    $("#areaCodeName").on("change",function(){
        $.ajax({
            type : "POST",
            url : base_path+"/marketInfoController/findMarketInfoByCode.do",
            cache : false,
            data : {areaCode:$("#areaCode").val()},
            async : false,
            success : function(data) {
                $("#marketInfo").empty();
                for(var m in data){
                    $("#marketInfo").append("<option value="+data[m].code+">"+data[m].name+"</option>")
                }

            },
            error : function(xhr, ajaxOptions, thrownError) {
                obj=null;
            }
        });
    });
})