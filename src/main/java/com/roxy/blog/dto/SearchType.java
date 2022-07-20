package com.roxy.blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchType {

    Long id;
    String name;

    public SearchType() {
    }

    public SearchType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
