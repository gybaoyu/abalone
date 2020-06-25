package com.abalone.service;

import com.abalone.po.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public interface UserService {

    User checkUser(String username, String password);
    User isThereHaveTheUser(String username);
    User checkCookie(Cookie[]cookies);
    User supplementary(User user);
    void addSession(User user, HttpSession session);
    void logout(HttpSession session, HttpServletRequest servletRequest, HttpServletResponse response);
}
