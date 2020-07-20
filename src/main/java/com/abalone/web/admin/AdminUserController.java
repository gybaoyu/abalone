package com.abalone.web.admin;

import com.abalone.po.User;
import com.abalone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Author: gavy
 * CreateTime: 2020-06-27-07-40
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private UserService service;

    @RequestMapping("/users")
    public String user(Model model) {
        List<User> users = service.show();
        model.addAttribute("users",users);
        return "admin/users";
    }

    @GetMapping("/users/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes model){
        service.deleteUser(id);
        model.addAttribute("message","删除成功!");
        return "redirect:admin/users";
    }
}
