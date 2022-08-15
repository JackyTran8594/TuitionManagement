package com.hta.tuitionmanagement.dto.response;

import com.hta.tuitionmanagement.dto.BaseDTO;
import com.hta.tuitionmanagement.model.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FunctionDTO extends BaseDTO<String> {
    private Long id;

    private String menuName;

    private String menuCode;

    private String actionCode;

    private String description;

    private String parentCode;

}
