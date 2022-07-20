package com.roxy.blog.dto;

import com.roxy.blog.entity.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class DetailedBlog {
    private Long id;
    private String firstPicture;
    private String flag;
    private String title;
    private String content;

    private Integer views;
    private Date updateTime;
    private boolean commentabled;
    private boolean shareStatement;
    private boolean appreciation;
    private String nickname;
    private String avatar;

    private List<Tag> tags = new ArrayList<>();

    public DetailedBlog() {
    }

    public DetailedBlog(Long id, String firstPicture, String flag, String title, String content, Integer views, Date updateTime, boolean commentabled, boolean shareStatement, boolean appreciation, String nickname, String avatar, List<Tag> tags) {
        this.id = id;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.title = title;
        this.content = content;
        this.views = views;
        this.updateTime = updateTime;
        this.commentabled = commentabled;
        this.shareStatement = shareStatement;
        this.appreciation = appreciation;
        this.nickname = nickname;
        this.avatar = avatar;
        this.tags = tags;
    }
}
