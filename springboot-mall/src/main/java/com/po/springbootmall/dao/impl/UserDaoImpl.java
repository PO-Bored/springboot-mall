package com.po.springbootmall.dao.impl;

import com.po.springbootmall.dao.UserDao;
import com.po.springbootmall.dto.UserRegisterRequest;
import com.po.springbootmall.model.User;
import com.po.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserById(Integer userId) {

        String sql = "SELECT user_id, email, password, create_time FROM user " +
                "WHERE user_id = :userid";

        Map<String ,Object> map = new HashMap<>();
        map.put("userid", userId);

        List<User> userLsit=namedParameterJdbcTemplate.query(sql, map,new UserRowMapper());

        if(userLsit.size() > 0){
            return userLsit.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        String sql = "INSERT INTO user(email, password, create_time, last_modified_date) " +
                "VALUES(:email, :password, :createTime, last_modified_date)";

        Map<String,Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());

        Date now = new Date();
        map.put("createTime", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(),keyHolder);

        int userId = keyHolder.getKey().intValue();
        return userId;
    }
}
