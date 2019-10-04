package com.boot.rowmap;

import com.boot.pojo.activiti.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by L on 2019/10/4.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(1));
        user.setLoginName(rs.getString(2));
//        user.setLoginPassword(rs.getString(3));
//        user.setRoleName(rs.getString(4));
        return user;
    }
}
