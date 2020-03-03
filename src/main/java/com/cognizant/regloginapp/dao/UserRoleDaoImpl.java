package com.cognizant.regloginapp.dao;

import com.cognizant.regloginapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public int register(String username) {
//        String sql = "insert into regloginapp.user_roles (username, role) values(?,?)";
//        return jdbcTemplate.update(sql, username, "ROLE_USER");
        return 1;
    }
}
