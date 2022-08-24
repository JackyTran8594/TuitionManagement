package com.hta.tuitionmanagement.dto.response;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class AuthorizationDTO {
    private Long id;
//    @Column(name = "menu_name")
    private String menuName;
    private String description;
//    @Column(name = "menu_code")
    private String menuCode;
//    private Integer type;
}
