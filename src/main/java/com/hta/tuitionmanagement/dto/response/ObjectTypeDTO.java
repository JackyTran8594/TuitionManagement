package com.hta.tuitionmanagement.dto.response;

import com.hta.tuitionmanagement.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ObjectTypeDTO extends BaseDTO<String> implements Serializable {
    private Long id;

    private String header;

    private String description;

    private BigDecimal money;

    private Float slop;

    private Integer shift;

    private Boolean isSpecific;
}
