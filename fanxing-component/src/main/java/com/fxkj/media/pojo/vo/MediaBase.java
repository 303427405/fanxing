package com.fxkj.media.pojo.vo;


import com.fxkj.media.enums.MediaType;

/**
 * 媒体资源基本信息
 */
public class MediaBase{
    /**
     * 1.静态资源行业应用
     * 2.行业应用装配列表封面
     * 3.电子券列表
     * 4.打赏被打赏人头像
     * 5.打赏赠送歌曲
     * 6.行业应用属性
     * 7.参与名单
     * 8.微信分享
     */
    private Integer type;

    private MediaType mediaType;

    private String name;

    /**
     * 文件地址
     */
    private String filePath;


    public MediaType getMediaType() {
        if(mediaType == null && type != null){
            for ( MediaType mt : MediaType.values()){
                if(mt.getCode().equals(type)){
                    this.mediaType = mt;
                    return mt;
                }
            }
        }
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
