package com.roxy.blog.service.Impl;

import com.roxy.blog.dao.CommentMapper;
import com.roxy.blog.entity.Comment;
import com.roxy.blog.exception.NotFountException;
import com.roxy.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> comments = commentMapper.findByBlogId(blogId);
        // 转换为扁平化的树结构
        return listToTree(comments);
    }

    /**
     * 转换为扁平化的树结构
     * @param comments 所有评论
     * @return 扁平化的树结构评论
     */
    private List<Comment> listToTree(List<Comment> comments){
        List<Comment> res = new ArrayList<>();
        int n = comments.size();
        for(int i = 0; i < n; i++){
            if(comments.get(i).getParentCommentId() == -1){
                res.add(comments.get(i));
            }
        }
        for(Comment comment : res){
            listToTree(comments, comment, comment);
        }
        // 按创建时间导序排列
        res.sort((a, b) -> b.getCreateTime().compareTo(a.getCreateTime()));
        return res;
    }

    /**
     * 递归转换
     * @param comments 所有评论
     * @param comment 父评论
     * @param root 根评论
     */
    private void listToTree(List<Comment> comments, Comment comment, Comment root){
        for(Comment c : comments){
            if(Objects.equals(c.getParentCommentId(), comment.getId())){
                c.setParentComment(comment);
                c.setParentNickname(comment.getNickname());
                root.getReplyComments().add(c);
                listToTree(comments, c, root);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveComment(Comment comment) {
        //判断有没有在别人的评论上进行评论还是一个新的评论
        Long parentCommentId = comment.getParentCommentId();
        //没有父级评论默认是-1
        if (parentCommentId != -1) {
            //有父级评论
            if(commentMapper.findByCommentId(comment.getParentCommentId()) == null){
                throw new NotFountException("回复的内容不存在");
            }
        }
        comment.setCreateTime(new Date());
        return commentMapper.saveComment(comment);
    }

    //    删除评论
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteComment(Comment comment, Long id) {
        commentMapper.deleteComment(id);
    }
}
