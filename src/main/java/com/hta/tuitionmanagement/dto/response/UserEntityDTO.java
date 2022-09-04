package com.hta.tuitionmanagement.dto.response;

import com.hta.tuitionmanagement.dto.BaseDTO;
import com.hta.tuitionmanagement.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntityDTO extends BaseDTO<String> implements Serializable {

    private Long id;

    private String username;

    private String code;

    private String fullname;

    private String email;

    private String phoneNumber;

    private String password;

    private String note;

    private Long role_id;

}
