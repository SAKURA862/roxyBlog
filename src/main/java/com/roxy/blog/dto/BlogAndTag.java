package com.roxy.blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlogAndTag {
    private Long tagId;

    private Long blogId;

    public BlogAndTag() {
    }

    public BlogAndTag(Long tagId, Long blogId) {
        this.tagId = tagId;
        this.blogId = blogId;
    }
}
