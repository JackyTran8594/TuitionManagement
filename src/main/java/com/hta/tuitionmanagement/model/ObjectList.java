package com.hta.tuitionmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="object_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectList extends Auditable<String> implements Serializable {
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

    @Column(name = "slop")
    private Float slop;

    @Column(name = "shift", precision = 18, scale = 2)
    private Integer shift;

    @Column(name = "is_specific")
    private Boolean isSpecific;
}
