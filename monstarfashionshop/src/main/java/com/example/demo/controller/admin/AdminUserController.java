package com.example.demo.controller.admin;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/admin")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping ("/user")
    public String user (Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user_admin";
    }

    @GetMapping ("/find_user/{id}")
    @ResponseBody
    public User findUser (@PathVariable (value = "id") Long userId) {
        System.out.println(userId);
        return userService.findUserById(userId);
    }

    @RequestMapping(value = "/cap_quyen", method = RequestMethod.POST)
    public String capQuyen (@RequestParam(value = "id", required = false) Long userId, Model model) {
        User user=userService.findUserById(userId);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> roles=new HashSet<Role>();
        roles.add(userRole);
        if(user.getRoles()!=null) {
            for (Role role:user.getRoles()) {
                roles.add(role);
            }
        }
        user.setRoles(roles);
        userService.saveUser(user);
        model.addAttribute("users", userRepository.findAll());
        return "user_admin";
    }

    @RequestMapping(value = "/khoa", method = RequestMethod.POST)
    public String khoa (@RequestParam(value = "id", required = false) Long userId, Model model) {
        User user=userService.findUserById(userId);
        user.setEnabled(false);
        userService.saveUser(user);
        model.addAttribute("users", userRepository.findAll());
        return "user_admin";
    }

    @RequestMapping(value = "/mo", method = RequestMethod.POST)
    public String mo (@RequestParam(value = "id", required = false) Long userId, Model model) {
        User user=userService.findUserById(userId);
        user.setEnabled(true);
        userService.saveUser(user);
        model.addAttribute("users", userRepository.findAll());
        return "user_admin";
    }
}
