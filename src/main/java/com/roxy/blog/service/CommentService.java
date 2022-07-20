package com.roxy.blog.service;

import com.roxy.blog.entity.Comment;

import java.util.List;

/**
 * 评论相关操作
 */
public interface CommentService {

    /**
     * 根据博客 id 查询该博客的评论
     * @param blogId 博客 id
     * @return 该博客的所有评论
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论
     * @param comment 评论内容 所在博客 id，父评论 id（没有父评论为 -1）
     * @return 是否成功
     */
    int saveComment(Comment comment);

    /**
     * 根据评论 id 删除评论
     * @param comment 评论
     * @param id 评论 id
     */
    void deleteComment(Comment comment, Long id);
}
