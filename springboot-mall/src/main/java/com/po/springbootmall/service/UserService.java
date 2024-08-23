package com.po.springbootmall.service;

import com.po.springbootmall.dto.UserLoginRequest;
import com.po.springbootmall.dto.UserRegisterRequest;
import com.po.springbootmall.model.User;


public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}
