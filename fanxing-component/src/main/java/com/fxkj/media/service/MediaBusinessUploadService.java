package com.fxkj.media.service;

import com.fxkj.media.enums.MediaType;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created By zhangyufei on 2018/1/5
 */
public interface MediaBusinessUploadService {

    /**
     * 上传资源
     * @param file
     * @param
     * @return
     * @throws
     */
    String upLoadMedia(MultipartFile file, MediaType mediaType , String path);

}
