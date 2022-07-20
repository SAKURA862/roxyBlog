package com.roxy.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roxy.blog.dto.BlogQuery;
import com.roxy.blog.dto.SearchBlog;
import com.roxy.blog.dto.ShowBlog;
import com.roxy.blog.entity.Blog;
import com.roxy.blog.entity.Tag;
import com.roxy.blog.entity.Type;
import com.roxy.blog.entity.User;
import com.roxy.blog.exception.AddException;
import com.roxy.blog.service.BlogService;
import com.roxy.blog.service.QiNiuService;
import com.roxy.blog.service.TagService;
import com.roxy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author roxy
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private QiNiuService qiNiuService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAdminType());
        model.addAttribute("tags", tagService.getAdminTag());
    }

    //去博客页面，显示博客
    @GetMapping("/blogs")
    public String toBlog(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<BlogQuery> allBlogQuery = blogService.getAllBlogQuery();
        PageInfo<BlogQuery> blogsPageInfo = new PageInfo<>(allBlogQuery);
        model.addAttribute("blogsPageInfo", blogsPageInfo);
        setTypeAndTag(model);
        model.addAttribute("href", "/admin/blogs");
        return "admin/blogs";
    }

    //博客搜索
    @PostMapping("/blogs/search")
    public String blogSearch(SearchBlog searchBlog, Model model,
                             @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<BlogQuery> allBlogQuery = blogService.searchByTitleOrTypeOrRecommend(searchBlog);
        PageInfo<BlogQuery> blogsPageInfo = new PageInfo<>(allBlogQuery);
        model.addAttribute("blogsPageInfo", blogsPageInfo);
        setTypeAndTag(model);
        model.addAttribute("message", "查询成功");
        return "admin/blogs :: blogList";//返回bloglist片段，不然会在网页嵌套一个网页
    }


    //去新增页面
    @GetMapping("/blogs/input")
    public String toBlogAdd(Model model) {
        setTypeAndTag(model);  //传递Blog
        return "admin/blogs-input";
    }

    //增加blog
    @PostMapping("/blogs/add")
    public String blogAdd(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getTypeById(blog.getTypeId()));
        //设置blog中的typeID属性
        blog.setTypeId(blog.getType().getId());
        //设置blog中tags属性
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        blog.setUserId(blog.getUser().getId());
        blogService.saveBlog(blog);
        attributes.addFlashAttribute("message", "新增成功");
        return "redirect:/admin/blogs";
    }

    //去编辑
    @GetMapping("/blogs/{id}/update")
    public String toBlogUpdate(@PathVariable Long id, Model model, Blog blog) {
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> adminType = typeService.getAdminType();
        List<Tag> adminTag = tagService.getAdminTag();
        model.addAttribute("blog", blogById);
        model.addAttribute("types", adminType);
        model.addAttribute("tags", adminTag);
        return "admin/blogs-input";
    }

    //编辑
    @PostMapping("/blogs/update")
    public String updateBlog(ShowBlog showBlog, RedirectAttributes attributes) {
        blogService.updateBlog(showBlog);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/blogs";
    }

    //删除
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}
