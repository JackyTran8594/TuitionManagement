package com.hta.tuitionmanagement.mapper;


import com.hta.tuitionmanagement.dto.response.TuitionDTO;
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

}
