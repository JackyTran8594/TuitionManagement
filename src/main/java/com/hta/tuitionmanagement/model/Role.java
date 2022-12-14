package com.hta.tuitionmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hta.tuitionmanagement.dto.response.RoleDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "`role`")
public class Role extends Auditable<String> implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition="nvarchar(100)")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description", columnDefinition="nvarchar(500)")
    private String description;

    @OneToOne(mappedBy = "role")
    private UserEntity user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "role_function", joinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")},inverseJoinColumns ={@JoinColumn(name="function_id", referencedColumnName="id")})
    @JsonIgnore
    private Set<Function> functionList;

    public void updateInfo(RoleDTO dto){

        setLastModifiedDate(LocalDateTime.now());
    }

}
