package com.cognizant.regloginapp.service;

import com.cognizant.regloginapp.model.Login;
import com.cognizant.regloginapp.model.User;

public interface UserService {
    int register(User user);

    User login(Login login);

    int update(User user);
}
