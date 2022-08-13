package com.hta.tuitionmanagement.constants;

import org.springframework.stereotype.Component;

@Component
public interface MessageConstant {
    String INVALID_TOKEN = "Invalid Token";
    String VALID_TOKEN = "Valid token for user ";
    String USERNAME_OR_PASSWORD_EMPTY = "Username or Password should not be empty";
    String USERNAME_OR_PASSWORD_INVALID = "Bad credentials";
    String USERNAME_INACTIVE = "User is inactive";
    String USERNAME_NOTFOUND = "User is not found";
    String SYSTEM_ERROR = "System error";
}
