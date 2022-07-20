package com.roxy.blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchBlog {
    private String title;
    private Long typeId;
    private boolean recommend;

    public SearchBlog() {
    }

    public SearchBlog(String title, Long typeId, boolean recommend) {
        this.title = title;
        this.typeId = typeId;
        this.recommend = recommend;
    }
}
