package com.hta.tuitionmanagement.dto.request;

import com.hta.tuitionmanagement.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TuitionRequest extends BaseDTO<String> implements Serializable {

    private Long id;

    private Long studentId;

    private Long trainClassId;

    private Long objectTypeId;

    private List<Long> listFeeId;
    //Số tiền nộp
    private BigDecimal money;
}
