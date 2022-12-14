package com.hta.tuitionmanagement.model;

import com.hta.tuitionmanagement.dto.response.TrainClassDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "train_class")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainClass extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "header", columnDefinition = "nvarchar(1000)")
    private String header;

    @Column(name = "description", columnDefinition = "nvarchar(1000)")
    private String description;

    @Column(name = "money", precision = 18, scale = 2)
    private BigDecimal money;

    @OneToMany(mappedBy = "trainClass")
    private Set<Student> studentList;

    public void updateInfo(TrainClassDTO dto){
//        setHeader(dto.getHeader());
//        setDescription(dto.getDescription());
//        setMoney(dto.getMoney());
        setLastModifiedDate(LocalDateTime.now());
    }
}
