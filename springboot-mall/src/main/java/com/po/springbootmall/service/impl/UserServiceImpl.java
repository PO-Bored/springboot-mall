package com.po.springbootmall.service.impl;

import com.po.springbootmall.dao.UserDao;
import com.po.springbootmall.dto.UserRegisterRequest;
import com.po.springbootmall.model.User;
import com.po.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
