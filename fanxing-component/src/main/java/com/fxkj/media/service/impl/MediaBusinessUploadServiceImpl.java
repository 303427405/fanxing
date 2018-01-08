package com.fxkj.media.service.impl;


import com.fxkj.core.utils.DateUtils;
import com.fxkj.media.enums.MediaType;
import com.fxkj.media.service.MediaBusinessUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created By zhangyufei on 2018/1/5
 */
@Service
public class MediaBusinessUploadServiceImpl implements MediaBusinessUploadService {


    @Value("${upload_root_path}")
    protected  String UPLOAD_ROOT_PATH;

    /**
     * 上传文件
     * @param file
     * @param
     * @return
     */
    public String upLoadMedia(MultipartFile file, MediaType mediaType , String fpath) {
        String path = "";

        /**2. 处理文件*/
        try {
            String realFileName = file.getOriginalFilename();
            String suffix = realFileName.substring(realFileName.indexOf("."), realFileName.length());

            /**文件保存路径*/
            String filePath = StringUtils.join(fpath,mediaType.getPath());

            String newName = StringUtils.join(DateUtils.longDate(),suffix);
            path = StringUtils.join(filePath, newName);

            File targetFile = new File(filePath, newName);

            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }


            file.transferTo(targetFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  path;
    }
}
