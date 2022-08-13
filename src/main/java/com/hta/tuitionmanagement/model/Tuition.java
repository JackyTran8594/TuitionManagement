package com.hta.tuitionmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="tuition")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tuition extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="student_id")
    private String studentId;

    @Column(name = "money", precision = 18, scale = 2)
    private BigDecimal money;

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @Column(name = "note", columnDefinition = "nvarchar(500)")
    private String note;

    @ManyToOne
    @JoinColumn(name = "tuition_id", nullable = true, referencedColumnName = "id")
    private Student student;


}
