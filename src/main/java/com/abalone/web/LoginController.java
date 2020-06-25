package com.abalone.web;

import com.abalone.po.User;
import com.abalone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, HttpSession session) {
        User user = userService.checkCookie(request.getCookies());
        if (user != null) {
            user = userService.supplementary(user);
            userService.addSession(user, session);
            return "redirect:";
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model,
                        HttpServletResponse httpServletResponse) {
        User user = userService.checkUser(username, password);
        if (user == null) {
            model.addAttribute("message", "用户名或密码错误!");
            return "login";
        } else {
            //存session
            userService.addSession(user, session);
            //存储cookie
            Cookie[] pass = new Cookie[]{
                    new Cookie("cookiename", username),
                    new Cookie("cookiepass", password)
            };
            for (Cookie cookie : pass) {
                cookie.setMaxAge(60 * 60 * 24 * 14);
                cookie.setPath("/login");
                httpServletResponse.addCookie(cookie);
            }
        }
        return "redirect:";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        userService.logout(session, request, response);
        return "redirect:";
    }
}