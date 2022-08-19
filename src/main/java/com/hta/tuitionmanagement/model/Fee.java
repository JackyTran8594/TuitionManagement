package com.hta.tuitionmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="fee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fee extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="header", columnDefinition = "nvarchar(1000)")
    private String header;

    @Column(name = "description", columnDefinition = "nvarchar(1000)")
    private String description;

    @Column(name = "money", precision = 18, scale = 2)
    private BigDecimal money;

    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "student_id", nullable = true, referencedColumnName = "id")
    @JoinTable(name = "student_fee", joinColumns = {@JoinColumn(name="fee_id", referencedColumnName="id")},inverseJoinColumns ={@JoinColumn(name="student_id", referencedColumnName="id")})
    @JsonIgnore
    private Set<Student> studentList;
}
