package com.hta.tuitionmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @Column(name = "image", columnDefinition = "nvarchar(100)")
    private String image;

//    @Column(name = "train_class_id", columnDefinition = "nvarchar(100)")
//    private String trainClassId;

    @ManyToOne
    @JoinColumn(name = "trainClass_id", referencedColumnName = "id")
    private TrainClass trainClass;


    @Column(name = "note", columnDefinition = "nvarchar(500)")
    private String note;

    @OneToMany(mappedBy = "student")
    private Set<Tuition> Tuition;

    @OneToMany(mappedBy = "student")
    private Set<Fee> Fee;



//    @Column(name = "object_id", columnDefinition = "nvarchar(100)")
//    private String objectId;

}
