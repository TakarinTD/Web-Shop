package com.example.demo.validator;

import static com.example.demo.constant.SystemConstant.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;

@Component
public class UserValidator implements Validator {
    @Autowired (required = true)
    private UserService userService;

    @Override
    public boolean supports (Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate (Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() < MIN_LENGTH_EMAIL || user.getEmail().length() > MAX_LENGTH_EMAIL) {
            errors.rejectValue("email", "Size.userForm.email");
        }
        if (userService.findOneByEmailAndStatus(user.getEmail(), ACTIVE_STATUS) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < MIN_LENGTH_PASSWORD || user.getPassword().length() > MAX_LENGTH_PASSWORD) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (! user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
