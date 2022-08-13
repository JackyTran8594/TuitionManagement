package com.hta.tuitionmanagement.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    public Boolean success;
    public String message;
}
