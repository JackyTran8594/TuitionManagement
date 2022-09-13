package com.hta.tuitionmanagement.model;

import com.hta.tuitionmanagement.dto.response.StudentDTO;
import com.hta.tuitionmanagement.dto.response.TrainClassDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="student")
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    //mã đăng ký học viên lấy từ báo cáo 1
    @Column(name="registration_id")
    private String registrationId;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "citizen_id", columnDefinition = "varchar(20)")
    private String citizenId;

    @Column(name = "temp_name", columnDefinition = "nvarchar(100)")
    private String tempName;

    @Column(name = "first_name", columnDefinition = "nvarchar(100)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "nvarchar(100)")
    private String lastName;

    @Column(name = "full_name", columnDefinition = "nvarchar(100)")
    private String fullName;

    @Column(name = "image", columnDefinition = "varbinary(max)")
    private byte[] image;

//    @Column(name = "train_class_id", columnDefinition = "nvarchar(100)")
//    private String trainClassId;

    @ManyToOne
    @JoinColumn(name = "trainClass_id", referencedColumnName = "id")
    private TrainClass trainClass;

    //Tên người bảo trợ (giáo viên phụ trách)
    @Column(name = "teacer", columnDefinition = "nvarchar(200)")
    private String teacher;

    @Column(name = "note", columnDefinition = "nvarchar(500)")
    private String note;

    @OneToMany(mappedBy = "student")
    private Set<Tuition> tuitionList;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "student_fee", joinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")},inverseJoinColumns ={@JoinColumn(name="fee_id", referencedColumnName="id")})
    private Set<Fee> feeList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "id")
    private ObjectType objectType;

    public void updateInfo(StudentDTO dto){
//        setRegistrationId(dto.getRegistrationId());
//        setCourseId(dto.getCourseId());
//        setCitizenId(dto.getCitizenId());
//        setTempName(dto.getTempName());
//        setFirstName(dto.getFirstName());
//        setLastName(dto.getLastName());
//        setFullName(dto.getFullName());
//        setNote(dto.getNote());
        setLastModifiedDate(LocalDateTime.now());

    }
}
