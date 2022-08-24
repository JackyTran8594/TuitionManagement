package com.hta.tuitionmanagement.mapper;


import com.hta.tuitionmanagement.dto.response.AuthorizationDTO;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;
import com.hta.tuitionmanagement.model.Function;
import com.hta.tuitionmanagement.model.Tuition;

public class CustomMapper{

    public static TuitionDTO entityToDtoTuition(Tuition entity){
        TuitionDTO dto = new TuitionDTO();
        dto.setId(entity.getId());
        dto.setMoney(entity.getMoney());
        dto.setTimeStamp(entity.getTimeStamp());
        dto.setNote(entity.getNote());
        dto.setStudentId(entity.getStudent().getId());
        return dto;
    }

    public static AuthorizationDTO toAuthorizationDTO(Function entity){
        AuthorizationDTO dto = new AuthorizationDTO();
        dto.setId(entity.getId());
        dto.setMenuName(entity.getMenuName());
        dto.setDescription(entity.getDescription());
        dto.setMenuCode(entity.getMenuCode());
        return dto;
    }

}
