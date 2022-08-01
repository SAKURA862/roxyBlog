package com.roxy.blog.controller;

import com.roxy.blog.dto.DetailedBlog;
import com.roxy.blog.dto.NormalUser;
import com.roxy.blog.entity.Comment;
import com.roxy.blog.entity.User;
import com.roxy.blog.service.BlogService;
import com.roxy.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author roxy
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${normal_avatar.url}")
    private String avatar;

    @Value("${normal_avatar.number}")
    private int normalUserAvatarNum;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
        } else {
            //设置头像
            comment.setAvatar(avatar + ((comment.getNickname().hashCode() & 0x7fffffff) % normalUserAvatarNum) + ".png");
            session.setAttribute("normalUser", new NormalUser(comment.getNickname(), comment.getEmail()));
        }
        commentService.saveComment(comment);

        return "redirect:/comments/" + blogId;
    }

    // 删除评论
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id, Comment comment, RedirectAttributes attributes, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            attributes.addFlashAttribute("message", "请先登录");
            return "blog";
        }
        commentService.deleteComment(comment, id, blogId);
        DetailedBlog detailedBlog = blogService.getDetailedBlog(blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);
        return "blog";
    }
}
