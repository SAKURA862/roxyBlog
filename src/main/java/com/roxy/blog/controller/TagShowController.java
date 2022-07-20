package com.roxy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roxy.blog.dto.FirstPageBlog;
import com.roxy.blog.entity.Tag;
import com.roxy.blog.service.BlogService;
import com.roxy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author roxy
 */
@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tag(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                      @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.getAllTag();
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        List<FirstPageBlog> blogs = blogService.getByTagId(id);
//        System.out.println("首页TagShow的blogs："+blogs);
        PageHelper.startPage(pageNum, 100);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
