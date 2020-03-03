package com.cognizant.regloginapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.regloginapp.model.Login;
import com.cognizant.regloginapp.model.User;
import com.cognizant.regloginapp.service.UserService;

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    private boolean verifyCaptcha(HttpServletRequest request) {
        String generatedCaptcha = request.getSession().getAttribute("captchaSecurityCode").toString();
        String userEnteredCaptcha = request.getParameter("captcha");
        return generatedCaptcha.equals(userEnteredCaptcha);
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login) {
        ModelAndView mav;

        if (!verifyCaptcha(request)) {
            logger.debug("*** Login captcha failed!");
            mav = new ModelAndView("login");
            mav.addObject("message", "Incorrect captcha - Please enter again!");
            return mav;
        }

        User user = userService.login(login);
        if (null != user) {
            logger.debug("*** Login successful!");

            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            mav = new ModelAndView("welcome");
            mav.addObject("name", user.getName());
        } else {
            logger.debug("*** Login failed!");

            mav = new ModelAndView("login");
            mav.addObject("message", "No user found for '" + login.getUsername() + "' and/or the given password!");
        }
        return mav;
    }
}
