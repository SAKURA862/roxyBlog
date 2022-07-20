package com.roxy.blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecommendBlog {
    private Long id;
    private String title;
    private boolean recommend;

    public RecommendBlog() {
    }

    public RecommendBlog(Long id, String title, boolean recommend) {
        this.id = id;
        this.title = title;
        this.recommend = recommend;
    }
}
