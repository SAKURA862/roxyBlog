package com.roxy.blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class FirstPageBlog {
    //Blog
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Date updateTime;
    private String description;

    //Type
    private String typeName;

    //User
    private String nickname;
    private String avatar;

    public FirstPageBlog() {
    }

    public FirstPageBlog(Long id, String title, String firstPicture, Integer views, Date updateTime, String description, String typeName, String nickname, String avatar) {
        this.id = id;
        this.title = title;
        this.firstPicture = firstPicture;
        this.views = views;
        this.updateTime = updateTime;
        this.description = description;
        this.typeName = typeName;
        this.nickname = nickname;
        this.avatar = avatar;
    }
}
