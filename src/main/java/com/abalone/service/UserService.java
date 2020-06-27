package com.abalone.service;

import com.abalone.po.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface UserService {

    //判断user账号密码
    User checkUser(String username, String password);
    //用户名查找user
    User isThereHaveTheUser(String username);
    //查cookie
    User checkCookie(Cookie[]cookies);
    //完善User信息
    User supplementary(User user);
    //加Session
    void addSession(User user, HttpSession session);
    //注销
    void logout(HttpSession session, HttpServletRequest servletRequest, HttpServletResponse response);
    //展示全部
    List<User>show();
    //删除用户
    void deleteUser(Long id);
}
