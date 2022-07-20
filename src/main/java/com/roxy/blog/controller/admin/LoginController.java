package com.roxy.blog.controller.admin;

import com.roxy.blog.entity.User;
import com.roxy.blog.service.UserService;
import com.roxy.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author roxy
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String loginPage(){
        return "admin/login";
    }

    @RequestMapping("/admin/tologin")
    public String toLogin(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        RedirectAttributes attributes,
                        HttpSession session){
        //获取当前用户
        User user = userService.getUserByName(username);
        if(user == null){
            attributes.addFlashAttribute("message","用户不存在");
            return "redirect:/admin";
        }else{
            if(!user.getPassword().equals(MD5Utils.code(password))){
                attributes.addFlashAttribute("message","用户名或密码错误");
                return "redirect:/admin";
            }
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        }
    }

    @GetMapping("/admin/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
