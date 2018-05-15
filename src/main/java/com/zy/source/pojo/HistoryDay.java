package com.zy.source.pojo;

/**
 *Created by zy on 15/05/2018.
 */
public class HistoryDay {
    /**
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `e_id` varchar(255) DEFAULT NULL COMMENT '事件id',
     `content` text COMMENT '事件详情',
     `picNo` varchar(255) DEFAULT NULL COMMENT '图片数量',
     `url` text COMMENT '图片地址',
     `title` varchar(255) DEFAULT NULL COMMENT '事件标题',
     `pic_title` varchar(255) DEFAULT NULL COMMENT '图片标题',
     `pic_id` varchar(255) DEFAULT NULL COMMENT '图片顺序id',
     */

    private Integer id;
    private String eId;
    private String content;
    private String picNo;
    private String url;
    private String title;
    private String picTitle;
    private String picId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicNo() {
        return picNo;
    }

    public void setPicNo(String picNo) {
        this.picNo = picNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicTitle() {
        return picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }
}
