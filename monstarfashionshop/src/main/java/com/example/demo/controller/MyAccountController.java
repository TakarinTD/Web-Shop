package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import java.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyAccountController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping ("/my_account")
    public String myAccount (Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "my_account";
    }

    @PostMapping (value = "/my_account")
    @ResponseBody
    public ResponseEntity editInfo (@RequestBody User userDetail, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        user.setFullName(userDetail.getFullName());
        user.setPhone(userDetail.getPhone());
        user.setAddress(userDetail.getAddress());
        user.setBirthday(userDetail.getBirthday());
        if(!userDetail.getPassword().equals("")) user.setPassword(bCryptPasswordEncoder.encode(userDetail.getPassword()));
        if(!userDetail.getPasswordConfirm().equals(""))  user.setPasswordConfirm(userDetail.getPasswordConfirm());
        System.out.println(userDetail.getPassword());
        try {
            return ResponseEntity.ok(userRepository.save(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
