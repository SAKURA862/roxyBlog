package com.roxy.blog.dao;

import com.roxy.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论持久层操作类
 * @author roxy
 */
@Mapper
@Repository
public interface CommentMapper {
    /**
     * 根据博客 id 查询所有评论
     * @param blogId 博客 id
     * @return 当前博客所有评论
     */
    List<Comment> findByBlogId(Long blogId);

    /**
     * 根据评论 id 查询
     * @param id 评论 id
     * @return 评论
     */
    Comment findByCommentId(Long id);

    /**
     * 添加一个评论
     * @param comment 评论
     * @return 是否成功
     */
    int saveComment(Comment comment);

    /**
     * 删除评论
     * @param id 待删除的评论 id
     */
    void deleteComment(Long id);
}
