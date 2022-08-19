package com.hta.tuitionmanagement.dto.response;

import com.hta.tuitionmanagement.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TuitionDTO extends BaseDTO<String> implements Serializable {

    private Long id;
    private BigDecimal money;
    private LocalDateTime timeStamp;
    private String note;
    private Long studentId;
}
