package com.po.springbootmall.dao;

import com.po.springbootmall.dto.UserRegisterRequest;
import com.po.springbootmall.model.User;

public interface UserDao {



    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
