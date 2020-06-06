package com.abalone.web;

import com.abalone.po.User;
import com.abalone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage() {
        return "/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.checkUser(username, password);
//        session.setMaxInactiveInterval();

        if (user == null) {
            model.addAttribute("message", "用户名或密码错误!");
            return "login";
        }
            if (user.getType() == 1) {
                session.setAttribute("user", user);
                session.setAttribute("type", 1);
                return "admin/index";
            } else {
                session.setAttribute("user", user);
                return "redirect:";
            }
        }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:";
    }
    @GetMapping("/logout")
    public String logout1(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:";
    }
}
