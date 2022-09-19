package com.hta.tuitionmanagement.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseImportDTO implements Serializable {

    //For import excel
    private Integer stt;
    private String errorMsg;
    private Boolean error = false;
}
