package com.roxy.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roxy.blog.dto.SearchType;
import com.roxy.blog.entity.Type;
import com.roxy.blog.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    //列表页
    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum
                        ,Model model){
        PageHelper.startPage(pageNum,8);
        List<Type> allType = typeService.getAdminType();
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    // 搜索type，分页
    @PostMapping("/types/search")
    public String searchTypes(SearchType searchType, Model model,
                              @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,8);
        List<Type> allType = typeService.getAdminType();
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("message", "查询成功");
        return "admin/types :: typeList";
    }

    //去新增页面
    @GetMapping("/types/input")
    public String toAdd(Model model) {
        //给前端返回type
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    //Add
    @PostMapping("/types/add")
    public String add(@Valid Type type, RedirectAttributes attributes) {
//        System.out.println("前端传过来的表单" + type);
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的类");
            return "redirect:/admin/types/input";
        }
        //添加操作
        typeService.saveType(type);
        attributes.addFlashAttribute("message", "添加成功");
        return "redirect:/admin/types";
    }

    /**
     * 携带ID到编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/update")
    public String editInput(@PathVariable Long id,Model model){
        Type type = typeService.getTypeById(id);
        model.addAttribute("type",type);
        return "admin/types-input";
    }

    @PostMapping("/types/update")
    public String editPost(Type type,RedirectAttributes attributes){
        typeService.updateType(type);
        attributes.addFlashAttribute("message", "添加成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
