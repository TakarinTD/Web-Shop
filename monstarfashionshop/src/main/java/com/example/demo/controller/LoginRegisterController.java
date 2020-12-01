package com.example.demo.controller;

import static com.example.demo.constant.Constant.*;
import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class LoginRegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String login (HttpServletRequest httpServletRequest, HttpSession httpSession) {
        String referrer = httpServletRequest.getHeader("referrer");
        httpSession.setAttribute("backPageUrl", referrer);
        System.out.println(referrer);
        return "login";
    }

    @RequestMapping(value = "/login-success", method = RequestMethod.GET)
    public String loginSuccess(HttpSession session, HttpServletRequest request, Authentication authentication) throws IOException, ServletException {
        String userEmail = authentication.getName();
        User userLogin = userService.findUserByEmail(userEmail);
        session.setAttribute("user", userLogin);
        String backPageUrl = (String) session.getAttribute("backPageUrl");
        session.removeAttribute("backPageUrl");
        int length = backPageUrl.split("/").length;
        String contextPath = backPageUrl.split("/")[length - 1];
        return "redirect:/" + contextPath;
    }

    @RequestMapping (value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView signup () {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("register");
        return model;
    }

    @RequestMapping (value = {"/register"}, method = RequestMethod.POST)
    public ModelAndView createUser (@Valid User user, BindingResult bindingResult, @RequestParam(name= "passwordConfirm") String passwordConfirm) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findOneByEmailAndStatus(user.getEmail(), ACTIVE_STATUS);
        if(user.getFullName().equals("")){
            bindingResult.rejectValue("fullName", "error.fullName", "Full name not null!");
        }
        if(user.getEmail().equals("")){
            bindingResult.rejectValue("email", "error.email", "Email not null!");
        }
        if(user.getPassword().equals("")){
            bindingResult.rejectValue("password", "error.password", "Password not null!");
        }
        if(passwordConfirm.equals("")){
            model.addObject("passwordConfirm", "Password confirm not null!");
        }
        if(!passwordConfirm.equals(user.getPassword())){
            model.addObject("passwordConfirm", "These passwords don't match!");
            model.setViewName("register");
            return model;
        }
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.email", "This email already exists!");
        }
        if (! bindingResult.hasErrors()) {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
        }

        //securityService.autoLogin(user.getUserName(), user.getPassword());

        model.setViewName("register");

        return model;
    }

}
