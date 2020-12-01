package com.example.demo.controller;

import static com.example.demo.constant.Constant.*;
import com.example.demo.entity.*;
import com.example.demo.exception.*;
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

    public void validatePassword (User user, String oldPassword, String password, String passwordConfirm) throws FailedCheckPassword {
//        User user = (User) session.getAttribute("user");

        //Check Password
        if(!password.equals("") && !password.equals("") && !oldPassword.equals("")) {
            //Check Old password
            if(bCryptPasswordEncoder.matches(oldPassword,user.getPassword())){
                //Check password confirm
                if(password.equals(passwordConfirm)){
                    user.setPassword(bCryptPasswordEncoder.encode(password));
                } else {
                    throw new FailedCheckPassword(FAILED_CONFIRM_PASSWORD);
                }
            }else {
                throw new FailedCheckPassword(FAILED_OLD_PASSWORD);
            }
        } else {
            throw new FailedCheckPassword(FAILED_PASSWORD);
        }
    }

    @RequestMapping (value = "/my_password", method = RequestMethod.POST)
    @ResponseBody
    public  ResponseEntity editPassword(@RequestBody User userDetail, Principal principal, @RequestParam (value = "oldPassword", required = false) String oldPassword, @RequestParam (value = "passwordConfirm", required = false) String passwordConfirm){

        User user = userService.findUserByEmail(principal.getName());
//        User user = (User) session.getAttribute("user");
        try{
            validatePassword(user,oldPassword,userDetail.getPassword(),passwordConfirm);
            return ResponseEntity.ok(userRepository.save(user));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @RequestMapping (value = "/my_account", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity editInfo (@RequestBody User userDetail, Principal principal)  {
        User user = userService.findUserByEmail(principal.getName());
        user.setFullName(userDetail.getFullName());
        user.setPhone(userDetail.getPhone());
        user.setAddress(userDetail.getAddress());
        user.setBirthday(userDetail.getBirthday());
        try {
            return ResponseEntity.ok(userRepository.save(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
