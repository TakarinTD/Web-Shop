package com.example.demo.service;

import com.example.demo.entity.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserService {
    User register(User user);

    User editInfo (User user);

    User findUserById(Long id);

    User findUserByEmail(String email);

    User editPassword (User user);

    void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    boolean verify(String verificationCode);

}
