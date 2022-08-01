package com.roxy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roxy.blog.constant.ConstantPool;
import com.roxy.blog.dto.DetailedBlog;
import com.roxy.blog.dto.FirstPageBlog;
import com.roxy.blog.dto.RecommendBlog;
import com.roxy.blog.entity.Comment;
import com.roxy.blog.entity.Tag;
import com.roxy.blog.entity.Type;
import com.roxy.blog.service.BlogService;
import com.roxy.blog.service.CommentService;
import com.roxy.blog.service.TagService;
import com.roxy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author roxy
 */
@Controller
public class IndexController {
    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;
    @Autowired
    TypeService typeService;
    @Autowired
    private CommentService commentService;
    @Autowired
    @Qualifier(value = "template")
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/")
    public String toIndex(Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 6);
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        List<Type> types = typeService.findTypeListBefore(7);
        List<Tag> allTag = tagService.getAllTag();
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTag);
        model.addAttribute("types", types);
        model.addAttribute("recommendedBlogs", recommendedBlog);
        return "index";
    }

    @PostMapping("/search")
    public String toSearch(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           Model model, @RequestParam String query) {
        PageHelper.startPage(pageNum, 100);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String toBlog(@PathVariable Long id, Model model) {
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.listCommentByBlogId(id);;
        model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }
}
