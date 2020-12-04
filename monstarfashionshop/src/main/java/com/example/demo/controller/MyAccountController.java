package com.example.demo.controller;

import static com.example.demo.constant.Constant.*;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;

import java.security.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class MyAccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    HttpSession session;

    private PasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/my_account")
    public String myAccount(Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "my_account";
    }

    @RequestMapping(value = "/my_account", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity editInfo(@RequestBody User userDetail) {
        User user = userService.findUserByEmail(userDetail.getEmail());
        user.setFullName(userDetail.getFullName());
        user.setPhone(userDetail.getPhone());
        user.setAddress(userDetail.getAddress());
        user.setBirthday(userDetail.getBirthday());
        try {
            User userSaved = userRepository.save(user);
            session.setAttribute("user", user);
            return ResponseEntity.ok(userSaved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    public void validatePassword(User user, String oldPassword, String password, String passwordConfirm) throws FailedCheckPassword {
        //Check Password
        if (!password.equals("") && !password.equals("") && !oldPassword.equals("")) {
            //Check Old password
            if (bcryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
                //Check password confirm
                if (password.equals(passwordConfirm)) {
                    user.setPassword(bcryptPasswordEncoder.encode(password));
                } else {
                    throw new FailedCheckPassword(FAILED_CONFIRM_PASSWORD);
                }
            } else {
                throw new FailedCheckPassword(FAILED_OLD_PASSWORD);
            }
        } else {
            throw new FailedCheckPassword(FAILED_PASSWORD);
        }
    }

    @RequestMapping(value = "/my_password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity editPassword(@RequestBody User userDetail,
                                       @RequestParam(value = "oldPassword", required = false) String oldPassword,
                                       @RequestParam(value = "passwordConfirm", required = false) String passwordConfirm) {

        User userss = (User) session.getAttribute("user");
        User user = userService.findUserByEmail(userss.getEmail());
        try {
            validatePassword(user, oldPassword, userDetail.getPassword(), passwordConfirm);
            return ResponseEntity.ok(userRepository.save(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

}
