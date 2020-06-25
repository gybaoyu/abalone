package com.abalone.service;

import com.abalone.dao.UserRepository;
import com.abalone.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public User isThereHaveTheUser(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User checkCookie(Cookie[] cookies) {
        String username = null, password = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cookiename")) {
                username = cookie.getValue();
            } else if (cookie.getName().equals("cookiepass")) {
                password = cookie.getValue();
            }
        }
        if (username != null && password != null) {
            User user = checkUser(username, password);
            return user;
        }
        return null;
    }

    @Override
    public User supplementary(User user) {
        user = checkUser(user.getUsername(), user.getPassword());
        return user;
    }

    @Override
    public void addSession(User user, HttpSession session) {
        session.setMaxInactiveInterval(60 * 60 * 24 * 14);
        if (user.getType() == 1) {
            session.setAttribute("user", user);
            session.setAttribute("type", 1);
        } else {
            session.setAttribute("user", user);
        }
    }

    @Override
    public void logout(HttpSession session, HttpServletRequest servletRequest, HttpServletResponse response) {
        Cookie cookiepass = new Cookie("cookiepass", null);
        Cookie cookiename = new Cookie("cookiename", null);
        response.addCookie(cookiepass);
        response.addCookie(cookiename);

        if (session.getAttribute("type") != null) {
            session.removeAttribute("user");
            session.removeAttribute("type");
        } else {
            session.removeAttribute("user");
        }
    }
}
