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

import com.cognizant.regloginapp.model.User;
import com.cognizant.regloginapp.service.UserService;

@Controller
public class RegistrationController {
    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    public UserService userService;

    private boolean verifyCaptcha(HttpServletRequest request) {
        logger.debug("Inside Verify Captcha...");

        String generatedCaptcha = request.getSession().getAttribute("captchaSecurityCode").toString();
        String userEnteredCaptcha = request.getParameter("captcha");
        return generatedCaptcha.equals(userEnteredCaptcha);
    }

    @RequestMapping(value = "/registrationProcess", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
        if (!verifyCaptcha(request)) {
            logger.debug("Inside Add User->Captcha verification failed ->User:" + user.getName());

            ModelAndView mav = new ModelAndView("register");
            mav.addObject("account", user);
            mav.addObject("message", "Incorrect captcha - Please Re-Enter!");
            return mav;
        }

        userService.register(user);

        HttpSession session = request.getSession();
        session.setAttribute("currentUser", user);

        return new ModelAndView("welcome", "name", user.getName());
    }
}
