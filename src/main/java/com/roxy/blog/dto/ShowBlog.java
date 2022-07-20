package com.roxy.blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ShowBlog {
    private Long id;
    private boolean published;
    private String flag;
    private String title;
    private String content;
    private Long typeId;
    private String tagIds;
    private String firstPicture;
    private String description;
    private boolean recommend;
    private boolean shareStatement;
    private boolean appreciation;
    private boolean commentabled;
    private Date updateTime;

    public ShowBlog() {
    }

    public ShowBlog(Long id, boolean published, String flag, String title, String content, Long typeId, String tagIds, String firstPicture, String description, boolean recommend, boolean shareStatement, boolean appreciation, boolean commentabled, Date updateTime) {
        this.id = id;
        this.published = published;
        this.flag = flag;
        this.title = title;
        this.content = content;
        this.typeId = typeId;
        this.tagIds = tagIds;
        this.firstPicture = firstPicture;
        this.description = description;
        this.recommend = recommend;
        this.shareStatement = shareStatement;
        this.appreciation = appreciation;
        this.commentabled = commentabled;
        this.updateTime = updateTime;
    }
}
