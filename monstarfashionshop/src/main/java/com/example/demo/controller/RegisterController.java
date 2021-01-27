package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping (value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView signup () {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("register");
        return model;
    }

    @RequestMapping (value = {"/register"}, method = RequestMethod.POST)
    public ModelAndView createUser (HttpServletRequest request,
                                    @Valid User user, BindingResult bindingResult,
                                    @RequestParam(name= "passwordConfirm") String passwordConfirm) throws UnsupportedEncodingException, MessagingException {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (user.getFullName().equals("")) {
            bindingResult.rejectValue("fullName", "error.fullName", "Full name not null!");
        }
        if (user.getEmail().equals("")) {
            bindingResult.rejectValue("email", "error.email", "Email not null!");
        }
        if (user.getPassword().equals("")) {
            bindingResult.rejectValue("password", "error.password", "Password not null!");
        }
        if (passwordConfirm.equals("")) {
            model.addObject("passwordConfirm", "Password confirm not null!");
        }
        if (! passwordConfirm.equals(user.getPassword())) {
            model.addObject("passwordConfirm", "These passwords don't match!");
            model.setViewName("register");
            return model;
        }
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.email", "This email already exists!");
        }
        if (!bindingResult.hasErrors()) {
            User dbUser = userService.register(user);
            if(dbUser != null) {
                String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
                userService.sendVerificationEmail(dbUser, siteURL);
            }
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
        }

        //securityService.autoLogin(user.getUserName(), user.getPassword());

        model.setViewName("register");

        return model;
    }

    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code, Model model) {
        System.out.println(code);
        boolean verified = userService.verify(code);
        model.addAttribute("registerMessage", "Tài khoản đã được kích hoạt, hãy đăng nhập ngay");
        return (verified ? "login" : "404");
    }
}
