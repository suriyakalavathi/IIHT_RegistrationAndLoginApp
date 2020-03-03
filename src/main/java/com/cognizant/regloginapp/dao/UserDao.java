package com.cognizant.regloginapp.dao;

import com.cognizant.regloginapp.model.Login;
import com.cognizant.regloginapp.model.User;

public interface UserDao {
    int register(User user);

    User login(Login login);

    int update(User user);
}
