package com.abalone.service;

import com.abalone.dao.UserRepository;
import com.abalone.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
