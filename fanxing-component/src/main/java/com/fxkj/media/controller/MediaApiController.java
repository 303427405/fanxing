package com.fxkj.media.controller;

import com.fxkj.core.base.BaseAction;
import com.fxkj.core.base.MsgUtil;
import com.fxkj.media.enums.MediaType;
import com.fxkj.media.service.MediaBusinessUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 多媒体上传
 * Created By zhangyufei on 2018/1/5
 */
@Controller
@RequestMapping("/mediaController/api")
public class MediaApiController  extends BaseAction {

    @Autowired
    private MediaBusinessUploadService mediaBusinessUploadService;


    /**媒体资源上传*/
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public MsgUtil uploadFile(MultipartFile file , HttpServletRequest request , MediaType mediaType){

        String filePath = mediaBusinessUploadService.upLoadMedia(file,mediaType ,  request.getSession().getServletContext().getRealPath("/"));
        MsgUtil msgUtil = new MsgUtil();
        if(StringUtils.isNotBlank(filePath)){
            msgUtil.setSuccess(Boolean.TRUE);
            msgUtil.setRows(filePath);
            msgUtil.setMsg("success");
        } else {
            msgUtil.setSuccess(Boolean.FALSE);
        }
        return  msgUtil;
    }
}
