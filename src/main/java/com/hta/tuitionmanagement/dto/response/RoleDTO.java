package com.hta.tuitionmanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hta.tuitionmanagement.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO extends BaseDTO<String> implements Serializable {

    private Long id;

    private String name;

    private String code;

    private String description;

}