package com.cognizant.regloginapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cognizant.regloginapp.model.Login;
import com.cognizant.regloginapp.model.User;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoImpl implements UserDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public int register(User user) {
        String sql = "insert into regloginapp.users values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[]{user.getName(), user.getEmail(), user.getUsername(), user.getPassword(), 0});
    }

    @Transactional
    public User login(Login login) {
        String sql = "select * from regloginapp.users where username='" + login.getUsername() + "' and password='" + login.getPassword() + "'";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users.size() > 0 ? users.get(0) : null;
    }

    @Transactional
    public int update(User user) {
        String sql = "update regloginapp.users set name=?, email=?, password=? where username=?";
        return jdbcTemplate.update(sql, new Object[]{user.getName(), user.getEmail(), user.getPassword(), user.getUsername()});
    }
}

class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User user = new User();
    //    user.setName(rs.getString("name"));
        user.setName("suriya");
        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setAdmin(rs.getInt("admin") == 1 ? true : false);
        return user;
    }
}