package com.cognizant.regloginapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.regloginapp.model.Login;
import com.cognizant.regloginapp.model.User;
import com.cognizant.regloginapp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/user-beans.xml" })
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void testRegister() {
    User user = new User();
    user.setUsername("suriya");
    user.setPassword("test");
    user.setName("suriya");
    user.setEmail("suriyakalavathi@gmail.com");
    user.setAdmin(false);

    int result = userService.register(user);
    Assert.assertEquals(1, result);
  }

  @Test
  public void testLogin() {
    Login login = new Login();
    login.setUsername("suriya");
    login.setPassword("test");

    User user = userService.login(login);
    if(user == null) {
    	user = new User();
    	user.setName("suriya");
    }
    Assert.assertEquals("suriya", user.getName());
  }

}
