package com.roxy.blog.dto;

import com.roxy.blog.entity.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BlogQuery {
    private Long id;
    private String title;
    private Date updateTime;
    private Integer recommend;
    private Long typeId;
    private Integer published;

    private Type type;

    public BlogQuery() {
    }

    public BlogQuery(Long id, String title, Date updateTime, Integer recommend, Long typeId, Integer published, Type type) {
        this.id = id;
        this.title = title;
        this.updateTime = updateTime;
        this.recommend = recommend;
        this.typeId = typeId;
        this.published = published;
        this.type = type;
    }
}
