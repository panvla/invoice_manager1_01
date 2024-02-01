package com.vladimirpandurov.invoice_manager01.service;

import com.vladimirpandurov.invoice_manager01.domain.User;
import com.vladimirpandurov.invoice_manager01.dto.UserDTO;

public interface UserService {

    UserDTO createUser(User user);

    UserDTO getUserByEmail(String email);

    void sendVerificationCode(UserDTO user);

    UserDTO verifyCode(String email, String code);

    void resetPassword(String email);

    UserDTO verifyPasswordKey(String key);

    void renewPassword(String key, String password, String confirmPassword);

    UserDTO verifyAccount(String key);
}
