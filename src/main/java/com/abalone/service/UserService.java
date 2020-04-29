package com.abalone.service;

import com.abalone.po.User;


public interface UserService {

    User checkUser(String username, String password);
}
