package com.hta.tuitionmanagement.model;

import com.hta.tuitionmanagement.dto.response.UserEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="user_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="code", length = 30)
    private String code;

    @Column(name="fullname", columnDefinition = "nvarchar(500)")
    private String fullname;

    @Column(name="email", length = 500)
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="password")
    private String password;

    @Column(name = "note", columnDefinition="nvarchar(500)")
    private String note;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public void updateInfo(UserEntityDTO dto){
//        setHeader(dto.getHeader());
//        setDescription(dto.getDescription());
//        setMoney(dto.getMoney());
        setLastModifiedDate(LocalDateTime.now());
    }
}
