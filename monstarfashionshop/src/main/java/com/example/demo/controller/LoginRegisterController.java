package com.example.demo.controller;

import static com.example.demo.constant.SystemConstant.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import com.example.demo.validator.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class LoginRegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping (value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login () {
        ModelAndView model = new ModelAndView();
        model.setViewName("/login");
        return model;
    }

    @RequestMapping (value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView signup () {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("/register");
        return model;
    }

    @RequestMapping (value = {"/register"}, method = RequestMethod.POST)
    public ModelAndView createUser (@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findOneByEmailAndStatus(user.getEmail(), ACTIVE_STATUS);
        if(!user.getPasswordConfirm().equals(user.getPassword())){
            bindingResult.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm","These passwords don't match!");
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

        model.setViewName("/register");

        return model;
    }

}
