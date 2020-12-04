package com.example.demo.controller;

import static com.example.demo.constant.Constant.BACK_REGISTER;
import com.example.demo.entity.*;
import com.example.demo.service.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpServletRequest httpServletRequest, HttpSession httpSession) throws URISyntaxException, MalformedURLException {
        System.out.println(httpServletRequest.getContextPath());
        String backUrlPath = httpServletRequest.getHeader("referer");
        String backPath;
        String backQuery;
        System.out.println(backUrlPath);
        if (backUrlPath == null||backUrlPath.equals(BACK_REGISTER)) {
            backPath = "/";
            backQuery="/";
        } else {
            backPath = new URL(backUrlPath).getPath();
            backQuery = new URL(backUrlPath).getQuery();
        }
        String backServletPath = backPath;
        if (backQuery != null) {
            backServletPath += "?" + backQuery;
        }
        httpSession.setAttribute("backServletPath", backServletPath);
        return "login";
    }

    @RequestMapping(value = "/login-success", method = RequestMethod.GET)
    public String loginSuccess(HttpSession session, Authentication authentication){
        String userEmail = authentication.getName();
        User userLogin = userService.findUserByEmail(userEmail);
        System.out.println(userEmail);
        session.setAttribute("user", userLogin);
        String backPageUrl = ((String) session.getAttribute("backServletPath"));
        session.removeAttribute("backServletPath");

        return "redirect:" + backPageUrl;
    }


}