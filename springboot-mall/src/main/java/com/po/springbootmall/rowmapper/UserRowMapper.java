package com.po.springbootmall.rowmapper;

import com.po.springbootmall.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("userId"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreateDate(rs.getTimestamp("createDate"));
        user.setLastModifiedDate(rs.getTimestamp("lastModifiedDate"));
        return user;
    }
}
