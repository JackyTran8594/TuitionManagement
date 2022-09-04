package com.hta.tuitionmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hta.tuitionmanagement.dto.response.FeeDTO;
import com.hta.tuitionmanagement.dto.response.ObjectTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "feeList")
//    @JoinColumn(name = "student_id", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Set<Student> studentList = new HashSet<>();

    public void updateInfo(FeeDTO dto){
//        setHeader(dto.getHeader());
//        setDescription(dto.getDescription());
//        setMoney(dto.getMoney());
        setLastModifiedDate(LocalDateTime.now());
    }
}
