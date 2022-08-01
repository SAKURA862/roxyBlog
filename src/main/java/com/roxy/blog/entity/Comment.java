package com.roxy.blog.entity;

import com.roxy.blog.dto.DetailedBlog;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Comment{
    private Long id;
    private String nickname;
    private String email;
    private String content;

    //头像
    private String avatar;
    private Date createTime;

    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;

    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
    private boolean adminComment;

    private DetailedBlog blog;

    public Comment() {
    }

    public Comment(Long id, String nickname, String email, String content, String avatar, Date createTime, Long blogId, Long parentCommentId, String parentNickname, List<Comment> replyComments, Comment parentComment, boolean adminComment, DetailedBlog blog) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.avatar = avatar;
        this.createTime = createTime;
        this.blogId = blogId;
        this.parentCommentId = parentCommentId;
        this.parentNickname = parentNickname;
        this.replyComments = replyComments;
        this.parentComment = parentComment;
        this.adminComment = adminComment;
        this.blog = blog;
    }
}
