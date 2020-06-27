package com.abalone.web;

import com.abalone.dao.UserRepository;
import com.abalone.po.User;
import com.abalone.service.UserService;
import com.abalone.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author: gavy
 * CreateTime: 2020-06-06-18-57
 */
@Controller
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;
    private UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/register")
    public String registerPage(){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@RequestParam String username,
                                      @RequestParam String email,
                                      @RequestParam String password,
                                      HttpSession session,
                                      Model model){
        session.setMaxInactiveInterval(60*60*12);
        User user = new User(username,email,password,0,new Date());
        if (userService.isThereHaveTheUser(username)!=null) {
            model.addAttribute("wrong","用户名已被占用!");
            return "register";
        }else {
            userRepository.save(user);
            session.setAttribute("user", user);
            session.setAttribute("type", 0);
            return "redirect:";
        }
    }
}
