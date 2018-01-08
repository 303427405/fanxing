$(function(){
    $("#areaCodeName").on("change",function(){
        $.ajax({
            type : "POST",
            url : base_path+"/marketInfoController/findMarketInfoByCode.do",
            cache : false,
            data : {areaCode:$("#areaCode").val()},
            async : false,
            success : function(data) {
                $("#marketCode").empty();
                for(var m in data){
                    $("#marketCode").append("<option value="+data[m].code+">"+data[m].name+"</option>")
                }

            },
            error : function(xhr, ajaxOptions, thrownError) {
                obj=null;
            }
        });
    });


    /**
     * 身份证正面
     */
    $("#idCardFaceFile").on("change",function(){
        var data = {"mediaType":"SHOPINFO_ID_CARD_FACE"};
        var ajaxUrl = base_path + "/mediaController/api/upload.do";
        $.ajaxFileUpload({
            type: "POST",
            url: ajaxUrl,
            data: data,
            fileElementId:'idCardFaceFile',
            secureUri:false,
            dataType:"json",
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            success: function (data) {
                $("#idCardFaceName").attr("placeholder",data.rows)
                $("#idCardFace").val(data.rows);
            },
            error: function(data) {
                alert("error:"+data.responseText);

            }
        });
    });


    /**
     * 身份证背面
     */
    $("#idCardBackFile").on("change",function(){
        var data = {"mediaType":"SHOPINFO_ID_CARD_BACK"};
        var ajaxUrl = base_path + "/mediaController/api/upload.do";
        $.ajaxFileUpload({
            type: "POST",
            url: ajaxUrl,
            data: data,
            fileElementId:'idCardBackFile',
            secureUri:false,
            dataType:"json",
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            success: function (data) {
                $("#idCardBackName").attr("placeholder",data.rows)
                $("#idCardBack").val(data.rows);
            },
            error: function(data) {
                alert("error:"+data.responseText);

            }
        });
    });


    /**
     * 商户大图
     */
    $("#bigLogoFile").on("change",function(){
        var data = {"mediaType":"SHOPINFO_BIG_LOGO"};
        var ajaxUrl = base_path + "/mediaController/api/upload.do";
        $.ajaxFileUpload({
            type: "POST",
            dataType:"JSON",
            url: ajaxUrl,
            data: data,
            fileElementId:'bigLogoFile',
            secureUri:false,
            dataType:"json",
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            success: function (data) {
                $("#bigLogoName").attr("placeholder",data.rows)
                $("#bigLogo").val(data.rows);
            },
            error: function(data) {
                alert("error:"+data.responseText);

            }
        });
    });


    /**
     * 商户小图
     */
    $("#minLogoFile").on("change",function(){
        var data = {"mediaType":"SHOPINFO_MIN_LOGO"};
        var ajaxUrl = base_path + "/mediaController/api/upload.do";
        $.ajaxFileUpload({
            type: "POST",
            dataType:"JSON",
            url: ajaxUrl,
            data: data,
            fileElementId:'minLogoFile',
            secureUri:false,
            dataType:"json",
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            success: function (data) {
                $("#minLogoName").attr("placeholder",data.rows)
                $("#minLogo").val(data.rows);
            },
            error: function(data) {
                alert("error:"+data.responseText);

            }
        });
    });


    /**
     * 营业执照
     */
    $("#businessLicenseFile").on("change",function(){
        var data = {"mediaType":"SHOPINFO_BUSINESS_LICENSE"};
        var ajaxUrl = base_path + "/mediaController/api/upload.do";
        $.ajaxFileUpload({
            type: "POST",
            dataType:"JSON",
            url: ajaxUrl,
            data: data,
            fileElementId:'businessLicenseFile',
            secureUri:false,
            dataType:"json",
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            success: function (data) {
                $("#businessLicenseName").attr("placeholder",data.rows)
                $("#businessLicense").val(data.rows);
            },
            error: function(data) {
                alert("error:"+data.responseText);

            }
        });
    });


    /**
     * 食品许可证执照
     */
    $("#foodLicenseFile").on("change",function(){
        var data = {"mediaType":"SHOPINFO_FOOD_LICENSE"};
        var ajaxUrl = base_path + "/mediaController/api/upload.do";
        $.ajaxFileUpload({
            type: "POST",
            dataType:"JSON",
            url: ajaxUrl,
            data: data,
            fileElementId:'foodLicenseFile',
            secureUri:false,
            dataType:"json",
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            success: function (data) {
                $("#foodLicenseName").attr("placeholder",data.rows)
                $("#foodLicense").val(data.rows);
            },
            error: function(data) {
                alert("error:"+data.responseText);

            }
        });
    });







})