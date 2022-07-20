package com.roxy.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roxy.blog.dto.SearchTag;
import com.roxy.blog.entity.Tag;
import com.roxy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author roxy
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    //去tag页面并分页
    @GetMapping("/tags")
    public String toTagPage(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,8);
        List<Tag> allTag = tagService.getAdminTag();
        PageInfo<Tag> tagPageInfo = new PageInfo<>(allTag);
        model.addAttribute("tagPageInfo",tagPageInfo);
        return "admin/tags";
    }

    //搜索tag，分页
    @PostMapping("/tags/search")
    public String tagSearch(SearchTag searchTag, Model model,
                            @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,8);
        List<Tag> allTag = tagService.getAdminTag();
        PageInfo<Tag> tagPageInfo = new PageInfo<>(allTag);
        model.addAttribute("tagPageInfo",tagPageInfo);
        model.addAttribute("message", "查询成功");
        return "admin/tags :: tagList";
    }

    //去新增tag页面
    @GetMapping("/tags/input")
    public String toInputTags(Model model){
        model.addAttribute("tag",new Tag());        //传递Tag给前端
        return "admin/tags-input";
    }

    //增加tag
    @PostMapping("/tags/add")
    public String tagsAdd(@Valid Tag tag, RedirectAttributes attributes){
//        System.out.println("前端传过来的表单tag" + tag);
        Tag tagByName = tagService.getTagByName(tag.getName());
//        System.out.println("前端传过来的表单查询的tagByName"+tagByName);
        if(tagByName!=null){
            attributes.addFlashAttribute("message","不允许添加重复的标签");
            return "redirect:/admin/tags/input";
        }
        tagService.saveTag(tag);
        attributes.addFlashAttribute("message","添加成功");
        return "redirect:/admin/tags";

    }

    //携带ID
    @GetMapping("/tags/{id}/update")
    public String tagsInputById(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTagById(id));
        return "admin/tags-input";
    }

    //修改tag
    @PostMapping("/tags/update")
    public String tagsUpdate(Tag tag,RedirectAttributes attributes){
//        System.out.println("前端传过来的修改的tag"+tag);
        tagService.updateTag(tag);
        attributes.addFlashAttribute("message","修改成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String tagsDelete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
