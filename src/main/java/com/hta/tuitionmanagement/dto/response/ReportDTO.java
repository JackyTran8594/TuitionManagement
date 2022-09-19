package com.hta.tuitionmanagement.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hta.tuitionmanagement.config.formatdate.LocalDateTimeDeserializer;
import com.hta.tuitionmanagement.config.formatdate.LocalDateTimeSerializer;
import com.hta.tuitionmanagement.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportDTO implements Serializable {
    
    private Long id;
    //mã đăng ký học viên lấy từ báo cáo 1
    private String registrationId;

    private String courseId;

    private String citizenId;

    private String tempName;

    private String firstName;

    private String lastName;

    private String fullName;

    // private String image;

    // private String note;

    private String teacher;   

    private String header;

    private Long tuitionFeePaid;

    private Long tuitionFeePayable;

    public List<String> headers() {
        return Arrays.asList("id", "Mã học viên", "Mã khóa học", "CMT/CCCD", "Tên đệm", "Họ", "Tên", "Họ và tên", "Giáo viên hướng dẫn", "Header", "Học phí đã nộp", "Học phí");
    }

}
