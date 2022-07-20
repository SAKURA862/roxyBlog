package com.roxy.blog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Tag {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public Tag() {
    }

    public Tag(Long id, String name, List<Blog> blogs) {
        this.id = id;
        this.name = name;
        this.blogs = blogs;
    }
}
