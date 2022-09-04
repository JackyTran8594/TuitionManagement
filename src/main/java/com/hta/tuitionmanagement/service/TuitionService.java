package com.hta.tuitionmanagement.service;

import com.hta.tuitionmanagement.dto.request.TuitionRequest;
import com.hta.tuitionmanagement.dto.response.TuitionDTO;

import java.util.List;
import java.util.Map;

public interface TuitionService {
    TuitionDTO findById(Long id);

    TuitionDTO save(TuitionRequest item);

    TuitionDTO saveTuition(TuitionDTO item);


    //    List<TuitionDTO> findAll();
    List<TuitionDTO> search(Map<String, Object> mapParam);
//
    Long count(Map<String, Object> mapParam);
//    TuitionDTO findByCode(String code);
    Boolean deleteById(Long id);

    Boolean deleteAll(List<Long> listId);
}
