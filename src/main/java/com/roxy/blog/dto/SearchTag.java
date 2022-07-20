package com.roxy.blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchTag {

    Long id;
    String name;

    public SearchTag() {
    }

    public SearchTag(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
