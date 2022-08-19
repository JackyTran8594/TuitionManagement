package com.hta.tuitionmanagement.dto.response;

import com.hta.tuitionmanagement.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO extends BaseDTO<String> implements Serializable {
    private Long id;
    //mã đăng ký học viên lấy từ báo cáo 1
    private String registrationId;

    private String courseId;

    private String citizenId;

    private String tempName;

    private String firstName;

    private String lastName;

    private String fullName;

    private String image;

    private String note;

//    private Long trainClassId;

}
