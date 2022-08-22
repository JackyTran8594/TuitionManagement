package com.hta.tuitionmanagement.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    OK("Success", "OK"),
    FAIL("Error", "FAIL"),
    DATA_NOTFOUND("Success", "NOT_FOUND");
    private final String result;
    private final String message;

}
